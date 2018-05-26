package com.krotos139;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Set;

public class SubZone extends ISubZone {
    private Column [] columns;
    private LinkedList<INeuron> inputActive;
    private LinkedList<INeuron> inputForecast;
    private LinkedList<Column> activeColumns;
    private ISubZone upSubZone;
    private LinkedList<ISubZone> downSubZones;

    private INeuron upOutput;
    private float upOutputActive;
    private LinkedList<INeuron> upOutputs;
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
        upOutputActive = 0.0f;
        upOutputs = new LinkedList<>();
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

        upOutput = null;

        for (Column c : columns) {
            c.pushInput(inputActive);
            c.onDegradation();
        }
        inputActive.clear();

        if (upSubZone != null && upOutput != null) {
            upSubZone.inSignalActive(upOutput);
        }

        for (Column c : columns) {
            if (!upOutputs.contains(c)) {
                c.onDeactive();
            }
        }
        upOutputs.clear();
//        if (downOutputs.size() >0) {
//            for (ISubZone z : downSubZones) {
//                for (InputSignal i : downOutputs) {
//                    z.inSignal(i);
//                }
//            }
//        }
    }


    public void outSignalActive(INeuron out, float active) {
        if (upOutput == null) {
            upOutput = out;
            upOutputActive = active;
        } else if (active > upOutputActive){
            upOutput = out;
            upOutputActive = active;
        }
        upOutputs.add(out);
//        if (signal.type == SignalType.Forecast || signal.type == SignalType.Motor) {
//            downOutputs.add(signal);
//        }
    }

    public void outSignalForecast(Set<INeuron> out) {
        for (ISubZone sz : downSubZones) {
            for (INeuron n : out) {
                sz.inSignalForecast(n);
            }
        }
    }

    public void onDeactive() {
        for (Column c : columns) {
            c.onDeactive();
        }
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
