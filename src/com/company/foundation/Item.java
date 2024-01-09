package com.company.foundation;

public abstract class Item {
    public enum ItemType {
        Key,
        PortalGenerator,
        PortalGeneratorPowerSupply,
        Rock
    }
    private Cell cell;
    private ItemType type;

    protected void setType(ItemType type) {
        this.type = type;
    }

    //only use this method directly
    public ItemType analyze() {
        return type;
    }

    public boolean isPickedUp() {
        return cell == null;
    }

    public Cell getCell() {
        return cell;
    }

    public void pickup() {
        if (cell == null) return;
        cell = null;
        if (type != ItemType.Key) {
            System.out.println("Robot has picked up a " + type + ".");
        } else {
            System.out.println("Robot has picked up the " + getKeyTypeString() + " key.");
        }
    }

    protected String getKeyTypeString() {
        return "";
    }

    public void dropoff(Cell cell) {
        if (this.cell != null) return;
        this.cell = cell;
        this.cell.addItem(this);
    }
}
