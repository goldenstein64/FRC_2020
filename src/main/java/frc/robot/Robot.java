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
 * - 1: Spark                         Elevator
 * - 2: Spark                         Winch
 * 
 * CAN Devices:                       
 * - 0: Power Distribution Panel
 * - 1: Talon SRX                     Drive (L)
 * - 2: Victor SPX                    Drive (L)
 * - 3: Talon SRX                     Drive (R)
 * - 4: Victor SPX                    Drive (R)
 * - 5: Spark MAX                     Conveyor
 * - 0: Pneumatic Control Module
 * 
 * PCM Solenoids:
 * - 1: Actuator                     Gate
 * - 2: Lock                         Winch
 */

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  
  private boolean autoInited = false;
  private boolean teleOpInited = false;

  public Compressor compressor = new Compressor(0);

  /**
   * This function is run when the robot is first started up and should be used
   * for any initialization code.
   */
  @Override
  public void robotInit() {
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
    //compressor.stop();
    if (!autoInited) {
      autoInited = true;
      Auto.init();
    }
  }

  @Override
  public void autonomousPeriodic() {
    Auto.execute();
  }

  @Override
  public void teleopInit() {
    compressor.stop();
    if (!teleOpInited) {
      teleOpInited = true;
      Teleop.init();
    }
    if (!autoInited) {
      autoInited = true;
      Auto.init();
    }
  }

  @Override
  public void teleopPeriodic() {
    Teleop.handleInput();
    Auto.executeTele();
  }

  @Override
  public void testInit() {
  }

  @Override
  public void testPeriodic() {
  }

}
