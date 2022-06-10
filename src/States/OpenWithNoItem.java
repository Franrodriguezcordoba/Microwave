package States;

import Microwave.Microwave;

public class OpenWithNoItem extends State {

	public OpenWithNoItem(Microwave m) {
		super(m);
		m.setDoorOpen(true);
		m.cooking_stop();
		m.setWithItem(false);
		m.beeper.beep(0);
		m.display.setDisplay(m.display.getDisplay());;
		m.heating.heating_off();;
		m.turntable.turntable_stop();;
		m.lamp.lamp_on();

	}

	@Override
	public void power_inc() {
		microwave.power_inc();
		
	}

	@Override
	public void power_dec() {
		microwave.power_dec();
		
	}

	@Override
	public void power_reset() {
		microwave.power_reset();
		
	}

	@Override
	public void timer_inc() {
		microwave.timer_inc();

		

	
	}

	@Override
	public void timer_dec() {
		microwave.timer_dec();
	}

	@Override
	public void timer_reset() {
		microwave.timer_reset();
	}

	@Override
	public State item_placed() {
		OpenWithItem state = new OpenWithItem(microwave);
		return state;
	}
	@Override
	public State item_removed() {
		throw new RuntimeException("No hay alimento para retirar") ;
	}
	@Override
	public State door_Closed() {
		ClosedWithNoItem state = new ClosedWithNoItem(microwave);
		return state;
	}
	@Override
	public State door_Opened() {
		throw new RuntimeException("La puerta ya está abierta") ;
	}

	public State cooking_Start() {

		throw new RuntimeException("No hay alimento para cocinar") ;
	}
	@Override
	public State cooking_Stop() {

		throw new RuntimeException("No se está cocinando") ;
	}

	@Override
	public String getEstado() {
		return "OpenWithNoItem";
	}
}
