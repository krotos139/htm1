package com.krotos139;

import org.junit.Assert;
import org.junit.Test;

public class Tests extends Assert {

    @Test
    public void HW1() {
        System.out.print("HW1\n");
        InputMatrix in1 = new InputMatrix(5);
        SubZone sz1 = new SubZone(5);
        OutputMatrix out1 = new OutputMatrix(5);
        in1.setUpSubZone(sz1);
        sz1.setDownSubZones(in1);
        sz1.setUpSubZone(out1);
        out1.addInputs(sz1);
        System.out.print("Learn\n");
        boolean [][]in_p1 = {
                {true, false, false, false, false},
                {false, true, false, false, false},
                {false, false, true, false, false},
                {false, false, false, true, false},
                {false, false, false, false, true},
        };
        for (int i=0 ; i<in_p1.length ; i++) {
            in1.setBooleans(in_p1[i]);
            sz1.setColumnNeurons(i, in1.inputs);
        }
        for (int n=0 ; n<in_p1.length ; n++) {
            out1.reset();
            System.out.print("Analyse " + n + " pattern\n");
            in1.setBooleans(in_p1[n]);
            System.out.print("Pattern : ");
            for (int i = 0; i < in1.inputs.length; i++) {
                System.out.print((in1.inputs[i].active > 0.8f?"A":"_") + " ");
            }
            System.out.print("\n");
            in1.sendSignals();
            sz1.analyze();
            System.out.print("Columns : ");
            for (int i = 0; i < out1.inputs.length; i++) {
                System.out.print((out1.inputs[i].active > 0.8f?"A":"_") + " ");
            }
            System.out.print("\n");
            out1.reset();
        }
        //sz1.teach();

    }

    @Test
    public void HW2() {
        System.out.print("HW1\n");
        InputMatrix in1 = new InputMatrix(5);
        SubZone sz1 = new SubZone(5);
        OutputMatrix out1 = new OutputMatrix(5);
        in1.setUpSubZone(sz1);
        sz1.setDownSubZones(in1);
        sz1.setUpSubZone(out1);
        out1.addInputs(sz1);
        System.out.print("Learn\n");
        boolean [][]in_p1 = {
                {true, true, false, false, false},
                {false, true, true, false, false},
        };
        for (int i=0 ; i<in_p1.length ; i++) {
            in1.setBooleans(in_p1[i]);
            sz1.setColumnNeurons(0, in1.inputs);
        }
        boolean [][]in_p2 = {
                {false, false, true, true, false},
                {false, false, false, true, true},
        };
        for (int i=0 ; i<in_p2.length ; i++) {
            in1.setBooleans(in_p2[i]);
            sz1.setColumnNeurons(1, in1.inputs);
        }
        boolean [][]in_p3 = {
                {false, false, true, true, false},
                {false, true, true, false, false},
                {true, true, false, false, false},
        };
        for (int i=0 ; i<in_p3.length ; i++) {
            in1.setBooleans(in_p3[i]);
            sz1.setColumnNeurons(2, in1.inputs);
        }
        boolean [][]in_p4 = {
                {true, false, false, false, true},
                {false, true, false, true, false},
                {false, false, true, false, false},
        };
        for (int i=0 ; i<in_p4.length ; i++) {
            in1.setBooleans(in_p4[i]);
            sz1.setColumnNeurons(3, in1.inputs);
        }
        boolean [][]in_p5 = {
                {true, false, false, false, false},
                {false, true, false, false, false},
                {false, false, true, false, false},
                {false, false, false, true, false},
                {false, false, false, false, true},
        };
        for (int i=0 ; i<in_p5.length ; i++) {
            in1.setBooleans(in_p5[i]);
            sz1.setColumnNeurons(4, in1.inputs);
        }
        boolean [][]in_p = {
                {true, false, false, false, false},// 5
                {false, true, false, false, false},// 5
                {false, false, true, false, false},// 5
                {false, false, false, true, false},// 5
                {false, false, false, false, true},// 5
                {false, false, false, true, true}, // _
                {false, false, true, true, false}, // 2, 3
                {false, true, true, false, false}, // 3
                {true, true, false, false, false}, // 3, 1
                {false, true, true, false, false}, // 1
                {false, false, true, true, false}, // 2
                {false, false, false, true, true}, // 2
                {true, false, false, false, true}, // 4
                {false, true, false, true, false}, // 4
                {false, false, true, false, false}, // 4
                {false, false, false, true, false}, // _
                {false, false, false, false, true}, // _
                {true, false, false, false, false}, // 5
                {false, true, false, false, false}, // 5
                {false, false, true, false, false}, // 5
                {true, false, false, false, true}, // 4
                {false, true, false, true, false}, // 4
                {false, false, true, true, false}, // 3
                {false, true, true, false, false}, // 3
                {true, true, false, false, false}, // 3
                {true, false, false, false, true}, // 4
                {true, true, false, false, false}, // 1
                {false, true, true, false, false}, // 1
                {false, false, true, true, false}, // 3
                {false, false, false, true, true}, // _
        };
        boolean [][]in_e = {
                {false, false, false, false, true},// 5
                {false, false, false, false, true},// 5
                {false, false, false, false, true},// 5
                {false, false, false, false, true},// 5
                {false, false, false, false, true},// 5
                {false, false, false, false, false}, // _
                {false, true, true, false, false}, // 2, 3
                {false, false, true, false, false}, // 3
                {true, false, true, false, false}, // 3, 1
                {true, false, false, false, false}, // 1
                {false, true, false, false, true}, // 2, 5
                {false, true, false, false, false}, // 2
                {false, false, false, true, false}, // 4
                {false, false, false, true, false}, // 4
                {false, false, false, true, false}, // 4
                {false, false, false, false, false}, // _
                {false, false, false, false, false}, // _
                {false, false, false, false, true}, // 5
                {false, false, false, false, true}, // 5
                {false, false, false, false, true}, // 5
                {false, false, false, true, false}, // 4
                {false, false, false, true, false}, // 4
                {false, true, true, false, false}, // 3, 2
                {false, false, true, false, false}, // 3
                {false, false, true, false, false}, // 3
                {false, false, false, true, false}, // 4
                {true, false, false, false, false}, // 1
                {true, false, false, false, false}, // 1
                {false, true, false, false, false}, // 2
                {false, true, false, false, false}, // 2
        };
        for (int n=0 ; n<in_p.length ; n++) {
            System.out.print("Analyse " + n + " pattern\n");
            in1.setBooleans(in_p[n]);
            System.out.print("Pattern    : ");
            for (int i = 0; i < in1.inputs.length; i++) {
                System.out.print((in1.inputs[i].active > 0.5f?"A":"_") + " ");
            }
            System.out.print("\n");
            System.out.print("Etalon     : ");
            for (int i = 0; i < in_e[n].length; i++) {
                System.out.print((in_e[n][i]?"A":"_") + " ");
            }
            System.out.print("\n");
            if (n == 6) {
                System.out.print("DEBUG\n");
            }
            in1.sendSignals();
            sz1.analyze();
            System.out.print("Columns    : ");
            for (int i = 0; i < out1.inputsActives.length; i++) {
                System.out.print((out1.inputsActives[i] > 0.8f?"A":"_") + " ");
            }
            System.out.print("\n");
            System.out.print("Prediction : ");
            for (int i = 0; i < in1.inputs.length; i++) {
                System.out.print((in1.inputs[i].prediction >= 0.5f?"A":"_") + " ");
            }
            System.out.print("\n");
//            System.out.print("Columns Val: ");
//            for (int i = 0; i < out1.inputsActives.length; i++) {
//                System.out.print(sz1.getColumns()[i].active  + " ");
//            }
//            System.out.print("\n");
            out1.reset();
        }
        //sz1.teach();

    }
    @Test
    public void HW3() {
        System.out.print("HW1\n");
        InputMatrix in1 = new InputMatrix(5);
        SubZone sz1 = new SubZone(5);
        OutputMatrix out1 = new OutputMatrix(5);
        in1.setUpSubZone(sz1);
        sz1.setDownSubZones(in1);
        sz1.setUpSubZone(out1);
        out1.setDownSubZones(sz1);
        out1.addInputs(sz1);
        System.out.print("Learn\n");

        boolean [][]in_p2 = {
                {false, false, true, true, false},
                {false, false, false, true, true},
        };
        for (int i=0 ; i<in_p2.length ; i++) {
            in1.setBooleans(in_p2[i]);
            sz1.setColumnNeurons(1, in1.inputs);
        }
        boolean [][]in_p3 = {
                {false, false, true, true, false},
                {false, true, true, false, false},
                {true, true, false, false, false},
        };
        for (int i=0 ; i<in_p3.length ; i++) {
            in1.setBooleans(in_p3[i]);
            sz1.setColumnNeurons(2, in1.inputs);
        }

        boolean [][]in_p = {
                {false, false, false, true, false},// _
                {false, false, true, true, false},// 2,3
        };

        for (int p=0 ; p<5 ; p++) {
            System.out.print("Forecast " + p + " pattern\n");
            out1.outSignalForecast(p);
            for (int n = 0; n < in_p.length; n++) {
                System.out.print("Analyse " + n + " pattern\n");
                in1.setBooleans(in_p[n]);
                System.out.print("Pattern    : ");
                for (int i = 0; i < in1.inputs.length; i++) {
                    System.out.print((in1.inputs[i].active > 0.5f ? "A" : "_") + " ");
                }
                System.out.print("\n");
                in1.sendSignals();
                sz1.analyze();
                System.out.print("Columns    : ");
                for (int i = 0; i < out1.inputsActives.length; i++) {
                    System.out.print((out1.inputsActives[i] > 0.8f ? "A" : "_") + " ");
                }
                System.out.print("\n");
                System.out.print("Prediction : ");
                for (int i = 0; i < in1.inputs.length; i++) {
                    System.out.print((in1.inputs[i].prediction >= 0.5f ? "A" : "_") + " ");
                }
                System.out.print("\n");
                out1.reset();
            }
            sz1.onDeactive();
        }

    }
    @Test
    public void HW4() {
        System.out.print("HW4 - Motor\n");
        InputMatrix in1 = new InputMatrix(5);
        SubZone sz1 = new SubZone(5);
        OutputMatrix out1 = new OutputMatrix(5);
        in1.setUpSubZone(sz1);
        sz1.setDownSubZones(in1);
        sz1.setUpSubZone(out1);
        out1.setDownSubZones(sz1);
        out1.addInputs(sz1);
        System.out.print("Learn\n");

        boolean [][]in_p2 = {
                {false, false, true, true, false},
                {false, false, false, true, true},
        };
        for (int i=0 ; i<in_p2.length ; i++) {
            in1.setBooleans(in_p2[i]);
            sz1.setColumnNeurons(1, in1.inputs);
        }
        boolean [][]in_p3 = {
                {false, false, true, true, false},
                {false, true, true, false, false},
                {true, true, false, false, false},
        };
        for (int i=0 ; i<in_p3.length ; i++) {
            in1.setBooleans(in_p3[i]);
            sz1.setColumnNeurons(2, in1.inputs);
        }

        boolean [][]in_p = {
                {false, false, false, true, false},// _
                {false, false, true, true, false},// 2,3
        };

        for (int n = 0; n < in_p.length; n++) {
            System.out.print("Analyse " + n + " pattern\n");
            in1.setBooleans(in_p[n]);
            System.out.print("Pattern    : ");
            for (int i = 0; i < in1.inputs.length; i++) {
                System.out.print((in1.inputs[i].active > 0.5f ? "A" : "_") + " ");
            }
            System.out.print("\n");
            in1.sendSignals();
            sz1.analyze();
            System.out.print("Columns    : ");
            for (int i = 0; i < out1.inputsActives.length; i++) {
                System.out.print((out1.inputsActives[i] > 0.8f ? "A" : "_") + " ");
            }
            System.out.print("\n");
            System.out.print("Prediction : ");
            for (int i = 0; i < in1.inputs.length; i++) {
                System.out.print((in1.inputs[i].prediction >= 0.5f ? "A" : "_") + " ");
            }
            System.out.print("\n");
            for (int p=0 ; p<5 ; p++) {
                System.out.print("Motor " + p + " pattern\n");
                out1.outSignalMotor(p);
                System.out.print("Motor : ");
                for (int i = 0; i < in1.inputs.length; i++) {
                    System.out.print((in1.getMotorValues()[i] >= 0.5f ? "A" : "_") + " ");
                }
                System.out.print("\n");
                in1.reset();
            }
            out1.reset();
        }
        sz1.onDeactive();

    }

}
