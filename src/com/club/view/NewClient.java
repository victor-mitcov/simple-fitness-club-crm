package com.club.view;

import com.club.controller.ClientController;
import com.club.controller.ManagerController;
import com.club.model.manager.Manager;

import javax.swing.*;
import java.awt.event.*;

public class NewClient extends JDialog {
    private final ClientController clientController;
    private Application application;

    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField firstName;
    private JTextField lastName;
    private JCheckBox isActive;
    private JComboBox managersDropDown;

    public NewClient(Application application) {
        this.application = application;
        this.clientController = new ClientController();

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

        this.managersDropDown.setModel(new DefaultComboBoxModel(new ManagerController().getAll().toArray()));
    }

    private void onOK() {
        clientController.create(firstName.getText(), lastName.getText(), isActive.isSelected(), (Manager) managersDropDown.getSelectedItem());
        application.showAllClientsInTable();
        dispose();
    }

    private void onCancel() {
        dispose();
    }
}
