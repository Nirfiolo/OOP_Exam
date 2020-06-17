package com.exam;

import com.exam.database.ConnectionToDB;
import com.exam.database.SQLScriptLoader;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class SubList {
    public float size;
    public List<Float> subList;

    public SubList() {
        this(0, new ArrayList<>());
    }

    public SubList(float size, List<Float> subList) {
        this.size = size;
        this.subList = subList;
    }
}

public class Triangles {
    public static void getClosestByArea(float area) throws SQLException, ClassNotFoundException {
        String query = SQLScriptLoader.load("query_1.sql");

        Connection connection = ConnectionToDB.initDB();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setFloat(1, area);

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            System.out.println("Triangle with area as close to " + area + " is " + resultSet.getInt("id") + " of difference " + resultSet.getFloat("area"));
        }
    }

    public static void getSumOfAreas(float area) throws SQLException, ClassNotFoundException {
        String query = SQLScriptLoader.load("query_2.sql");

        Connection connection = ConnectionToDB.initDB();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setFloat(1, area);

        ResultSet resultSet = preparedStatement.executeQuery();

        List<Float> inputs = new ArrayList<>();

        while (resultSet.next()) {
            inputs.add(resultSet.getFloat("area"));
        }

        SubList opt = new SubList();

        Set<SubList> sums = new HashSet<>();
        sums.add(opt);

        for (Float input : inputs) {
            Set<SubList> newSums = new HashSet<>();

            for (SubList sum : sums) {
                List<Float> newSubList = new ArrayList<>(sum.subList);
                newSubList.add(input);
                SubList newSum = new SubList(sum.size + input, newSubList);

                if (newSum.size <= area) {
                    newSums.add(newSum);

                    if (newSum.size > opt.size) {
                        opt = newSum;
                    }
                }
            }

            sums.addAll(newSums);
        }

        System.out.println("Closest values are: ");
        System.out.print(opt.subList.get(0));
        for (int i = 1; i < opt.subList.size(); i++) {
            System.out.print(" + " + opt.subList.get(i));
        }

        System.out.println();

    }

    public static void getInsideCircle(float r) throws SQLException, ClassNotFoundException {
        String query = SQLScriptLoader.load("query_3.sql");

        Connection connection = ConnectionToDB.initDB();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setFloat(1, r);

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            System.out.println("Triangle " + resultSet.getInt("id") + " can fit inside.");
        }
    }
}
