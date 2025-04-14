 # routes.py
from flask import Blueprint
from app import db

main_bp = Blueprint('main', __name__)

# Import your route definitions here
from . import auth_routes, inventory_routes  # etc