package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

import frc.robot.Robot;

/**
 * Controls the gate in front of the conveyor
 */
public class Gate {

    private static final double OPEN_INTERVAL = 0.2;

    private static DoubleSolenoid solenoid = new DoubleSolenoid(2, 3);

    private static double openValue = 0;
    private static Boolean openAction;

    private static double clamp(double n, double min, double max) {
        return Math.max(Math.min(n, max), min);
    }

    public static double getOpen() {
        return openValue;
    }

    public static void setOpen(Boolean isOpen) {

        openAction = isOpen;
    }

    public static void incr(double interval) {
        Value solenoidValue;
        if (openAction != null) {
            interval *= openAction ? 1 : -1;
            solenoidValue = openAction ? Value.kForward : Value.kReverse;

            openValue += interval/OPEN_INTERVAL;
            openValue = clamp(openValue, 0, 1);
        } else {
            solenoidValue = Value.kOff;
        }

        solenoid.set(solenoidValue);

        if (openValue == 0 || openValue == 1) {
            openAction = null;
        }

    }
}