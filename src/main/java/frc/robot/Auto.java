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

	private static AutoRoutine routine;

	public static void init() {
		if (!inited) {
			inited = true;
			// do thing
		}
		start();
	}

	private static void start() {
		routine = new flexibleRoutine(0, true);
		routine.init();
		Drive.resetEncoder(true);
		Drive.resetEncoder(false);
	}

	private static abstract class AutoRoutine {
		protected double duration = 0;
		protected int mode = 0;
		public boolean enabled = false;

		public void init() {
			duration = 0;
			mode = 0;
			enabled = true;
		}

		public abstract void incr(double interval);
	}

	private static class flexibleRoutine extends AutoRoutine {
		private static double stoppedMoving = 0;

		private double driveDistance;
		private double turnDistance;
		private Boolean useLeftEncoder;

		public flexibleRoutine(double angle, boolean frontOnLine) {
			double driveY = frontOnLine ? 10 * 12 : 7 * 12;
			int turnNum =  (int) (Math.signum(angle)%2);

			driveDistance = driveY/Math.cos(angle);
			turnDistance = angle * 24;
			useLeftEncoder = (turnNum == -1);
		}

		public void init() {
			duration = 0;
			mode = 0;
			enabled = true;
			Drive.resetEncoder(true);
			Drive.resetEncoder(false);
		}

		public void incr(double interval) {
			duration += interval;
			switch (mode) {
			case 0:
				if (Drive.getConvertedValue(useLeftEncoder) < driveDistance) {
					Drive.smoothSet(-0.7, -0.7);
				} else {
					Drive.resetEncoder(true);
					Drive.resetEncoder(false);
					mode = 1;
				}
				break;
			case 1:
				if (Drive.getConvertedValue(useLeftEncoder) < turnDistance) {
					if (useLeftEncoder) { 
						Drive.smoothSet(0.5, 0); 
					} else { 
						Drive.smoothSet(0, 0.5); 
					}
				} else {
					stoppedMoving += interval;
					Drive.set(0, 0);
					Drive.setMode(NeutralMode.Brake);
					mode = 2;
				}
				// turn to face the goal

				break;
			case 2:
				stoppedMoving += interval;
				if (stoppedMoving < 0.5) {
					Gate.setOpen(true);
				} else {
					mode = 3;
				}
				// open the gate
				break;
			case 3:
				if (duration < 13.75) {
					Conveyor.set(1);
				} else {
					mode = 4;
				}
				// run the conveyor until 13.75 seconds pass
				
			case 4:
				Gate.setOpen(false);
				Drive.setMode(NeutralMode.Coast);
				if (duration >= 15) {
					enabled = false;
				}
			}
		}
	}

	private static class straightFarRoutine extends AutoRoutine {
		private double stoppedMoving = 0;

		public void incr(double interval) {
			duration += interval;
			switch (mode) {
			case 0:
				if (Drive.getConvertedValue(false) < 10 * 12) { // if sensor picks up less than 10 feet,
					Drive.set(-0.7, -0.7);
				} else {
					mode = 1;
					Drive.set(0, 0);
					Drive.setMode(NeutralMode.Brake);
				}
				break;
			case 1:
				stoppedMoving += interval;
				if (stoppedMoving < 0.5) {
					Gate.setOpen(true);
				} else {
					mode = 2;
				}
				break;
			case 2:
				if (duration < 13.75) {
					Conveyor.set(1);
				} else {
					mode = 3;
				}
				break;
			case 3:
				Gate.setOpen(false);
				Drive.setMode(NeutralMode.Coast);
				enabled = false;
				break;
			}
		}
	}

	public static class winchRoutine extends AutoRoutine {
		public static boolean enabled = false;
		public static double duration = 0;
		public static int state = 0;

		public void init() {
			enabled = true;
			duration = 0;
			state = 0;
		}

		public void incr(double interval) {
			Drive.resetEncoder(true);
			Drive.resetEncoder(false);
			duration += interval;

			switch (state) {
			case 0:
				Elevator.set(1);
				if (duration > 6) {
					state = 1;
				}
				// wait however long it takes to move the elevator up
				break;
			case 1:
				Drive.smoothSet(0.5, 0.5);
				if (Drive.getConvertedValue(false) > 6) {
					Drive.setMode(NeutralMode.Brake);
					duration = 0;
					state = 2;
				}
				// move the robot forward 6 inches
				break;
			case 2:
				Elevator.set(-1);
				Winch.set(1);
				if (duration > 5) { // stop everything
					enabled = false;
				}
				// lower the elevator
				// retract the winch
				// wait 5 seconds
				break;
			}
		}
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

	/*
	public static void autoStraightFar() {
		switch (autoMode) {
		case 0:
			if (Drive.getConvertedValue(false) < 10 * 12) { // if sensor picks up less than 10 feet,
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
	*/

	public static void execute() {
		if (routine.enabled) {
			routine.incr(currInterval);
		}
	}
}