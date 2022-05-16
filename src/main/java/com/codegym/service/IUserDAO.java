package com.codegym.service;

import com.codegym.model.User;

import java.sql.SQLDataException;
import java.util.List;

public interface IUserDAO {
    public void insert(User user) throws SQLDataException;

    public User select(int id);

    public List<User> selectAll();

    public boolean delete(int id)throws SQLDataException;

    public boolean update(User user)throws SQLDataException;
}