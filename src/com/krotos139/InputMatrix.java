package com.krotos139;

public class InputMatrix extends ISubZone {
    public Input [] inputs;
    private float[] oldInputsActive;
    private float[] inputsMotor;
    private SubZone upSubZone;
    private final float threshold = 0.8f;

    public InputMatrix(int count) {
        inputs = new Input[count];
        oldInputsActive = new float[count];
        inputsMotor = new float[count];
        for (int i=0 ; i < count ; i++) {
            inputs[i] = new Input();
            oldInputsActive[i] = 0.0f;
            inputsMotor[i] = 0.0f;
        }

        upSubZone = null;
    }
    public void setUpSubZone(SubZone zone) {
        if (upSubZone!=null) {
            System.out.println("ERROR setUpSubZone: upSubZone is set already");
        }
        upSubZone = zone;
    }
    public void sendSignals() {
        for (int i=0 ; i<inputs.length ; i++) {
            if (inputs[i].active > oldInputsActive[i] && inputs[i].active > threshold) {
                upSubZone.inSignalActive(inputs[i]);
            }
        }
    }

    @Override
    public void inSignalForecast(INeuron inputNeuron) {
        inputNeuron.prediction = 1.0f;
    }

    @Override
    public void inSignalMotor(INeuron inputNeuron) {
        for (int i=0 ; i<inputs.length ; i++) {
            if (inputs[i] == inputNeuron) {
                inputsMotor[i] = 1.0f;
                break;
            }
        }
        inputNeuron.prediction = 1.0f;
    }

    public float[] getMotorValues() {
        return inputsMotor;
    }

    // Debug
    public void setBooleans( boolean []in1) {
        for (int i=0 ; i < Math.min(inputs.length, in1.length) ; i++) {
            inputs[i].active = in1[i] ? 1.0f : 0.0f;
            inputs[i].prediction = 0.0f;
        }
    }

    public void reset() {
        for (int i=0 ; i < inputs.length ; i++) {
            inputs[i].active =  0.0f;
            inputs[i].prediction = 0.0f;
            inputsMotor[i] = 0.0f;
        }
    }
}
