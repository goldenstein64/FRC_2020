/*
class that controls the gate in front of the conveyor
*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;

public class Gate {

    private static DoubleSolenoid solenoid = new DoubleSolenoid(1, 2);

    public static void setOpen(boolean isOpen) {

    }
}