package testing;
import lejos.hardware.motor.Motor;

public class Eyes implements Runnable {
//	private volatile boolean running = true;
//	
//	public void terminate(){
//		running = false;
//	}
	public void run(){
		lejos.hardware.motor.NXTRegulatedMotor augenMotor = Motor.A;
		augenMotor.setSpeed(200);
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
//				running = false;
				break;
			}
//			Delay.msDelay(1000);
//			Motor.B.stop();

		}
		augenMotor.stop();
	}
}
