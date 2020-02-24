package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;

import frc.robot.Robot;

/**
 * Controls the gate in front of the conveyor
 */
public class Gate {

    private static final double OPEN_INTERVAL = 0.1;

    private static DoubleSolenoid solenoid = new DoubleSolenoid(0, 3);

    private static double openValue = 0;
    private static Boolean openAction = null;

    private static double clamp(double n, double min, double max) {
        return Math.max(Math.min(n, max), min);
    }

    public static double getOpen() {
        return openValue;
    }

    public static void setOpen(boolean isOpen) {
        if ((openValue != 1 || !isOpen) && (openValue != 0 || isOpen)) {
            openAction = isOpen;
        }
    }

    public static void incr(double interval) {
        DoubleSolenoid.Value solenoidValue;
        if (openAction != null) {
            interval *= openAction ? 1 : -1;
            solenoidValue = openAction ? DoubleSolenoid.Value.kForward : DoubleSolenoid.Value.kReverse;

            openValue = clamp(openValue + interval/OPEN_INTERVAL, 0, 1);
        } else {
            solenoidValue = DoubleSolenoid.Value.kOff;
        }

        //System.out.println(solenoidValue.toString());
        solenoid.set(solenoidValue);

        if (openValue == 0 || openValue == 1) {
            openAction = null;
        }

    }
}