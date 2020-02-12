package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

/**
 * Controls the movement of the balls while inside the robot
 */
public class Conveyor {

    private static CANSparkMax motor = new CANSparkMax(7, MotorType.kBrushless);

    private static double targetSpeed = 0;
    private static double speed = 0;

    public static void set(double inputSpeed) {
        targetSpeed = inputSpeed;
        speed = inputSpeed;
        motor.set(inputSpeed);
    }

    public static void smoothSet(double inputSpeed) {
        targetSpeed = inputSpeed;
        speed = (targetSpeed - speed) * 0.2 + speed;

        motor.set(speed);
    }
} 