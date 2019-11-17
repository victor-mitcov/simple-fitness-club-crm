package com.club.controller;

import com.club.model.manager.Manager;
import com.club.model.manager.ManagerDao;

import java.sql.SQLException;
import java.util.ArrayList;

public class ManagerController {
    private final ManagerDao managerDao;

    public ManagerController() {
        managerDao = new ManagerDao();
    }

    public void create(String firstName, String lastName, Integer salary) {
        try {
            managerDao.create(new Manager(firstName, lastName, salary));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void update(Integer id, String firstName, String lastName, Integer salary) {
        try {
            managerDao.update(new Manager(id, firstName, lastName, salary));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Manager> getAll() {
        ArrayList<Manager> managers = null;

        try {
            managers = managerDao.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return managers;
    }
}
