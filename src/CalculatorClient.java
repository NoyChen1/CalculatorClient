import java.io.*;
import java.net.*;

public class CalculatorClient {

    public static void main(String[] args) {
        try {

            Socket socket = new Socket("localhost", 1234);
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));


            System.out.println("Enter your expression: ");
            String expression = userInput.readLine();
            String [] oparts = expression.split(" ");

            out.println(oparts[1]);
            out.println(oparts[0]);
            out.println(oparts[2]);


            double result = Double.parseDouble(in.readLine());
            System.out.println("Result: " + result);


            userInput.close();
            in.close();
            out.close();
            socket.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
