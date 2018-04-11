package com.krotos139;

import java.util.ArrayList;

public class Column extends INeuron {
    private ArrayList<Neuron> neurons;
    private ArrayList<Neuron> enableNeurons;
    private SubZone zone;
    public Column(SubZone zone) {
        this.zone = zone;
        this.neurons = new ArrayList<Neuron>();
        this.enableNeurons = new ArrayList<Neuron>();
        active = 0.0f;
    }
    private void getEnableNeurons() {

    }
    private void addEnableNeurons(Neuron n) {
        if (!enableNeurons.contains(n)) {
            enableNeurons.add(n);
        }
    }
    public void analyse() {
        active = 0.0f;
        if (neurons.size() == 0) return;
        getEnableNeurons();
        for (int i=0; i<enableNeurons.size() ; i++) {
            enableNeurons.get(i).analyse();
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
    public void addNeurons(INeuron[] c) {
        Neuron n = new Neuron(this);
        n.addNeirons(c);
        neurons.add(n);
        if (neurons.size() > 1) {
            neurons.get(neurons.size()-2).setNext(n);
        }
        if (enableNeurons.size() == 0) {
            this.enableNeurons.add(n);
        }
    }
}
