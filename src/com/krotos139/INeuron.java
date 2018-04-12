package com.krotos139;

public class INeuron {
    protected float active = 0.0f;
    protected float prediction = 0.0f;

    public float getActive() {
        return active+ prediction;
    }
    public void setPrediction(float a) {
        prediction = a;
    }

}
