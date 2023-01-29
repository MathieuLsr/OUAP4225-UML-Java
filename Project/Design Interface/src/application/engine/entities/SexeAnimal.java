package application.engine.entities;

public enum SexeAnimal {

	MALE, FEMELE ;
	
	public static SexeAnimal getRandomSexe() {
		return Math.random() < 0.5 ? SexeAnimal.MALE : SexeAnimal.FEMELE ;
	}
	
}
