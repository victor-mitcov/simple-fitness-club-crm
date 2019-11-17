package com.club.controller;

import com.club.model.client.Client;
import com.club.model.client.ClientDao;
import com.club.model.manager.Manager;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClientController {
    private ClientDao clientDao;

    public ClientController() {
        clientDao = new ClientDao();
    }

    public void create(String firstName, String lastName, Boolean isActive, Manager manager) {
        try {
            clientDao.create(new Client(firstName, lastName, isActive, manager));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Integer id, String firstName, String lastName, Boolean isActive, Manager manager) {
        try {
            clientDao.update(new Client(id, firstName, lastName, isActive, manager));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Client> getAll() {
        ArrayList<Client> clients = null;

        try {
            clients = clientDao.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clients;
    }

    public ArrayList<Client> search(String searchWord) {
        ArrayList<Client> clients = null;

        try {
            clients = clientDao.search(searchWord);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clients;
    }

    public void importClients(File file) {
        try {
            clientDao.importClients(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
