package com.krotos139;

import java.util.HashMap;
import java.util.LinkedList;

public class SubZone extends ISubZone {
    private Column [] columns;
    private LinkedList<INeuron> inputActive;
    private LinkedList<INeuron> inputForecast;
    private LinkedList<Column> activeColumns;
    private ISubZone upSubZone;
    private LinkedList<ISubZone> downSubZones;

    private INeuron upOutput;
    private boolean upOutputChange;
    private LinkedList<INeuron> downOutputs;

    public SubZone(int numColumns) {
        columns = new Column[numColumns];
        for (int i=0 ; i<numColumns ; i++) {
            columns[i] = new Column(this);
        }
        inputActive = new LinkedList<>();
        inputForecast = new LinkedList<>();
        activeColumns = new LinkedList<>();
        upSubZone = null;
        downSubZones = new LinkedList<>();
        downOutputs = new LinkedList<>();
        upOutputChange = false;
    }

    @Override
    public void inSignalActive(INeuron inputNeuron) {
        inputActive.add(inputNeuron);
    }

    @Override
    public void inSignalForecast(INeuron inputNeuron) {
        inputForecast.add(inputNeuron);
    }

    public void setDownSubZones(ISubZone zone) {
        downSubZones.push(zone);
    }
    public void setUpSubZone(ISubZone zone) {
        if (upSubZone!=null) {
            System.out.println("ERROR setUpSubZone: upSubZone is set already");
        }
        upSubZone = zone;
    }

    public void analyze() {
        if (inputForecast.size() > 0) {
            for (INeuron s : inputForecast) {
                ((Column)s).onForecast();
            }
        }
        for (Column c : columns) {
            c.pushInput(inputActive);
        }
        inputActive.clear();

        if (upOutputChange) {
            if (upSubZone != null) {
                upSubZone.inSignalActive(upOutput);
            }
            for (Column c : columns) {
                if (c != upOutput) {
                    c.active = 0.0f; // Disable after active differ
                }
            }

        }
//        if (downOutputs.size() >0) {
//            for (ISubZone z : downSubZones) {
//                for (InputSignal i : downOutputs) {
//                    z.inSignal(i);
//                }
//            }
//        }
    }


    public void outSignalActive(INeuron out) {
        if (upOutput == null || upOutput.active < out.active) {
            upOutput = out;
            upOutputChange = true;
        }
//        if (signal.type == SignalType.Forecast || signal.type == SignalType.Motor) {
//            downOutputs.add(signal);
//        }
    }

    // DEBUG
    public void setColumnNeurons(int column, INeuron [] in) {
        Column c = columns[column];
        c.addNeurons(in);
    }

    public Column[] getColumns() {
        return columns;
    }
}
