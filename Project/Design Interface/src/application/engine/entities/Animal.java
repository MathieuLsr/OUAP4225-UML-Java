package application.engine.entities;

import application.engine.Ground;
import application.gui.TypeEntity;
import lombok.Getter;
import lombok.Setter;

public abstract class Animal extends Entity {
	
	@Getter @Setter protected int age;
	@Getter @Setter protected int hunger;
    @Getter @Setter private SexeAnimal sexeAnimal ;
    @Getter @Setter private boolean reproduced;
    @Getter @Setter protected boolean isAlive;

    public Animal(Ground ground, TypeEntity type, int x, int y) {
		super(ground, type, x, y);
		setSexeAnimal(SexeAnimal.getRandomSexe());
		setReproduced(false);
		setAlive(true);
	}
    
    public abstract void move();
    public abstract void checkDeath();
    public abstract void checkReproduction();
    
}

