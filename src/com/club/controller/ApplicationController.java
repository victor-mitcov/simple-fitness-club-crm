package com.club.controller;

import com.club.view.Application;

import javax.swing.*;

public class ApplicationController {
    public void openWindow() {
        JFrame jFrame = new JFrame("Fitness club CRM");
        jFrame.setContentPane(new Application().getMainPanel());
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.pack();
        jFrame.setVisible(true);
    }
}
