package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Spark;

public class Winch {
    
    private static Spark motor = Spark(1);
    private static DoubleSolenoid lock = new DoubleSolenoid(3, 4);

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