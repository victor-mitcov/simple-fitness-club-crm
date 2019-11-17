package com.club.model.manager;

import com.club.controller.ManagerController;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;


public class ManagerTableModel extends AbstractTableModel {
    private final ManagerController managerController;
    private ArrayList<Manager> managers;
    private String[] columnNames = {"Id", "First name", "Last name", "Salary"};

    public ManagerTableModel(ArrayList<Manager> managers) {
        this.managers = managers;

        this.managerController = new ManagerController();
    }

    @Override
    public int getRowCount() {
        return managers.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int row, int column) {
        return managers.get(row).toArray()[column];
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    public boolean isCellEditable(int row, int col) {
        return col != 0;
    }

    public void setValueAt(Object value, int row, int col) {
        Manager manager = managers.get(row);

        switch (col) {
            case 1:
                manager.setFirstName((String) value);
                break;
            case 2:
                manager.setLastName((String) value);
                break;
            case 3:
                manager.setSalary((Integer) value);
                break;
        }

        managerController.update(
                manager.getId(),
                manager.getFirstName(),
                manager.getLastName(),
                manager.getSalary()
        );
    }

    @Override
    public Class getColumnClass(int column) {
        switch (column) {
            case 1:
            case 2:
                return String.class;
            case 3:
            default:
                return Integer.class;
        }
    }
}
