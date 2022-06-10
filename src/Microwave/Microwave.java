package Microwave;

import States.*;

public class Microwave {
	private boolean doorOpen;
	private int power;
	private float timer;
	private boolean cooking;
	private boolean withItem;
	public State estado;

	public Beeper beeper = new Beeper();
	public Display display = new Display();
	public Heating heating = new Heating();
	public Lamp lamp = new Lamp();
	public Turntable turntable = new Turntable();

	public Microwave() {
		doorOpen = false;
		heating.setPower(0);
		power = heating.getPower();
		timer = 0;
		cooking = false;
		withItem = false;
		this.estado = new ClosedWithNoItem(this);
	}

	public void setTimer(float t) {
		
		timer = t;
		display.setDisplay(getTimer()+"s");
	}

	public float getTimer() {
		return timer;
	}

	public void setPower(int p) {
		if(p>800) {
			display.setDisplay("Se ha alcanzado la potencia máxima");
			
		}
		else if(p<100) {
			display.setDisplay("La potencia mínima es de 100");
		}
		else {
			power = p;
			display.setDisplay("Potencia=" + getPower());
		}
	}

	public int getPower() {
		return power;
	}

	public void cooking_start() {
		cooking = true;
	}

	public void cooking_stop() {
		cooking = false;
	}

	public boolean isCooking() {
		return cooking;

	}

	public void setDoorOpen(boolean door) {
		doorOpen = door;
	}

	public boolean getDoorOpen() {
		return doorOpen;
	}

	public void setWithItem(boolean item) {
		withItem = item;
	}

	public boolean getWithItem() {
		return withItem;
	}

	public String getEstado() {
		return estado.getEstado();
	}

	public boolean getHeating() {
		return heating.isHeating();
	}

	public void item_placed() {

		estado = estado.item_placed();
	}

	public void item_removed() {
		estado = estado.item_removed();
	}

	public void door_Closed() {

		estado = estado.door_Closed();
	}

	public void door_Opened() {
		estado = estado.door_Opened();
	}

	public void cooking_Start() {
		estado = estado.cooking_Start();
	}

	public void cooking_Stop() {

		estado = estado.cooking_Stop();
	}

	public void power_inc() {
		if (getPower() < 800) {
			setPower(getPower() + 100);
			display.setDisplay("Potencia=" + getPower());

		} else {
			display.setDisplay("Se ha alcanzado la máxima potencia");

		}
	}
	
	public void power_dec() {

		if (getPower() > 100) {
			setPower(getPower() - 100);
			display.setDisplay("Potencia=" + getPower());

		} else {
			display.setDisplay("No es posible decrementar más la potencia");

		}
	}
	
	public void timer_inc() {
		setTimer(getTimer() + 10);
		display.setDisplay(getTimer() + "s");

		

	
	}

	
	public void timer_dec() {
		if (getTimer() > 10) {
			setTimer(getTimer() - 10);
			display.setDisplay(getTimer() + "s");
			
			} else {
				setTimer(0);
				display.setDisplay(getTimer() + "s");

			}
		

		
	}
	
	public void power_reset() {
		setPower(0);
		display.setDisplay("Potencia=" + getPower());
	}

	
	public void timer_reset() {
		setTimer(0);
		display.setDisplay(getTimer() + "s");
		
	}
	
	public void setState(State e) {
		this.estado=e;
	}
}
