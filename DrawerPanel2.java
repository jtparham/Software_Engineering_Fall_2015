package pictionary;

import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics; 
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseMotionAdapter;

/**
*
* @author Michaela Gates
*/
public class DrawerPanel2 {
    
    private static void createAndShowGUI() {
        System.out.println("Created GUI on EDT? "+
        SwingUtilities.isEventDispatchThread());
        JFrame window = new JFrame("Drawer View");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        
        //f.add(new MyPanel());
        JPanel topPanel = new JPanel();
        topPanel.setLayout(null);
        topPanel.setLocation(10, 5);
        topPanel.setSize(510, 60);
                 
        JPanel draw = new JPanel();
        draw.setLayout(null);
        draw.setLocation(10,75); 
        draw.setSize(500, 395);
        draw.setBorder(BorderFactory.createLineBorder(Color.black));
        
        JPanel guess = new JPanel();
        guess.setLayout(null);
        guess.setLocation(10,480); 
        guess.setSize(500, 90);
        guess.setBorder(BorderFactory.createLineBorder(Color.black));
              
        JLabel guessInfo = new JLabel("Guess info... ");
        guessInfo.setLocation(160,510);
        guessInfo.setSize(50, 30);
        //guessInfo.setHorizontalAlignment(0);
        guessInfo.setForeground(Color.black);
        guessInfo.setBorder(BorderFactory.createLineBorder(Color.pink));
        guess.add(guessInfo);
        
        JButton quit = new JButton("Quit");
        quit.setLocation(270,580);
        quit.setSize(60,30);
        quit.setHorizontalAlignment(0);
        window.add(quit);
        
        JButton logout = new JButton("Logout");
        logout.setLocation(340,580);
        logout.setSize(80,30);
        logout.setHorizontalAlignment(0);
        window.add(logout);
        
        window.add(topPanel);
        window.add(draw);
        window.add(guess);

        JPanel drawTools = new JPanel();
        drawTools.setLayout(null);
        drawTools.setLocation(530,5); 
        drawTools.setSize(130,100);
        //drawTools.setBorder(BorderFactory.createLineBorder(Color.green));
        window.add(drawTools);
               
        JButton red = new JButton("red");
        red.setLocation(570,20);
        red.setSize(60,30);
        red.setHorizontalAlignment(0);
        drawTools.add(red);
        
        JButton blue = new JButton("blue");
        blue.setLocation(570,70);
        blue.setSize(60,30);
        blue.setHorizontalAlignment(0);
        drawTools.add(blue);
        
        JButton etc = new JButton("etc");
        etc.setLocation(570,120);
        etc.setSize(60,30);
        etc.setHorizontalAlignment(0);
        drawTools.add(etc);
        
       
        
        JLabel time = new JLabel("TIME: ");
        time.setLocation(0,0);
        time.setSize(150, 60);
        time.setHorizontalAlignment(0);
        time.setForeground(Color.red);
        time.setBorder(BorderFactory.createLineBorder(Color.black));
        topPanel.add(time);
        
        JLabel catHint = new JLabel("Category hint goes here...");
        catHint.setLocation(150, 0);
        catHint.setSize(350, 60);
        catHint.setHorizontalAlignment(0);
        catHint.setForeground(Color.blue);
        catHint.setBorder(BorderFactory.createLineBorder(Color.black));
        topPanel.add(catHint);
                
        window.setSize(730,660);
        window.setVisible(true);
    } 

}

@SuppressWarnings("serial")
class MyPanel extends JPanel {

    public MyPanel() {

        setBorder(BorderFactory.createLineBorder(Color.black));


    }



    public Dimension getPreferredSize() {
        return new Dimension(250,200);
    }
}


