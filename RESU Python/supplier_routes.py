# supplier_routes.py
from flask import Blueprint, render_template, request, redirect, url_for, flash
from flask_jwt_extended import jwt_required, get_jwt_identity
from models import db, Supplier, Customer

supplier_bp = Blueprint('supplier', __name__)

def is_staff_user(user):
    return user and user.is_staff

@supplier_bp.route('/suppliers')
@jwt_required()
def list_suppliers():
    current_user = Customer.query.get(int(get_jwt_identity()))
    if not is_staff_user(current_user):
        flash("Staff access required.")
        return redirect(url_for('dashboard'))
    suppliers = Supplier.query.all()
    return render_template('supplier_list.html', suppliers=suppliers)

@supplier_bp.route('/supplier/add', methods=['GET', 'POST'])
@jwt_required()
def add_supplier():
    current_user = Customer.query.get(int(get_jwt_identity()))
    if not is_staff_user(current_user):
        flash("Staff access required.")
        return redirect(url_for('dashboard'))
    if request.method == 'POST':
        name = request.form.get('name')
        contact = request.form.get('contact')
        if not name:
            flash("Supplier name is required.")
            return redirect(url_for('supplier.add_supplier'))
        new_supplier = Supplier(name=name, contact=contact)
        db.session.add(new_supplier)
        try:
            db.session.commit()
            flash("Supplier added successfully.")
            return redirect(url_for('supplier.list_suppliers'))
        except Exception as e:
            db.session.rollback()
            flash(f"Error adding supplier: {str(e)}")
            return redirect(url_for('supplier.add_supplier'))
    return render_template('add_supplier.html')

@supplier_bp.route('/supplier/edit/<int:supplier_id>', methods=['GET', 'POST'])
@jwt_required()
def edit_supplier(supplier_id):
    current_user = Customer.query.get(int(get_jwt_identity()))
    if not is_staff_user(current_user):
        flash("Staff access required.")
        return redirect(url_for('dashboard'))
    supplier = Supplier.query.get_or_404(supplier_id)
    if request.method == 'POST':
        supplier.name = request.form.get('name')
        supplier.contact = request.form.get('contact')
        try:
            db.session.commit()
            flash("Supplier updated successfully.")
            return redirect(url_for('supplier.list_suppliers'))
        except Exception as e:
            db.session.rollback()
            flash(f"Error updating supplier: {str(e)}")
            return redirect(url_for('supplier.edit_supplier', supplier_id=supplier_id))
    return render_template('edit_supplier.html', supplier=supplier)

@supplier_bp.route('/supplier/delete/<int:supplier_id>', methods=['POST'])
@jwt_required()
def delete_supplier(supplier_id):
    current_user = Customer.query.get(int(get_jwt_identity()))
    if not is_staff_user(current_user):
        flash("Staff access required.")
        return redirect(url_for('dashboard'))
    supplier = Supplier.query.get_or_404(supplier_id)
    try:
        db.session.delete(supplier)
        db.session.commit()
        flash("Supplier deleted successfully.")
    except Exception as e:
        db.session.rollback()
        flash(f"Error deleting supplier: {str(e)}")
    return redirect(url_for('supplier.list_suppliers'))
