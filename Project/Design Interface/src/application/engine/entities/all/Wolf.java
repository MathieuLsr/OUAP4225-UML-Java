package application.engine.entities.all ;

import java.util.Random;

import application.engine.Ground;
import application.engine.entities.Animal;
import application.engine.entities.Entity;
import application.gui.TypeEntity;

public class Wolf extends Animal {
    

    public Wolf(Ground ground, int x, int y) {
		super(ground, TypeEntity.WOLF, x, y);
	}

    public void checkDeath() {
        if (hunger >= 6 || age >= 60) {
            if (hunger >= 6) {
                this.isAlive = false;
                System.out.println("Wolf has died of hunger");
            } else {
                this.isAlive = false;
                System.out.println("Wolf has died of old age");
            }
            ground.addMineralSalts(getX(), getY());
            ground.removeWolf(this);
        }
    }

    public void doTurn() {
        if (!isAlive) {
            return;
        }
        age++;
        hunger++;
        if (hunger > 10) {
            die();
            return;
        }
        if (age > 60) {
            die();
            return;
        }
        hunt();
        move();
        checkReproduction();
    }

    public void hunt() {
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (getX() + i >= 0 && getX() + i < ground.getSize_x() && getY() + j >= 0 && getY() + j < ground.getSize_y()) {
                    Entity ent = ground.getEntity(getX() + i, getY() + j);
                	if (ent instanceof Sheep) {
                        Sheep sheep = (Sheep) ent;
                        sheep.die();
                        hunger = 0;
                        return;
                    }
                }
            }
        }
    }

    public void move() {
    	Random rand = new Random() ;
        int newX = getX() + rand.nextInt(3) - 1;
        int newY = getY() + rand.nextInt(3) - 1;
        if (newX < 0 || newX >= ground.getSize_x() || newY < 0 || newY >= ground.getSize_y()) {
            return;
        }
        if (ground.getEntity(newX, newY) == null) {
            ground.setEntity(null, getX(), getY()) ;
            setX(newX);
            setY(newY);
            ground.setEntity(this);
        }
    }

    public void checkReproduction() {
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (getX() + i >= 0 && getX() + i < ground.getSize_x() && getY() + j >= 0 && getY() + j < ground.getSize_y()) {
                    
                	Entity ent = ground.getEntity(getX()+1, getY()+j) ;
                	
                	if (ent instanceof Wolf) {
                        Wolf other = (Wolf) ent ;
                        if (other.getSexeAnimal() != this.getSexeAnimal()) {
                            int[] emptyCell = ground.getRandomEmptyCell();
                            if (emptyCell != null) {
                            	Wolf newWolf = new Wolf(ground, emptyCell[0], emptyCell[1]) ;
                                ground.addWolf(newWolf);
                            }
                        }
                    }
                }
            }
        }
    }

    public boolean isAlive() {
        return this.isAlive;
    }

    public void die() {
    	isAlive = false;
        ground.setEntity(null, getX(), getY());
        ground.removeWolf(this);
        ground.addMineralSalts(getX(), getY());
    }
}


