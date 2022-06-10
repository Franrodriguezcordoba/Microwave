package States;

import Microwave.Microwave;

public class Cooking extends State {

	public Cooking(Microwave m) {
		super(m);
		m.setDoorOpen(false);
		m.cooking_start();
		m.setWithItem(true);
		m.beeper.beep(0);
		m.display.setDisplay(m.display.getDisplay());
		m.heating.heating_on();
		m.turntable.turntable_start();
		m.lamp.lamp_off();
		tick();
	}

	@Override
	public void power_inc() {
		microwave.power_inc();
		ClosedWithItem state = new ClosedWithItem(microwave);
		microwave.setState(state);

		
	}

	@Override
	public void power_dec() {

		microwave.power_dec();
		ClosedWithItem state = new ClosedWithItem(microwave);
		microwave.setState(state);

	}

	@Override
	public void power_reset() {
		microwave.power_reset();
		ClosedWithItem state = new ClosedWithItem(microwave);
		microwave.setState(state);

	}

	@Override
	public void timer_inc() {
		microwave.timer_inc();
		ClosedWithItem state = new ClosedWithItem(microwave);
		microwave.setState(state);

	}

	@Override
	public void timer_dec() {
		microwave.timer_dec();
		ClosedWithItem state = new ClosedWithItem(microwave);
		microwave.setState(state);

	}

	@Override
	public void timer_reset() {
		microwave.timer_reset();
		ClosedWithItem state = new ClosedWithItem(microwave);
		microwave.setState(state);

	}

	
	
	@Override
	public State item_placed() {

		throw new RuntimeException("No se puede introducir el alimento mientras se está cocinando");
	}

	@Override
	public State item_removed() {

		throw new RuntimeException("No se puede sacar el alimento con la puerta cerrada");
	}

	@Override
	public State door_Closed() {

		throw new RuntimeException("La puerta ya está cerrada");
	}

	@Override
	public State door_Opened() {
		OpenWithItem state = new OpenWithItem(microwave);
		return state;

	}

	public State cooking_Start() {

		throw new RuntimeException("Ya se está cocinando");

	}

	public State cooking_Stop() {
		ClosedWithItem state = new ClosedWithItem(microwave);
		return state;

	}

	public void tick() {
		float i = microwave.getTimer();
		while (i > 0) {
			microwave.setTimer(microwave.getTimer() - 1);
			microwave.display.setDisplay(microwave.getTimer() + "s");
			i--;
		}
		microwave.beeper.beep(3);
		microwave.display.setDisplay("Comida lista");

	}

	@Override
	public String getEstado() {
		return "Cooking";
	}

}
