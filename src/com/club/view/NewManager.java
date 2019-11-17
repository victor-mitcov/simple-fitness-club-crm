package com.club.view;

import com.club.controller.ManagerController;

import javax.swing.*;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;
import java.awt.event.*;
import java.text.NumberFormat;

public class NewManager extends JDialog {
    private final Application application;

    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField lastName;
    private JTextField firstName;
    private JFormattedTextField salary;

    public NewManager(Application application) {
        this.application = application;

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(e -> onOK());

        buttonCancel.addActionListener(e -> onCancel());

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        this.salary.setFormatterFactory(new DefaultFormatterFactory(new NumberFormatter(NumberFormat.getNumberInstance())));
    }

    private void onOK() {
        new ManagerController().create(firstName.getText(), lastName.getText(), Integer.parseInt(salary.getText()));

        application.showAllManagersInTable();

        dispose();
    }

    private void onCancel() {
        dispose();
    }
}