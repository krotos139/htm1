package com.krotos139;

import org.junit.Assert;
import org.junit.Test;

public class Tests extends Assert {

    @Test
    public void HW1() {
        System.out.print("HW1\n");
        SubZone sz1 = new SubZone(5, 5);
        boolean []in1 = {true, false, false, false, false};
        sz1.setSensorInputs(in1);
        sz1.analyze();
        sz1.teach();

    }

}
