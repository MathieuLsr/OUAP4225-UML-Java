package application.engine.entities;

import application.engine.Ground;
import application.engine.Position;
import application.engine.Universe;
import application.gui.TypeEntity;

public abstract class Entity {

	private Position pos ;
    protected Ground ground ;
    private Universe universe;
    private TypeEntity typeEntity ;
    
    public Entity(Ground ground, TypeEntity typeEntity, int x, int y) {
		setGround(ground);
    	setTypeEntity(typeEntity);
		setPos(new Position(x, y));
	}

	public int getX() {
		return pos.getX() ;
	}
	public void setX(int x) {
		this.pos.setX(x);
	}
	public int getY() {
		return pos.getY() ;
	}
	public void setY(int y) {
		this.pos.setY(y);
	}
    
	public Universe getUniverse() {
		return universe;
	}
	public void setUniverse(Universe universe) {
		this.universe = universe;
	}

	public Ground getGround() {
		return ground;
	}

	public void setGround(Ground ground) {
		this.ground = ground;
	}

	public TypeEntity getTypeEntity() {
		return typeEntity;
	}

	public void setTypeEntity(TypeEntity typeEntity) {
		this.typeEntity = typeEntity;
	}

	public Position getPos() {
		return pos;
	}

	public void setPos(Position pos) {
		this.pos = pos;
	}
    
    
	
}
