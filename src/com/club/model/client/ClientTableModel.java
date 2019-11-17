package com.club.model.client;

import com.club.controller.ClientController;
import com.club.model.manager.Manager;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;


public class ClientTableModel extends AbstractTableModel {
    private final ClientController clientController;
    private ArrayList<Client> clients;
    private String[] columnNames = {"Id", "First name", "Last name", "Is active", "Manager"};

    public ClientTableModel(ArrayList<Client> clients) {
        this.clients = clients;

        this.clientController = new ClientController();
    }

    @Override
    public int getRowCount() {
        return clients.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int row, int column) {
        if (column == 4) {
            return clients.get(row).getManager();
        }

        return clients.get(row).toArray()[column];
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    public boolean isCellEditable(int row, int col) {
        return col != 0;
    }

    public void setValueAt(Object value, int row, int col) {
        Client client = clients.get(row);

        switch (col) {
            case 1:
                client.setFirstName((String) value);
                break;
            case 2:
                client.setLastName((String) value);
                break;
            case 3:
                client.setActive((Boolean) value);
                break;
            case 4:
                client.setManager((Manager) value);
                break;
        }

        clientController.update(
                client.getId(),
                client.getFirstName(),
                client.getLastName(),
                client.isActive(),
                client.getManager()
        );
    }

    @Override
    public Class getColumnClass(int column) {
        switch (column) {
            case 0:
                return Integer.class;
            case 1:
            case 2:
                return String.class;
            case 4:
                return Manager.class;
            case 3:
            default:
                return Boolean.class;
        }
    }
}
