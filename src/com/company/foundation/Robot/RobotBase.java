package com.company.foundation.Robot;

import com.company.foundation.Cell;
import com.company.foundation.Item;
import com.company.foundation.Item.*;
import com.company.foundation.Maze;
import com.company.foundation.Pathway;
import com.company.foundation.items.Key;
import com.company.foundation.items.PortalGenerator;
import com.company.foundation.pathways.Door;
import com.company.foundation.pathways.Portal;

import java.util.ArrayList;
import java.util.function.Supplier;

public abstract class RobotBase {
    private Maze maze;
    private long delay;
    private Cell current_cell;
    private final ArrayList<CommandBase> commandList = new ArrayList<>();

    public abstract void robotInit();

    public RobotBase() {
        runRobot();
    }

    public static void startRobot(Supplier<?> robot) {
      robot.get();
    }

    private void startMaze() {
        maze = new Maze();
        maze.generateMaze();
        current_cell = maze.findCell();
    }

    public void setDelay(double seconds) {
        delay = (long) (seconds * 1000);
    }

    public void runRobot() {
        startMaze();
        robotInit();
        while (true) {
            runCommands();
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void runCommands() {
        for (CommandBase command : commandList) {
            command.doinitialize();
            command.doExecute();
            if (command.getIsFinished()) {
                command.doEnd();
            }
        }
    }

    public void launchCommand(CommandBase command) {
        commandList.add(command);
    }
    public enum Direction {
        North,
        South,
        East,
        West
    }

    private Pathway getPathway(Direction direction) {
        switch (direction) {
            case North:
                return current_cell.getNorth();
            case South:
                return current_cell.getSouth();
            case East:
                return current_cell.getEast();
            case West:
                return current_cell.getWest();
        }
        return current_cell.getNorth();
    }

    private Door getDoorway(Direction direction) {
        Pathway pathway = getPathway(direction);
        if (pathway.examine() == Pathway.PathwayType.Door)
            return (Door) pathway;
        return null;
    }

    private Portal getPortal(Direction direction) {
        Pathway pathway = getPathway(direction);
        if (pathway.examine() == Pathway.PathwayType.Portal)
            return (Portal) pathway;
        return null;
    }

    public void move(Direction direction) {
        Cell cell = getPathway(direction).enter();
        if (cell == null) return;
        current_cell = cell;
        System.out.println("Robot has moved: " + direction + ".");
        System.out.println("Welcome to grid cell x:" + current_cell.getX() + " and y:" + current_cell.getY()+ ".");
        System.out.println("New cell has a " + getPathway(Direction.North).examine() + " in the north.");
        System.out.println("New cell has a " + getPathway(Direction.East).examine() + " in the east.");
        System.out.println("New cell has a " + getPathway(Direction.South).examine() + " in the south.");
        System.out.println("New cell has a " + getPathway(Direction.West).examine() + " in the west.");
    }

    public void generatePortal(Direction direction, PortalGenerator generator) {
        Portal portal = getPortal(direction);
        if (portal != null) {
            portal.givePortalGenerator(generator);
        }
    }

    public boolean isPortalGenerated(Direction direction) {
        Portal portal = getPortal(direction);
        if (portal != null) {
            return portal.isPortalGenerated();
        }
        return false;
    }

    public void unlockDoor(Direction direction, Key key) {
        Door door = getDoorway(direction);
        if (door != null) {
            Cell cell = door.enter(key.analyzeKey());
            if (cell == null) return;
            current_cell = cell;
            System.out.println("Robot has moved: " + direction + ".");
            System.out.println("Welcome to grid cell x:" + current_cell.getX() + " and y:" + current_cell.getY()+ ".");
            System.out.println("New cell has a " + getPathway(Direction.North).examine() + " in the north.");
            System.out.println("New cell has a " + getPathway(Direction.East).examine() + " in the east.");
            System.out.println("New cell has a " + getPathway(Direction.South).examine() + " in the south.");
            System.out.println("New cell has a " + getPathway(Direction.West).examine() + " in the west.");
        }
    }

    public Key.KeyType getKeyType(Direction direction) {
        Door door = getDoorway(direction);
        if (door != null) return door.getKeyType();
        return Key.KeyType.Red;
    }

    public boolean isDoorLocked(Direction direction) {
        Door door = getDoorway(direction);
        if (door != null) return door.getIsLocked();
        return false;
    }

    public boolean isSecurityCameraActive(Direction direction) {
        Door door = getDoorway(direction);
        if (door != null) return door.getSecurityCameraActive() && door.getHasSecurityCamera();
        return false;
    }

    public Pathway.PathwayType examine(Direction direction) {
        return getPathway(direction).examine();
    }

    public ArrayList<Item> getItems() {
        return current_cell.getItems();
    }

    public double detectItems(ItemType item) {
        return maze.findRange(current_cell, item);
    }

    public double detectPortal() {
        return maze.findRange(current_cell);
    }

}
