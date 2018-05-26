package com.krotos139;

import java.util.ArrayList;
import java.util.LinkedList;

public class Column extends INeuron {

    private ArrayList<Neuron> neurons;
    private SubZone zone;
    private int lastActiveNeuron;
    private final int confDeepanalyse;
    private final float threshold = 0.8f;


    public Column(SubZone zone) {
        this.zone = zone;
        this.neurons = new ArrayList<Neuron>();
        active = 0.0f;
        lastActiveNeuron = 0;
        confDeepanalyse = 1;
    }

    public void pushInput(LinkedList<INeuron> inputs) {
        float dactive = 0.0f;
        for (int i=0 ; i<confDeepanalyse ; i++) {
            dactive += neurons.get(lastActiveNeuron+i).pushInputActive(inputs);
        }
        dactive = (dactive - 0.5f)*2f;

        if (dactive > threshold) {
            active = 1.0f;
            zone.outSignalActive(this);
            lastActiveNeuron += 1;
        }
        if (lastActiveNeuron >= neurons.size()) {
            lastActiveNeuron = 0;
        }
        if (dactive < 1-threshold) {
            //Deactive
            lastActiveNeuron = 0;
        }
        if (lastActiveNeuron != 0) {
            zone.outSignalForecast(neurons.get(lastActiveNeuron).getSynapses());
        }
    }


    public void onForecast() {
        active += 1.0f;
        // todo send forecasts
    }

    // =========================================
    // Debug
    public void addNeurons(INeuron[] c) {
        Neuron n = new Neuron(this);
        n.addNeirons(c);
        neurons.add(n);
//        if (neurons.size() > 1) {
//            neurons.get(neurons.size()-2).setNext(n);
//        }
    }


}
