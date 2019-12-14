package Server;

import java.sql.Array;
import java.util.ArrayList;

public class test {
    public static void main(String[] args) {
        AuthService.connect();
        System.out.println(AuthService.getBlackList("nick1", "nick2"));
        System.out.println(AuthService.getBlackList("nick1", "nick3"));
//        System.out.println(AuthService.getBlackList("nick1", "nick2"));
//        ArrayList<String> arr = AuthService.getBlackList("nick1", "nick2");
//        System.out.println(arr);
    }
}
