package com.krotos139;

import java.util.ArrayList;

public class Neuron implements INeuron {
    private Column column;
    private ArrayList<Synaps> synaps;
    private float threshold = 0.8f;
    public float active;

    public Neuron(Column column) {
        this.synaps = new ArrayList<Synaps>();
        this.column = column;
    }
    public void analyse() {
        float active =0;
        for (int i=0; i<synaps.size(); i++) {
            active += synaps.get(i).getActive();
        }
        if (active > threshold) {
            onActive();
        }
    }
    private void onActive() {

    }
}
