package com.krotos139;

import java.util.LinkedList;

public class SubZone extends ISubZone {
    private Column [] columns;
    private LinkedList<InputSignal> input;
    private LinkedList<InputSignal> inputForecast;
    private LinkedList<Column> activeColumns;
    private ISubZone upSubZone;
    private LinkedList<ISubZone> downSubZones;

    private InputSignal upOutput;
    private boolean upOutputChange;
    private LinkedList<InputSignal> downOutputs;

    public SubZone(int numColumns) {
        columns = new Column[numColumns];
        for (int i=0 ; i<numColumns ; i++) {
            columns[i] = new Column(this);
        }
        input = new LinkedList<>();
        inputForecast = new LinkedList<>();
        activeColumns = new LinkedList<>();
        upSubZone = null;
        downSubZones = new LinkedList<>();
        downOutputs = new LinkedList<>();
        upOutputChange = false;
    }

    @Override
    public void inSignal(InputSignal signal) {
        if (signal.type == SignalType.Forecast) {
            inputForecast.push(signal);
        } else {
            input.push(signal);
        }
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
            for (InputSignal s : inputForecast) {
                ((Column)s.id).onForecast(s.id.active);
            }
        }
        if (activeColumns.size() == 0) {
            for (Column c : columns) {
                c.pushInput(input);
            }
        } else {
            for (Column c : activeColumns) {
                c.pushInput(input);
            }
        }
        input.clear();
        // DEBUG
        activeColumns.clear();
        if (upOutputChange) {
            activeColumns.clear();
            activeColumns.push((Column) upOutput.id);
            if (upSubZone != null) {
                upSubZone.inSignal(upOutput);
            }
            for (Column c : columns) {
                if (c != upOutput.id) {
                    c.active = 0.0f; // Disable after active differ
                }
            }

        }
        if (downOutputs.size() >0) {
            for (ISubZone z : downSubZones) {
                for (InputSignal i : downOutputs) {
                    z.inSignal(i);
                }
            }
        }
    }

    public void setActiveColumn(Column column) {
        for (Column c : activeColumns) {
            if (c != column) {
                c.setUnactive();
            }
        }
        activeColumns.clear();
        activeColumns.push(column);

    }
    public void setUnactiveColumn(Column c) {
        activeColumns.clear();
        activeColumns.push(column);
        for (Column c : columns) {
            if (c != column) {
                c.active = 0.0f; // Disable after active differ
            }
        }
    }

    public void outSignal(InputSignal signal) {
        if (signal.type == SignalType.Active) {
            if (upOutput == null || upOutput.id.active < signal.id.active) {
                upOutput = signal;
                upOutputChange = true;
            }
        }
        if (signal.type == SignalType.Forecast || signal.type == SignalType.Motor) {
            downOutputs.add(signal);
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
