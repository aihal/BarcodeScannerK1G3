package testing;
import lejos.hardware.motor.Motor;

public class Eyes implements Runnable {
	/**
	 * Diese Methode lässt die Augen wechselweise drehen. Mal im Uhrzeigersinn, mal dagegen.
	 * Diese Methode läuft in einem Thread und wird erst abgebrochen, wenn ein Interruptsignal 
	 * (von .interrupt() in main) empfangen wird.
	 */
	@Override
	public void run(){
		lejos.hardware.motor.NXTRegulatedMotor augenMotor = Motor.A;
		augenMotor.setSpeed(300);
		while(true){
			augenMotor.forward();// von vorne auf den Drehmotor geguckt im Uhrzeigersinn
			try{
			Thread.sleep(2500);
			} catch(InterruptedException e) {
//				running = false;
				break;
			}
			augenMotor.backward();// von vorne auf den Drehmotor geguckt gegen Uhrzeigersinn
			try{
			Thread.sleep(2500);
			} catch(InterruptedException e) {
				break;
			}
		}
		augenMotor.stop();
	}
}
