package AppInterface;

import AppInterface.UserInterface.Navigation;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Window {

    public static int width = 1600;
    public static int height = 1000;
    public static JFrame frame;
    private static String AppName = "Московское метро";
    public static Panel panel;
    public static GridBagLayout gridBagLayout;
    public static GridBagConstraints gridBagConstraints;

    private static Navigation navigation;

    public static void runApplication() throws IOException {

        frame = new JFrame(AppName);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(0,0);



        frame.setSize(width, height);

        //gridBagLayout = new GridBagLayout();

        frame.setLayout(new BorderLayout());

//        gridBagConstraints = new GridBagConstraints();
//        gridBagConstraints.anchor = GridBagConstraints.EAST;
//        gridBagConstraints.ipadx = 300;
//        gridBagConstraints.ipady = 100;

        navigation = new Navigation();

        panel = new Panel();

        JPanel jp = new JPanel();

        jp.setLayout(new BorderLayout());

        jp.add(navigation,BorderLayout.NORTH);

        jp.setPreferredSize(new Dimension(200,500));
        //frame.add(navigation, gridBagConstraints);


        frame.add(panel);
        frame.add(jp, BorderLayout.EAST);

        frame.setVisible(true);

        run();

    }

    public static void run(){
        panel.run();
    }

    public static int getWidth() {
        return width;
    }

    public static int getHeight() {
        return height;
    }
}
