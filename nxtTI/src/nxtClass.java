import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.comm.USB;
import lejos.nxt.comm.USBConnection;
import lejos.nxt.remote.NXTComm;

public class nxtClass {
	public static void main(String[] args) {
		DataInputStream is;
		DataOutputStream os;
		USBConnection usbc;
		// establish connection.
		LCD.drawString("waiting", 0, 0);
		usbc = USB.waitForConnection();
		LCD.drawString("connected", 0, 0);
		is = usbc.openDataInputStream();
		os = usbc.openDataOutputStream();
		Button.waitForAnyPress();
		char i = '0';
		try {
			LCD.drawString("trying to read", 0, 0);
			LCD.drawInt(is.available(), 0, 4);
			i = is.readChar();
		} catch (IOException e) {
			LCD.drawString("i cant read", 0, 0);
			e.printStackTrace();
		}
		LCD.clear();
		LCD.drawChar(i, 0, 0);
		Button.waitForAnyPress();
		try {
			os.writeInt(-1);
			os.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		LCD.clear();
		LCD.drawString("sending int", 0, 0);
		Button.waitForAnyPress();
	}
}
