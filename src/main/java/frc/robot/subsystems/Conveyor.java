/*
class that controls the conveyor using a CAN SparkMAX
*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.CAN;
import com.revrobotics.SparkMax;

public class Conveyor {

    //private static SparkMax motor = new SparkMax(deviceId);

    private static double targetSpeed = 0;
    private static double speed = 0;

    public static void set(double speed) {

    }

    public static void smoothSet(double speed) {

    }
} 