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

        try {
            String  user_id = getIdByNickname(userNick);
//            System.out.println(user_id);
            String bl_user_id = getIdByNickname(blacklistNick);
            System.out.println(bl_user_id);

            String query = "INSERT INTO blacklist (user_id ,bl_user_id) VALUES (?, ?);";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, Integer.parseInt(user_id));
            ps.setInt(2, Integer.parseInt(bl_user_id));
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String getIdByNickname(String userNick) {
        String sql = String.format("select id from users where nickname = '%s'", userNick);
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
//        String sql = String.format("select id from users where login = '%s'", userNick);
//        ResultSet rs = null;
//        try {
//            rs = stmt.executeQuery(sql);
//            System.out.println(rs);
//            if(rs.next()){
//                return rs.getString(1);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return  null;
//    }


    public static void disconnect(){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
