package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Spark;

import frc.robot.Robot;

/**
 * Has the job of pushing up the hook and pulling the robot up as well
 */
public class Winch {

    private static final double LOCKED_DURATION = 0.1;
    private static final double UNLOCKED_DURATION = 0.05;
    
    private static Spark motor = new Spark(3);
    private static Solenoid lock = new Solenoid(7);

    /**
     * 0 is unlocked,
     * 1 is locked.
     * There will most likely be a middle ground between locking and unlocking
     */
    private static double lockedValue = 1;
    private static Boolean lockAction = null;

    private static double motorSpeed = 0;

    public static void init() {
        //motor.setBounds(1, 0.05, 0, -0.05, -1);
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
        if ((lockedValue != 1 || !isLocked) && (lockedValue != 0 || isLocked)) {
            lockAction = isLocked;
        }
    }

    public static void incr(double interval) {
        Boolean solenoidValue = true;
        if (lockAction != null) {
            interval /= lockAction ? LOCKED_DURATION : -UNLOCKED_DURATION;
            solenoidValue = lockAction;

            lockedValue = clamp(lockedValue + interval, 0, 1);
        } else {
            if (lockedValue == 0) {
                solenoidValue = false;
            } else if (lockedValue == 1) {
                solenoidValue = true;
            } else {
                // this should never happen >.>
                System.out.println("Solenoid should be set to a value!");
            }
        }

        lock.set(solenoidValue);

        //System.out.print("lockedValue = ");
        //System.out.println(lockedValue);
        if (lockedValue == 0 && motorSpeed != 0) {
            //System.out.print("Setting motor to ");
            //System.out.println(motorSpeed);
            motor.set(motorSpeed);
        } else if (lockedValue != 1) {
            motor.set(0);
            lockAction = true;
        }
    }
}