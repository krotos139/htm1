package com.krotos139;

enum SignalType { Active, Forecast, Motor};

public class InputSignal {
    INeuron id;
    SignalType type;
    public InputSignal(INeuron id, SignalType type) {
        this.id = id;
        this.type = type;
    }
}
