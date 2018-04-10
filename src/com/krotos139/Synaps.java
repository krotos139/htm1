package com.krotos139;

public class Synaps {
    Input input;
    float strength;
    public Synaps(Input input, float strength) {
        this.input = input;
        this.strength = strength;
    }
    public float getActive() {
        return (input.sensor ? 1.0f: 0.0f)*strength;
    }
}
