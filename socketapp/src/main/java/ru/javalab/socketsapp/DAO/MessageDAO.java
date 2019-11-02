package ru.javalab.socketsapp.DAO;


import ru.javalab.socketsapp.entities.Message;

import java.sql.*;
import java.time.LocalDateTime;

public class MessageDAO {
    public static Message save(long sender_id, LocalDateTime dateTime, String text) {
        try {
            Connection conn = Properties.getConnection();
            PreparedStatement ps = conn.prepareStatement(
                    "INSERT INTO messages (sender_id, dateTime, text) VALUES (?,?,?)"
            );
            ps.setLong(1, sender_id);
            ps.setObject(2, dateTime);
            ps.setString(3, text);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }
}
