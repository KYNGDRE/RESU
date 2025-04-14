# customer_management_routes.py
from flask import Blueprint, render_template, request, redirect, url_for, flash
from flask_jwt_extended import jwt_required, get_jwt_identity
from models import db, Customer

customer_mgmt_bp = Blueprint('customer_mgmt', __name__)

def is_staff_user(user):
    return user and user.is_staff

@customer_mgmt_bp.route('/customers')
@jwt_required()
def list_customers():
    current_user = Customer.query.get(int(get_jwt_identity()))
    if not is_staff_user(current_user):
        flash("Staff access required.")
        return redirect(url_for('dashboard'))
    customers = Customer.query.all()
    return render_template('customer_list.html', customers=customers)

@customer_mgmt_bp.route('/customer/edit/<int:customer_id>', methods=['GET', 'POST'])
@jwt_required()
def edit_customer(customer_id):
    current_user = Customer.query.get(int(get_jwt_identity()))
    if not is_staff_user(current_user):
        flash("Staff access required.")
        return redirect(url_for('dashboard'))
    customer = Customer.query.get_or_404(customer_id)
    if request.method == 'POST':
        customer.username = request.form.get('username')
        customer.email = request.form.get('email')
        # Update password only if provided
        password = request.form.get('password')
        if password:
            customer.set_password(password)
        # Update the staff flag if needed
        is_staff_str = request.form.get('is_staff', 'false')
        customer.is_staff = (is_staff_str.lower() == 'true')
        try:
            db.session.commit()
            flash("Customer updated successfully.")
            return redirect(url_for('customer_mgmt.list_customers'))
        except Exception as e:
            db.session.rollback()
            flash(f"Error updating customer: {str(e)}")
            return redirect(url_for('customer_mgmt.edit_customer', customer_id=customer_id))
    return render_template('edit_customer.html', customer=customer)

@customer_mgmt_bp.route('/customer/delete/<int:customer_id>', methods=['POST'])
@jwt_required()
def delete_customer(customer_id):
    current_user = Customer.query.get(int(get_jwt_identity()))
    if not is_staff_user(current_user):
        flash("Staff access required.")
        return redirect(url_for('dashboard'))
    customer = Customer.query.get_or_404(customer_id)
    try:
        db.session.delete(customer)
        db.session.commit()
        flash("Customer deleted successfully.")
    except Exception as e:
        db.session.rollback()
        flash(f"Error deleting customer: {str(e)}")
    return redirect(url_for('customer_mgmt.list_customers'))
