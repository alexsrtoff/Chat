package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler {
    private Socket socket;
    DataInputStream in;
    DataOutputStream out;
    MainServ serv;

    public String getNick() {return nick;}

    String nick;

    public ClientHandler(MainServ serv, Socket socket){
    try {
        this.socket = socket;
        this.serv = serv;
        this.in = new DataInputStream(socket.getInputStream());
        this.out = new DataOutputStream(socket.getOutputStream());

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    while (true) {
                        String msg = in.readUTF();
                        if (msg.startsWith("/auth")) {
                            String[] tockens = msg.split(" ");
                            String newNick = AuthService.getNickByLoginAndPass(tockens[1], tockens[2]);
                            if(serv.checkNick(newNick)){
                                sendMsg("Логин/ник занят. Введите другой логин");
                            }
                            else if(newNick != null){
                                sendMsg("/authok");
                                nick = newNick;
                                serv.subscribe(ClientHandler.this);
                                break;
                            }else{
                                sendMsg("Неверный логин/пароль");
                            }
                        }
                    }

                    while (true) {
                        String msg = in.readUTF();
                        if (msg.equals("/end")) {
                            out.writeUTF("/serverClosed");
                            break;
                        }
                        if(msg.startsWith("/w")) {
                            serv.sendPrivateMsg(nick, msg);
                        }else if(msg.startsWith("/bl")){
                            String tockens[] =msg.split(" ");
                            if(AuthService.getIdByNickname(tockens[1]) != null){
                                AuthService.addToBlackList(nick, tockens[1]);
                                sendMsg("Пользователь: " + tockens[1] + " в черном списке.");
                            }else sendMsg("Вы хотите добавить в черный список несуществующего пользователя");
                        }else serv.broadcastMsg(nick + ": " + msg);

                    }



                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        in.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        out.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }serv.unsubscribe(ClientHandler.this);
                }
            }
        }).start();
    } catch (IOException e) {
        e.printStackTrace();
    }
}


    public void sendMsg(String msg) {
        try {
            out.writeUTF(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
