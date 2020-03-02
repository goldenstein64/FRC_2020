package frc.robot;

import edu.wpi.first.wpilibj.Timer;

import frc.robot.subsystems.*;
import frc.robot.Robot;

/**
 * Implements any autonomous actions done by the robot
 */
public class Auto {

	private static boolean inited = false;

	private static double lastPeriod = Timer.getFPGATimestamp();

	private static double currInterval = 0;

	public static void init() {
		if (!inited) {
			inited = true;
			// do thing
		}
	}

	public static void start() {
		
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

	public static void executeTele() {
		Drive.incr(currInterval);
		Winch.incr(currInterval);
		Gate.incr(currInterval);
		Conveyor.incr(currInterval);
	}

	public static void execute() {
		if (Drive.getConvertedValue(false) < 10) { // if sensor picks up less than 10 feet,
			Drive.smoothSet(0.5, 0.5);
		} else {
			Drive.smoothSet(0, 0);
		}
	}
}