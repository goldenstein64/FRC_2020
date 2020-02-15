/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.TimedRobot;
import frc.robot.subsystems.*;

/* 
 * PWM Devices:                       Subsystem:
 * - 0: Spark                         Winch
 * - 1: Spark                         Elevator
 * 
 * CAN Devices:                       
 * - 0: Power Distribution Panel
 * - 1: Pneumatic Control Module
 * - 2: Talon SRX                     Drive (L)
 * - 3: Talon SRX                     Drive (R)
 * - 4: Victor SPX                    Drive (L)
 * - 5: Victor SPX                    Drive (R)
 * - 6: Spark MAX Motor               Conveyor
 * 
 * PCM Solenoids:
 * - 0, 1: Lock                         Winch
 * - 2, 3: Actuator                     Gate
 */

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  
  public Compressor compressor = new Compressor();

  /**
   * This function is run when the robot is first started up and should be used
   * for any initialization code.
   */
  @Override
  public void robotInit() {
    compressor.start();
    Drive.init();
    Winch.init();
  }

  /**
   * This function is run every "period," around every 0.02 seconds?
   */
  @Override
  public void robotPeriodic() {
    Auto.updatePeriod();
  }

  @Override
  public void autonomousInit() {
    Auto.init();
  }

  @Override
  public void autonomousPeriodic() {
    Auto.execute();
  }

  @Override
  public void teleopInit() {
    TeleOp.init();
    // Auto.init();
  }

  @Override
  public void teleopPeriodic() {
    TeleOp.handleInput();
    Auto.executeTele();
  }

  @Override
  public void testInit() {
  }

  @Override
  public void testPeriodic() {
  }

}
