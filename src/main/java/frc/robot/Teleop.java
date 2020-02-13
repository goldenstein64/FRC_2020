/*
Interfaces between robot and joysticks
*/



package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.POVButton;

import frc.robot.subsystems.*;

public class Teleop {

    private static Joystick joystick = new Joystick(0);
    private static POVButton dPadRight = new POVButton(joystick, 0);
    private static POVButton dPadLeft = new POVButton(joystick, 180);
    private static POVButton dPadUp = new POVButton(joystick, 90);
    private static POVButton dPadDown = new POVButton(joystick, 270);
    private static  JoystickButton[] buttons = new JoystickButton[13];
    
    public static void init() {
        for (int i = 0; i < 13; i++) {
            buttons[i] = new JoystickButton(joystick, i);

        }
    }

    public static void handleInput() {
        double leftSpeed = joystick.getRawAxis(1); // left Y
        double rightSpeed = joystick.getRawAxis(3); // right Y

        {
            boolean dUp = dPadUp.get();
            boolean dDown = dPadDown.get();

            if (dUp && !dDown) {
                Conveyor.smoothSet(1);
            } else if (dDown && !dUp) {
                Conveyor.smoothSet(-1);
            } else {
                Conveyor.smoothSet(0);
            }
        }

        
        if (buttons[1].get()) {
            
        }

        Drive.tankDrive(leftSpeed, rightSpeed);
    }
}