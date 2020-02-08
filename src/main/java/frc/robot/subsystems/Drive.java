/*
Drive.java
Class meant to control the wheels of the robot
*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.CAN;
import edu.wpi.first.wpilibj.Encoder;

public class Drive {

    private static CAN talonLeft = new CAN(1);
    private static CAN victorLeft = new CAN(3);
    private static Encoder encoderLeft = new Encoder(0, 1);

    private static CAN talonRight = new CAN(2);
    private static CAN victorRight = new CAN(4);
    private static Encoder encoderRight = new Encoder(2, 3);

    public static void init() {
        // make sure the motors are synced
        
        // set up the encoders

        
    }

    public static void tankDrive(double left, double right) {

    }
}