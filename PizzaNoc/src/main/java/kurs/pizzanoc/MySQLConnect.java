package kurs.pizzanoc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Properties;

public class MySQLConnect {
    private String DATABASE_DRIVER = "com.mysql.jdbc.Driver";
    private String DATABASE_URL = "jdbc:mysql://localhost:3306/pizzanoc";
    private String USERNAME = "";
    private String PASSWORD = "";
    private String MAX_POOL = "250";

    private Statement stmt = null;
    private ResultSet rs = null;
    private ResultSetMetaData rsmd = null;

    private Connection connection;
    private Properties properties;

    private void setUsername(String usr) {
        this.USERNAME = usr;
    }

    private void setPassword(String pss) {
        this.PASSWORD = pss;
    }

    public String correctData(String usr, String pass) {
        if ((usr.equals("Kucharz") && pass.equals("cook")) ||
                (usr.equals("DostawcaTowaru") && pass.equals("towar")) ||
                (usr.equals("DostawcaPizzy") && pass.equals("pizzaboy")) ||
                (usr.equals("admin") && pass.equals("pizzanoc"))) {

            setUsername(usr);
            setPassword(pass);

            connect();

            return usr;
        } else {
            return "WRONG";
        }
    }

    private Properties getProperties() {
        if (properties == null) {
            properties = new Properties();
            properties.setProperty("user", USERNAME);
            properties.setProperty("password", PASSWORD);
            properties.setProperty("MaxPooledStatements", MAX_POOL);
        }
        return properties;
    }

    public Connection connect() {
        System.out.println("Connecting as " + USERNAME + "...");
        if (connection == null) {
            try {
                Class.forName(DATABASE_DRIVER);
                connection = DriverManager.getConnection(DATABASE_URL, getProperties());
                System.out.println("Connected.");
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
                System.out.println("Connect failure.");
            }
        }
        return connection;
    }

    public void disconnect() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
                System.out.println("Disconnected");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void getOrders(String q) {
        try {
            stmt = connection.createStatement();
            rs = stmt.executeQuery(q);
            rsmd = rs.getMetaData();

            int columnsNumber = rsmd.getColumnCount();
            while (rs.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    String columnValue = rs.getString(i);
                    System.out.print(columnValue);
                    if(i!=columnsNumber) System.out.print(",");
                }
                System.out.println("");
            }

        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        } finally {
            // it is a good idea to release
            // resources in a finally{} block
            // in reverse-order of their creation
            // if they are no-longer needed

            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx) {
                } // ignore
                rs = null;
            }

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) {
                } // ignore
                stmt = null;
            }
        }

    }
}