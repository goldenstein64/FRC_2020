package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Spark;

/**
 * Has the job of pushing up the hook and pulling the robot up as well
 */
public class Winch {
    
    private static Spark motor = new Spark(0);
    private static DoubleSolenoid lock = new DoubleSolenoid(0, 1);

    public static void extend() {
        // unlock it?

        // start extending the scaffolding

        // lock it?
    }

    public static void retract() {
        // unlock it?

        // start retracting from the hook

        // lock it?
    }
}