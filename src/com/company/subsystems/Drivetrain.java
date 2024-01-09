package com.company.subsystems;

import com.company.Robot;
import com.company.foundation.Pathway.*;
import com.company.foundation.Robot.RobotBase.*;
import com.company.foundation.Robot.SubsystemBase;
import com.company.foundation.items.Key;

public class Drivetrain extends SubsystemBase {

    private Robot robot;
    private static Drivetrain instance;

    public static Drivetrain getInstance(Robot robot) {
        if (instance == null) {
            instance = new Drivetrain(robot);
        }
        return instance;
    }

    public static Drivetrain getInstance() {
        return instance;
    }

    public Drivetrain(Robot robot) {
        this.robot = robot;
    }

    public void drive(Direction direction) {
        robot.move(direction);
    }

    public void driveToPortal() {
        robot.move(Direction.East);
    }

    public void unlockDoorAndEnter(Direction direction, Key key) {
      robot.unlockDoor(direction, key);
    }

}
