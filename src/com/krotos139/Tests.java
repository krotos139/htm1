package com.krotos139;

import org.junit.Assert;
import org.junit.Test;

public class Tests extends Assert {

    @Test
    public void HW1() {
        System.out.print("HW1\n");
        InputMatrix in1 = new InputMatrix(5);
        SubZone sz1 = new SubZone(5, 5);
        System.out.print("Learn\n");
        boolean [][]in_p1 = {{true, false, false, false, false},
                {false, true, false, false, false},
                {false, false, true, false, false},
                {false, false, false, true, false},
                {false, false, false, false, true},
        };
        for (int i=0 ; i<in_p1.length ; i++) {
            in1.setBooleans(in_p1[i]);
            sz1.setColumnNeurons(0, in1.inputs);
        }
        System.out.print("Analyse\n");
        in1.setBooleans(in_p1[0]);
        System.out.print("Inputs\n");
        for (int i=0 ; i<in1.inputs.length ; i++) {
            System.out.print(i + ":" + in1.inputs[i].active+" ");
        }
        System.out.print("\n");
        sz1.analyze();
        System.out.print("Columns\n");
        for (int i=0 ; i<sz1.columns.length ; i++) {
            System.out.print(i + ":" + sz1.columns[i].active+" ");
        }
        System.out.print("\n");
        //sz1.teach();

    }

}
