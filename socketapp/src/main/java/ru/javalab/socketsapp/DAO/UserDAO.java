package ru.javalab.socketsapp.DAO;

import ru.javalab.socketsapp.entities.User;

import java.sql.*;

public class UserDAO {

    public static User get(String login) {
        try {
            Connection conn = Properties.getConnection();
            PreparedStatement ps = conn.prepareStatement(
                    "select * from users where login = ?"
            );
            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new User (
                        rs.getLong(1), rs.getString(2), rs.getString(3)
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }
}
