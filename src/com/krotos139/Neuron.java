package com.krotos139;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Set;

public class Neuron {
    private float forecast  = 0.0f;
    private Column column;
    private HashMap<INeuron, Float> synaps;
    private final float threshold = 0.5f;

    public Neuron(Column column) {
        this.synaps = new HashMap<>();
        this.column = column;
    }

    public float pushInputActive(LinkedList<INeuron> inputs) {
        //active = 0.0f;
        float activeSynapsCount = 0f;
        for (INeuron s : inputs) {
            Float syn = synaps.get(s);
            if (syn!= null) {
                activeSynapsCount+=syn;
            }
        }
        return activeSynapsCount;
    }

    public HashMap<INeuron, Float> getSynapses() {
        return synaps;
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
                synaps.put(neurons[i], new Float(strength));
            }
        }
        if (true) {
            int synapsNCount = 0;
            for (int i=0 ; i<neurons.length ; i++) {
                if (neurons[i].active < threshold) synapsNCount++;
            }
            float strengthN = 1.0f/synapsNCount;
            for (int i=0 ; i<neurons.length ; i++) {
                if (neurons[i].active < threshold) {
                    synaps.put(neurons[i], new Float(-strengthN));
                }
            }
        }
    }


//    private Column column;
//    private ArrayList<Synaps> synaps;
//    private float threshold = 0.8f;
//    private Neuron next = null;
//    private static float colunmActive = 0.5f;
//    private static float colunmAttenuation = 0.25f;
//
//    public Neuron(Column column) {
//        this.synaps = new ArrayList<Synaps>();
//        this.column = column;
//        this.next = null;
//    }
//    public void analyse() {
//        active = 0;
//        for (int i=0; i<synaps.size(); i++) {
//            active += synaps.get(i).getActive();
//        }
//        if (getActive() > threshold) {
//            onActive();
//        }
//    }
//    public void setNext(Neuron next) {
//        this.next = next;
//    }
//    private void onActive() {
//        column.onActive(this.next, colunmActive, colunmAttenuation);
//    }
//    public void sendPreActive() {
//        for (int i=0; i<synaps.size(); i++) {
//            synaps.get(i).input.prediction = colunmActive; // TODO not shure...
//        }
//    }
//    private void onDeactive() {
//        column.active = 0.0f;
//    }
//
//    // Debug
//    public void addNeirons(INeuron []neurons) {
//        int synapsCount = 0;
//        for (int i=0 ; i<neurons.length ; i++) {
//            if (neurons[i].active > threshold) synapsCount++;
//        }
//        float strength = 1.0f/synapsCount;
//        for (int i=0 ; i<neurons.length ; i++) {
//            if (neurons[i].active > threshold) {
//                Synaps s = new Synaps(neurons[i], strength);
//                synaps.add(s);
//            }
//        }
//    }
}
