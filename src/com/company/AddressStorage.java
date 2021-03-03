package com.company;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AddressStorage {


    public void save(Address address) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "root");
            PreparedStatement preparedStatement = connection.prepareStatement("insert into address values(default, ?, ?)");
            preparedStatement.setString(1, address.getStreet());
            preparedStatement.setInt(2, address.getHome());
            preparedStatement.execute();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    public void updateStreet(int id, String street) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "root");
            PreparedStatement preparedStatement = connection.prepareStatement("update address set street = ? where id = ?");
            preparedStatement.setString(1, street);
            preparedStatement.setInt(2, id);
            preparedStatement.execute();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    public void updateHome(int id, int home) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "root");
            PreparedStatement preparedStatement = connection.prepareStatement("update address set home = ? where id = ?");
            preparedStatement.setInt(1, home);
            preparedStatement.setInt(2, id);
            preparedStatement.execute();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    public void deleteById(int id) {

        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "root");
            PreparedStatement preparedStatement = connection.prepareStatement("delete from address where id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    public void deleteByAddress(Address address) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "root");
            PreparedStatement preparedStatement = connection.prepareStatement("delete from address where street = ? and home = ? ");
            preparedStatement.setString(1, address.getStreet());
            preparedStatement.setInt(2, address.getHome());
            preparedStatement.execute();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }


    public List<Address> getAll() {
        List<Address> address = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "root");
            PreparedStatement preparedStatement = connection.prepareStatement("select * from address");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Address address1 = new Address(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getInt(3));
                address.add(address1);
            }
            return address;
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return address;
    }


    public Address getById(int id) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "root");
            PreparedStatement preparedStatement = connection.prepareStatement("select * from address where id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return new Address(resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getInt(3));
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return null;
    }

    public Address getByAddress(Address address) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "root");
            PreparedStatement preparedStatement = connection.prepareStatement("select * from address where street = ? and home = ?");
            preparedStatement.setString(1, address.getStreet());
            preparedStatement.setInt(2, address.getHome());
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return new Address(resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getInt(3));
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return null;
    }


    public boolean containsById(int id) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "root");
            PreparedStatement preparedStatement = connection.prepareStatement("select * from address where id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return false;
    }

    public boolean containsByAddress(Address address) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "root");
            PreparedStatement preparedStatement = connection.prepareStatement("select * from address where street = ? and home = ?");
            preparedStatement.setString(1, address.getStreet());
            preparedStatement.setInt(2, address.getHome());
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return false;
    }
}

