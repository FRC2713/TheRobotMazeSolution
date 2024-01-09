package com.company.commands;

import com.company.foundation.Pathway.*;
import com.company.foundation.Robot.CommandBase;
import com.company.foundation.Robot.RobotBase.*;
import com.company.foundation.items.Key;
import com.company.subsystems.Camera;
import com.company.subsystems.Drivetrain;
import com.company.subsystems.Grabber;

public class Go extends CommandBase {

    Drivetrain drivetrain = Drivetrain.getInstance();
    Camera camera = Camera.getInstance();
    Grabber grabber = Grabber.getInstance();

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        Direction direction = Direction.values()[(int) (Math.random() * 4)];
        PathwayType path = camera.detectPath(direction);
        if (path == PathwayType.Door) {
            if (!camera.isSecurityCameraActive(direction)) {
                if (!camera.isDoorLocked(direction)) {
                    drivetrain.drive(direction);
                } else {
                    for (Key key : grabber.getKeys()) {
                        if (key.analyzeKey() == camera.getKeyTypeForDoor(direction)) {
                            drivetrain.unlockDoorAndEnter(direction, key);
                            break;
                        }
                    }
                }
            }
        }
    }

    @Override
    public void end() {
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
