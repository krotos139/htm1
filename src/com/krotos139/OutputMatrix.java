package com.krotos139;

import java.util.HashMap;

public class OutputMatrix extends ISubZone {
    public float[] inputsActives;
    public INeuron[] inputs;

    public OutputMatrix(int count) {
        inputs = new INeuron[count];
        inputsActives = new float[count];
    }

    public void addInput(int i, INeuron in) {
        inputs[i] = in;
        inputsActives[i] = 0;
    }

    public void addInputs(SubZone zone) {
        if (zone.getColumns().length != inputs.length) {
            System.out.println("ERROR: addInputs invalid input");
            return;
        }
        for (int i=0; i<zone.getColumns().length; i++ ) {
            inputs[i] = zone.getColumns()[i];
            inputsActives[i] = 0;
        }

    }

    @Override
    public void inSignalActive(INeuron input) {
        for (int i=0 ; i<inputs.length ; i++) {
            if (inputs[i] == input) {
                inputsActives[i] = 1;
                break;
            }
        }
    }

    // DEBUG
    public void reset() {
        for (int i=0 ; i<inputs.length ; i++) {
            inputsActives[i] = 0;
        }
    }

}
