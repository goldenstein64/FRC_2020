package frc.robot;

import edu.wpi.first.wpilibj.Timer;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import frc.robot.subsystems.*;
//import frc.robot.Robot;

/**
 * Implements any autonomous actions done by the robot
 */
public class Auto {

	private static boolean inited = false;

	private static double lastPeriod = Timer.getFPGATimestamp();

	private static double currInterval = 0;

	private static double autoDuration = 0;
	private static double stoppedMoving = 0;

	private static int autoMode = 0;

	private static AutoRoutine routine;

	private static class AutoRoutine {
		AutoRoutine() {

		}
	}

	public static void init() {
		if (!inited) {
			inited = true;
			// do thing
		}
		start();
	}

	private static void start() {
		
	}

	public static void updatePeriod() {
		double nextPeriod = Timer.getFPGATimestamp();
		double interval = nextPeriod - lastPeriod;
		lastPeriod = nextPeriod;
		currInterval = interval;
	}

	public static double getPeriod() {
		return currInterval;
	}

	public static void runIncr() {
		Drive.incr(currInterval);
		Winch.incr(currInterval);
		Gate.incr(currInterval);
		Conveyor.incr(currInterval);
	}

	public static void startAutoRoutine(double angle, boolean isFrontBumper) { // angle is in radians
		routine = new AutoRoutine();
		double driveY = isFrontBumper ? 84 : 120;
		double driveDistance = driveY/Math.cos(angle);
		double turnDistance = angle * 24;
		int turnNum =  (int) Math.signum(angle);
		
		Boolean useLeftEncoder;
		if (turnNum == 1) { // starts with robot to the left
			useLeftEncoder = false;
		} else if (turnNum == -1) {
			useLeftEncoder = true;
		}



	}
	public static void autoStraightFar() {
		switch (autoMode) {
		case 0:
			if (Drive.getConvertedValue(false) < 120) { // if sensor picks up less than 10 feet,
				Drive.set(0.5, 0.5);
			} else {
				autoMode = 1;
				stoppedMoving += currInterval;
				Drive.set(0, 0);
				Drive.setMode(NeutralMode.Brake);
			}
			break;
		case 1:
			stoppedMoving += currInterval;
			if (stoppedMoving < 0.5) {
				Gate.setOpen(true);
			} else {
				autoMode = 2;
			}
			break;
		case 2:
			if (autoDuration < 13.75) {
				Conveyor.set(1);
			} else {
				autoMode = 3;
			}
			break;
		case 3:
			Gate.setOpen(false);
			Drive.setMode(NeutralMode.Coast);
			break;
		}
	}

	public static void execute() {
		autoDuration += currInterval;
		autoStraightFar();
	}
}