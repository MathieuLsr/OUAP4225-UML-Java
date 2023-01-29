package application;
	
import java.io.IOException;

import application.engine.Universe;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;


public class MainEcosystem extends Application {
	
	@Getter private static MainEcosystem instance ;
	
	@Getter @Setter private Universe universe ;
	@Getter @Setter private Stage primaryStage;
	
	@FXML private Slider sliderSheep ;
	@FXML private Slider sliderWolf ;
	
	@Override
	public void start(Stage primaryStage) {
		
		MainEcosystem.instance = this ;
		
		this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Menu Ecosysteme");

        initRootLayout();
		
	}
	
	/**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainEcosystem.class.getResource("Design1_0.fxml"));
            Parent rootLayout = loader.load();
            
            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
            
        } catch (IOException e) {
            e.printStackTrace();
            return ;
        }
    }
    

	@FXML 
	public void startGame() {
		System.out.println("Start game");
		
		int nb_sheep = (int) sliderSheep.getValue() ;
		int nb_wolf = (int) sliderWolf.getValue() ;
		
		GameInterface gameInterface = new GameInterface() ;
		Universe universe = new Universe(nb_sheep, nb_wolf, gameInterface) ;
		MainEcosystem.getInstance().setUniverse(universe);

		
		//Universe universe = new Universe(nb_sheep, nb_wolf);
		//universe.startSimulation(nb_sheep, nb_sheep);
		
		/*
		
		try {
			new GameInterface().launchGame();
		} catch (IOException e) {
			e.printStackTrace();
		}
		*/
		
	}
	
	@FXML
	public void increaseValue() {
		
		sliderSheep.setValue((int) sliderSheep.getValue());
		sliderWolf.setValue((int) sliderWolf.getValue());
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
