import javax.swing.*;
import java.awt.*;

public class UserInterface {

    //A Boundary Class which deals only with the User Interface

    public UserInterface() {
        createScreen();
    }

    //Using Border Layout Manager
    private void createScreen() {
        JFrame f=new JFrame();

        JButton b1=new JButton("NORTH");
        JButton b2=new JButton("SOUTH");
        JButton b3=new JButton("EAST");
        JButton b4=new JButton("WEST");
        JButton b5=new JButton("CENTER");

        f.add(b1, BorderLayout.NORTH);
        f.add(b2,BorderLayout.SOUTH);
        f.add(b3,BorderLayout.EAST);
        f.add(b4,BorderLayout.WEST);
        f.add(b5,BorderLayout.CENTER);

        f.setSize(600,600);
        f.setVisible(true);
    }
}
