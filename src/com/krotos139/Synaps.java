package com.krotos139;

public class Synaps {
    INeuron input;
    float strength;
    public Synaps(INeuron input, float strength) {
        this.input = input;
        this.strength = strength;
    }
    public float getActive() {
        return input.active*strength;
    }
}
