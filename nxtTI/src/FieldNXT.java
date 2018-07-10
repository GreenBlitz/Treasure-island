package treasureIsland;

import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.nxt.TouchSensor;
import lejos.nxt.comm.*;

public class FieldNXT {
	private static USBConnection usbc;
	static int cargoPoints = 40;

	public static void waitForConnect()
	{
		LCD.drawString("Waiting...", 0, 0);
		usbc = new USBConnection(0);
		LCD.drawString("Connected  ", 0, 0);
		Button.waitForAnyPress();  
	}
	
	public static void main(String[] args) {
		TouchSensor t1 = new TouchSensor(SensorPort.S1);
		TouchSensor t2 = new TouchSensor(SensorPort.S2);
		Motor.A.setSpeed(210);
		Motor.B.setSpeed(210);
		LCD.drawString("Raised:", 0, 0);
		//this loop handles raising the anchors, exits when both are raised.
		while (Motor.A.getTachoCount() < 828 || Motor.B.getTachoCount() < 828) {
			if (t1.isPressed() && Motor.A.getTachoCount() < 828) {
				Motor.A.forward();
			} else {
				Motor.A.stop();	
			}
			if (t2.isPressed() && Motor.B.getTachoCount() < 828) {
				Motor.B.forward();
			} else {
				Motor.B.stop();
			}
			if (Motor.A.getTachoCount() > 827) {
				LCD.drawInt(1, 7, 0);
			}
			LCD.drawChar(',', 8, 0);
			if (Motor.B.getTachoCount() > 827) {
				LCD.drawInt(2, 9, 0);
			}
			LCD.drawInt(Motor.A.getTachoCount(), 1, 4);
			LCD.drawInt(Motor.B.getTachoCount(), 6, 4);
		}
		Motor.A.stop();
		Motor.B.stop();
		//make this check over and over until it actually works.
		//this loop also sets the value of cargopoints according to values recieved from the inputstream
		if (cargoPoints >= 30) {
			Motor.C.setSpeed(100);
			Motor.C.forward();
			
			while(Motor.C.getTachoCount() <= 225) {
			}
			Motor.C.stop();	
		}
		Button.waitForAnyPress();
		
	}
}