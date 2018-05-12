package com.krotos139;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class Neuron {
    private Column column;
    private HashMap<INeuron, Synaps> synaps;
    private final float threshold = 0.8f;

    public Neuron(Column column) {
        this.synaps = new HashMap<>();
        this.column = column;
    }

    public void pushInput(LinkedList<InputSignal> inputs) {
        //active = 0.0f;
        for (InputSignal s : inputs) {
            if (s.type == SignalType.Active) {
                Synaps syn = synaps.get(s.id);
                if (syn!= null) column.active += syn.onSignal(s);
            }
            if (s.type == SignalType.Forecast) {
                column.onForecast(s.id.active);
            }
            if (s.type == SignalType.Motor) {
                column.active = s.id.active;
                for (HashMap.Entry me : synaps.entrySet()) {
                    column.outSignal(new InputSignal((INeuron)me.getKey(), SignalType.Motor));
                }

            }
        }
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
                synaps.put(neurons[i], new Synaps(neurons[i], strength));
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
