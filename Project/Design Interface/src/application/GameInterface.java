package application;

import java.io.IOException;

import application.engine.Ground;
import application.engine.Universe;
import application.gui.TypeEntity;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import lombok.Getter;
import lombok.Setter;

public class GameInterface {
	
	@Getter @Setter private static GameInterface instance ;
	
	@Getter @Setter private int sheep, wolf ;
	
	public static GridPane grid ;
	
	@FXML @Getter @Setter private GridPane gameGridPane ;
	@FXML @Getter @Setter private Button button_update ;
	
	@FXML @Getter @Setter private Label label_tour ;
	
	
	public GameInterface() {
		System.out.println("NEW GameInterface");
	}
	
	
	public void setScene() {
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainEcosystem.class.getResource("Design2_0.fxml"));
        Parent rootLayout = null;
		try {
			rootLayout = loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ;
		}
        
        // Show the scene containing the root layout.
        Scene scene = new Scene(rootLayout);
        MainEcosystem.getInstance().getPrimaryStage().setScene(scene);
	}
	
	
	@FXML
	public void update() {
		System.out.println("=== Update ===");
		System.out.println(gameGridPane);
		System.out.println(button_update);
		GameInterface.grid = gameGridPane ;
		System.out.println(">> "+GameInterface.grid);
		Universe universe = MainEcosystem.getInstance().getUniverse() ;
		this.updateInterface(universe.getGround());
		GameInterface.instance = this ;
	}
	
	@FXML
	public void startSimulation() {
		GameInterface.grid = gameGridPane ;
		GameInterface.instance = this ;
		Universe universe = MainEcosystem.getInstance().getUniverse() ;
		universe.doSimulation();
	}
	
	public void updateInterface(Ground ground) {
		
		System.out.println(">> "+GameInterface.grid);
		
		gameGridPane.getChildren().removeIf(e -> e instanceof ImageView) ;
		
		for(int x=0 ; x<ground.getSize_x() ; x++)
			for(int y=0 ; y<ground.getSize_y() ; y++) {
				TypeEntity type = ground.getEntity(x, y).getTypeEntity() ;
				if(type == TypeEntity.NOTHING) continue ;
				ImageView view = new ImageView(type.getImg()) ;
				view.setFitHeight(18);
				view.setFitWidth(18);
				gameGridPane.add(view, x, y);
				GridPane.setHalignment(view, HPos.CENTER);
			}
		
	}
	
	public void setTour(int nbTour) {
		label_tour.setText("Tour n° "+nbTour);
	}
	
	
}
