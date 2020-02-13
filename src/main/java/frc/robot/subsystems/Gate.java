package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import frc.robot.Robot;

/**
 * Controls the gate in front of the conveyor
 */
public class Gate {

    private static DoubleSolenoid solenoid = new DoubleSolenoid(2, 3);

    public static void setOpen(Boolean isOpen) {
        if (isOpen == true) {
            solenoid.set(DoubleSolenoid.Value.kForward);
            // open the thing
        } else if (isOpen == false) {
            solenoid.set(DoubleSolenoid.Value.kReverse);
            // close the thing
        } else if (isOpen == null) {
            solenoid.set(DoubleSolenoid.Value.kOff);
        }
    }
}