import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket servSocket = new ServerSocket(9999);

        while (true) {
            try (Socket socket = servSocket.accept();
                 PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                 BufferedReader in = new BufferedReader(new
                         InputStreamReader(socket.getInputStream()))) {
                String line;
                while ((line = in.readLine()) != null) {
                    int number = Integer.parseInt(line);
                    out.println(fibonacci(number));
                    if (line.equals("end")) {
                        break;
                    }
                }
            } catch (IOException ex) {
                ex.printStackTrace(System.out);
            }
        }
    }

    public static long fibonacci(int number) {
        int last = 0;
        int next = 1;
        for (int i = 0; i < number; i++) {
            int oldLast = last;
            last = next;
            next = oldLast + next;
        }
        return last;
    }
}
