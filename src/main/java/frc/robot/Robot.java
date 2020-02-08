/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import frc.robot.subsystems.*;

/*
 *
 * Assumed PWM ID's:
 * 1 = Spark                        Winch
 * 2 = Spark                        Elevator
 * 
 * Assumed CAN ID's:
 * 
 * 0 = Power Distribution Panel
 * 1 = Left Talon SRX               Drive
 * 2 = Left Victor SPX              Drive
 * 3 = Right Talon SRX              Drive
 * 4 = Right Victor SPX             Drive
 * 5 = Spark Max                    Intake
 * 6 = Spark Max                    Winch
 * 
 * Assumed PCM ID's:
 * 
 * 1, 2 = DoubleSolenoid            Gate
 * 3, 4 = DoubleSolenoid            Winch
 * 
 */

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  /**
   * This function is run when the robot is first started up and should be used
   * for any initialization code.
   */

  public static Class<Drive> Drive = Drive.class;
  public static Class<Intake> Intake = Intake.class;
  public static Class<Conveyor> Conveyor = Conveyor.class;
  public static Class<Gate> Gate = Gate.class;
  public static Class<Winch> Winch = Winch.class;
  public static Class<Elevator> Elevator = Elevator.class;

  @Override
  public void robotInit() {
  }

  @Override
  public void autonomousInit() {
  }

  @Override
  public void autonomousPeriodic() {
  }

  @Override
  public void teleopInit() {
  }

  @Override
  public void teleopPeriodic() {
  }

  @Override
  public void testInit() {
  }

  @Override
  public void testPeriodic() {
  }

}
