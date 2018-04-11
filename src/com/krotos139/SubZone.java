package com.krotos139;

public class SubZone {
    private Input [] inputs;
    public Column [] columns;

    protected Column activeColumn;
    protected float activeColumnStreng;
    protected Column predictionColumn;

    public SubZone(int numInputs, int numColumns) {
        inputs = new Input[numInputs];
        for (int i=0 ; i<numInputs ; i++) {
            inputs[i] = new Input();
        }
        columns = new Column[numColumns];
        for (int i=0 ; i<numColumns ; i++) {
            columns[i] = new Column(this);
        }
        activeColumn = null;
        predictionColumn = null;
    }
    public void analyze() {
        activeColumn = null;
        predictionColumn = null;
        for (int i=0 ; i<columns.length ; i++) {
            columns[i].analyse();
        }
    }
    public void teach() {


    }
    // DEBUG
    public void setSensorInputs(boolean [] in) {
        for (int i=0 ; i<Math.min(inputs.length, in.length); i++) {
            this.inputs[i].active = in[i] ? 1.0f : 0.0f;
        }
    }
    public void setColumnNeurons(int column, INeuron [] in) {
        Column c = columns[column];
        c.addNeurons(in);
    }
}
