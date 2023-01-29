package application.engine;

import java.util.Scanner;

import application.GameInterface;
import lombok.Getter;
import lombok.Setter;

public class Universe {
	
	@Getter @Setter private int numSheep ;
	@Getter @Setter private int numWolf ;
    
    @Getter private Ground ground ; 
    @Getter @Setter private GameInterface gameInterface ;

    public Universe(int numSheep, int numWolf, GameInterface gameInterface2) {
        
    	this.ground = new Ground(numSheep, numWolf) ;
    	setGameInterface(gameInterface2);
    	
    	gameInterface.setScene();
    	setNumSheep(numSheep);
    	setNumWolf(numWolf);
    	this.initSimulation();
    	
    }
    
    public void initSimulation() {
    	
    	//generate random sheep and wolves
    	ground.initGround();
        ground.generateSheep(numSheep);
        ground.generateWolves(numWolf);
        ground.updateGrid();
        
    	
    }

    public void startSimulation() throws InterruptedException {
    	
    	System.out.println("- Start simulation");
        //start simulation
    	
    	Thread thr = new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(ground.isAliveSheep() || ground.isAliveWolves()){
		            
		        	System.out.println(GameInterface.grid);
		        	ground.updateGrid();
		            GameInterface.getInstance().updateInterface(ground);
		            
		            try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
		            
		        }

		        //print end message
		        System.out.println("Simulation ended. Press any key to exit.");
		        Scanner scan = new Scanner(System.in);
		        scan.nextLine();
		        scan.close();
			}
		}) ;
    	
        thr.run();
    	
    }



	public void updateInterface() {
		
		gameInterface.updateInterface(ground);
		
	}
}






