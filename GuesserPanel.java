/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pictionary;

/**
 *
 * @author Jordan Esty
 */

/*
public class GuesserPanel extends javax.swing.JPanel {

    
     //Creates new form GuesserPanel
     
    public GuesserPanel() {
        initComponents();
    }

    
     // This method is called from within the constructor to initialize the form.
     // WARNING: Do NOT modify this code. The content of this method is always
     // regenerated by the Form Editor.
     
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
*/


import javax.swing.Box;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.JButton;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;


public class GuesserPanel extends JPanel {
    
    /**
     * Creates new form GuesserPanel
     */
    public GuesserPanel() {
        initComponents();
    }

    /**
     * Initialize components
     */
    private void initComponents() {
        this.setLayout(new FlowLayout());
        
        JPanel topPanel = new JPanel();
        topPanel.setName("");
        topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        topPanel.setSize(510, 60);
                 
        JPanel draw = new JPanel();
        draw.setName("");
        draw.setLayout(new FlowLayout(FlowLayout.CENTER));
        draw.setBorder(BorderFactory.createLineBorder(Color.black));
        draw.setSize(550, 180);

        
        JTextArea guessTextField = new JTextArea(5, 40);
        guessTextField.setName("");
        guessTextField.setBorder(BorderFactory.createLineBorder(Color.black));
        JPanel guess = new JPanel();
        guess.setName("");
        guess.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 75));
        guess.setSize(450, 90);
        guess.add(guessTextField);
      
        
        JButton quitButton = new javax.swing.JButton("Quit");
        quitButton.setName("quitButton");
        quitButton.setSize(60,30);
        quitButton.setHorizontalAlignment(0);
        this.add(quitButton);
        
        JButton logout = new JButton("Logout");
        logout.setName("logoutButton");
        logout.setSize(80,30);
        logout.setHorizontalAlignment(0);
        this.add(logout);
        
        JButton guessButton = new JButton("Guess...");
        guessButton.setName("guessButton");
        guessButton.setSize(80, 30);
        this.add(guessButton);

        
        JLabel time = new JLabel("TIME: ");
        time.setName("");
        time.setLocation(0,0);
        time.setSize(150, 60);
        time.setHorizontalAlignment(0);
        time.setForeground(Color.red);
        time.setBorder(BorderFactory.createLineBorder(Color.black));
        topPanel.add(time);
        topPanel.add(Box.createHorizontalStrut(295));   
        
        JPanel catPanel = new JPanel();
        catPanel.setName("");
        catPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        JLabel catHint = new JLabel("Category hint goes here...");
        catHint.setName("");
        catHint.setSize(350, 60);
        catHint.setHorizontalAlignment(0);
        catHint.setForeground(Color.blue);
        catHint.setBorder(BorderFactory.createLineBorder(Color.black));
        topPanel.add(catHint);
        
        this.add(topPanel, BorderLayout.NORTH );
        this.add(draw, BorderLayout.CENTER);
        this.add(guess, BorderLayout.SOUTH);
        
        this.setSize(700,650);
        this.setVisible(true);
    } 
}
