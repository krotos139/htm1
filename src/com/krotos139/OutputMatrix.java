package com.krotos139;

import java.util.HashMap;
import java.util.LinkedList;

public class OutputMatrix extends ISubZone {
    public float[] inputsActives;
    public INeuron[] inputs;
    private LinkedList<ISubZone> downSubZones;

    public OutputMatrix(int count) {
        inputs = new INeuron[count];
        inputsActives = new float[count];
        downSubZones = new LinkedList<>();
    }

    public void setDownSubZones(ISubZone zone) {
        downSubZones.push(zone);
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

    public void outSignalForecast(int column) {
        outSignalForecast(inputs[column]);
    }

    public void outSignalForecast(INeuron out) {
        for (ISubZone sz : downSubZones) {
            sz.inSignalForecast(out);
        }
    }

    public void outSignalMotor(int column) {
        outSignalMotor(inputs[column]);
    }

    public void outSignalMotor(INeuron out) {
        for (ISubZone sz : downSubZones) {
            sz.inSignalMotor(out);
        }
    }

    // DEBUG
    public void reset() {
        for (int i=0 ; i<inputs.length ; i++) {
            inputsActives[i] = 0;
        }
    }

}
