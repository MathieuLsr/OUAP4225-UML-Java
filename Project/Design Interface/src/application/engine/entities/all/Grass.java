package application.engine.entities.all;

import application.engine.Ground;
import application.engine.entities.Entity;
import application.gui.TypeEntity;

public class Grass extends Entity {
	
	private int mineralSalts = 0;
	
    public Grass(Ground ground, int x, int y) {
		super(ground, TypeEntity.GRASS, x, y);
	}

    public void grow() {
        if (mineralSalts > 0) {
            mineralSalts--;
        }
    }

    public void addMineralSalts(int amount) {
        mineralSalts += amount;
    }
}

