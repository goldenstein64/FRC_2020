package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

/**
 * Retrieves balls from the "back" of the robot
 */
public class Intake {

    private static CANSparkMax motor = new CANSparkMax(5, MotorType.kBrushless);

    public static void setRunning(boolean isRunning) {

    }
}