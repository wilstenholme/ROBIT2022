/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.motorcontrol.PWMVictorSPX;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.motorcontrol.VictorSP;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private PWMVictorSPX leftfrontmotor;
  private PWMVictorSPX rightfrontmotor;
  private PWMVictorSPX leftbackmotor;
  private PWMVictorSPX rightbackmotor;

  MotorControllerGroup leftMotors_;
  MotorControllerGroup rightMotors_;
  SpeedControllerGroup leftmotors;
  SpeedControllerGroup rightmotors;
  DifferentialDrive drivebase;

  Joystick controller1;

  Timer autonomoustimer;

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    leftfrontmotor = new PWMVictorSPX(0);
    leftbackmotor = new PWMVictorSPX(1);
    rightfrontmotor = new PWMVictorSPX(2);
    rightbackmotor = new PWMVictorSPX(3);

    leftMotors_ = new MotorControllerGroup(leftfrontmotor, leftbackmotor);
    rightMotors_ = new MotorControllerGroup(rightfrontmotor, rightbackmotor);
    drivebase = new DifferentialDrive(leftMotors_, rightMotors_);
    // leftmotors = new SpeedControllerGroup(leftfrontmotor, leftbackmotor);
    // rightmotors = new SpeedControllerGroup(rightbackmotor, rightfrontmotor);
    // drivebase = new DifferentialDrive(leftmotors, rightmotors);
    controller1 = new Joystick(0);
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>
   * This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString line to get the auto name from the text box below the Gyro
   *
   * <p>
   * You can add additional auto modes by adding additional comparisons to
   * the switch structure below with additional strings. If using the
   * SendableChooser make sure to add them to the chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    autonomoustimer = new Timer();
    autonomoustimer.start();
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    if (autonomoustimer.get() < 2) { // less than 3 seconds
      drivebase.arcadeDrive(0.5, 0);
    }

    if ((autonomoustimer.get() >= 2) && (autonomoustimer.get() < 4)) {
      drivebase.arcadeDrive(0, 0);
    }

    if ((autonomoustimer.get() >= 4) && (autonomoustimer.get() < 5)) {
      drivebase.arcadeDrive(0, 0);
    }

    if (autonomoustimer.get() > 5) {
      drivebase.arcadeDrive(0, 0);
    }

  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    drivebase.arcadeDrive(controller1.getRawAxis(4), -controller1.getRawAxis(1), true);

  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
