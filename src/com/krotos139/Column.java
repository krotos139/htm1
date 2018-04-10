package com.krotos139;

import java.util.ArrayList;
import java.util.Iterator;

public class Column {
    private ArrayList<Neuron> neurons;
    private byte active;
    private SubZone zone;
    public Column(SubZone zone) {
        this.zone = zone;
        this.neurons = new ArrayList<Neuron>();
        active = (byte) -1;
    }
    public void analyse() {
        if (neurons.size() == 0) return;
        if (active == -1) {
            neurons.get(0).analyse();
        }
    }
    public void teach() {
        if (neurons.size() == 0) {
            zone.activeColumn = this;
            neurons.add(new Neuron(this));
            active = 0;
        }
    }
    // Debug
    public void setNeurons() {

    }
}
