import java.awt.*;
import java.awt.event.*;

/**
 * Description:
 * Created with IntelliJ IDEA.
 *
 * @author apktool
 * @version 1.0
 * @since 2017/8/19
 */
public class FrameDemo {
    public static void main(String[] argv){
        Frame f = new Frame();
        f.setTitle("Hello world");

        /*
        f.setSize(400, 300);
        f.setLocation(400, 200);
        */

        f.setBounds(400, 200, 400, 300);
        f.setLayout(new FlowLayout());

        /*
        f.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });
        */
        f.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        Button bu = new Button("Button");
        bu.setSize(20, 10);
        bu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Hello java");
            }
        });
        f.add(bu);

        f.setVisible(true);
    }
}
