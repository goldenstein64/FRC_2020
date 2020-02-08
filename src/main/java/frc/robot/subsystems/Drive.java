package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

/**
 * Controls movement via the wheels
 */
public class Drive {

    private static WPI_TalonSRX talonLeft = new WPI_TalonSRX(1);
    private static WPI_VictorSPX victorLeft = new WPI_VictorSPX(2);
    private static Encoder encoderLeft = new Encoder(1, 2); // these are output channels, not input channels

    private static WPI_TalonSRX talonRight = new WPI_TalonSRX(3);
    private static WPI_VictorSPX victorRight = new WPI_VictorSPX(4);
    private static Encoder encoderRight = new Encoder(3, 4);

    public static void init() {

        // make sure the motors are synced
        victorLeft.follow(talonLeft);
        victorRight.follow(talonRight);
        
        // set up the encoders

        
    }

    public static void tankDrive(double left, double right) {

    }
}