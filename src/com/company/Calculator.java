package com.company;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;

class Calculator {
    private final double length;
    private final double gauge;
    private final String frequency;
    private final String type;

    Calculator(double length, double gauge, String frequency, String type) {
        this.length = length;
        this.gauge = gauge;
        this.frequency = frequency;
        this.type = type;
    }


    public double getFrequency() {
        switch (frequency) {
            case "E4":
                return 329.63;
            case "B3":
                return 246.94;
            case "G3":
                return 196.00;
            case "D3":
                return 146.83;
            case "A2":
                return 110.00;
            case "E2":
                return 82.41;
            default:
                throw new IllegalStateException("Unexpected value: " + frequency);
        }
    }

    public double calculate() {
        DecimalFormat df = new DecimalFormat("0");
        Database db = new Database();
        Connection conn = db.openConnection();
        Statement stmt;
        ResultSet rs;
        double tension = -1;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT weight FROM " + new StringType(type).getStringType() + " WHERE gauge = " + this.gauge);
            if (rs.next()) {
                double GRAVITATIONAL_ACCELERATION = 386.08858;
                tension = (Math.pow((2 * this.length * getFrequency()), 2) * (rs.getDouble("weight") / GRAVITATIONAL_ACCELERATION));
            } else {
                throw new IllegalInputException("No string on entered gauge found in records.");
            }
        } catch (SQLException | IllegalInputException e) {
            e.printStackTrace();
        } finally {
            db.closeConnection(conn);
        }
        return Double.parseDouble(df.format(tension));
    }
}
