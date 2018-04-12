package com.krotos139;

import java.util.ArrayList;

public class Neuron extends INeuron {
    private Column column;
    private ArrayList<Synaps> synaps;
    private float threshold = 0.8f;
    private Neuron next = null;
    private float nextActiveVal = 0.8f;

    public Neuron(Column column) {
        this.synaps = new ArrayList<Synaps>();
        this.column = column;
        this.next = null;
    }
    public void analyse() {
        float dActive = 0;
        for (int i=0; i<synaps.size(); i++) {
            dActive += synaps.get(i).getActive();
        }
        if (active > threshold) {
            onActive();
        }
    }
    public void setNext(Neuron next) {
        this.next = next;
    }
    private void onActive() {
        column.active += active;
        column.onActive(this.next);
    }
    private void onDeactive() {
        column.active = 0.0f;
    }

    // Debug
    public void addNeirons(INeuron []neurons) {
        int synapsCount = 0;
        for (int i=0 ; i<neurons.length ; i++) {
            if (neurons[i].active > threshold) synapsCount++;
        }
        float strength = 1.0f/synapsCount;
        for (int i=0 ; i<neurons.length ; i++) {
            if (neurons[i].active > threshold) {
                Synaps s = new Synaps(neurons[i], strength);
                synaps.add(s);
            }
        }
    }
}
