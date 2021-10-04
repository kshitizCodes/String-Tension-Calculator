package dbFiles;

import com.company.Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

public class DABS {

    public static void main(String[] args) {
        Materials materials = new Materials();
        Database db = new Database();
        Connection conn = db.openConnection();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS DABS(" + "gauge FLOAT(53) NOT NULL,"
                    + "weight FLOAT(53) NOT NULL," + "PRIMARY KEY(gauge, weight))");
            for (Map.Entry<Double, Double> entry : materials.DABS.entrySet()) {
                stmt.executeUpdate("INSERT IGNORE INTO DABS(gauge, weight) VALUE ('" + entry.getKey() + "', '"
                        + entry.getValue() + "')");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            assert stmt != null;
            rs = stmt.executeQuery("SELECT * FROM DABS");
            System.out.println("id" + "\t" + "gauge" + "\t" + " weight");
            int i = 1;
            while (rs.next()) {
                System.out.println(i++ + "\t" + rs.getString("gauge") + "\t" + rs.getString("weight"));
            }
        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
        }
    }
}
