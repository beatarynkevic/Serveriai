package serveriai;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.ServerSocket;
import java.net.Socket;

public class Serveriai {

    public static final int PORT = 9999; //final reiskai ,kad niekada nekeis reiksmes

    public static void main(String[] args) {
        try (
                ServerSocket ss = new ServerSocket(PORT);) {
            try (
                    Socket s = ss.accept(); //sedesiu ir lauksiu laisko
                    Reader sr = new InputStreamReader(s.getInputStream(), "UTF-8");
                    BufferedReader br = new BufferedReader(sr);) {

                String line;
                do {
                    line = br.readLine();
                    if (line != null) {
                        System.out.println(line);
                    }
                } while (line != null || !"".equals(line));
//                bw.write("HTTP/1.1")
            } catch (IOException ex) {
                System.out.println("Port already in use");
            }
        } catch (IOException ex) {
            System.out.println("Port " + PORT + "already in use");
        }

    }
}
