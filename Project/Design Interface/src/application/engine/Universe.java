package application.engine;

import application.GameInterface;
import lombok.Getter;
import lombok.Setter;

public class Universe {
	
	@Getter @Setter private int numSheep ;
	@Getter @Setter private int numWolf ;
    
    @Getter private Ground ground ; 
    @Getter @Setter private GameInterface gameInterface ;
    
    @Getter @Setter private int numTour ;

    public Universe(int numSheep, int numWolf, GameInterface gameInterface2) {
        
    	this.ground = new Ground(numSheep, numWolf) ;
    	setGameInterface(gameInterface2);
    	setNumTour(0);
    	
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
    
    public void doSimulation() {
    	numTour++ ;
    	ground.updateGrid();
    	GameInterface.getInstance().setTour(numTour);
        GameInterface.getInstance().updateInterface(ground);
    }

    /*
    public void startSimulation() throws InterruptedException {
    	
    	System.out.println("- Start simulation");
        //start simulation
    	
    	Thread thr = new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(ground.isAliveSheep() || ground.isAliveWolves()){
		            
		        	doSimulation();
		            
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
	*/


	public void updateInterface() {
		gameInterface.updateInterface(ground);
	}
}






