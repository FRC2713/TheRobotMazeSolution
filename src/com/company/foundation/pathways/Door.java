package com.company.foundation.pathways;

import com.company.foundation.Cell;
import com.company.foundation.Pathway;
import com.company.foundation.items.Key;

public class Door extends Pathway {

    private boolean isLocked;
    private Key.KeyType keyType;
    private boolean hasSecurityCamera;
    private boolean securityCameraActive;

    public Door(boolean hasOpenDoor) {
        setType(PathwayType.Door);
        hasSecurityCamera = Math.random() < 0.5;
        securityCameraActive = Math.random() < 0.5;
        isLocked = hasOpenDoor && Math.random() < 0.5;
        keyType = Key.KeyType.values()[(int)(Math.random() * 6)];
    }

    public boolean getIsLocked() {
        return isLocked;
    }

    public Key.KeyType getKeyType() {
        return keyType;
    }

    public boolean getHasSecurityCamera() {
        return hasSecurityCamera;
    }

    public boolean getSecurityCameraActive() {
        securityCameraActive = Math.random() < 0.5;
        return securityCameraActive;
    }

    public Cell enter(Key.KeyType key) {
        if (!isLocked || key == keyType) {
            isLocked = false;
            return enter();
        }
        System.out.println("Robot has failed to enter a door, door was locked, used wrong key.");
        return null;
    }

    @Override
    public Cell enter() {
        if (isLocked) {
            System.out.println("Robot has failed to enter a door, door was locked.");
            return null;
        }
        if (!hasSecurityCamera || !securityCameraActive) {
            System.out.println("Robot has entered a door.");
            return getNextCell();
        }
        System.out.println("Robot has failed to enter a door, was detected by a security camera.");
        return null;
    }
}
