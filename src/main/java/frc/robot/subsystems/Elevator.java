package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;

/**
 * Controls the lift for the winch
 */
public class Elevator {
    
    private static Spark motor = new Spark(2);

    public static void set(double speed) {
        // System.out.println(speed);
        motor.set(speed);
    }
}