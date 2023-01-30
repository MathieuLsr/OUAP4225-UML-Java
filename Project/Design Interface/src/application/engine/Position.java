package application.engine;

import lombok.Getter;
import lombok.Setter;

public class Position {

	@Getter @Setter private int x,y ;
	
	public Position(int x, int y) {
		setX(x);
		setY(y);
	}
	
	public void add(int addX, int addY) {
		this.x += addX ;
		this.y += addY ;
	}
	
	public void clipRange(int maxX, int maxY) {
		if(x < 0) x = 0 ;
		else if(x >= maxX) x = maxX-1 ;
		
		if(y < 0) y = 0 ;
		else if(y >= maxY) y = maxY-1 ;
	}
	
	@Override
	public String toString() {
		return "["+x+","+y+"]" ;
	}
	
}
