package com.club.model.client;

import com.club.db.DatabaseManager;
import com.club.model.manager.Manager;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ClientDao {
    private Connection connection = null;

    private String SQL_CREATE_CLIENT = "insert into clients (first_name, last_name, is_active, manager_id) values (?,?,?,?)";

    private String SQL_UPDATE_CLIENT = "update clients set first_name = ?, last_name = ?, is_active = ?, manager_id = ? where id = ?";

    private String SQL_GET_ALL_CLIENTS = "select clients.id, clients.first_name, clients.last_name, clients.is_active, managers.id, managers.first_name, managers.last_name from clients left join managers on clients.manager_id = managers.id";
    private String SQL_GET_ALL_CLIENTS_ORDERED = SQL_GET_ALL_CLIENTS + " order by clients.id";

    private String SQL_SEARCH_CLIENTS = SQL_GET_ALL_CLIENTS + " where clients.first_name like ? or clients.last_name like ?";

    public ClientDao() {
        connection = new DatabaseManager().getConnection();
    }

    public void create(Client client) throws SQLException {
        PreparedStatement query = connection.prepareStatement(SQL_CREATE_CLIENT);
        query.setString(1, client.getFirstName());
        query.setString(2, client.getLastName());
        query.setBoolean(3, client.isActive());

        if (client.getManager() != null) {
            query.setInt(4, client.getManager().getId());
        } else {
            query.setNull(4, Types.INTEGER);
        }

        query.executeUpdate();
    }

    public void update(Client client) throws SQLException {
        PreparedStatement query = connection.prepareStatement(SQL_UPDATE_CLIENT);
        query.setString(1, client.getFirstName());
        query.setString(2, client.getLastName());
        query.setBoolean(3, client.isActive());

        if (client.getManager().getId() != null) {
            query.setInt(4, client.getManager().getId());
        } else {
            query.setNull(4, Types.INTEGER);
        }

        query.setInt(5, client.getId());

        query.executeUpdate();
    }

    public ArrayList<Client> getAll() throws SQLException {
        return hydrateResultSet(connection.prepareStatement(SQL_GET_ALL_CLIENTS_ORDERED).executeQuery());
    }

    public ArrayList<Client> search(String searchWord) throws SQLException {
        String likeSearchWord = "%" + searchWord + "%";

        PreparedStatement searchClientsQuery = connection.prepareStatement(SQL_SEARCH_CLIENTS);
        searchClientsQuery.setString(1, likeSearchWord);
        searchClientsQuery.setString(2, likeSearchWord);

        return hydrateResultSet(searchClientsQuery.executeQuery());
    }

    public void importClients(File file) throws FileNotFoundException, SQLException {
        Scanner scanner = new Scanner(file);

        String[] splittedLine;

        while (scanner.hasNextLine()) {
            splittedLine = scanner.nextLine().split(";");

            create(new Client(splittedLine[0], splittedLine[1], splittedLine[2].equals("1") ? true : false));
        }
    }

    private ArrayList<Client> hydrateResultSet(ResultSet resultSet) throws SQLException {
        ArrayList<Client> clients = new ArrayList();

        while (resultSet.next()) {
            Manager manager = new Manager(
                    resultSet.getInt(5) == 0 ? null : resultSet.getInt(5),
                    resultSet.getString(6),
                    resultSet.getString(7)
            );

            Client client = new Client(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getBoolean(4),
                    manager
            );

            clients.add(client);
        }

        return clients;
    }
}
