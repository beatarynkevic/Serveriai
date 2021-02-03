package serveriai;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;

public class Serveriai {

    public static final int PORT = 9999; //final reiskai ,kad niekada nekeis reiksmes

    public static void main(String[] args) {
        boolean running = true;
        try (ServerSocket ss = new ServerSocket(PORT);) {
            while (running) {
                try (
                        Socket s = ss.accept(); //sedesiu ir lauksiu laisko
                        Reader sr = new InputStreamReader(s.getInputStream(), "UTF-8");
                        BufferedReader br = new BufferedReader(sr);
                        Writer sw = new OutputStreamWriter(s.getOutputStream(), "UTF-8");
                        BufferedWriter bw = new BufferedWriter(sw);) {
//                    String line;
//                do {
//                    line = br.readLine();
//                    if (line != null) {
//                        System.out.println(line);
//                    }
//                } while (line != null && !"".equals(line));

                    String firstLine = br.readLine();
                    if (firstLine != null) {
                        String[] parts = firstLine.split(" ");
                        if (parts.length != 3 || !"HTTP/1.1".equals(parts[2])) {
                            bw.write("HTTP/1.1 400 Bad Request\r\n");
                            bw.write("\r\n");
                        } else {
                            if ("/end".equals(parts[1])) {
                                bw.write("HTTP/1.1 200 OK\r\n");
                                bw.write("\r\n");
                                bw.write("<html><body><h1>server is down </h1></body></html>");
                                running = false;
                            } else {
                                bw.write("HTTP/1.1 200 OK\r\n");
                                bw.write("\r\n");
                                bw.write("<html><body><h1>Hello World!!! </h1></body></html>");
                            }
                        }
                    } else {
                    bw.write("HTTP/1.1 400 Bad request\r\n");
                    bw.write("\r\n");
                    bw.write("<html><body><h1>Hello World!!! </h1></body></html>");
                    }
                    } catch (IOException ex) {
                    System.out.println(ex);
                }
            }
        } catch (IOException ex) {
            System.out.println("Port " + PORT + "already in use");
        }

    }
}
