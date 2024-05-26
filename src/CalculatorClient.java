import java.io.*;
import java.net.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CalculatorClient {
    private static JTextField inputField;
    private static JTextArea resultArea;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Calculator Client");
        inputField = new JTextField(30);
        resultArea = new JTextArea(10, 30);
        resultArea.setEditable(false);

        JButton sendButton = new JButton("Send");
        sendButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sendExpression();
            }
        });

        JPanel panel = new JPanel();
        panel.add(inputField);
        panel.add(sendButton);

        frame.add(panel, BorderLayout.NORTH);
        frame.add(new JScrollPane(resultArea), BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private static void sendExpression() {
        try (Socket socket = new Socket("localhost", 5000);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            String expression = inputField.getText();
            out.println(expression);
            String result = in.readLine();
            resultArea.append(expression + " = " + result + "\n");
        } catch (IOException e) {
            resultArea.append("Error: " + e.getMessage() + "\n");
        }
    }
}
