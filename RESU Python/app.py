from flask import Flask, jsonify, request, render_template, redirect, url_for, flash
from flask_jwt_extended import (
    JWTManager, create_access_token, jwt_required,
    get_jwt_identity, set_access_cookies, unset_jwt_cookies,
    verify_jwt_in_request
)
from models import db, Customer, Supplier, InventoryItem, Order, OrderItem, Payment
from config import Config
from werkzeug.security import generate_password_hash, check_password_hash
from sqlalchemy.exc import IntegrityError
from dotenv import load_dotenv
load_dotenv()
from supplier_routes import supplier_bp
app.register_blueprint(supplier_bp)
from customer_management_routes import customer_mgmt_bp
app.register_blueprint(customer_mgmt_bp)
from inventory_routes import inventory_bp
app.register_blueprint(inventory_bp)

app = Flask(__name__)
app.config.from_object(Config)

db.init_app(app)
jwt = JWTManager(app)

@app.context_processor
def inject_user():
    try:
        verify_jwt_in_request(optional=True)
        user_id = get_jwt_identity()
        if user_id:
            user = Customer.query.get(int(user_id))
            return {'current_user': user}
        return {'current_user': None}
    except:
        return {'current_user': None}

# Initialize database
with app.app_context():
    db.create_all()

def check_low_stock():
    return InventoryItem.query.filter(InventoryItem.quantity < InventoryItem.threshold).all()

@app.route('/')
def home():
    return redirect(url_for('login'))

@app.route('/register', methods=['GET', 'POST'])
def register():
    if request.method == 'POST':
        username = request.form.get('username')
        email = request.form.get('email')
        password = request.form.get('password')
        role = request.form.get('role')
        
        if not all([username, email, password, role]):
            flash('Please fill all fields')
            return redirect(url_for('register'))
        
        if role not in ['staff', 'customer']:
            flash('Invalid role selection')
            return redirect(url_for('register'))

        try:
            is_staff = (role == 'staff')
            customer = Customer(
                username=username,
                email=email,
                is_staff=is_staff
            )
            customer.set_password(password)
            db.session.add(customer)
            db.session.commit()
            flash('Registration successful! Please login.')
            return redirect(url_for('login'))
        except IntegrityError:
            db.session.rollback()
            flash('Username/email already exists')
    return render_template('register.html')

@app.route('/login', methods=['GET', 'POST'])
def login():
    if request.method == 'POST':
        username = request.form.get('username')
        password = request.form.get('password')
        customer = Customer.query.filter_by(username=username).first()
        
        if customer and customer.check_password(password):
            access_token = create_access_token(identity=str(customer.id))
            response = redirect(url_for('dashboard'))
            set_access_cookies(response, access_token)
            return response
        flash('Invalid credentials')
    return render_template('login.html')

@app.route('/logout')
def logout():
    response = redirect(url_for('login'))
    unset_jwt_cookies(response)
    return response

@app.route('/dashboard')
@jwt_required()
def dashboard():
    current_user_id = int(get_jwt_identity())
    customer = Customer.query.get(current_user_id)
    return redirect(url_for('staff_dashboard' if customer.is_staff else 'customer_dashboard'))

@app.route('/customer/dashboard')
@jwt_required()
def customer_dashboard():
    current_user_id = int(get_jwt_identity())
    customer = Customer.query.get(current_user_id)
    orders = Order.query.filter_by(customer_id=current_user_id).order_by(Order.created_at.desc()).limit(5).all()
    return render_template('customer_dashboard.html', orders=orders)

@app.route('/staff/dashboard')
@jwt_required()
def staff_dashboard():
    current_user_id = int(get_jwt_identity())
    customer = Customer.query.get(current_user_id)
    if not customer.is_staff:
        flash('Unauthorized access')
        return redirect(url_for('customer_dashboard'))
    
    low_stock = check_low_stock()
    all_orders = Order.query.order_by(Order.created_at.desc()).limit(10).all()
    return render_template('staff_dashboard.html', low_stock=low_stock, orders=all_orders)

@app.route('/inventory')
@jwt_required()
def inventory():
    items = InventoryItem.query.all()
    return render_template('inventory.html', items=items)

@app.route('/add_inventory', methods=['GET', 'POST'])
@jwt_required()
def add_inventory():
    current_user_id = int(get_jwt_identity())
    customer = Customer.query.get(current_user_id)
    if not customer.is_staff:
        flash('Staff access required')
        return redirect(url_for('dashboard'))
    
    if request.method == 'POST':
        try:
            new_item = InventoryItem(
                name=request.form['name'],
                quantity=int(request.form['quantity']),
                price=float(request.form['price']),
                supplier_id=request.form.get('supplier_id')
            )
            db.session.add(new_item)
            db.session.commit()
            flash('Inventory item added')
            return redirect(url_for('inventory'))
        except Exception as e:
            db.session.rollback()
            flash(f'Error: {str(e)}')
    
    suppliers = Supplier.query.all()
    return render_template('add_inventory.html', suppliers=suppliers)

@app.route('/order', methods=['GET', 'POST'])
@jwt_required()
def create_order():
    current_user_id = int(get_jwt_identity())
    
    if request.method == 'POST':
        items = request.form.getlist('item_id')
        quantities = request.form.getlist('quantity')
        
        if len(items) != len(quantities):
            flash('Item/quantity mismatch')
            return redirect(url_for('create_order'))
        
        try:
            total = 0
            order = Order(customer_id=current_user_id, status='Placed', total=0)
            db.session.add(order)
            
            for item_id, qty in zip(items, quantities):
                item = InventoryItem.query.get(item_id)
                if not item or item.quantity < int(qty):
                    raise ValueError(f'Invalid item or quantity for {item.name if item else "unknown item"}')
                
                OrderItem(
                    order_id=order.id,
                    item_id=item.id,
                    quantity=int(qty)
                )
                item.quantity -= int(qty)
                total += item.price * int(qty)
            
            order.total = total
            db.session.commit()
            flash('Order created successfully')
            return redirect(url_for('customer_dashboard'))
        except Exception as e:
            db.session.rollback()
            flash(str(e))
    
    items = InventoryItem.query.all()
    return render_template('order.html', items=items)

@app.route('/payment/<int:order_id>', methods=['GET', 'POST'])
@jwt_required()
def process_payment(order_id):
    current_user_id = int(get_jwt_identity())
    order = Order.query.get_or_404(order_id)
    
    if request.method == 'POST':
        try:
            payment = Payment(
                order_id=order.id,
                method=request.form['payment_method'],
                amount=order.total,
                status='Completed'
            )
            order.status = 'Confirmed'
            db.session.add(payment)
            db.session.commit()
            flash('Payment processed')
            return redirect(url_for('customer_dashboard'))
        except Exception as e:
            db.session.rollback()
            flash(str(e))
    
    return render_template('payment.html', order=order)

if __name__ == '__main__':
    app.run(debug=True)