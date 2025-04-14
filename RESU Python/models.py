from flask_sqlalchemy import SQLAlchemy
from datetime import datetime
from werkzeug.security import generate_password_hash, check_password_hash

db = SQLAlchemy()

class Customer(db.Model):
    __tablename__ = 'customers'
    id = db.Column(db.Integer, primary_key=True)
    username = db.Column(db.String(64), unique=True, nullable=False)
    password_hash = db.Column(db.String(128), nullable=False)
    email = db.Column(db.String(120), unique=True, nullable=False)
    is_staff = db.Column(db.Boolean, default=False)
    orders = db.relationship('Order', back_populates='customer', lazy='dynamic')
    
    def set_password(self, password):
        self.password_hash = generate_password_hash(password)
    
    def check_password(self, password):
        return check_password_hash(self.password_hash, password)

    def __repr__(self):
        return f'<Customer {self.username}>'

class OrderItem(db.Model):
    __tablename__ = 'order_items'
    id = db.Column(db.Integer, primary_key=True)
    quantity = db.Column(db.Integer, nullable=False)
    
    # Relationships
    order_id = db.Column(db.Integer, db.ForeignKey('orders.id'))
    item_id = db.Column(db.Integer, db.ForeignKey('inventory_items.id'))
    
    order = db.relationship('Order', back_populates='order_items')
    item = db.relationship('InventoryItem', back_populates='order_items')

class InventoryItem(db.Model):
    __tablename__ = 'inventory_items'
    id = db.Column(db.Integer, primary_key=True)
    name = db.Column(db.String(120), unique=True, nullable=False)
    quantity = db.Column(db.Integer, default=0)
    price = db.Column(db.Float, nullable=False)
    threshold = db.Column(db.Integer, default=10)
    
    # Relationships
    supplier_id = db.Column(db.Integer, db.ForeignKey('suppliers.id'))
    supplier = db.relationship('Supplier', back_populates='items')
    order_items = db.relationship('OrderItem', back_populates='item')

    def needs_restock(self):
        return self.quantity < self.threshold

    def __repr__(self):
        return f'<InventoryItem {self.name}>'

class Order(db.Model):
    __tablename__ = 'orders'
    id = db.Column(db.Integer, primary_key=True)
    status = db.Column(db.String(20), default='Placed')
    total = db.Column(db.Float, nullable=False)
    created_at = db.Column(db.DateTime, default=datetime.utcnow)
    
    # Relationships
    customer_id = db.Column(db.Integer, db.ForeignKey('customers.id'))
    customer = db.relationship('Customer', back_populates='orders')
    order_items = db.relationship('OrderItem', back_populates='order')
    payment = db.relationship('Payment', back_populates='order', uselist=False)

    def __repr__(self):
        return f'<Order {self.id} - {self.status}>'

class Payment(db.Model):
    __tablename__ = 'payments'
    id = db.Column(db.Integer, primary_key=True)
    amount = db.Column(db.Float, nullable=False)
    method = db.Column(db.String(20), nullable=False)
    status = db.Column(db.String(20), default='Pending')
    transaction_date = db.Column(db.DateTime, default=datetime.utcnow)
    
    # Relationships
    order_id = db.Column(db.Integer, db.ForeignKey('orders.id'))
    order = db.relationship('Order', back_populates='payment')

    def __repr__(self):
        return f'<Payment {self.method} - {self.status}>'

class Supplier(db.Model):
    __tablename__ = 'suppliers'
    id = db.Column(db.Integer, primary_key=True)
    name = db.Column(db.String(120), unique=True, nullable=False)
    contact = db.Column(db.String(120))
    
    # Relationships
    items = db.relationship('InventoryItem', back_populates='supplier')

    def __repr__(self):
        return f'<Supplier {self.name}>'