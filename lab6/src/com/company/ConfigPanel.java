package com.company;

import javax.swing.*;
import java.lang.reflect.Array;
import java.security.KeyPair;

public class ConfigPanel extends JPanel {
    final MainFrame frame;
    JLabel sidesLabel; // we’re drawing regular polygons
    static JSpinner sidesField; // number of sides
    static JComboBox colorCombo; // the color of the shape
    static JComboBox shapeCombo; // the color of the shape
    public ConfigPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }
    private void init() {
        //create the label and the spinner
        sidesLabel = new JLabel("Number of sides:");
        //we should have at least 3 sides for a polygon
        sidesField = new JSpinner(new SpinnerNumberModel(4, 3, 100, 1));
        sidesField.setValue(4); //default number of sides

        //create the colorCombo, containing the values: Random and Black
        colorCombo = new JComboBox(new String[]{"Random","Black"});

        //choosing between shapes
        shapeCombo = new JComboBox(new String[]{"Polygon","Circle"});

        add(sidesLabel); //JPanel uses FlowLayout by default
        add(sidesField);
        add(colorCombo);
        add(shapeCombo);
    }
}
