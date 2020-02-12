/*
Interfaces between robot and joysticks
*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton; // somehow this is valid?

import frc.robot.subsystems.*;

public class Interop {

    private static Joystick joystick = new Joystick(0);
    private static JoystickButton[] buttons;

    public static void init() {
        for (int i = 0; i < 12; i++) {
            buttons[i] = new JoystickButton(joystick, i);
        }
    }

    public static void handleInput() {
        double leftSpeed = joystick.getRawAxis(1); // left Y
        double rightSpeed = joystick.getRawAxis(3); // right Y

        Drive.tankDrive(leftSpeed, rightSpeed);
    }
}