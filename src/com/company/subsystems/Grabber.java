package com.company.subsystems;

import com.company.Robot;
import com.company.foundation.Item;
import com.company.foundation.Robot.RobotBase.*;
import com.company.foundation.Robot.SubsystemBase;
import com.company.foundation.items.Key;
import com.company.foundation.items.PortalGenerator;
import com.company.foundation.items.PortalGeneratorPowerSupply;

import java.util.ArrayList;

public class Grabber extends SubsystemBase {

    private Robot robot;
    private static Grabber instance;

    private ArrayList<Key> keys = new ArrayList<>();
    private ArrayList<PortalGenerator> generators = new ArrayList<>();
    private ArrayList<PortalGeneratorPowerSupply> powerSupplies = new ArrayList<>();

    public static Grabber getInstance(Robot robot) {
        if (instance == null) {
            instance = new Grabber(robot);
        }
        return instance;
    }

    public static Grabber getInstance() {
        return instance;
    }

    public Grabber(Robot robot) {
        this.robot = robot;
    }

    public ArrayList<Item> grabItems() {
        return robot.getItems();
    }

    public void grabItem(Item item) {
        if (item == null) return;
        if (item.isPickedUp()) return;
        switch (item.analyze()) {
            case Rock:
                item.pickup();
                break;
            case Key:
                item.pickup();
                keys.add((Key) item);
                break;
            case PortalGenerator:
                item.pickup();
                generators.add((PortalGenerator) item);
                break;
            case PortalGeneratorPowerSupply:
                item.pickup();
                powerSupplies.add((PortalGeneratorPowerSupply) item);
                break;
        }
    }

    public boolean areAllPortalGeneratorsPowered() {
        if (generators.isEmpty()) return false;
        for (PortalGenerator generator : generators) {
            if (!generator.isPortalGeneratorPowered()) return false;
        }
        return true;
    }

    public void usePortalGeneratorToGeneratePortal(PortalGenerator generator) {
        robot.generatePortal(Direction.East,generator);
    }

    public void giveAllPortalGeneratorsPowerSupplies() {
        int index = 0;

        for (PortalGenerator generator : generators) {
            generator.givePortalGeneratorPowerSupply(getPowerSupply(index));
            generator.givePortalGeneratorPowerSupply(getPowerSupply(index+1));
            generator.givePortalGeneratorPowerSupply(getPowerSupply(index+2));
            index += 3;
        }
    }

    public boolean hasAllTheKeys() {
        return keys.size() == 6;
    }

    public boolean hasAllThePowerSupplies() {
        return powerSupplies.size() == 15;
    }

    public boolean hasAllTheGenerators() {
        return generators.size() == 5;
    }

    public Key getKey(int index) {
        return keys.get(index);
    }

    public PortalGenerator getGenerator(int index) {
        return generators.get(index);
    }

    public PortalGeneratorPowerSupply getPowerSupply(int index) {
        return powerSupplies.get(index);
    }

    public ArrayList<Key> getKeys() {
        return keys;
    }

    public ArrayList<PortalGenerator> getGenerators() {
        return generators;
    }

    public ArrayList<PortalGeneratorPowerSupply> getPowerSupplies() {
        return powerSupplies;
    }

}
