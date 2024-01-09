package com.company.foundation.items;

import com.company.foundation.Item;

public class Key extends Item {
    public enum KeyType {
        Red,
        Green,
        Blue,
        Yellow,
        Orange,
        Purple
    }

    private KeyType type;

    public Key() {
        setType(ItemType.Key);
        type = KeyType.values()[(int)(Math.random() * 6)];
    }

    public void setKeyType(KeyType type) {
        this.type = type;
    }

    public KeyType analyzeKey() {
        return type;
    }

    protected String getKeyTypeString() {
        return type.toString();
    }
}
