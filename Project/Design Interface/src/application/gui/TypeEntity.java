package application.gui;

import javafx.scene.image.Image;
import lombok.Getter;

public enum TypeEntity {
	
	SALT("File:images/salt.png"), 
	GRASS("File:images/grass.png"),
	WOLF("File:images/wolf.png"), 
	SHEEP("File:images/sheep.png"),
	NOTHING("File:images/nothing.png") ;
	
	@Getter private Image img ;
	
	private TypeEntity(String str) {
		this.img = new Image(str) ;
	}

}
