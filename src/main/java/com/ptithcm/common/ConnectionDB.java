package com.ptithcm.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {

  public final static String host = System.getenv("DB_HOST");
  public final static String port = System.getenv("DB_PORT");
  public final static String databaseName = System.getenv("DB_DATABASE");
  public final static String password = System.getenv("DB_PASSWORD");
  public final static String user = System.getenv("DB_USER");
  public final static String db = System.getenv("DB");


  public static String connectionString() {
    return String.format("jdbc:%s://%s:%s/%s", db, host, port, databaseName);
  }

  public static Connection getConnection() throws SQLException {
    return DriverManager.getConnection(connectionString(), user, password);
  }


}
