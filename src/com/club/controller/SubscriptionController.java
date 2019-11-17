package com.club.controller;

import com.club.model.subscription.Subscription;
import com.club.model.subscription.SubscriptionDao;

import java.sql.SQLException;
import java.util.ArrayList;

public class SubscriptionController {
    private final SubscriptionDao subscriptionDao;

    public SubscriptionController() {
        subscriptionDao = new SubscriptionDao();
    }

    public ArrayList<Subscription> filter(ArrayList<String> types) {
        ArrayList<Subscription> subscriptions = null;

        try {
            subscriptions = subscriptionDao.filter(types);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return subscriptions;
    }

    public ArrayList<Subscription> getAll() {
        ArrayList<Subscription> subscriptions = null;

        try {
            subscriptions = subscriptionDao.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return subscriptions;
    }
}
