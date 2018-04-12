package com.krotos139;

import java.util.ArrayList;

public class Column extends INeuron {
    private ArrayList<Neuron> neurons;
    private Neuron enableNeuron;
    private SubZone zone;
    public Column(SubZone zone) {
        this.zone = zone;
        this.neurons = new ArrayList<Neuron>();
        this.enableNeuron = null;
        active = 0.0f;
    }

    protected void onActive(Neuron n) {
        enableNeuron = n;
    }
    public float analyse() {
        active = 0.0f;
        if (neurons.size() == 0) return 0.0f;
        if (enableNeuron == null) {
            enableNeuron = neurons.get(0);
        }
        enableNeuron.analyse();
        return active;
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
    }
}
