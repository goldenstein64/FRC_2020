package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;

/**
 * Controls the lift for the winch
 */
public class Elevator {

	// ((19500 / 2) / 71) * 0.75 * Math.PI

	// 19500 / 71 =?= 5310 * 15 / 24 * 2
	// in inches / minute

	private static Spark motor = new Spark(2);

	public static void set(double speed) {
		// System.out.println(speed);
		motor.set(-speed);
	}
}