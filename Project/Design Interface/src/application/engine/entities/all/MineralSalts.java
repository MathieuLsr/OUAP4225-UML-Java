package application.engine.entities.all;

import application.engine.Ground;
import application.engine.entities.Entity;
import application.gui.TypeEntity;

public class MineralSalts extends Entity {
  
    public MineralSalts(Ground ground, int x, int y) {
		super(ground, TypeEntity.SALT, x, y);
	}

	public void spread() {
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
            	Entity ent = ground.getEntity(getX() + i, getY() + j) ;
                if (getX() + i >= 0 && getX() + i < ground.getSize_x() && getY() + j >= 0 && getY() + j < ground.getSize_y()) {
                    if (ent instanceof Grass) {
                        Grass grass = (Grass) ent ;
                        grass.grow();
                    }
                }
            }
        }
    }
}
