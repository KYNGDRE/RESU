from flask import Flask
from config import Config
from models import db

def create_app(config_class=Config):
    app = Flask(__name__)
    app.config.from_object(config_class)
    
    # Initialize extensions
    db.init_app(app)
    
    # Import and register blueprints/routes here
    from app.routes import main_bp
    app.register_blueprint(main_bp)
    
    return app
