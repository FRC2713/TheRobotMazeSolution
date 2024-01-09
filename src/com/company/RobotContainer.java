package com.company;

import com.company.commands.Go;
import com.company.foundation.Item.*;
import com.company.foundation.Robot.*;
import com.company.subsystems.Camera;
import com.company.subsystems.Drivetrain;
import com.company.subsystems.Grabber;
import com.company.subsystems.RangeFinder;

public class RobotContainer {

    public RobotContainer(Robot robot) {

        Camera camera = Camera.getInstance(robot);
        Drivetrain drivetrain = Drivetrain.getInstance(robot);
        Grabber grabber = Grabber.getInstance(robot);
        RangeFinder rangeFinder = RangeFinder.getInstance(robot);

        robot.setDelay(0);

        robot.launchCommand(new Go());

        robot.launchCommand(new WhenCommand(() -> rangeFinder.findRange(ItemType.Key) == 0,
                new MiniCommand(() -> grabber.grabItems().forEach(
                        (item) -> grabber.grabItem((item.analyze() == ItemType.Key) ? item : null)
                ))));

        robot.launchCommand(new WhenCommand(() -> grabber.hasAllTheKeys() &&
                rangeFinder.findRange(ItemType.PortalGeneratorPowerSupply) == 0,
                new MiniCommand(() -> grabber.grabItems().forEach(
                        (item) -> grabber.grabItem((item.analyze() == ItemType.PortalGeneratorPowerSupply) ? item : null)
                ))));

        robot.launchCommand(new WhenCommand(() -> grabber.hasAllThePowerSupplies() &&
                rangeFinder.findRange(ItemType.PortalGenerator) == 0,
                new MiniCommand(() -> grabber.grabItems().forEach(
                        (item) -> grabber.grabItem((item.analyze() == ItemType.PortalGenerator) ? item : null)
                ))));

        robot.launchCommand(new WhenCommand(grabber::hasAllTheGenerators,
                new MiniCommand(grabber::giveAllPortalGeneratorsPowerSupplies)));

        robot.launchCommand(new WhenCommand(() -> grabber.areAllPortalGeneratorsPowered() &&
                rangeFinder.findRange() == 0,
                new ParallelCommandGroup(
                        new MiniCommand(() -> grabber.usePortalGeneratorToGeneratePortal(grabber.getGenerator(0))),
                        new MiniCommand(() -> grabber.usePortalGeneratorToGeneratePortal(grabber.getGenerator(1))),
                        new MiniCommand(() -> grabber.usePortalGeneratorToGeneratePortal(grabber.getGenerator(2))),
                        new MiniCommand(() -> grabber.usePortalGeneratorToGeneratePortal(grabber.getGenerator(3))),
                        new MiniCommand(() -> grabber.usePortalGeneratorToGeneratePortal(grabber.getGenerator(4))),
                        new WhenCommand(camera::isPortalGenerated, new MiniCommand(drivetrain::driveToPortal))
                )));
    }
}
