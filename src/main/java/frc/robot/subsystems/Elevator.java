package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;

/**
 * I don't know what this does yet
 */
public class Elevator {
    
    private static Spark motor = new Spark(2);

    public static void set(double speed) {
        System.out.println(speed);
        motor.set(speed);
    }
}