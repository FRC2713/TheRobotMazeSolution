package com.company.foundation.pathways;

import com.company.foundation.Cell;
import com.company.foundation.Pathway;
import com.company.foundation.items.PortalGenerator;

public class Portal extends Pathway {

    private PortalGenerator A;
    private PortalGenerator B;
    private PortalGenerator C;
    private PortalGenerator D;
    private PortalGenerator E;

    private int percent = 0;

    public Portal() {
        setType(PathwayType.Portal);
    }

    public boolean isPortalGenerated() {
        return A != null && B != null && C != null && D != null && E != null
                && A.isPortalGeneratorPowered()
                && B.isPortalGeneratorPowered()
                && C.isPortalGeneratorPowered()
                && D.isPortalGeneratorPowered()
                && E.isPortalGeneratorPowered();
    }

    public void givePortalGenerator(PortalGenerator generator) {
        if (!generator.isPickedUp()) return;
        if (A == null) {
            A = generator;
            percent += 20;
        }
        if (B == null && A != generator) {
            B = generator;
            percent += 20;
        }
        if (C == null && A != generator && B != generator) {
            C = generator;
            percent += 20;
        }
        if (D == null && A != generator && B != generator && C != generator) {
            D = generator;
            percent += 20;
        }
        if (E == null && A != generator && B != generator && C != generator && D != generator) {
            percent += 20;
            E = generator;
        }
        System.out.println("Exit portal is " + percent + "% generated.");
    }

    @Override
    public Cell enter() {
        if (isPortalGenerated()) {
            System.out.println("Robot has entered the exit portal, and found its way out of the maze.");
            System.out.println("Congratulations!!!!!");
            System.exit(0);
        }
        System.out.println("Robot has failed to enter the exit portal, portal has not yet been generated.");
        return null;
    }
}
