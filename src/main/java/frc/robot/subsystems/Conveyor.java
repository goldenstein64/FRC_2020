package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

/**
 * Controls the movement of the balls while inside the robot
 */
public class Conveyor {

    private static CANSparkMax motor = new CANSparkMax(5, MotorType.kBrushless);

    private static double targetSpeed = 0;
    private static double speed = 0;

    public static void init() {
        motor.setCANTimeout(10);
    }

    public static void set(double inputSpeed) {
        targetSpeed = speed = inputSpeed;
    }

    public static void smoothSet(double inputSpeed) {
        targetSpeed = inputSpeed;
    }

    public static void incr(double interval) {
        speed = (targetSpeed - speed) * 0.2 + speed;
        if (Math.abs(speed) >= 0.05) {
            motor.set(speed);
        } else {
            motor.set(0);
        }
    }
} 