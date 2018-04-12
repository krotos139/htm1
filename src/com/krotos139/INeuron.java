package com.krotos139;

public class INeuron {
    protected float active = 0.0f;
    protected float preActive = 0.0f;

    public float getActive() {
        return active+preActive;
    }
    public void setPreActive(float a) {
        preActive = a;
    }

}
