package Microwave;

public class Heating {
	private boolean heating=false;
	private int power;
	
	public void heating_on() {
		heating=true;
	}
	public void heating_off() {
		heating=false;
	}
	public boolean isHeating() {
		return heating;
		
	}
	public void setPower(int d) {
		power=d;
	}
	public int getPower() {
		return power;
	}

}
