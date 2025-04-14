# inventory_routes.py
from flask import Blueprint, render_template, request, redirect, url_for, flash
from flask_jwt_extended import jwt_required, get_jwt_identity
from models import db, InventoryItem, Customer

inventory_bp = Blueprint('inventory', __name__)

def is_staff_user(user):
    return user and user.is_staff

# This route is already defined in your app.py. If you are moving all inventory routes into this blueprint,
# then remove or update the existing /inventory route in app.py accordingly.
@inventory_bp.route('/inventory')
@jwt_required()
def view_inventory():
    items = InventoryItem.query.all()
    return render_template('inventory.html', items=items)

@inventory_bp.route('/inventory/update/<int:item_id>', methods=['GET', 'POST'])
@jwt_required()
def update_inventory(item_id):
    current_user = Customer.query.get(int(get_jwt_identity()))
    if not is_staff_user(current_user):
        flash("Staff access required.")
        return redirect(url_for('dashboard'))
    item = InventoryItem.query.get_or_404(item_id)
    if request.method == 'POST':
        item.name = request.form.get('name')
        try:
            item.quantity = int(request.form.get('quantity'))
            item.price = float(request.form.get('price'))
            item.threshold = int(request.form.get('threshold'))
        except (ValueError, TypeError):
            flash("Invalid numeric input.")
            return redirect(url_for('inventory.update_inventory', item_id=item_id))
        try:
            db.session.commit()
            flash("Inventory item updated successfully.")
            return redirect(url_for('inventory.view_inventory'))
        except Exception as e:
            db.session.rollback()
            flash(f"Error updating inventory item: {str(e)}")
            return redirect(url_for('inventory.update_inventory', item_id=item_id))
    return render_template('update_inventory.html', item=item)

@inventory_bp.route('/inventory/delete/<int:item_id>', methods=['POST'])
@jwt_required()
def delete_inventory(item_id):
    current_user = Customer.query.get(int(get_jwt_identity()))
    if not is_staff_user(current_user):
        flash("Staff access required.")
        return redirect(url_for('dashboard'))
    item = InventoryItem.query.get_or_404(item_id)
    try:
        db.session.delete(item)
        db.session.commit()
        flash("Inventory item deleted successfully.")
    except Exception as e:
        db.session.rollback()
        flash(f"Error deleting inventory item: {str(e)}")
    return redirect(url_for('inventory.view_inventory'))
