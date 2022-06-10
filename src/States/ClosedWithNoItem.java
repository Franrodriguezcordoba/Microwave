package States;

import Microwave.Microwave;

public class ClosedWithNoItem extends State {

	public ClosedWithNoItem(Microwave m) {
		super(m);
		m.setDoorOpen(false);
		m.cooking_stop();
		m.setWithItem(false);
		m.beeper.beep(0);
		m.display.setDisplay(m.display.getDisplay());
		m.heating.heating_off();
		m.turntable.turntable_stop();
		m.lamp.lamp_off();
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
	
		throw new RuntimeException("No es posible introducir alimento con la puerta cerrada") ;
	}

	@Override
	public State item_removed() {
		throw new RuntimeException("No hay ningun alimento para retirar") ;
	}

	@Override
	public State door_Closed() {
		throw new RuntimeException("Puerta ya cerrada");
		
	}

	@Override
	public State door_Opened() {
		OpenWithNoItem state= new OpenWithNoItem(microwave);
		return state ;
	}

	@Override
	public State cooking_Start() {
		throw new RuntimeException("No se hay ningun alimento dentro del microondas");	
	}
	@Override
	public State cooking_Stop() {
		throw new RuntimeException("No se está cocinando");	
	}
	@Override
	public String getEstado() {
		return "ClosedWithNoItem";
	}

}
