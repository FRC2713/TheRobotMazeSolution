package com.company.foundation.items;

import com.company.foundation.Item;

public class PortalGenerator extends Item {

    private PortalGeneratorPowerSupply A;
    private PortalGeneratorPowerSupply B;
    private PortalGeneratorPowerSupply C;

    public PortalGenerator() {
        setType(ItemType.PortalGenerator);
    }

    public boolean isPortalGeneratorPowered() {
        return A != null && B != null && C != null;
    }

    public void givePortalGeneratorPowerSupply(PortalGeneratorPowerSupply ps) {
        if (!ps.isPickedUp()) return;
        if (A == null) {
            A = ps;

        }
        if (B == null && A != ps) {
            B = ps;

        }
        if (C == null && A != ps && B != ps) {
            C = ps;

        }
    }
}
