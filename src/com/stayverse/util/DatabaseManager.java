package com.stayverse.util;

import java.sql.*;

public class DatabaseManager {
    private static DatabaseManager instance;
    private Connection connection;

    private DatabaseManager() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:database.sqlite");
            initializeDatabase();
        } catch (Exception e) { e.printStackTrace(); }
    }

    public static DatabaseManager getInstance() {
        if (instance == null) instance = new DatabaseManager();
        return instance;
    }

    public Connection getConnection() { return connection; }

    private void initializeDatabase() throws SQLException {
        Statement stmt = connection.createStatement();
        
        // --- STABILITY PRAGMAS ---
        stmt.execute("PRAGMA journal_mode=WAL;");
        stmt.execute("PRAGMA busy_timeout=5000;");
        stmt.execute("PRAGMA synchronous=NORMAL;");

        stmt.execute("CREATE TABLE IF NOT EXISTS users (email TEXT PRIMARY KEY, name TEXT, password TEXT, loyalty_level INTEGER)");
        stmt.execute("CREATE TABLE IF NOT EXISTS properties (id TEXT PRIMARY KEY, name TEXT, type TEXT, city TEXT, base_price REAL, rating REAL, description TEXT)");
        stmt.execute("CREATE TABLE IF NOT EXISTS bookings (id TEXT PRIMARY KEY, property_id TEXT, guest_name TEXT, check_in TEXT, check_out TEXT, total_cost REAL, status TEXT, created_at TEXT)");
        stmt.close();
    }
}
