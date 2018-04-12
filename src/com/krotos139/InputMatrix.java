package com.krotos139;

public class InputMatrix {
    public Input [] inputs;
    public InputMatrix(int count) {
        inputs = new Input[count];
        for (int i=0 ; i < count ; i++) {
            inputs[i] = new Input();
        }
    }
    // Debug
    public void setBooleans( boolean []in1) {
        for (int i=0 ; i < Math.min(inputs.length, in1.length) ; i++) {
            inputs[i].active = in1[i] ? 1.0f : 0.0f;
            inputs[i].prediction = 0.0f;
        }
    }
}
