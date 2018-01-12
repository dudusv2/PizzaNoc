package kurs.pizzanoc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Properties;
import java.io.*;

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

    private Object newdata[][];
    private String products[];

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


    public void updateOrders (String status, Object id) {
        int id_row = Integer.parseInt((String)id);
        try {
            String updateTableSQL = "UPDATE orders SET status = ? WHERE ID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(updateTableSQL);
            preparedStatement.setString(1, status);
            preparedStatement.setInt(2, id_row);
            preparedStatement .executeUpdate();
        } catch(SQLException ex){
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        } finally{
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) {
                } // ignore
                stmt = null;
            }
        }
    }

    public int getRowNumbers (String table_name) {
        try {
            String query = "SELECT count(*) FROM " + table_name;
            stmt = connection.createStatement();
            rs = stmt.executeQuery(query);

            rs.next();
            int c = rs.getInt(1);
            return c;
        } catch(SQLException ex){
                // handle any errors
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
                return 0;
        } finally{
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

    public Object[][] getOrders(String q, int numOfRows) {
        try {
            stmt = connection.createStatement();
            rs = stmt.executeQuery(q);
            rsmd = rs.getMetaData();

            newdata = new Object[numOfRows][7];
            int y=0;
            while (rs.next()) {
                String ID = rs.getString(1);
                newdata[y][0] = ID;

                String date = rs.getString(2);
                String[] parts = date.split(" ");
                newdata[y][1] = parts[1].trim();

                String status = rs.getString(3);
                newdata[y][2] = status;
                String fullPrice = rs.getString(4);
                newdata[y][3] = fullPrice;
                String ifcard = rs.getString(5);
                newdata[y][4] = ifcard;
                String phoneNumber = rs.getString(6);
                newdata[y][5] = phoneNumber;
                String adres = rs.getString(7);
                newdata[y][6] = adres;
                y++;
            }
            return newdata;
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            return null;
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

    public String[] getOrderedProductsID(String q) {
        try {
            stmt = connection.createStatement();
            rs = stmt.executeQuery(q);
            rsmd = rs.getMetaData();

            products = new String[200];
            int y=0;
            while (rs.next()) {
                String ID = rs.getString(1);
                products[y] = ID;
                y++;
            }
            products[y] = "n,"+y;

            return products;
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            return null;
        } finally {
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

    public String getProducts(String q) {
        try {
            stmt = connection.createStatement();
            rs = stmt.executeQuery(q);
            rsmd = rs.getMetaData();

            String ord_products="";
            while (rs.next()) {
                String name = rs.getString(1);
                ord_products += name;
                ord_products += " - ";
                String price = rs.getString(2);
                ord_products += price;
                ord_products += "zł - ";
                String diameter = rs.getString(3);
                ord_products += diameter;
                ord_products += "Φ";
            }
            return ord_products;

        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            return null;
        } finally {
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