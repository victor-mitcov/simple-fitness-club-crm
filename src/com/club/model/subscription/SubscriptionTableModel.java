package com.club.model.subscription;

import com.club.controller.SubscriptionController;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class SubscriptionTableModel extends AbstractTableModel {
    private final SubscriptionController subscriptionController;
    private ArrayList<Subscription> subscriptions;
    private String[] columnNames = {"Id", "Description", "Price", "type"};

    public SubscriptionTableModel(ArrayList<Subscription> subscriptions) {
        this.subscriptions = subscriptions;

        this.subscriptionController = new SubscriptionController();
    }

    @Override
    public int getRowCount() {
        return subscriptions.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int row, int column) {
        return subscriptions.get(row).toArray()[column];
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
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
