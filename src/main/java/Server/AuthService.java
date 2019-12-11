package Server;

import java.sql.*;

public class AuthService {
    private static Connection connection;
    private static Statement stmt;

    public static void connect(){
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:users.db");
            stmt = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static String getNickByLoginAndPass(String login, String pass) {
        String sql = String.format("select nickname from users where login = '%s' and password = '%s'", login, pass);
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery(sql);
            if(rs.next()){
                return rs.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  null;
    }
    public static void addUser(String login, String pass, String nick) {
        try {
            String query = "INSERT INTO main (login, password, nickname) VALUES (?, ?, ?);";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, login);
            ps.setInt(2, pass.hashCode());
            ps.setString(3, nick);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addToBlackList(String userNick, String blacklistNick) {
        String sql1 = String.format("select id from users where login = '%s'", userNick);
        System.out.println(sql1);
        String sql2 = String.format("select id from users where login = '%s'", blacklistNick);
//        System.out.println(bl_user_id);

        ResultSet rs = null;
        int uid = 0;
        try {
            rs = stmt.executeQuery(sql1);
//            if(rs.next()){
                uid = rs.getInt(1);
                System.out.println(uid);

//            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


//        int uid = Integer.parseInt(user_id);
//        int bluid = Integer.parseInt(bl_user_id);
        try {
            String query = "INSERT INTO blacklist (user_id /*,bl_user_id*/) VALUES (?/*, ?*/);";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, uid);
//            ps.setInt(2, bluid);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void disconnect(){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
