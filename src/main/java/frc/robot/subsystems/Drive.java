package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;

/**
 * Controls movement via the wheels
 */
public class Drive {

    private static final double SPEED_MODIFIER = 0.6;

    private static WPI_TalonSRX talonLeft = new WPI_TalonSRX(1);
    private static WPI_VictorSPX victorLeft = new WPI_VictorSPX(2);

    private static WPI_TalonSRX talonRight = new WPI_TalonSRX(3);
    private static WPI_VictorSPX victorRight = new WPI_VictorSPX(4);

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
        talonLeft.configNeutralDeadband(0.075);
        talonRight.configNeutralDeadband(0.075);
        victorLeft.configNeutralDeadband(0.075);
        victorRight.configNeutralDeadband(0.075);

        // make sure the motors are synced
        victorLeft.follow(talonLeft);
        victorRight.follow(talonRight);
        
        
        talonLeft.setInverted(true);
        victorLeft.setInverted(true);

        talonRight.setInverted(false);
        victorRight.setInverted(false);

        // set up the encoders
        // 4096 units/revolution
        // 7.75 in diameter
        // conversion: (S / 4096) * 7.79*pi = feet covered
        talonLeft.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
        talonRight.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);

        talonLeft.setSensorPhase(false);
        talonRight.setSensorPhase(true);

        talonLeft.setSelectedSensorPosition(0, 0, 10);
        talonRight.setSelectedSensorPosition(0, 0, 10);
    }

    public static void setMode(NeutralMode neutralMode) {
        talonLeft.setNeutralMode(neutralMode);
        victorLeft.setNeutralMode(neutralMode);
        talonRight.setNeutralMode(neutralMode);
        victorRight.setNeutralMode(neutralMode);
    }

    public static void switchInverted() {
        isInverted = !isInverted;

        talonLeft.setInverted(isInverted);
        victorLeft.setInverted(isInverted);

        talonRight.setInverted(!isInverted);
        victorRight.setInverted(!isInverted);

        WPI_TalonSRX temp = talonLeft;
        talonLeft = talonRight;
        talonRight = temp;
    }

    public static void set(double leftInput, double rightInput) {
        left.targetSpeed = left.speed = leftInput;
        right.targetSpeed = right.speed = rightInput;
    }

    public static void smoothSet(double leftInput, double rightInput) {
        double lSign = Math.signum(leftInput);
        double rSign = Math.signum(rightInput);

        System.out.println(String.valueOf(Math.pow(leftInput, 2)) + ", " + String.valueOf(Math.pow(rightInput, 2)));
        left.targetSpeed = lSign * Math.pow(leftInput, 2) * SPEED_MODIFIER;
        right.targetSpeed = rSign * Math.pow(rightInput, 2) * SPEED_MODIFIER;
    }

    private static int getSensorValue(boolean isLeft) {
        WPI_TalonSRX talon;
        if (isLeft) {
            talon = talonLeft;
        } else {
            talon = talonRight;
        }
        return talon.getSelectedSensorPosition();
    }

    public static double getConvertedValue(boolean isLeft) {
        return getSensorValue(isLeft) * 6 * Math.PI / 4096;
    }

    public static void incr(double interval) {
        left.speed = (left.targetSpeed - left.speed) * 0.2 + left.speed;
        right.speed = (right.targetSpeed - right.speed) * 0.2 + right.speed;

        //System.out.println(String.valueOf(left.speed) + ", " + String.valueOf(right.speed));

        talonLeft.set(left.speed);
        talonRight.set(right.speed);
    }
}