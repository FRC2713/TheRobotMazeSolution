package com.company.foundation;

import java.util.ArrayList;

public class Cell {

    private int x;
    private int y;

    private Pathway north;
    private Pathway south;
    private Pathway east;
    private Pathway west;

    private ArrayList<Item> items = new ArrayList<>();

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Pathway getNorth() {
        return north;
    }

    public void setNorth(Pathway north) {
        this.north = north;
    }

    public Pathway getSouth() {
        return south;
    }

    public void setSouth(Pathway south) {
        this.south = south;
    }

    public Pathway getEast() {
        return east;
    }

    public void setEast(Pathway east) {
        this.east = east;
    }

    public Pathway getWest() {
        return west;
    }

    public void setWest(Pathway west) {
        this.west = west;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    public ArrayList<Item> getItems() {
        return items;
    }
}
