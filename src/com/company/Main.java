package com.company;

import com.company.foundation.Robot.RobotBase;

public class Main {

    public static void main(String[] args) {
        RobotBase.startRobot(Robot::new);
    }
}
