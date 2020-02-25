package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;

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

    private static class left {
        public static double targetSpeed = 0;
        public static double speed = 0;
    }

    private static class right {
        public static double targetSpeed = 0;
        public static double speed = 0;
    }

    private static boolean isInverted = false;
    private static boolean invertDebounce = false;

    public static void setInvertDebounce(boolean newBool) {
        invertDebounce = newBool;
    }

    public static boolean getInvertDebounce() {
        return invertDebounce;
    }

    public static void init() {
        talonLeft.configNeutralDeadband(0.05);
        talonRight.configNeutralDeadband(0.05);
        victorLeft.configNeutralDeadband(0.05);
        victorRight.configNeutralDeadband(0.05);

        // make sure the motors are synced
        victorLeft.follow(talonLeft);
        victorRight.follow(talonRight);
        
        
        talonLeft.setInverted(true);
        victorLeft.setInverted(true);

        talonRight.setInverted(false);
        victorRight.setInverted(false);
        // set up the encoders
        talonLeft.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
        talonRight.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);

        talonLeft.setSensorPhase(false);
        talonRight.setSensorPhase(true);

        talonLeft.setSelectedSensorPosition(0, 0, 10);
        talonRight.setSelectedSensorPosition(0, 0, 10);
    }

    public static void switchInverted() {
        isInverted = !isInverted;

        talonLeft.setInverted(!isInverted);
        victorLeft.setInverted(!isInverted);
        talonRight.setInverted(isInverted);
        victorRight.setInverted(isInverted);
    }

    public static void set(double leftInput, double rightInput) {
        left.targetSpeed = left.speed = leftInput;
        right.targetSpeed = right.speed = rightInput;
    }

    public static void smoothSet(double leftInput, double rightInput) {
        left.targetSpeed = leftInput;
        right.targetSpeed = rightInput;
    }

    public static void incr(double interval) {
        left.speed = (left.targetSpeed - left.speed) * 0.2 + left.speed;
        right.speed = (right.targetSpeed - right.speed) * 0.2 + right.speed;

        talonLeft.set(left.speed);
        talonRight.set(right.speed);
    }
}