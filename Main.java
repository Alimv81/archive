import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;

public class Main {
    public static int son = 50;
    public static int doral = 20;
    public static int number_of_zombies = 20;


    public static boolean small_window = true, back_image = true;
    public static JFrame frame = new JFrame("Plants Versus Zombies");


    public static void reset() throws IOException {
        frame.setVisible(false);
        frame = null;

        if (small_window){
            frame = new JFrame("plants_vs_zombies");
            frame.setBounds(590,200,300,400);
            if (back_image){
                JLabel label = new JLabel();
                label.setIcon(new ImageIcon(ImageIO.read(new File("images/first.jpg"))));
                label.setBounds(0,0,300,400);
                frame.add(label);
            }
            else {
                frame.getContentPane().setBackground(new Color(64, 59, 220));
            }
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(null);
            frame.setVisible(true);
        }
    }

    public static void show(int a) throws IOException {
        switch (a) {
            case -1 -> {
                reset();

                JButton button = new JButton("Next");

                JLabel label = new JLabel("amount of zombies:");
                JLabel label1 = new JLabel("amount of dorals:");
                JLabel label2 = new JLabel("amount of sons:");

                JLabel label3 = new JLabel(String.valueOf(number_of_zombies));
                JLabel label4 = new JLabel(String.valueOf(doral));
                JLabel label5 = new JLabel(String.valueOf(son));

                JSlider slider = new JSlider(0,100,number_of_zombies);
                JSlider slider1 = new JSlider(0,100,doral);
                JSlider slider2 = new JSlider(0,100,son);

                label.setBounds(10,5,150,10);
                slider.setBounds(10,20,200,70);
                label3.setBounds(250,40,50,10);

                label1.setBounds(10,105,150,10);
                slider1.setBounds(10,120,200,70);
                label4.setBounds(250,140,50,10);

                label2.setBounds(10,205,150,10);
                slider2.setBounds(10,220,200,70);
                label5.setBounds(250,240,50,10);

                button.setBounds(210,305,80,50);

                slider.addChangeListener(e -> {
                    number_of_zombies = slider.getValue();
                    label3.setText(String.valueOf(number_of_zombies));
                });

                slider1.addChangeListener(e -> {
                    doral = slider1.getValue();
                    label4.setText(String.valueOf(doral));
                });

                slider2.addChangeListener(e -> {
                    son =  slider2.getValue();
                    label5.setText(String.valueOf(son));
                });

                button.addActionListener(e -> {
                    try {
                        small_window = false;
                        back_image = false;

                        show(2);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                });

                frame.add(button);

                frame.add(label);
                frame.add(label1);
                frame.add(label2);

                frame.add(label3);
                frame.add(label4);
                frame.add(label5);

                frame.add(slider);
                frame.add(slider1);
                frame.add(slider2);
            }

            case 0 -> {
                reset();

                JButton button = new JButton("Play");
                JButton button1 = new JButton("Setting");
                JButton button2 = new JButton("Exit");

                button.setBounds(100, 40, 100, 60);
                button1.setBounds(100, 150, 100, 60);
                button2.setBounds(100, 260, 100, 60);

                button.addActionListener(e -> {
                    try {
                        small_window = false;
                        back_image = false;

                        show(2);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                });

                button1.addActionListener(e -> {
                    try {
                        back_image = false;

                        show(-1);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                });

                button2.addActionListener(e -> System.exit(0));

                frame.add(button);
                frame.add(button1);
                frame.add(button2);
            }

            case 1 -> {
                reset();

                JButton button3 = new JButton("Client");
                JButton button4 = new JButton("Offline");
                JButton button5 = new JButton("Host");

                button3.setBounds(100, 150, 100, 60);
                button4.setBounds(100, 40, 100, 60);
                button5.setBounds(100, 260, 100, 60);

                button3.addActionListener(e -> {
                    try {

                        show(3);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                });

                button4.addActionListener(e -> {
                    try {

                        show(0);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                });

                button5.addActionListener(e -> {
                    try {

                        show(4);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                });

                frame.add(button3);
                frame.add(button4);
                frame.add(button5);
            }

            case 2 -> {
                frame.setVisible(false);
                frame = null;

                new MyFrame(false, false);
            }

            case 3 -> {
                frame.setVisible(false);
                frame = null;

                new MyFrame(true, false);
            }
            case 4 -> {
                frame.setVisible(false);
                frame = null;

                new MyFrame(true, true);
            }

        }
    }

    public static void main(String[] args) throws IOException {
        show(1);
    }
}class MyFrame extends JFrame{
    MyPanel myPanel;

    public MyFrame(boolean a, boolean b){
        myPanel = new MyPanel(a, b);

        this.setTitle("plants vs zombies");

        this.add(myPanel);

        this.getContentPane().setBackground(new Color(49, 68, 165));

        this.setIconImage(new ImageIcon("first.jpg").getImage());
        this.pack();

        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

    }
}class MyPanel extends JPanel implements Runnable {

    public static int Now;

    boolean Online;
    boolean Host;

    public int amount_of_son;
    public int doral;
    public int number_of_zombies;

    String dragging = "n";

    ArrayList<Zombies> zombies = new ArrayList<>();
    ArrayList<Plants> plants = new ArrayList<>();
    ArrayList<Shot> shots = new ArrayList<>();
    ArrayList<Potato> potatoes = new ArrayList<>();
    ArrayList<Sun> suns = new ArrayList<>();
    ArrayList<Zombie2> zombies2 = new ArrayList<>();
    ArrayList<Plants2> plants2 = new ArrayList<>();

    static Socket socket, socket2;
    static ServerSocket serverSocket;

    static DataInputStream inputStream, inputStream2;
    static DataOutputStream outputStream, outputStream2;

    boolean did_player_plant_something = false, did_the_other_player_plant_something = false;
    int type_of_planted_object, type_of_planted_object_by_other_player;
    Point where_player_planted_point = new Point(), where_other_player_planted_point = new Point();

    boolean running = true;

    Image img;

    MyPanel(boolean a, boolean b) {
        Online = a;
        Host = b;

        amount_of_son = Main.son;
        doral = Main.doral;
        number_of_zombies = Main.number_of_zombies;


        ClicksListener clicksListener = new ClicksListener();
        this.addMouseListener(clicksListener);

        if (Online) {
            if (Host)
                make_server();
            else
                make_socket();
        }
    }


    public void Draw() {
        Graphics2D graphics2D = (Graphics2D) getGraphics();

        img = new ImageIcon("images/back.png").getImage();
        graphics2D.drawImage(img, 0, 0, null);

        this.setSize(1980, 1280);

        if (!Host){
            graphics2D.setPaint(new Color(0xBABD17));
            graphics2D.setFont(new Font("TimesRoman", Font.ITALIC, 30));
            graphics2D.drawString("Sons: " + amount_of_son, 100, 80);

        }
        else{
            graphics2D.setPaint(new Color(0xBABD17));
            graphics2D.setFont(new Font("TimesRoman", Font.ITALIC, 30));
            graphics2D.drawString("Dorals: " + doral, 100, 80);

        }

        graphics2D.setPaint(new Color(0xBABD17));
        graphics2D.setFont(new Font("TimesRoman", Font.ITALIC, 30));
        graphics2D.drawString("Zombies Left: " + number_of_zombies, 400, 80);

        if (Host) {
            img = new ImageIcon("images/3.png").getImage();
            graphics2D.drawImage(img, 0, 238, null);


            img = new ImageIcon("images/5.jpg").getImage();
            graphics2D.drawImage(img, 0, 502, null);
        } else {

            img = new ImageIcon("images/2.png").getImage();
            graphics2D.drawImage(img, 0, 150, null);

            img = new ImageIcon("images/1.jpg").getImage();
            graphics2D.drawImage(img, 0, 590, null);

            img = new ImageIcon("images/4.jpg").getImage();
            graphics2D.drawImage(img, 0, 326, null);

            img = new ImageIcon("images/6.png").getImage();
            graphics2D.drawImage(img, 0, 414, null);

        }


        for (Zombies zombie : zombies) {
            if (zombie.alive) {

                graphics2D.drawImage(zombie.image, zombie.place.x, zombie.place.y, null);

                graphics2D.setPaint(new Color(0x1818C1));
                graphics2D.setStroke(new BasicStroke(4));

                graphics2D.drawLine(zombie.place.x, zombie.place.y - 10, (zombie.place.x) + 70 * zombie.Hp / 300, zombie.place.y - 10);

                if (zombie.place.x <= 400){
                    running = false;
                }
            }
        }

        for (Plants plant : plants) {
            if (plant.alive) {
                graphics2D.setPaint(new Color(0xD90A0A));
                graphics2D.setStroke(new BasicStroke(4));

                graphics2D.drawLine(plant.place.x, plant.place.y - 10, plant.place.x + 70 * plant.Hp / 200, plant.place.y - 10);


                graphics2D.drawImage(plant.image, plant.place.x, plant.place.y, null);
            }
        }

        for (Shot shot : shots) {
            if (shot.alive) {
                graphics2D.drawImage(shot.image, shot.place.x, shot.place.y, null);
            }
        }

        for (Potato potato : potatoes) {
            if (potato.alive) {
                graphics2D.drawImage(potato.image, potato.place.x, potato.place.y, null);
            }
        }

        for (Sun sun : suns) {
            if (sun.alive) {

                graphics2D.setPaint(new Color(0xD90A0A));
                graphics2D.setStroke(new BasicStroke(4));

                graphics2D.drawLine(sun.place.x, sun.place.y - 10, sun.place.x + 70 * sun.Hp / 100, sun.place.y - 10);

                graphics2D.drawImage(sun.image, sun.place.x, sun.place.y, null);
            }
        }

        for (Zombie2 zombie : zombies2) {
            if (zombie.alive) {
                graphics2D.drawImage(zombie.image, zombie.place.x, zombie.place.y, null);

                graphics2D.setPaint(new Color(0x1818C1));
                graphics2D.setStroke(new BasicStroke(4));

                graphics2D.drawLine(zombie.place.x, zombie.place.y - 10, zombie.place.x + 70 * zombie.Hp / 450, zombie.place.y - 10);

                if (zombie.place.x <= 400){
                    running = false;
                }
            }
        }

        for (Plants2 plant : plants2) {
            if (plant.alive) {

                graphics2D.setPaint(new Color(0xD90A0A));
                graphics2D.setStroke(new BasicStroke(4));

                graphics2D.drawLine(plant.place.x, plant.place.y - 10, plant.place.x + 70 * plant.Hp / 300, plant.place.y - 10);

                graphics2D.drawImage(plant.image, plant.place.x, plant.place.y, null);
            }
        }
    }

    @Override
    public void run() {
        while (running) {

            update();
            Draw();


            Now += 50;

            if (Now % 10000 == 0) {
                amount_of_son += 25;
            }

            if (Now % 3000 == 0){
                doral += 9;
            }


            if (Online) {
                try {
                    connect();
                    update_for_online();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else if(Now % 8000 == 0 && number_of_zombies != 0){
                update_for_offline();
            }

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void update() {

        for (Zombies zombie : zombies) {
            if (zombie.alive) {
                for (Plants plant : plants) {
                    if (plant.place.y == zombie.place.y && plant.alive) {
                        if (plant.should_shoot()) {
                            shots.add(new Shot(new Point(plant.place.x, plant.place.y + 20)));
                            plant.setLast_shot(Now);
                        }

                        if (plant.place.x + 70 > zombie.place.x && plant.place.x < zombie.place.x && plant.alive) {

                            zombie.stop = true;
                        }
                    }

                }

                for (Plants2 plant : plants2) {
                    if (plant.place.y == zombie.place.y && plant.alive) {
                        if (plant.should_shoot()) {
                            shots.add(new Shot(new Point(plant.place.x, plant.place.y + 20)));
                            shots.add(new Shot(new Point(plant.place.x - 20, plant.place.y + 20)));
                            plant.setLast_shot(Now);
                        }

                        if (plant.place.x + 70 > zombie.place.x && plant.place.x < zombie.place.x && plant.alive) {

                            zombie.stop = true;
                        }
                    }

                }

                for (Sun sun : suns) {
                    if (sun.alive && sun.place.y == zombie.place.y) {
                        if (sun.place.x + 70 > zombie.place.x && sun.place.x < zombie.place.x) {

                            zombie.stop = true;
                        }
                    }
                }
                for (Shot shot : shots) {
                    if (shot.place.y - 20 == zombie.place.y && shot.alive) {
                        if (shot.place.x + 10 > zombie.place.x && shot.place.x - 10 < zombie.place.x) {
                            zombie.get_hit(shot.hit());
                        }
                    }
                }
                if (!zombie.stop) {
                    zombie.update();
                } else {
                    boolean is_there = false;
                    for (Plants plant : plants) {
                        if (plant.place.y == zombie.place.y && plant.alive) {
                            if (zombie.should_hit()) {
                                if (plant.place.x + 70 > zombie.place.x && plant.place.x < zombie.place.x) {
                                    is_there = true;
                                    zombie.setLast_hit();
                                    plant.get_hit(zombie.hit());
                                    if (!plant.alive) {
                                        zombie.stop = false;
                                    }
                                }
                            }
                        }
                    }

                    for (Plants2 plant : plants2) {
                        if (plant.place.y == zombie.place.y && plant.alive) {
                            if (zombie.should_hit()) {
                                if (plant.place.x + 70 > zombie.place.x && plant.place.x < zombie.place.x) {
                                    is_there = true;
                                    zombie.setLast_hit();
                                    plant.get_hit(zombie.hit());
                                    if (!plant.alive) {
                                        zombie.stop = false;
                                    }
                                }
                            }
                        }
                    }

                    for (Sun sun : suns) {
                        if (sun.place.y == zombie.place.y && sun.alive) {
                            if (zombie.should_hit()) {
                                if (sun.place.x + 70 > zombie.place.x && sun.place.x < zombie.place.x) {
                                    is_there = true;
                                    zombie.setLast_hit();
                                    sun.get_hit(zombie.hit());
                                    if (!sun.alive) {
                                        zombie.stop = false;
                                    }
                                }
                            }
                        }
                    }
                    if (!is_there) {
                        zombie.stop = false;
                    }
                }
            }
        }

        for (Zombie2 zombie : zombies2) {
            if (zombie.alive) {
                for (Plants plant : plants) {
                    if (plant.place.y == zombie.place.y && plant.alive) {
                        if (plant.should_shoot()) {
                            shots.add(new Shot(new Point(plant.place.x, plant.place.y + 20)));
                            plant.setLast_shot(Now);
                        }

                        if (plant.place.x + 70 > zombie.place.x && plant.place.x < zombie.place.x && plant.alive) {

                            zombie.stop = true;
                        }
                    }

                }

                for (Plants2 plant : plants2) {
                    if (plant.place.y == zombie.place.y && plant.alive) {
                        if (plant.should_shoot()) {
                            shots.add(new Shot(new Point( plant.place.x, plant.place.y + 20)));
                            shots.add(new Shot(new Point( plant.place.x - 20, plant.place.y + 20)));
                            plant.setLast_shot(Now);
                        }

                        if (plant.place.x + 70 > zombie.place.x && plant.place.x < zombie.place.x && plant.alive) {

                            zombie.stop = true;
                        }
                    }

                }

                for (Sun sun : suns) {
                    if (sun.alive && sun.place.y == zombie.place.y) {
                        if (sun.place.x + 70 > zombie.place.x && sun.place.x < zombie.place.x) {

                            zombie.stop = true;
                        }
                    }
                }
                for (Shot shot : shots) {
                    if (shot.place.y - 20 == zombie.place.y && shot.alive) {
                        if (shot.place.x + 10 > zombie.place.x && shot.place.x - 10 < zombie.place.x) {
                            zombie.get_hit(shot.hit());
                        }
                    }
                }
                if (!zombie.stop) {
                    zombie.update();
                } else {
                    boolean is_there = false;

                    for (Plants plant : plants) {
                        if (plant.place.y == zombie.place.y && plant.alive) {
                            if (zombie.should_hit()) {
                                if (plant.place.x + 70 > zombie.place.x && plant.place.x < zombie.place.x) {
                                    is_there = true;
                                    zombie.setLast_hit();
                                    plant.get_hit(zombie.hit());
                                    if (!plant.alive) {
                                        zombie.stop = false;
                                    }
                                }
                            }
                        }
                    }

                    for (Plants2 plant : plants2) {
                        if (plant.place.y == zombie.place.y && plant.alive) {
                            if (zombie.should_hit()) {
                                if (plant.place.x + 70 > zombie.place.x && plant.place.x < zombie.place.x) {
                                    is_there = true;
                                    zombie.setLast_hit();
                                    plant.get_hit(zombie.hit());
                                    if (!plant.alive) {
                                        zombie.stop = false;
                                    }
                                }
                            }
                        }
                    }

                    for (Sun sun : suns) {
                        if (sun.place.y == zombie.place.y && sun.alive) {
                            if (zombie.should_hit()) {
                                if (sun.place.x + 70 > zombie.place.x && sun.place.x < zombie.place.x) {
                                    is_there = true;
                                    zombie.setLast_hit();
                                    sun.get_hit(zombie.hit());
                                    if (!sun.alive) {
                                        zombie.stop = false;
                                    }
                                }
                            }
                        }
                    }
                    if (!is_there) {
                        zombie.stop = false;
                    }
                }
            }
        }

        for (Shot shot : shots) {
            shot.update();
        }

        for (Potato potato : potatoes) {
            for (Zombies zombie : zombies) {
                if (potato.place.y == zombie.place.y && potato.place.x + 60 > zombie.place.x && potato.place.x - 60 < zombie.place.x && potato.alive && zombie.alive) {
                    zombie.die();
                    potato.die();
                }
            }

            for (Zombie2 zombie : zombies2) {
                if (potato.place.y == zombie.place.y && potato.place.x + 60 > zombie.place.x && potato.place.x - 60 < zombie.place.x && potato.alive && zombie.alive) {
                    zombie.die();
                    potato.die();
                }
            }
        }

        for (Sun sun : suns) {
            if (sun.alive && sun.should_give()) {
                amount_of_son += Sun.give;
                sun.setLast_give(Now);
            }
        }
    }

    public void update_for_online() {
        if (Host) {
            if (did_the_other_player_plant_something) {
                int type = type_of_planted_object_by_other_player;
                Point point = new Point(where_other_player_planted_point);

                if (type == 0){
                    plants.add(new Plants(new Point(point)));
                }
                else if (type == 1){
                    potatoes.add(new Potato(new Point(point)));
                }
                else if (type == 2){
                    suns.add(new Sun(new Point(point)));
                }
                else {
                    plants2.add(new Plants2(new Point(point)));
                }
            }
        } else {
            if (did_player_plant_something) {
                number_of_zombies --;

                int type = type_of_planted_object;
                Point point = new Point(where_player_planted_point);

                if (type == 0){
                    zombies.add(new Zombies(new Point(point)));
                }
                else{
                    zombies2.add(new Zombie2(new Point(point)));
                }
            }
        }

        did_player_plant_something = false;
        did_the_other_player_plant_something = false;
    }

    public void update_for_offline(){
        int type = RandomManager.getRandom() % 2;
        int line = RandomManager.getRandom() % 5;

        Point point = new Point();

        if (line == 0) {
            point.y = 220;
        } else if (line == 1) {
            point.y = 320;
        } else if (line == 2) {
            point.y = 420;
        } else if (line == 3) {
            point.y = 520;
        } else {
            point.y = 630;
        }

        point.x = 1500;

        if (type == 0){
            zombies.add(new Zombies(new Point(point)));
        }
        else {
            zombies2.add(new Zombie2(new Point(point)));
        }

        number_of_zombies --;
    }

    @Override
    public void addNotify() {
        super.addNotify();
        Thread animationThread = new Thread(this);
        animationThread.start();
    }

    static void make_socket() {
        try {
            socket = new Socket("localhost", 8765);

            inputStream = new DataInputStream(socket.getInputStream());
            outputStream = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void make_server() {
        try {
            serverSocket = new ServerSocket(8765);

            socket2 = serverSocket.accept();

            inputStream2 = new DataInputStream(socket2.getInputStream());
            outputStream2 = new DataOutputStream(socket2.getOutputStream());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void connect() throws IOException {
        if (Host) {
            outputStream2.writeBoolean(did_player_plant_something);
            if (did_player_plant_something) {
                outputStream2.writeInt(type_of_planted_object);
                outputStream2.writeInt(where_player_planted_point.x);
                outputStream2.writeInt(where_player_planted_point.y);
            }
            did_the_other_player_plant_something = inputStream2.readBoolean();

            if (did_the_other_player_plant_something) {
                type_of_planted_object_by_other_player = inputStream2.readInt();
                where_other_player_planted_point.x = inputStream2.readInt();
                where_other_player_planted_point.y = inputStream2.readInt();
            }
        } else {
            did_player_plant_something = inputStream.readBoolean();

            if (did_player_plant_something) {
                type_of_planted_object = inputStream.readInt();
                where_player_planted_point.x = inputStream.readInt();
                where_player_planted_point.y = inputStream.readInt();
            }

            outputStream.writeBoolean(did_the_other_player_plant_something);
            if (did_the_other_player_plant_something) {
                outputStream.writeInt(type_of_planted_object_by_other_player);
                outputStream.writeInt(where_other_player_planted_point.x);
                outputStream.writeInt(where_other_player_planted_point.y);
            }
        }

    }

    class ClicksListener extends MouseAdapter {

        Point previous;

        @Override
        public void mousePressed(MouseEvent e) {
            super.mousePressed(e);
            previous = e.getPoint();

            if (previous.x < 70 && 150 < previous.y) {
                if (previous.y < 238) {
                    dragging = "p";
                } else if (previous.y < 326) {
                    dragging = "z";
                } else if (previous.y < 414) {
                    dragging = "p1";
                } else if (previous.y < 502) {
                    dragging = "s";
                } else if (previous.y < 590) {
                    dragging = "z2";
                }
                else if (previous.y < 678){
                    dragging = "p2";
                }
            }


        }

        @Override
        public void mouseReleased(MouseEvent event) {
            super.mouseReleased(event);

            Point point = event.getPoint();

            if (point.y < 300) {
                point.y = 220;
            } else if (point.y < 400) {
                point.y = 320;
            } else if (point.y < 500) {
                point.y = 420;
            } else if (point.y < 600) {
                point.y = 520;
            } else {
                point.y = 630;
            }

            if (point.x < 400) {
                point.x = 400;
            }

            switch (dragging) {
                case "n":
                    break;

                case "z":
                    point.x = 1500;

                    if (doral >= Zombies.price && Zombies.can_be_planted() && Host && number_of_zombies != 0) {
                        zombies.add(new Zombies(point));
                        doral -= Zombies.price;
                        Zombies.setLast_planted(Now);
                        did_player_plant_something = true;
                        where_player_planted_point = point;
                        type_of_planted_object = 0;
                        number_of_zombies --;
                    }
                    break;
                case "p":
                    if (amount_of_son >= Plants.price && Plants.can_be_planted() && !Host) {
                        plants.add(new Plants(point));
                        amount_of_son -= Plants.price;
                        Plants.setLast_planted(Now);
                        did_the_other_player_plant_something = true;
                        where_other_player_planted_point = point;
                        type_of_planted_object_by_other_player = 0;
                    }
                    break;
                case "p1":
                    if (amount_of_son >= Potato.price && Potato.can_be_planted() && !Host) {
                        potatoes.add(new Potato(point));
                        amount_of_son -= Potato.price;
                        Plants.setLast_planted(Now);
                        did_the_other_player_plant_something = true;
                        where_other_player_planted_point = point;
                        type_of_planted_object_by_other_player = 1;
                    }
                    break;
                case "s":
                    if (amount_of_son >= Sun.price && Sun.can_be_planted() && !Host) {
                        suns.add(new Sun(point));
                        amount_of_son -= Sun.price;
                        Sun.setLast_planted();
                        did_the_other_player_plant_something = true;
                        where_other_player_planted_point = point;
                        type_of_planted_object_by_other_player = 2;
                    }
                    break;
                case "z2":
                    point.x = 1500;

                    if (doral >= Zombie2.price && Zombie2.can_be_planted() && Host && number_of_zombies != 0) {
                        zombies2.add(new Zombie2(point));
                        doral -= Zombie2.price;
                        Zombie2.setLast_planted(Now);
                        did_player_plant_something = true;
                        where_player_planted_point = point;
                        type_of_planted_object = 1;
                        number_of_zombies --;
                    }

                    break;
                case "p2":
                    if (amount_of_son >= Plants2.price && Plants2.can_be_planted() && !Host){
                        plants2.add(new Plants2(point));
                        amount_of_son -= Plants2.price;
                        Plants2.setLast_planted(Now);
                        did_the_other_player_plant_something = true;
                        where_other_player_planted_point = point;
                        type_of_planted_object_by_other_player = 3;
                    }

                    break;
            }


            dragging = "n";
        }
    }
}class Plants {

    public static int price = 20;

    public int last_shot = 0;

    public int hitting_cool_down = 5000;

    public boolean alive = true;

    public static int cool_down = 0;

    public static int last_planted = 0;

    public Image image = new ImageIcon("images/2.png").getImage();

    public Point place;

    public int Hp = 200;

    public void die(){
        alive = false;
    }

    public Plants(Point point){
        place = point;

    }

    public void get_hit(int a){
        Hp -= a;

        if(Hp <= 0){
            die();
        }
    }

    public static void setLast_planted(int planted){
        last_planted = planted;
    }

    public static boolean can_be_planted(){
        return MyPanel.Now -last_planted > cool_down;
    }

    public boolean should_shoot(){
        return MyPanel.Now - last_shot > hitting_cool_down;
    }

    public void setLast_shot(int shot){
        last_shot = shot;
    }

}class Plants2 {

    public static int price = 30;

    public int last_shot = 0;

    public int hitting_cool_down = 7000;

    public boolean alive = true;

    public static int cool_down = 0;

    public static int last_planted = 0;

    public Image image = new ImageIcon("images/1.jpg").getImage();

    public Point place;

    public int Hp = 300;

    public void die(){
        alive = false;
    }

    public Plants2(Point point){
        place = point;

    }

    public void get_hit(int a){
        Hp -= a;

        if(Hp <= 0){
            die();
        }
    }

    public static void setLast_planted(int planted){
        last_planted = planted;
    }

    public static boolean can_be_planted(){
        return MyPanel.Now -last_planted > cool_down;
    }

    public boolean should_shoot(){
        return MyPanel.Now - last_shot > hitting_cool_down;
    }

    public void setLast_shot(int shot){
        last_shot = shot;
    }

}

class Potato{

    public static int price = 50;

    public Point place;

    public static int last_planted = 0;

    public static int cool_down = 5000;

    public Image image = new ImageIcon("images/4.jpg").getImage();

    public int damage = 200;

    public boolean alive = true;

    public Potato(Point point) {
        place = point;
    }

    public int hit(){
        die();
        return damage;
    }

    public void die(){
        alive = false;
    }

    public static void setLast_planted(int last_planted) {
        Potato.last_planted = last_planted;
    }

    public static boolean can_be_planted(){
        return MyPanel.Now - last_planted > cool_down;
    }
}

class RandomManager {

    static Random random = new Random();

    public static Integer getRandom(){
        return random.nextInt(5);
    }

    public static void main(String[] args) {
        System.out.println(getRandom());
    }
}
class Shot {

    public boolean alive = true;

    public int damage = 20;

    public Image image = new ImageIcon("images/7.png").getImage();

    public Point place;

    public Shot(Point point){
        this.place = point;
    }

    public int hit(){
        alive = false;
        return damage;
    }

    public void update(){
        this.place.x += 16;
        if (place.x > 1600){
            alive = false;
        }
    }
}
class Sun {

    public static int price = 40;

    public static int give = 25;

    public int last_give = 0;

    public int give_cool_down = 7000;

    public static int cool_down = 2000;

    public static int last_planted = 0;

    public Image image = new ImageIcon("images/6.png").getImage();

    public boolean alive = true;

    public int Hp = 100;

    public Point place;

    public Sun(Point point){
        place = point;
        last_give = MyPanel.Now;
    }

    public int update(){
        return 20;
    }

    public void die(){
        alive = false;
    }

    public static boolean can_be_planted(){
        return MyPanel.Now - last_planted > cool_down;
    }

    public boolean should_give(){
        return MyPanel.Now - last_give > give_cool_down;
    }

    public void setLast_give(int shot){
        last_give = shot;
    }

    public static void setLast_planted(){
        last_planted = MyPanel.Now;
    }

    public void get_hit(int hit){
        Hp -= hit;

        if (Hp <= 0){
            alive = false;
        }
    }
}
class Zombie2 {

    public boolean stop = false;

    public static int last_planted = 0;

    public static int price = 37;

    public int last_hit = 0;

    public static int hitting_cool_down = 1000;

    public static int cool_down_time = 2000;

    public boolean alive = true;

    public Image image = new ImageIcon("images/5.jpg").getImage();;

    public Point place;

    public int Hp = 450;
    public int damage  = 45;

    public Zombie2(Point Point){

        this.place = Point;

    }

    public void die(){
        alive = false;
    }

    public void update(){
        this.place.x -= 1;
    }

    public int hit(){
        return damage;
    }

    public void get_hit(int x){
        Hp -= x;

        if (Hp <= 0){
            die();
        }
    }

    public static void setLast_planted(int planted){
        last_planted = planted;
    }

    public static boolean can_be_planted(){
        return MyPanel.Now -last_planted > cool_down_time;
    }

    public boolean should_hit(){
        return MyPanel.Now - last_hit > hitting_cool_down;
    }

    public void setLast_hit(){
        last_hit = MyPanel.Now;
    }
}
class Zombies {

    public boolean stop = false;

    public static int last_planted = 0;

    public static int price = 25;

    public int last_hit = 0;

    public static int hitting_cool_down = 1000;

    public static int cool_down_time = 2500;

    public boolean alive = true;

    public Image image = new ImageIcon("images/3.png").getImage();;

    public Point place;

    public int Hp = 300;
    public int damage  = 30;

    public Zombies(Point Point){

        this.place = Point;

    }

    public void die(){
        alive = false;
    }

    public void update(){
        this.place.x -= 1;
    }

    public int hit(){
        return damage;
    }

    public void get_hit(int x){
        Hp -= x;

        if (Hp <= 0){
            die();
        }
    }

    public static void setLast_planted(int planted){
        last_planted = planted;
    }

    public static boolean can_be_planted(){
        return MyPanel.Now -last_planted > cool_down_time;
    }

    public boolean should_hit(){
        return MyPanel.Now - last_hit > hitting_cool_down;
    }

    public void setLast_hit(){
        last_hit = MyPanel.Now;
    }
}
