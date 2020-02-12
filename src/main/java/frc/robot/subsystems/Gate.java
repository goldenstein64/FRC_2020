package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;

/**
 * Controls the gate in front of the conveyor
 */
public class Gate {

    private static DoubleSolenoid solenoid = new DoubleSolenoid(2, 3);

    public static void setOpen(boolean isOpen) {
        if (isOpen) {
            solenoid.set(DoubleSolenoid.Value.kForward);
            // open the thing
        } else {
            // close the thing
        }
    }
}