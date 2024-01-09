package com.company.foundation;

import com.company.foundation.items.Key;
import com.company.foundation.items.PortalGenerator;
import com.company.foundation.items.PortalGeneratorPowerSupply;
import com.company.foundation.items.Rock;
import com.company.foundation.pathways.Door;
import com.company.foundation.pathways.Portal;
import com.company.foundation.pathways.Wall;

import java.util.ArrayList;

public class Maze {

    private Cell firstcell;
    private Cell cell_with_exit_portal;
    private ArrayList<Key> keys = new_Keys();
    private ArrayList<PortalGenerator> generators = new_PortalGenerators();
    private ArrayList<PortalGeneratorPowerSupply> powerSupplies = new_PortalGeneratorPowerSupply();

    private static int keys_index = 0;
    private static int rock_index = 0;
    private static int generators_index = 0;
    private static int powerSupplies_index = 0;


    private ArrayList<Key> new_Keys() {
        ArrayList<Key> keys = new ArrayList<>(6);
        for (int i = 0; i < 6; i++) {
            keys.add(new Key());
        }
        keys.get(0).setKeyType(Key.KeyType.Red);
        keys.get(1).setKeyType(Key.KeyType.Green);
        keys.get(2).setKeyType(Key.KeyType.Blue);
        keys.get(3).setKeyType(Key.KeyType.Yellow);
        keys.get(4).setKeyType(Key.KeyType.Orange);
        keys.get(5).setKeyType(Key.KeyType.Purple);
        return keys;
    }

    private ArrayList<PortalGenerator> new_PortalGenerators() {
        ArrayList<PortalGenerator> generators = new ArrayList<>(5);
        for (int i = 0; i < 5; i++) {
            generators.add(new PortalGenerator());
        }
        return generators;
    }

    private ArrayList<PortalGeneratorPowerSupply> new_PortalGeneratorPowerSupply() {
        ArrayList<PortalGeneratorPowerSupply> ps = new ArrayList<>(15);
        for (int i = 0; i < 15; i++) {
            ps.add(new PortalGeneratorPowerSupply());
        }
        return ps;
    }

    private Item getItemForCell() {
        Item item = null;
        switch ((int) (Math.random() * 6)) {
            case 0:
                if (keys_index < 6) {
                    item = keys.get(keys_index);
                    keys_index++;
                }
                break;
            case 1:
                if (generators_index < 5) {
                    item = generators.get(generators_index);
                    generators_index++;
                }
                break;
            case 2:
                if (powerSupplies_index < 15) {
                    item = powerSupplies.get(powerSupplies_index);
                    powerSupplies_index++;
                }
                break;
            case 3:
            case 4:
            case 5:
                item = new Rock();
                rock_index++;
               break;
        }

        return item;
    }

    private void itemize() {
        Cell row ;
        Cell cell;
        Item item;
        while (keys_index < 6
                || generators_index < 5
                || powerSupplies_index < 15
                || rock_index < 500) {
            row = firstcell;
            cell = firstcell;
            while (cell != null) {
                item = getItemForCell();
                if (item != null) item.dropoff(cell);
                if (cell.getEast().getNextCell() != null) {
                    cell = cell.getEast().getNextCell();
                } else {
                    cell = row.getSouth().getNextCell();
                    row = row.getSouth().getNextCell();
                }
            }
        }
    }

    private double getRange(Cell cell1, Cell cell2) {
        if (cell1 == null || cell2 == null) return Double.MAX_VALUE;
        return Math.sqrt(Math.pow(cell2.getX() - cell1.getX(),2)
                + Math.pow(cell2.getY() - cell1.getY(),2));
    }

    private double findPortal(Cell cell) {
        return getRange(cell,cell_with_exit_portal);
    }

    private double findKeys(Cell cell) {
        double distance = 0;
        double range = -1;
        for (Key key : keys) {
            distance = getRange(cell,key.getCell());
            if (range == -1 || distance < range) range = distance;
        }
        return range;
    }

    private double findGenerators(Cell cell) {
        double distance = 0;
        double range = -1;
        for (PortalGenerator generator : generators) {
            distance = getRange(cell,generator.getCell());
            if (range == -1 || distance < range) range = distance;
        }
        return range;
    }

    private double findPowerSupplies(Cell cell) {
        double distance = 0;
        double range = -1;
        for (PortalGeneratorPowerSupply ps : powerSupplies) {
          distance = getRange(cell,ps.getCell());
          if (range == -1 || distance < range) range = distance;
        }
        return range;
    }

    public double findRange(Cell cell, Item.ItemType item) {
        switch (item) {
            case Key:
                return findKeys(cell);
            case PortalGenerator:
                return findGenerators(cell);
            case PortalGeneratorPowerSupply:
                return findPowerSupplies(cell);
        }
        return findRange(cell);
    }

    public double findRange(Cell cell) {
        return findPortal(cell);
    }

    public Cell findCell() {
        return findCell((int) (Math.random() * 8),(int) (Math.random() * 8));
    }

    public Cell findCell(int x, int y) {
      Cell cell = firstcell;
        for (int i = 0; i < x; i++) {
            if (cell != null) cell = cell.getEast().getNextCell();
        }
        for (int i = 0; i < y; i++) {
            if (cell != null) cell = cell.getSouth().getNextCell();
        }
        if (cell == null) return firstcell;
        return cell;
    }

    private Pathway getPathway(boolean rowHasOpenDoor) {
        switch ((int) (Math.random() * 3)) {
            case 0:
                return new Door(rowHasOpenDoor);
            case 1:
                return (rowHasOpenDoor) ? new Wall() : new Door(false);
            case 2:
                if (cell_with_exit_portal == null) {
                    return new Portal();
                }
        }
        return new Door(false);
    }

    public void generateMaze() {
        int y = 0;
        int x;

        boolean rowHasDoor;

        Cell first_cell_in_row = null;
        Cell last_row = null;
        Cell last_cell;
        Cell new_cell;
        Pathway path;

        while (y < 8) {
            x = 0;
            last_cell = null;
            rowHasDoor = false;
            while (x < 8) {
                new_cell = new Cell(x, y);

                if (x == 0 && y == 0) {
                    firstcell = new_cell;
                }

                if (x == 0) {
                    first_cell_in_row = new_cell;
                }

                if (x < 7) {
                    path = getPathway(rowHasDoor);
                    rowHasDoor = path.examine() == Pathway.PathwayType.Door && !((Door)path).getIsLocked();
                    if (path.examine() == Pathway.PathwayType.Portal) cell_with_exit_portal = new_cell;
                    path.setCell(null);
                    new_cell.setEast(path);
                } else {
                    path = new Wall();
                    new_cell.setEast(path);
                }

                if (last_cell != null) {
                    last_cell.getEast().setCell(new_cell);

                    path = new Door(true);
                    path.setCell(last_cell);
                    new_cell.setWest(path);
                } else {
                    path = new Wall();
                    new_cell.setWest(path);
                }

                if (last_row != null) {
                    path = new Door(false);
                    path.setCell(last_row);
                    new_cell.setNorth(path);

                    path = new Door(false);
                    path.setCell(new_cell);
                    last_row.setSouth(path);
                    last_row = last_row.getEast().getNextCell();
                } else {
                    path = new Wall();
                    new_cell.setNorth(path);
                }

                if (y == 7) {
                    path = new Wall();
                    new_cell.setSouth(path);
                }

                if (x == 7) {
                    last_row = first_cell_in_row;
                }

                last_cell = new_cell;
                x++;
            }
            y++;
        }

        itemize();
    }
}
