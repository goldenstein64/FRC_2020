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

    public static void init() {
        
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

    }
}