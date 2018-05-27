package com.krotos139;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

public class Column extends INeuron {

    private ArrayList<Neuron> neurons;
    private SubZone zone;
    private int lastActiveNeuron;
    private final int confDeepanalyse;
    private final float threshold = 0.8f;
    private final float degradation = 0.4f;


    public Column(SubZone zone) {
        this.zone = zone;
        this.neurons = new ArrayList<Neuron>();
        active = 0.0f;
        lastActiveNeuron = 0;
        confDeepanalyse = 1;
    }

    public void pushInput(LinkedList<INeuron> inputs) {
        if (neurons.size() == 0) return;
        float dactive = 0.0f;
        for (int i=0 ; i<confDeepanalyse ; i++) {
            dactive += neurons.get(lastActiveNeuron+i).pushInputActive(inputs);
        }
        dactive = (dactive - 0.5f)*2f;

        if (dactive > threshold) {
            active += dactive;
            lastActiveNeuron += 1;
        }
        if (active > threshold) {
            zone.outSignalActive(this, active);
        }
        if (lastActiveNeuron >= neurons.size()) {
            onDeactive();
        }
        if (active > 1) active = 1;
        if (active < 1-threshold) {
            onDeactive();
        }
        if (lastActiveNeuron != 0) {
            Iterator it = neurons.get(lastActiveNeuron).getSynapses().entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry me = (Map.Entry)it.next();
                if ((Float)me.getValue() > 0) {
                    zone.outSignalForecast((INeuron) me.getKey());
                }
            }
        }
    }

    public void onDegradation() {
        active -= degradation;
        if (active < 1-threshold) {
            onDeactive();
        }
    }

    public void onForecast() {
        active += 1.0f;
        // todo send forecasts
    }

    public void onMotor() {
        if (neurons.size() == 0) return;
        Iterator it = neurons.get(lastActiveNeuron).getSynapses().entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry me = (Map.Entry)it.next();
            if ((Float)me.getValue() > 0) {
                zone.outSignalMotor((INeuron) me.getKey());
            }
        }
    }

    public void onDeactive() {
        lastActiveNeuron = 0;
        active = 0;
    }

    public void onDeactiveZone() {
        lastActiveNeuron = 0;
        active = 0;
        zone.onDeactive();
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
