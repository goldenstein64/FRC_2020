package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.POVButton;

import frc.robot.subsystems.*;

/**
 * Interfaces between robot and joysticks
 */
public class Teleop {

    private static Joystick joystick = new Joystick(0);
    private static POVButton dPadRight = new POVButton(joystick, 90);
    private static POVButton dPadLeft = new POVButton(joystick, 270);
    private static POVButton dPadUp = new POVButton(joystick, 0);
    private static POVButton dPadDown = new POVButton(joystick, 180);
    private static JoystickButton[] buttons = new JoystickButton[13];

    private static boolean intensityDebounce = false;
    private static boolean invertDebounce = false;

    /**
     * called on robotInit to set up controller buttons
     */
    public static void init() {
        for (int i = 0; i < 13; i++) {
            buttons[i] = new JoystickButton(joystick, i);

        }
    }

    /**
     * Called per teleopPeriodic to handle controller input and delegate it to subsystems.
     */
    public static void handleInput() {

        { // Drive control logic
            double leftSpeed = joystick.getRawAxis(1); // left Y
            double rightSpeed = joystick.getRawAxis(3); // right Y
            Drive.smoothSet(leftSpeed, rightSpeed);

            boolean mode = buttons[9].get();
            if (mode && !invertDebounce) { // make the button only switch once until it is not pressed again
                Drive.switchInverted();
                invertDebounce = true;
            } else if (invertDebounce) {
                invertDebounce = false;
            }

            boolean intensity = buttons[10].get();
            if (intensity && !intensityDebounce) {
                intensityDebounce = true;
                // change intensity of things
            } else if (intensityDebounce) {
                intensityDebounce = false;
                // change intensity of things
            }
        }

        { // Conveyor control logic
            boolean rShoulderUp = buttons[8].get();
            boolean rShoulderDown = buttons[6].get();

            if (rShoulderUp && !rShoulderDown) {
                Conveyor.smoothSet(1);
            } else if (rShoulderDown && !rShoulderUp) {
                Conveyor.smoothSet(-1);
            } else {
                Conveyor.smoothSet(0);
            }
        }

        { // Elevator control logic
            boolean lShoulderUp = buttons[7].get();
            boolean lShoulderDown = buttons[5].get();

            if (lShoulderUp && !lShoulderDown) {
                Elevator.set(1);
            } else if (lShoulderDown && !lShoulderUp) {
                Elevator.set(-1);
            } else {
                Elevator.set(0);
            }
        }

        
        { // Winch control logic
            boolean dUp = dPadUp.get();
            boolean dDown = dPadDown.get();

            if (dDown && !dUp) {
                Winch.set(1); // retracts winch to grab
            } else if (dUp && !dDown) {
                Winch.set(-1); // extends winch out to reach
            } else {
                Winch.set(0);
            }

            /*
            boolean dLeft = dPadLeft.get();
            boolean dRight = dPadRight.get();
            if (dLeft && !dRight) {
                Winch.setLocked(true);

            } else {
                Winch.setLocked(false);
                // turn it off
            }
            // */
        }
        


        { // Gate control logic
            boolean buttonUp = buttons[4].get();
            boolean buttonDown = buttons[2].get();

            if (buttonUp && !buttonDown) {
                Gate.setOpen(true);
            } else if (buttonDown && !buttonUp) {
                Gate.setOpen(false);
            }
        }


    }
}