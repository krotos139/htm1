package com.krotos139;

import sun.misc.Signal;

public class Synaps {
    INeuron input;
    float strength;
    float active = 0;
    public Synaps(INeuron input, float strength) {
        this.input = input;
        this.strength = strength;
        this.active = 0;
    }
    public float onSignal(InputSignal s) {
        if (s.id != input) return 0;
        float result = s.id.active * strength - active;
        active += result;
        return result;
    }
    public void reset() {
        active = 0;
    }
}
