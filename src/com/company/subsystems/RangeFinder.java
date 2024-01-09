package com.company.subsystems;

import com.company.Robot;
import com.company.foundation.Item.*;
import com.company.foundation.Robot.SubsystemBase;

public class RangeFinder extends SubsystemBase {

    private Robot robot;
    private static RangeFinder instance;

    public static RangeFinder getInstance(Robot robot) {
        if (instance == null) {
            instance = new RangeFinder(robot);
        }
        return instance;
    }

    public static RangeFinder getInstance() {
        return instance;
    }

    public RangeFinder(Robot robot) {
        this.robot = robot;
    }

    public double findRange(ItemType item) {
        return robot.detectItems(item);
    }

    public double findRange() {
        return robot.detectPortal();
    }

}
