package com.club.model.manager;

import com.club.db.DatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ManagerDao {
    private final Connection connection;

    private String SQL_GET_ALL_MANAGERS = "select * from managers";
    private String SQL_UPDATE_MANAGER = "update managers set first_name = ?, last_name = ?, salary = ? where id = ?";
    private String SQL_CREATE_MANAGER = "insert into managers (first_name, last_name, salary) values (?,?,?)";

    public ManagerDao() {
        connection = new DatabaseManager().getConnection();
    }

    public void create(Manager manager) throws SQLException {
        PreparedStatement query = connection.prepareStatement(SQL_CREATE_MANAGER);
        query.setString(1, manager.getFirstName());
        query.setString(2, manager.getLastName());
        query.setInt(3, manager.getSalary());
        query.executeUpdate();
    }

    public void update(Manager manager) throws SQLException {
        PreparedStatement query = connection.prepareStatement(SQL_UPDATE_MANAGER);
        query.setString(1, manager.getFirstName());
        query.setString(2, manager.getLastName());
        query.setInt(3, manager.getSalary());
        query.setInt(4, manager.getId());
        query.executeUpdate();
    }

    public ArrayList<Manager> getAll() throws SQLException {
        return hydrateResultSet(connection.prepareStatement(SQL_GET_ALL_MANAGERS).executeQuery());
    }

    private ArrayList<Manager> hydrateResultSet(ResultSet resultSet) throws SQLException {
        ArrayList<Manager> managers = new ArrayList();

        while (resultSet.next()) {
            Manager manager = new Manager(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getInt(4)
            );

            managers.add(manager);
        }

        return managers;
    }
}
