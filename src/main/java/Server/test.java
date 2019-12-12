package Server;

public class test {
    public static void main(String[] args) {
        AuthService.connect();
        AuthService.addToBlackList("nick1", "nick2");
//        AuthService.getNickByLoginAndPass("login1", "pass1");

//        System.out.println(AuthService.getIdByNickname("login1"));
    }
}
