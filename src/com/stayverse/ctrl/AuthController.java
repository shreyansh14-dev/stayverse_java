package com.stayverse.ctrl;

import com.stayverse.model.User;
import com.stayverse.model.GeniusUser;
import com.stayverse.util.DatabaseManager;
import java.sql.*;

public class AuthController {
    public User login(String email, String password) {
        String sql = "SELECT * FROM users WHERE email = ? AND password = ?";
        try (PreparedStatement pstmt = DatabaseManager.getInstance().getConnection().prepareStatement(sql)) {
            pstmt.setString(1, email);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                if (rs.getInt("loyalty_level") >= 5) return new GeniusUser(rs.getString("email"), rs.getString("name"));
                return new User(rs.getString("email"), rs.getString("name"), rs.getInt("loyalty_level"));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public boolean register(String email, String name, String password) {
        String sql = "INSERT INTO users(email, name, password, loyalty_level) VALUES(?,?,?,?)";
        try (PreparedStatement pstmt = DatabaseManager.getInstance().getConnection().prepareStatement(sql)) {
            pstmt.setString(1, email);
            pstmt.setString(2, name);
            pstmt.setString(3, password);
            pstmt.setInt(4, 0);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) { return false; }
    }
}
