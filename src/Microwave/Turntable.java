package Microwave;

public class Turntable {
	private boolean turnTableOn;
	
	public void turntable_start() {
		turnTableOn=true;
		
	}
	
	public void turntable_stop() {
		turnTableOn=false;
		
	}
	public boolean isMoving() {
		return turnTableOn;
	}

}
