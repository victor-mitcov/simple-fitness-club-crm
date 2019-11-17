package com.club.view;

import com.club.controller.ClientController;
import com.club.controller.ManagerController;
import com.club.controller.SubscriptionController;
import com.club.model.client.Client;
import com.club.model.client.ClientTableModel;
import com.club.model.manager.ManagerTableModel;
import com.club.model.subscription.Subscription;
import com.club.model.subscription.SubscriptionTableModel;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.TableColumn;
import java.io.File;
import java.util.ArrayList;

public class Application {
    private JTabbedPane tabbedPane1;
    private JTextField searchInput;
    private JButton searchButton;
    private JTable clientsTable;
    private JPanel mainPanel;
    private JCheckBox unlimitedCheckBox;
    private JCheckBox morningCheckBox;
    private JCheckBox eveningCheckBox;
    private JCheckBox holidaysCheckBox;
    private JTable subscriptionsTable;
    private JButton createNewButton;
    private JPanel clientsOverview;
    private JButton importButton;
    private JTable managersTable;
    private JButton addNewManagerButton;
    private JPanel panel1;

    private ClientController clientController = new ClientController();
    private ManagerController managerController = new ManagerController();
    private SubscriptionController subscriptionController = new SubscriptionController();

    public Application() {
        showAllClientsInTable();
        showAllManagersInTable();
        showAllSubscriptionsInTable();
        registerListeners();
    }

    public void showAllClientsInTable() {
        setupClientTable(clientController.getAll());
    }

    public void showAllManagersInTable() {
        managersTable.setModel(new ManagerTableModel(managerController.getAll()));
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    private void setupClientTable(ArrayList<Client> clients) {
        clientsTable.setModel(new ClientTableModel(clients));

        JComboBox comboBox = new JComboBox(managerController.getAll().toArray());
        TableColumn managersColumn = clientsTable.getColumnModel().getColumn(4);
        managersColumn.setCellEditor(new DefaultCellEditor(comboBox));
    }

    private void showAllSubscriptionsInTable() {
        setupSubscriptionsTable(subscriptionController.getAll());
    }

    private void setupSubscriptionsTable(ArrayList<Subscription> subscriptions) {
        subscriptionsTable.setModel(new SubscriptionTableModel(subscriptions));
    }

    private void filterSubscriptionsTable() {
        ArrayList<String> typesSelected = new ArrayList();

        if (unlimitedCheckBox.isSelected()) {
            typesSelected.add("unlimited");
        }
        if (eveningCheckBox.isSelected()) {
            typesSelected.add("evening");
        }
        if (morningCheckBox.isSelected()) {
            typesSelected.add("morning");
        }
        if (holidaysCheckBox.isSelected()) {
            typesSelected.add("holidays");
        }

        setupSubscriptionsTable(subscriptionController.filter(typesSelected));
    }

    private void search(String searchWord) {
        setupClientTable(clientController.search(searchWord));
    }

    private void registerListeners() {
        Application application = this;

        createNewButton.addActionListener(actionEvent -> {
            NewClient dialog = new NewClient(application);
            dialog.pack();
            dialog.setVisible(true);
        });

        addNewManagerButton.addActionListener(actionEvent -> {
            NewManager dialog = new NewManager(application);
            dialog.pack();
            dialog.setVisible(true);
        });

        searchButton.addActionListener(actionEvent -> search(searchInput.getText()));

        importButton.addActionListener(actionEvent -> {
            JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

            int returnValue = jfc.showOpenDialog(null);

            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = jfc.getSelectedFile();

                clientController.importClients(selectedFile);

                showAllClientsInTable();
            }
        });

        for (JCheckBox checkBox : new JCheckBox[]{unlimitedCheckBox, morningCheckBox, eveningCheckBox, holidaysCheckBox}) {
            checkBox.addActionListener(actionEvent -> {
                filterSubscriptionsTable();
            });
        }
    }
}
