package com.company;

import com.company.foundation.Robot.RobotBase;

public class Robot extends RobotBase {
    @Override
    public void robotInit() {
      new RobotContainer(this);
    }
}
