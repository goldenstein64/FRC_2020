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
 * PWM Devices:
 * - 0: Spark Winch
 * - 1: Spark Elevator
 * 
 * CAN Devices:
 * - 0: PDP
 * - 1: PCM
 * - 2: Talon SRX Drive Left
 * - 3: Talon SRX Drive Right
 * - 4: Victor SPX Drive Left
 * - 5: Victor SPX Drive Right
 * - 6: Spark MAX Motor Intake
 * - 7: Spark MAX Motor Conveyor
 * 
 * PCM Solenoids:
 * - 01: Gate
 * - 23: Winch Lock
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

  @Override
  public void robotInit() {
    Drive.init();
  }

  @Override
  public void autonomousInit() {
  }

  @Override
  public void autonomousPeriodic() {
  }

  @Override
  public void teleopInit() {
    Interop.init();
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
