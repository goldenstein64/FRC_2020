package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;

/**
 * I don't know what this does yet
 */
public class Elevator {
    
    private static Spark motor = new Spark(1);

    public static void move(double speed) {
        motor.set(speed);
    }
}