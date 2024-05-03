package Chatting.Application;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.FontUIResource;
import java.awt.*;//color
import java.awt.event.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Client extends JFrame implements ActionListener {
    JTextField text;
    static JPanel a1;
    Box vertical = Box.createVerticalBox();
    DataOutputStream dataOutputStream;

    Client() {
        setLayout(null);// Border,flow,box layout

        // header panel:----

        JPanel p1 = new JPanel();
        p1.setLayout(null);
        p1.setBackground(new Color(7, 94, 84));
        p1.setBounds(0, 0, 450, 65);
        add(p1);
        // back icon
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/3.png"));
        Image i2 = i1.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel back = new JLabel(i3);
        back.setBounds(5, 20, 25, 25);
        p1.add(back);
        back.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent ae) {
                System.exit(0);
            }
        });
        // profile photo
        ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("icons/2.png"));
        Image i5 = i4.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
        ImageIcon i6 = new ImageIcon(i5);
        JLabel profile = new JLabel(i6);
        profile.setBounds(40, 10, 50, 50);
        p1.add(profile);
        // video
        ImageIcon i7 = new ImageIcon(ClassLoader.getSystemResource("icons/video.png"));
        Image i8 = i7.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        ImageIcon i9 = new ImageIcon(i8);
        JLabel video = new JLabel(i9);
        video.setBounds(300, 20, 30, 30);
        p1.add(video);
        // audio
        ImageIcon i10 = new ImageIcon(ClassLoader.getSystemResource("icons/phone.png"));
        Image i11 = i10.getImage().getScaledInstance(35, 30, Image.SCALE_DEFAULT);
        ImageIcon i12 = new ImageIcon(i11);
        JLabel audio = new JLabel(i12);
        audio.setBounds(360, 20, 35, 30);
        p1.add(audio);

        // more option
        ImageIcon i13 = new ImageIcon(ClassLoader.getSystemResource("icons/3icon.png"));
        Image i14 = i13.getImage().getScaledInstance(10, 25, Image.SCALE_DEFAULT);
        ImageIcon i15 = new ImageIcon(i14);
        JLabel more = new JLabel(i15);
        more.setBounds(420, 20, 10, 25);
        p1.add(more);
        // profile name
        JLabel name = new JLabel("Ramesh2.O");
        name.setBounds(110, 15, 110, 18);
        name.setForeground(Color.white);
        name.setFont(new Font("SAN_SERIF", Font.BOLD, 18));
        p1.add(name);
        // active status
        JLabel status = new JLabel("Active Now");
        status.setBounds(110, 40, 80, 14);
        status.setForeground(Color.YELLOW);
        status.setFont(new Font("SAN_SERIF", Font.ITALIC, 14));
        p1.add(status);

        // text panel:--------------
        a1 = new JPanel();
//        a1.setLayout(null);
        a1.setLayout(new BoxLayout(a1, BoxLayout.Y_AXIS)); // Adjust layout to vertical
        JScrollPane scrollPane = new JScrollPane(a1);
        scrollPane.setBounds(0, 66, 450, 570);
        a1.setBackground(Color.CYAN);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); // Assuming you only need vertical scrolling
        add(scrollPane);
        // message panel:-------------
        text = new JTextField();
        text.setBounds(4, 645, 310, 35);
        text.setFont(new Font("SAN_SARIF", Font.PLAIN, 16));
        add(text);


        // sent button
        JButton send = new JButton("Send");
        send.setBounds(320, 645, 125, 35);
        text.setFont(new Font("SAN_SARIF", Font.PLAIN, 18));
        send.setBackground(Color.GREEN);
        send.setForeground(Color.white);
        send.addActionListener(this);
        add(send);
        // main frame
        setSize(450, 700);
        setLocation(800, 50);
        setUndecorated(true);
        getContentPane().setBackground(Color.WHITE);
        setVisible(true);// must be last statement
    }// end of costructor

    public void actionPerformed(ActionEvent ae) {
        String out = text.getText();
        JPanel p2 = formatLabel(out);
        a1.setLayout(new BorderLayout());
        JPanel right = new JPanel(new BorderLayout());
        right.add(p2, BorderLayout.LINE_END);
        vertical.add(right);
        vertical.add(Box.createVerticalStrut(10));
        a1.add(vertical, BorderLayout.PAGE_START);
        try {
            dataOutputStream.writeUTF(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
        text.setText("");
        repaint();
        invalidate();
        validate();
    }

    public static JPanel formatLabel(String out) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JLabel output = new JLabel("<html><p style=\"width: 150px\">" + out + "</p></html>");
        output.setFont(new Font("Tahoma", Font.PLAIN, 16));
        output.setBackground(Color.PINK);
        output.setOpaque(true);
        output.setBorder(new EmptyBorder(15, 15, 15, 50));
        panel.add(output);
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        JLabel time = new JLabel();
        time.setText(sdf.format(cal.getTime()));
        panel.add(time);
        return panel;
    }

    public void run_client() {
        try {
            Socket s = new Socket("127.0.0.1", 6002);
            DataInputStream dataInputStream = new DataInputStream(s.getInputStream());
            dataOutputStream = new DataOutputStream(s.getOutputStream());
            while (true) {
                a1.setLayout(new BorderLayout());
                String msg = dataInputStream.readUTF();
                JPanel panel = formatLabel(msg);
                JPanel left = new JPanel(new BorderLayout());
                left.add(panel, BorderLayout.LINE_START);
                vertical.add(left);
                vertical.add(Box.createVerticalStrut(10));
                a1.add(vertical, BorderLayout.PAGE_START);
                validate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Client client = new Client();
        client.run_client();

    }
}