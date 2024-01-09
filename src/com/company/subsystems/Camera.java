package com.company.subsystems;

import com.company.Robot;
import com.company.foundation.Pathway.*;
import com.company.foundation.Robot.RobotBase.*;
import com.company.foundation.Robot.SubsystemBase;
import com.company.foundation.items.Key.*;

public class Camera extends SubsystemBase {

    private Robot robot;
    private static Camera instance;

    public static Camera getInstance(Robot robot) {
        if (instance == null) {
            instance = new Camera(robot);
        }
        return instance;
    }

    public static Camera getInstance() {
        return instance;
    }

    public Camera(Robot robot) {
        this.robot = robot;
    }

    public boolean isPortalGenerated() {
        return robot.isPortalGenerated(Direction.East);
    }

    public boolean isDoorLocked(Direction direction) {
        return robot.isDoorLocked(direction);
    }

    public KeyType getKeyTypeForDoor(Direction direction) {
        return robot.getKeyType(direction);
    }

    public boolean isSecurityCameraActive(Direction direction) {
        return robot.isSecurityCameraActive(direction);
    }

    public PathwayType detectPath(Direction direction) {
        return robot.examine(direction);
    }
}
