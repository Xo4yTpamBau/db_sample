package com.company;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserStorage {
    public static void main(String[] args) throws SQLException {
        UserStorage userStorage = new UserStorage();
        System.out.println(userStorage.getById(10));
        System.out.println(userStorage.getByLogin("test"));
        System.out.println(userStorage.getAll());
//        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "root");
//
//        connection.setAutoCommit(false);
//
//        PreparedStatement preparedStatement = connection.prepareStatement("insert into address values(default, ?,?) returning id");
//        preparedStatement.setString(1, "Test");
//        preparedStatement.setInt(2, 22);
//        ResultSet resultSet = preparedStatement.executeQuery();
//        resultSet.next();
//        int addressId = resultSet.getInt(1);
//
//
//        PreparedStatement preparedStatement1 = connection.prepareStatement("insert into users values(default, ?, ?, ?, ?)");
//        preparedStatement1.setString(1, "Test");
//        preparedStatement1.setString(2, "test");
//        preparedStatement1.setString(3, "test");
//        preparedStatement1.setInt(4, addressId);
//        preparedStatement1.execute();
//
//        connection.commit();
//
//        connection.setAutoCommit(true);
    }

    public void save(User user) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "root");
            PreparedStatement preparedStatement = connection.prepareStatement("insert into users values(default, ?, ?, ?)");
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getLogin());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.execute();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    public void updateName(int id, String name) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "root");
            PreparedStatement preparedStatement = connection.prepareStatement("update users set name = ? where id = ?");
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, id);
            preparedStatement.execute();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    public void updatePassword(int id, String newPassword) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "root");
            PreparedStatement preparedStatement = connection.prepareStatement("update users set password = ? where id = ?");
            preparedStatement.setString(1, newPassword);
            preparedStatement.setInt(2, id);
            preparedStatement.execute();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    public void deleteById(int id) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "root");
            PreparedStatement preparedStatement = connection.prepareStatement("delete from users where id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    public void deleteByLogin(String login) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "root");
            PreparedStatement preparedStatement = connection.prepareStatement("delete from users where login = ?");
            preparedStatement.setString(1, login);
            preparedStatement.execute();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }


    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "root");
            PreparedStatement preparedStatement = connection.prepareStatement("select * from users u  join address a on u.address_id = a.id");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4),
                    new Address(resultSet.getInt(5), resultSet.getString(7), resultSet.getInt(8)));
                users.add(user);
            }
            return users;
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return users;
    }


    public User getById(int id) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "root");
            PreparedStatement preparedStatement = connection.prepareStatement("select * from users u  join address a on u.address_id = a.id where u.id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4),
                    new Address(resultSet.getInt(5), resultSet.getString(7), resultSet.getInt(8)));
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return null;
    }

    public User getByLogin(String login) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "root");
            PreparedStatement preparedStatement = connection.prepareStatement("select * from users u  join address a on u.address_id = a.id where u.login = ?");
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4),
                    new Address(resultSet.getInt(5), resultSet.getString(7), resultSet.getInt(8)));
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return null;
    }


    public boolean containsById(int id) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "root");
            PreparedStatement preparedStatement = connection.prepareStatement("select * from users where id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return false;
    }

    public boolean containsByLogin(String login) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "root");
            PreparedStatement preparedStatement = connection.prepareStatement("select * from users where login = ?");
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return false;
    }
}




