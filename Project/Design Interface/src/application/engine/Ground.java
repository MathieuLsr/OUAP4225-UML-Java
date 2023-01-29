package application.engine;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import application.engine.entities.Entity;
import application.engine.entities.all.Grass;
import application.engine.entities.all.MineralSalts;
import application.engine.entities.all.Sheep;
import application.engine.entities.all.Wolf;
import application.gui.TypeEntity;
import lombok.Getter;

public class Ground {

	private Entity[][] grid ;
	@Getter private int size_x, size_y ;
	
	@Getter private int numSheep, numWolf ;
	
	@Getter private List<Sheep> sheepList;
	@Getter private List<Wolf> wolfList;
	
	public Ground(int numSheep, int numWolf) {
		
		this.numSheep = numSheep ;
		this.numWolf = numWolf ;
		
		size_x = 31 ;
		size_y = 20 ;
		
        grid = new Entity[size_x][size_y];
        this.sheepList = new ArrayList<Sheep>();
        this.wolfList = new ArrayList<Wolf>();
		
	}
	
	public void initGround() {
		for (int i = 0; i < size_x; i++) {
            for (int j = 0; j < size_y; j++) {
            	Grass grass = new Grass(this, i, j) ;
            	this.setEntity(grass);
            }
        }
		/*
        for (int i = 0; i < numSheep; i++) {
            int[] emptyCell = findEmptyCell();
            Sheep sheep = new Sheep(this, emptyCell[0], emptyCell[1]) ;
            this.setEntity(sheep);
            sheepList.add(sheep);
        }
        for (int i = 0; i < numWolf; i++) {
            int[] emptyCell = findEmptyCell();
            Wolf wolf = new Wolf(this, emptyCell[0], emptyCell[1]) ;
            this.setEntity(wolf);
            wolfList.add(wolf);
        }
        */
	}
	
	public Entity getEntity(int x, int y) {
		
		Entity ent = null ;
		
		try {
			ent = grid[x][y] ;
		} catch(ArrayIndexOutOfBoundsException e) {
			return null ;
		}
				
		return ent ;
	}
	public void setEntity(Entity ent, int x, int y) {
		ent.setX(x);
		ent.setY(y);
		this.setEntity(ent);
	}
	/* WARNING */
	public void setEntity(Entity ent) {
		grid[ent.getX()][ent.getY()] = ent ;
		
		if(ent instanceof Sheep) this.sheepList.add((Sheep) ent) ;
		if(ent instanceof Wolf) this.wolfList.add((Wolf) ent) ;
		
	}
	/***********/
	
	public Position findEmptyCell() {
        
        List<Position> list = new ArrayList<Position>() ;
        
        for(int x = 0 ; x < size_x ; x++)
        	for(int y = 0 ; y < size_y ; y++)
        		if(grid[x][y].getTypeEntity() == TypeEntity.GRASS)
        			list.add(new Position(x, y)) ;
        
        int rand = (int) (Math.random()*list.size()) ;
        return list.get(rand) ;
    }
	

    public void updateGrid(){
    	
    	
    	System.out.println("=======");
    	System.out.println("1.0");
    	this.growGrass();
    	System.out.println("2.0");
    	this.moveSheep();
    	System.out.println("3.0");
    	this.moveWolves();
    	System.out.println("4.0");
    	this.checkDeathSheep();
    	System.out.println("5.0");
        this.checkDeathWolves();
        System.out.println("6.0");
        this.checkReproductionSheep();
        System.out.println("7.0");
        this.checkReproductionWolves();
        System.out.println("8.0");
    	
    	/*
        for(int i=0; i<size_x; i++){
            for(int j=0; j<size_y; j++){
                if(grid[i][j] instanceof Sheep){
                    gui.gridButtons[i][j].setBackthis(Color.WHITE);
                }else if(grid[i][j] instanceof Wolf){
                    gui.gridButtons[i][j].setBackthis(Color.GRAY);
                }else if(grid[i][j] instanceof Grass){
                    gui.gridButtons[i][j].setBackthis(Color.GREEN);
                }else if(grid[i][j] instanceof MineralSalts){
                    gui.gridButtons[i][j].setBackthis(Color.YELLOW);
                }else{
                    gui.gridButtons[i][j].setBackthis(Color.BLACK);
                }
            }
        }
        */
    }
    

    public boolean isAliveSheep() {
    	return !sheepList.isEmpty() ;
        /*
    	for (Sheep sheep : sheepList) {
            if (sheep.isAlive()) {
                return true;
            }
        }
        return false; */
    }

    private void growGrass() {
        for (int i = 0; i < this.getSize_x(); i++) {
            for (int j = 0; j < this.getSize_y(); j++) {
                if (this.getEntity(i, j) instanceof MineralSalts) {
                    this.setEntity(new Grass(this, j, j)) ;
                }
            }
        }
    }

    private void moveSheep() {
    	 for (int i = 0; i < this.getSize_x(); i++) {
             for (int j = 0; j < this.getSize_y(); j++) {
            	Entity ent = this.getEntity(i, j) ;
                if (ent instanceof Sheep) {
                    Sheep sheep = (Sheep) ent;
                    sheep.move();
                }
            }
        }
    }
    
    private void moveWolves() {
    	 for (int i = 0; i < this.getSize_x(); i++) {
             for (int j = 0; j < this.getSize_y(); j++) {
            	Entity ent = this.getEntity(i, j) ;
                if (ent instanceof Wolf) {
                    Wolf wolf = (Wolf) ent ;
                    wolf.move();
                }
            }
        }
    }
    
    
    
    public boolean isAliveWolves() {
    	return !wolfList.isEmpty() ;
    	/*
        for (Wolf wolf : wolfList) {
            if (wolf.isAlive()) {
                return true;
            }
        }
        return false; */
    }

    

    public void addSheep(Sheep sheep) {
        sheepList.add(sheep);
        this.setEntity(sheep) ;
    }

    public void addWolf(Wolf wolf) {
        wolfList.add(wolf);
        this.setEntity(wolf) ;
    }

    public void removeSheep(Sheep sheep) {
        sheepList.remove(sheep);
    }

    public void removeWolf(Wolf wolf) {
        wolfList.remove(wolf);
    }

    public void addMineralSalts(int x, int y) {
        Grass grass = (Grass) this.getEntity(x, y) ;
        grass.addMineralSalts(1);
    }

    
    public int[] getRandomEmptyCell() {
        List<int[]> emptyCells = new ArrayList<>();
        for (int i = 0; i < this.getSize_x(); i++) {
            for (int j = 0; j < this.getSize_y(); j++) {
            	Entity ent = this.getEntity(i, j) ;
                if (ent == null) {
                    emptyCells.add(new int[] {i, j});
                }
            }
        }
        if (emptyCells.size() > 0) {
            return emptyCells.get(new Random().nextInt(emptyCells.size()));
        } else {
            return null;
        }
    }

    public void checkDeathSheep(){
    	 for (int i = 0; i < this.getSize_x(); i++) {
             for (int j = 0; j < this.getSize_y(); j++) {
            	Entity ent = this.getEntity(i, j) ;
                if (ent instanceof Sheep) {
                    Sheep sheep = (Sheep) ent ;
                    sheep.checkDeath();
                    if (!sheep.isAlive()) {
                        addMineralSalts(sheep.getX(), sheep.getY());
                        removeSheep(sheep);
                    }
                }
            }
        }
    }
    
    public void checkDeathWolves(){
    	 for (int i = 0; i < this.getSize_x(); i++) {
             for (int j = 0; j < this.getSize_y(); j++) {
            	Entity ent = this.getEntity(i, j) ;
                if (ent instanceof Wolf) {
                    Wolf wolf = (Wolf) ent ;
                    wolf.checkDeath();
                    if (!wolf.isAlive()) {
                        addMineralSalts(wolf.getX(), wolf.getY());
                        removeWolf(wolf);
                    }
                }
            }
        }
    }

    public void checkReproductionWolves(){
    	 for (int i = 0; i < this.getSize_x(); i++) {
             for (int j = 0; j < this.getSize_y(); j++) {
            	Entity ent = this.getEntity(i, j) ;
                if (ent instanceof Wolf) {
                    Wolf wolf = (Wolf) ent ;
                    wolf.checkReproduction();
                    if (wolf.isReproduced()) {
                        Position emptyCell = this.findEmptyCell();
                        if (emptyCell != null) {
                        	Wolf newWolf = new Wolf(this, emptyCell.getX(), emptyCell.getY()) ;
                            this.setEntity(newWolf);
                        }
                    }
                }
            }
        }
    }

    public void checkReproductionSheep(){
    	 for (int i = 0; i < this.getSize_x(); i++) {
             for (int j = 0; j < this.getSize_y(); j++) {
            	Entity ent = this.getEntity(i, j) ;
                if (ent instanceof Sheep) {
                    Sheep sheep = (Sheep) ent ;
                    sheep.checkReproduction();
                    if (sheep.isReproduced()) {
                    	Position emptyCell = this.findEmptyCell();
                        if (emptyCell != null) {
                        	Sheep newSheep = new Sheep(this, emptyCell.getX(), emptyCell.getY()) ;
                        	this.setEntity(newSheep);
                        }
                    }
                }
            }
        }
    }


    public void generateSheep(int numSheep) {
        for (int i = 0; i < numSheep; i++) {
        	Position emptyCell = this.findEmptyCell();
            Sheep newSheep = new Sheep(this, emptyCell.getX(), emptyCell.getY()) ;
        	this.setEntity(newSheep);
        }
    }
    
    public void generateWolves(int numWolves) {
        for (int i = 0; i < numWolves; i++) {
        	Position emptyCell = this.findEmptyCell();
            Wolf newWolf = new Wolf(this, emptyCell.getX(), emptyCell.getY()) ;
        	this.setEntity(newWolf);
        }
    }
    
    
    
	
}
