package com.krotos139;

import java.util.ArrayList;

public class Column extends INeuron {
    private ArrayList<Neuron> neurons;
    private Neuron enableNeuron;
    private SubZone zone;
    private float activeAttenuation = 0.0f;
    private boolean isAttenuation = false;
    public Column(SubZone zone) {
        this.zone = zone;
        this.neurons = new ArrayList<Neuron>();
        this.enableNeuron = null;
        active = 0.0f;
    }
    public void setActiveAttenuation(float attenuation) {
        activeAttenuation = attenuation;
    }
    protected void onActive(Neuron n, float receiveActive, float activeAttenuation) {
        this.enableNeuron = n;
        this.active = Math.min(this.active+receiveActive, 1.0f);
        this.activeAttenuation = activeAttenuation;
        if (n != null) {
            n.sendPreActive();
        }
        isAttenuation = false;
    }
    public float analyse() {
        isAttenuation = true;

        if (neurons.size() == 0) return 0.0f;
        if (enableNeuron == null) {
            enableNeuron = neurons.get(0);
        }
        enableNeuron.analyse();
        if (isAttenuation) {
            active = Math.max(0.0f, active - activeAttenuation);
        }
        //System.out.println("active:"+active+" prediction:"+ prediction +" enableNeuron:"+neurons.indexOf(enableNeuron));
        return getActive();
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
