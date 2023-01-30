package application.engine.entities.all ;

import java.util.Arrays;
import java.util.List;

import application.engine.Ground;
import application.engine.Position;
import application.engine.entities.Animal;
import application.engine.entities.Entity;
import application.gui.TypeEntity;

public class Sheep extends Animal {

    public Sheep(Ground ground, int x, int y) {
		super(ground, TypeEntity.SHEEP, x, y);
	}
    
    @Override
    public void checkDeath() {
        if (hunger >= 5 || age >= 50) {
            if (hunger >= 5) {
                this.isAlive = false;
                System.out.println("Sheep has died of hunger");
            } else {
                this.isAlive = false;
                System.out.println("Sheep has died of old age");
            }
            ground.addMineralSalts(getX(), getY());
            ground.removeSheep(this);
        }
    }


    public void doTurn() {
        if (!isAlive) {
            return;
        }
        age++;
        hunger++;
        if (hunger > 5) {
            die();
            return;
        }
        if (age > 50) {
            die();
            return;
        }
        move();
        checkReproduction();
    }

    /*
    public void move() {
        int newX = x + rand.nextInt(3) - 1;
        int newY = y + rand.nextInt(3) - 1;
        if (newX < 0 || newX >= universe.getM() || newY < 0 || newY >= universe.getN()) {
            return;
        }
        if (grid[newX][newY] instanceof Grass) {
            grid[newX][newY] = null;
            hunger = 0;
        }
        if (grid[newX][newY] == null) {
            grid[x][y] = null;
            x = newX;
            y = newY;
            grid[x][y] = this;
        }
    }
	*/
    
    public void checkReproduction() {
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (getX() + i >= 0 && getX() + i < ground.getSize_x() && getY() + j >= 0 && getY() + j < ground.getSize_y()) {
                	
                	Entity ent = ground.getEntity(getX()+1, getX()+j) ;
                	if(ent == null) continue ;
                	
                    if (ent instanceof Sheep) {
                        Sheep other = (Sheep) ent ;
                        if (other.getSexeAnimal() != this.getSexeAnimal()) {
                            int[] emptyCell = ground.getRandomEmptyCell();
                            if (emptyCell != null){
                                Sheep newSheep = new Sheep(ground, emptyCell[0], emptyCell[1]) ;
                                ground.addSheep(newSheep);
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
        ground.removeSheep(this);
        ground.addMineralSalts(getX(), getY());
    }

	@Override
	public void move() {
		
		List<Integer> list = Arrays.asList(-1, 0, 1) ; 
		
		Position pos = new Position(getX(), getY()) ;
		ground.setEntity(new Grass(ground, getX(), getY())) ;
		
		
		pos.add(
				list.get((int) (Math.random()*list.size())), 
				list.get((int) (Math.random()*list.size()))
			);
		
		pos.clipRange(ground.getSize_x(), ground.getSize_y()) ;
		super.setPos(pos);
		ground.setEntity(this);
		
		/*
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
        */
        //System.out.println("=> "+getPos());
	}
}


                            

