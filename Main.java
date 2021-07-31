import javax.swing.*;
import java.awt.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Main {
    static String Name;
    static JFrame frame = new JFrame("Chat Application");

    static Socket socket;
    static DataInputStream dataInputStream;
    static DataOutputStream dataOutputStream;

    private static void make_socket() {
        try {
            socket = new Socket("localhost",2048);
            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void reset() {
        frame.setVisible(false);
        frame = null;
        frame = new JFrame("Chat Application");

        frame.setLayout(null);
        frame.setIconImage(new ImageIcon("images/icon.jpg").getImage());
        frame.setSize(700, 700);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocation(380, 60);
        frame.setVisible(true);
    }

    static void View(int ViewType) {
        JLabel label;
        JButton button;
        JTextArea textArea;

        switch (ViewType) {
            case 1 :

                textArea = new JTextArea();
                textArea.setBounds(50,50,200,20);
                textArea.setForeground(new Color(0xC01520));
                textArea.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 17));

                button = new JButton("Next");
                button.setBounds(200,116,70,30);
                button.addActionListener(e -> {
                    Name = textArea.getText();
                    View(2);
                });


                label = new JLabel("Enter Your Name Here ::");
                label.setBounds(50,20,250,10);
                label.setForeground(Color.green);

                frame.add(button);
                frame.add(textArea);
                frame.add(label);

                frame.setLayout(null);
                frame.getContentPane().setBackground(new Color(0));
                frame.setIconImage(new ImageIcon("images/icon.jpg").getImage());
                frame.setSize(300, 200);
                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                frame.setLocation(550, 300);
                frame.setVisible(true);
                break;
            
            case 2 :
                reset();

                label = new JLabel("User ::  " + Name);
                label.setForeground(Color.BLACK);
                label.setBounds(20,30,label.getText().length() * 15,18);
                label.setFont(new Font(Font.DIALOG, Font.BOLD, 20));

                frame.add(label);

                make_background();
                break;

            
        }
    }

    private static void make_background() {
        JLabel label = new JLabel();
        label.setIcon(new ImageIcon("images/back.jpg"));
        label.setBounds(0,0,700, 700);

        frame.add(label);
    }

    public static void main(String[] args) {
//        make_socket();
        View(1);

    }
}
