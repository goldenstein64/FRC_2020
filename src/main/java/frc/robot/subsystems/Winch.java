package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Spark;

import frc.robot.Robot;

/**
 * Has the job of pushing up the hook and pulling the robot up as well
 */
public class Winch {
    
    private static Spark motor = new Spark(0);
    private static DoubleSolenoid lock = new DoubleSolenoid(0, 1);

    private static boolean locked = true;

    public static void set(double speed) {
        // if the winch is locked, unlock it


    }

    public static boolean getLocked() {
        return locked;
    }

    public static void setLocked(boolean isLocked) {
        // this command takes precedence over moving the motor, since it will be operated by a button
        // if the motor is moving, tell it to stop moving

        // unlock or lock it

        // set the property
    }
}