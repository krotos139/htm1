package com.krotos139;

import java.util.ArrayList;
import java.util.LinkedList;

public class Column extends INeuron {

    private ArrayList<Neuron> neurons;
    private ArrayList<Neuron> forecastNeurons;
    private SubZone zone;
    private int lastActiveNeuron;
    private final int confDeepanalyse;
    private final float threshold = 0.8f;


    public Column(SubZone zone) {
        this.zone = zone;
        this.neurons = new ArrayList<Neuron>();
        forecastNeurons = new ArrayList<Neuron>();
        active = 0.0f;
        lastActiveNeuron = -1;
        confDeepanalyse = 1;
    }

    public void pushInput(LinkedList<INeuron> inputs) {
        float dactive = 0.0f;
        if (forecastNeurons.size() == 0) {
            dactive = neurons.get(0).pushInputActive(inputs);
        } else {
            for (Neuron n : forecastNeurons) {
                dactive += n.pushInputActive(inputs);
            }
        }

        if (dactive > threshold) {
            active = 1.0f;
            zone.outSignalActive(this);
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
