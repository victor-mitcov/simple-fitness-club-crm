package com.club.model.client;

import com.club.model.manager.Manager;

public class Client {
    private Integer id;
    private String firstName;
    private String lastName;
    private Boolean isActive;
    private Manager manager;

    public Client(String firstName, String lastName, Boolean isActive) {
        setFirstName(firstName);
        setLastName(lastName);
        setActive(isActive);
    }

    public Client(String firstName, String lastName, Boolean isActive, Manager manager) {
        setFirstName(firstName);
        setLastName(lastName);
        setActive(isActive);
        setManager(manager);
    }

    public Client(Integer id, String firstName, String lastName, Boolean isActive, Manager manager) {
        setId(id);
        setFirstName(firstName);
        setLastName(lastName);
        setActive(isActive);
        setManager(manager);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public Object[] toArray() {
        Object[] clientData = {getId(), getFirstName(), getLastName(), isActive()};

        return clientData;
    }
}
