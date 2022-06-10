package States;

import Microwave.Microwave;

public abstract class State {
	Microwave microwave;
	public State(Microwave m) {
		this.microwave=m;
	}
	public abstract void power_inc();

	public abstract void power_dec();

	public abstract void power_reset();

	public abstract void timer_inc();

	public abstract void timer_dec();

	public abstract void timer_reset();

	public abstract State item_placed();

	public abstract State item_removed();

	public abstract State door_Closed();

	public abstract State door_Opened();
	
	public abstract State cooking_Start();
	
	public abstract State cooking_Stop() ;
	
	public abstract String getEstado();

	
	
	
	


}
