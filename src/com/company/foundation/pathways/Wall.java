package com.company.foundation.pathways;

import com.company.foundation.Cell;
import com.company.foundation.Pathway;

public class Wall extends Pathway {
    public Wall() {
        setType(PathwayType.Wall);
    }
    @Override
    public Cell enter() {
        System.out.println("Robot has failed to detect a wall, and has run into a wall.");
        return null;
    }
}
