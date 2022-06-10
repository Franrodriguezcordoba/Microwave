import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import Microwave.*;

class Test_Microwave {

	private Microwave m = new Microwave();

// Empezamos probando los diferentes componentes
	@Test
	void testDisplay() {
		Display display = new Display();

		Assertions.assertEquals(null, display.getDisplay());

		display.setDisplay("La comida está lista");

		Assertions.assertEquals("La comida está lista", display.getDisplay());

		display.clearDisplay();

		Assertions.assertEquals("", display.getDisplay());
	}

	@Test
	void testHeating() {
		Heating heating = new Heating();

		heating.heating_on();
		Assertions.assertTrue(heating.isHeating());

		heating.heating_off();
		Assertions.assertFalse(heating.isHeating());

		heating.setPower(50);

		Assertions.assertEquals(50, heating.getPower());

	}

	@Test
	void testLamp() {
		Lamp lamp = new Lamp();

		lamp.lamp_on();
		Assertions.assertTrue(lamp.isLampOn());

		lamp.lamp_off();
		Assertions.assertFalse(lamp.isLampOn());
	}

	@Test
	void testTurntable() {
		Turntable turntable = new Turntable();

		turntable.turntable_start();
		Assertions.assertTrue(turntable.isMoving());

		turntable.turntable_stop();
		Assertions.assertFalse(turntable.isMoving());
	}

	@Test
	// A continuación probamos la creación del microondas
	void testMicroondas() {

		Assertions.assertFalse(m.getDoorOpen());
		Assertions.assertEquals(0, m.getPower());
		Assertions.assertFalse(m.isCooking());
		Assertions.assertFalse(m.getWithItem());
		Assertions.assertEquals("ClosedWithNoItem", m.getEstado());

		m.setTimer(10);
		Assertions.assertEquals(10, m.getTimer());

		m.setPower(100);
		Assertions.assertEquals(100, m.getPower());

		m.cooking_start();
		Assertions.assertTrue(m.isCooking());

		m.cooking_stop();
		Assertions.assertFalse(m.isCooking());

		m.setDoorOpen(true);
		Assertions.assertTrue(m.getDoorOpen());

		m.setWithItem(true);
		Assertions.assertTrue(m.getWithItem());

		m.power_inc();
		Assertions.assertEquals(200, m.getPower());

		m.setPower(900);
		Assertions.assertEquals("Se ha alcanzado la potencia máxima", m.display.getDisplay());

		m.getPower();
		Assertions.assertEquals(200, m.getPower());

		m.setPower(800);
		m.power_inc();
		Assertions.assertEquals("Se ha alcanzado la máxima potencia", m.display.getDisplay());

		m.power_dec();
		Assertions.assertEquals(700, m.getPower());

		m.setPower(100);
		m.power_dec();
		Assertions.assertEquals("No es posible decrementar más la potencia", m.display.getDisplay());

		m.timer_dec();
		Assertions.assertEquals(0, m.getTimer());

		m.setTimer(60);
		Assertions.assertEquals(60.0 + "s", m.display.getDisplay());

		m.timer_reset();
		Assertions.assertEquals(0.0 + "s", m.display.getDisplay());

	}

	// Ahora los diferentes estados
	@Test
	void testClosedWithNoItem() {
		Assertions.assertFalse(m.getDoorOpen());
		Assertions.assertFalse(m.isCooking());
		Assertions.assertFalse(m.getWithItem());
		Assertions.assertFalse(m.heating.isHeating());
		Assertions.assertFalse(m.turntable.isMoving());
		Assertions.assertFalse(m.lamp.isLampOn());
		Assertions.assertEquals("ClosedWithNoItem", m.getEstado());

		// Vemos si lanza excepciones
		Assertions.assertThrows(RuntimeException.class, () -> m.item_placed());
		Assertions.assertThrows(RuntimeException.class, () -> m.item_removed());
		Assertions.assertThrows(RuntimeException.class, () -> m.door_Closed());
		Assertions.assertThrows(RuntimeException.class, () -> m.cooking_Start());
		Assertions.assertThrows(RuntimeException.class, () -> m.cooking_Stop());

		m.door_Opened();
		Assertions.assertEquals("OpenWithNoItem", m.getEstado());

	}

	@Test
	void testOpenWithNoItem() {
		m.door_Opened();
		Assertions.assertTrue(m.getDoorOpen());
		Assertions.assertFalse(m.isCooking());
		Assertions.assertFalse(m.getWithItem());
		Assertions.assertFalse(m.heating.isHeating());
		Assertions.assertFalse(m.turntable.isMoving());
		Assertions.assertTrue(m.lamp.isLampOn());
		Assertions.assertEquals("OpenWithNoItem", m.getEstado());

		
		Assertions.assertThrows(RuntimeException.class, () -> m.item_removed());
		Assertions.assertThrows(RuntimeException.class, () -> m.door_Opened());
		Assertions.assertThrows(RuntimeException.class, () -> m.cooking_Start());
		Assertions.assertThrows(RuntimeException.class, () -> m.cooking_Stop());

		m.door_Closed();
		Assertions.assertEquals("ClosedWithNoItem", m.getEstado());

		m.door_Opened();
		m.item_placed();
		Assertions.assertEquals("OpenWithItem", m.getEstado());

	}
	
	@Test
	void testOpenWithItem() {
		m.door_Opened();
		m.item_placed();
		Assertions.assertTrue(m.getDoorOpen());
		Assertions.assertFalse(m.isCooking());
		Assertions.assertTrue(m.getWithItem());
		Assertions.assertFalse(m.heating.isHeating());
		Assertions.assertFalse(m.turntable.isMoving());
		Assertions.assertTrue(m.lamp.isLampOn());
		Assertions.assertEquals("OpenWithItem", m.getEstado());

		
		
		Assertions.assertThrows(RuntimeException.class, () -> m.door_Opened());
		Assertions.assertThrows(RuntimeException.class, () -> m.cooking_Start());
		Assertions.assertThrows(RuntimeException.class, () -> m.cooking_Stop());

		
		
		m.item_removed();
		Assertions.assertEquals("OpenWithNoItem", m.getEstado());
		m.door_Closed();
		Assertions.assertEquals("ClosedWithNoItem", m.getEstado());
		
		m.door_Opened();
		Assertions.assertEquals("OpenWithNoItem", m.getEstado());
		m.item_placed();
		Assertions.assertEquals("OpenWithItem", m.getEstado());
		m.door_Closed();
		

	}
	@Test
	void testClosedWithItem() {
		m.door_Opened();
		m.item_placed();
		m.door_Closed();
		Assertions.assertFalse(m.getDoorOpen());
		Assertions.assertFalse(m.isCooking());
		Assertions.assertTrue(m.getWithItem());
		Assertions.assertFalse(m.heating.isHeating());
		Assertions.assertFalse(m.turntable.isMoving());
		Assertions.assertFalse(m.lamp.isLampOn());
		Assertions.assertEquals("ClosedWithItem", m.getEstado());

		
		Assertions.assertThrows(RuntimeException.class, () -> m.item_placed());
		Assertions.assertThrows(RuntimeException.class, () -> m.item_removed());
		Assertions.assertThrows(RuntimeException.class, () -> m.door_Closed());
		Assertions.assertThrows(RuntimeException.class, () -> m.cooking_Stop());

		
		
		m.door_Opened();
		Assertions.assertEquals("OpenWithItem", m.getEstado());
		m.door_Closed();
		Assertions.assertEquals("ClosedWithItem", m.getEstado());
		
		m.cooking_Start();
		Assertions.assertEquals("Cooking", m.getEstado());
		
		

	}
	@Test
	void testCooking() {
		m.door_Opened();
		m.item_placed();
		m.door_Closed();
		m.cooking_Start();
		Assertions.assertFalse(m.getDoorOpen());
		Assertions.assertTrue(m.isCooking());
		Assertions.assertTrue(m.getWithItem());
		Assertions.assertTrue(m.heating.isHeating());
		Assertions.assertTrue(m.turntable.isMoving());
		Assertions.assertFalse(m.lamp.isLampOn());
		Assertions.assertEquals("Cooking", m.getEstado());

		
		Assertions.assertThrows(RuntimeException.class, () -> m.item_placed());
		Assertions.assertThrows(RuntimeException.class, () -> m.item_removed());
		Assertions.assertThrows(RuntimeException.class, () -> m.door_Closed());
		Assertions.assertThrows(RuntimeException.class, () -> m.cooking_Start());

		
		
		m.cooking_Stop();
		Assertions.assertEquals("ClosedWithItem", m.getEstado());
		
		
		
		

	}

}
