package com.company.foundation;

public abstract class Pathway {
    public enum PathwayType {
        Door,
        Wall,
        Portal
    }
    private Cell nextcell;
    private PathwayType type;

    protected void setType(PathwayType type) {
        this.type = type;
    }

    public PathwayType examine() {
        return type;
    }

    public void setCell(Cell cell) {
        nextcell = cell;
    }

    protected Cell getNextCell() {
        return nextcell;
    }

    public abstract Cell enter();
}
