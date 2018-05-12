package com.krotos139;

import java.util.HashMap;

public class OutputMatrix extends ISubZone {
    public Synaps[] inputs;

    public OutputMatrix(int count) {
        inputs = new Synaps[count];
    }

    public void addInput(int i, INeuron in) {
        inputs[i] = new Synaps(in, 1.0f);
    }

    public void addInputs(SubZone zone) {
        if (zone.getColumns().length != inputs.length) {
            System.out.println("ERROR: addInputs invalid input");
            return;
        }
        for (int i=0; i<zone.getColumns().length; i++ ) {
            inputs[i] = new Synaps(zone.getColumns()[i], 1.0f);
        }

    }

    @Override
    public void inSignal(InputSignal signal) {
        if (signal.type == SignalType.Forecast) {
            System.out.println("ERROR OutputMatrix receive Forecast Signal");
        } else {
            for (int i=0 ; i<inputs.length ; i++) {
                inputs[i].onSignal(signal);
            }
        }
    }

    // DEBUG
    public void reset() {
        for (int i=0 ; i<inputs.length ; i++) {
            inputs[i].reset();
        }
    }

}
