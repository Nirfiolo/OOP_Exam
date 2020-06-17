package com.exam;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Find a triangle with an area as close to: ");
            float area = scanner.nextFloat();
            Triangles.getClosestByArea(area);

            System.out.print("Find triangles that have the sum of the areas as close to: ");
            area = scanner.nextFloat();
            Triangles.getSumOfAreas(area);

            System.out.print("Find circles that can fit inside a circle with a radius: ");
            float r = scanner.nextFloat();
            Triangles.getInsideCircle(r);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
