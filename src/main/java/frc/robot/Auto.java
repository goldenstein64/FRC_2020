package frc.robot;

import edu.wpi.first.wpilibj.Timer;

import frc.robot.subsystems.*;
import frc.robot.Robot;

/**
 * Implements any autonomous actions done by the robot
 */
public class Auto {

    private static double lastPeriod = Timer.getFPGATimestamp();

    private static double currInterval = 0;

    public static void updatePeriod() {
        double nextPeriod = Timer.getFPGATimestamp();
        double interval = lastPeriod - nextPeriod;
        lastPeriod = nextPeriod;
        currInterval = interval;
    }

    public static void executeTele() {
        Winch.incr(currInterval);
        Gate.incr(currInterval);
    }
}