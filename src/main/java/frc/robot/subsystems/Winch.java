package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Spark;

//import frc.robot.Robot;

/**
 * Has the job of pushing up the hook and pulling the robot up as well
 */
public class Winch {

    private static final double LOCKED_DURATION = 0.05;
    private static final double UNLOCKED_DURATION = 0.1;
    
    private static Spark motor = new Spark(5);

    private static class fakeSolenoid {
        private DoubleSolenoid self;

        public fakeSolenoid(int channelA, int channelB) {
            self = new DoubleSolenoid(channelA, channelB);
        }

        public void set(boolean on) {
            self.set(on ? DoubleSolenoid.Value.kForward : DoubleSolenoid.Value.kReverse);
        }

        public boolean get() {
            return self.get() == DoubleSolenoid.Value.kForward;
        }
    }
    private static fakeSolenoid lock = new fakeSolenoid(6, 7);

    /**
     * 0 is unlocked,
     * 1 is locked.
     * There will most likely be a middle ground between locking and unlocking
     * starts unlocked
     */
    private static double lockedValue = 0;
    private static Boolean lockAction = null;

    private static double motorSpeed = 0;

    private static boolean winchUsed = false;

    public static void init() {
        // problem code?
        //motor.setBounds(1, 0.05, 0, -0.05, -1);
        lock.set(false);
    }

    private static double clamp(double n, double min, double max) {
        return Math.max(Math.min(n, max), min);
    }

    public static void set(double speed) {
        if (speed != 0 ) {
            winchUsed = true;
        }
        if (winchUsed) {
            if (speed == 0) {
                setLocked(true);
            } else {
                setLocked(false);
            }
        }

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

        //*
        Boolean solenoidValue = true;
        if (lockAction != null) {
            interval /= lockAction ? LOCKED_DURATION : -UNLOCKED_DURATION;
            solenoidValue = !lockAction;

            lockedValue = clamp(lockedValue + interval, 0, 1);
        } else {
            if (lockedValue == 0) {
                solenoidValue = true;
            } else if (lockedValue == 1) {
                solenoidValue = false;
            } else {
                // this should never happen >.>
                // System.out.println("Solenoid should be set to a value!");
            }
        }

        lock.set(solenoidValue);
        //*/

        //System.out.print(String.valueOf(lockedValue) + ", ");
        //System.out.print(String.valueOf(motorSpeed) + ", ");
        //System.out.println(winchUsed);
        if (lockedValue == 0 && motorSpeed != 0) {
            //System.out.println(motorSpeed);
            motor.set(motorSpeed);
        } else if (motorSpeed == 0 && lockedValue != 1 && winchUsed) {
            //System.out.println(0.0);
            motor.set(0);
            lockAction = true;
        }
    }
}