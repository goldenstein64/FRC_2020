package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Spark;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

import frc.robot.Robot;

/**
 * Has the job of pushing up the hook and pulling the robot up as well
 */
public class Winch {

    private static final double LOCKED_DURATION = 0.2;
    
    private static Spark motor = new Spark(0);
    private static DoubleSolenoid lock = new DoubleSolenoid(0, 1);

    /**
     * 0 is unlocked,
     * 1 is locked.
     * There will most likely be a middle ground between locking and unlocking
     */
    private static double lockedValue = 1;
    private static Boolean lockAction = null;

    private static double motorSpeed = 0;

    public static void init() {
        motor.setBounds(1, 0.05, 0, -0.05, -1);
    }

    private static double clamp(double n, double min, double max) {
        return Math.max(Math.min(n, max), min);
    }

    public static void set(double speed) {
        // speed is already clamped so don't worry about it
        motorSpeed = speed;
    }

    public static double getLocked() {
        return lockedValue;
    }

    public static void setLocked(boolean isLocked) {
        lockAction = isLocked;
    }

    public static void incr(double interval) {
        Value solenoidValue;
        if (lockAction != null) {
            interval *= lockAction ? 1 : -1;
            solenoidValue = lockAction ? Value.kForward : Value.kReverse;

            lockedValue += interval/LOCKED_DURATION;
            lockedValue = clamp(lockedValue, 0, 1);
        } else {
            solenoidValue = Value.kOff;
        }

        lock.set(solenoidValue);

        if (lockedValue == 0 || lockedValue == 1) {
            lockAction = null;
        }

        if (lockedValue == 1 && motorSpeed != 0) {
            motor.set(motorSpeed);
        } else if (lockedValue != 1) {
            lockAction = true;
        }
    }
}