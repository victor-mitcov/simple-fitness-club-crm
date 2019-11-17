package com.club.model.subscription;

import com.club.db.DatabaseManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SubscriptionDao {
    private final Connection connection;

    private String SQL_GET_SUBSCRIPTIONS = "select * from subscriptions";

    public SubscriptionDao() {
        connection = new DatabaseManager().getConnection();
    }

    public ArrayList<Subscription> filter(ArrayList<String> types) throws SQLException {
        String query = SQL_GET_SUBSCRIPTIONS;

        for (int counter = 0; counter < types.size(); counter++) {
            if (counter == 0) {
                query += String.format(" where type = '%s'", types.get(counter));
            } else {
                query += String.format(" or type = '%s'", types.get(counter));
            }
        }

        return hydrateResultSet(connection.prepareStatement(query).executeQuery());
    }

    public ArrayList<Subscription> getAll() throws SQLException {
        return hydrateResultSet(connection.prepareStatement(SQL_GET_SUBSCRIPTIONS).executeQuery());
    }

    private ArrayList<Subscription> hydrateResultSet(ResultSet resultSet) throws SQLException {
        ArrayList<Subscription> subscriptions = new ArrayList();

        while (resultSet.next()) {
            Subscription subscription = new Subscription(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getInt(3),
                    resultSet.getString(4)
            );

            subscriptions.add(subscription);
        }

        return subscriptions;
    }
}
