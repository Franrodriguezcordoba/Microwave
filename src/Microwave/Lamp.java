package Microwave;

public class Lamp {
	private boolean lampON;
	
	public void lamp_on() {
		lampON=true;
		
	}
	public void lamp_off() {
		lampON=false;
	}
	public boolean isLampOn() {
		return lampON;
	}

}
