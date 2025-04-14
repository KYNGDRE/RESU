# customer_routes.py
from flask import Blueprint, render_template, request, redirect, url_for, flash
from flask_jwt_extended import jwt_required, get_jwt_identity
from models import db, Customer

customer_bp = Blueprint('customer_mgmt', __name__)

def is_staff_user(user):
    """Helper function to check if the current user is a staff member."""
    return user and user.is_staff

@customer_bp.route('/customers')
@jwt_required()
def list_customers():
    current_user = Customer.query.get(int(get_jwt_identity()))
    if not is_staff_user(current_user):
        flash("Staff access required.")
        return redirect(url_for('dashboard'))
    customers = Customer.query.all()
    return render_template('customer_list.html', customers=customers)

@customer_bp.route('/customer/edit/<int:customer_id>', methods=['GET', 'POST'])