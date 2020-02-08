package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

/**
 * Controls the movement of the balls while inside the robot
 */
public class Conveyor {

    private static CANSparkMax motor = new CANSparkMax(4, MotorType.kBrushless);

    private static double targetSpeed = 0;
    private static double speed = 0;

    public static void set(double speed) {
        Conveyor.targetSpeed = speed;
        Conveyor.speed = speed;
        motor.set(speed);
    }

    public static void smoothSet(double speed) {

    }
} 