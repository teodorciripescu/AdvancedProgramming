package com.company;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ControlPanel extends JPanel {
    final MainFrame frame;
    JButton saveBtn = new JButton("Save");
    JButton loadBtn = new JButton("Load");
    JButton resetBtn = new JButton("Reset");
    JButton exitBtn = new JButton("Exit");
    //create all buttons (Load, Reset, Exit)
 //...TODO
    public ControlPanel(MainFrame frame) {
        this.frame = frame; init();
    }
    private void init() {
        //changing the layout manager
        setLayout(new GridLayout(1, 4));
        //adding all the buttons
        add(saveBtn);
        add(loadBtn);
        add(resetBtn);
        add(exitBtn);

        //configure listeners for all buttons
        saveBtn.addActionListener(this::save);
        loadBtn.addActionListener(this::load);
        resetBtn.addActionListener(this::reset);
        exitBtn.addActionListener(this::exit);

    }
    private void save(ActionEvent e) {
        /*  Compulsory part:
        try {
            ImageIO.write(frame.canvas.image, "PNG", new File("./saved_image.png"));
        } catch (IOException ex) { System.err.println(ex); }
         */
        //the Optional part( using a file chooser)
        try {
            //creating the file chooser
            JFileChooser fileChooser = new JFileChooser();
            //calling the save dialog
            int returnValue = fileChooser.showSaveDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                //getting the selected file
                File selectedFile = fileChooser.getSelectedFile();
                //saving the image as PNG
                ImageIO.write(frame.canvas.image, "PNG", new File(selectedFile.getAbsolutePath()));
            }
        } catch (IOException ex) { System.err.println(ex); }
    }
    private void load(ActionEvent e) {
        try {
            /* This is the compulsory part
            BufferedImage image;
            image = ImageIO.read(new File("./saved_image.png"));
            frame.canvas.graphics.drawImage(image, 0, 0, this);
            frame.canvas.repaint();
             */
            //the Optional part:(using a file chooser)
            String filename;
            JFileChooser fileChooser = new JFileChooser();
            int returnValue = fileChooser.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                filename = selectedFile.getAbsolutePath();
                BufferedImage image;
                //reading the image
                image = ImageIO.read(new File(filename));
                //drawing the image
                frame.canvas.graphics.drawImage(image, 0, 0, this);
                //displaying the image
                frame.canvas.repaint();
            }
        } catch (Exception ex) { System.err.println(ex); }
    }


    private void reset(ActionEvent e) {
        try {
            frame.canvas.createOffscreenImage(); //draws the empty canvas
            frame.canvas.repaint(); // loads the empty canvas
        } catch (Exception ex) { System.err.println(ex); }
    }
    private void exit(ActionEvent e) {
        System.exit(0); // exit the app
    }
}