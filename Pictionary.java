/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pictionary;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.embed.swing.SwingNode;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.embed.swing.SwingFXUtils;

import javax.imageio.ImageIO;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
 

/**
*
* @author Chris Taormino
*/
/**
 * Describe pictionary
 */
public class Pictionary extends JApplet {
    
    private static final int JFXPANEL_WIDTH_INT = 700;
    private static final int JFXPANEL_HEIGHT_INT = 650;
    private static JFXPanel fxContainer;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
                } catch (Exception e) {
                }
                
                JFrame frame = new JFrame("Pictionary");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                
                JApplet applet = new Pictionary();
                applet.init();
                
                frame.setContentPane(applet.getContentPane());
                
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
                
                applet.start();
            }
        });
    }
    
    @Override
    public void init() {
        fxContainer = new JFXPanel();
        fxContainer.setPreferredSize(new Dimension(JFXPANEL_WIDTH_INT, JFXPANEL_HEIGHT_INT));
        add(fxContainer, BorderLayout.CENTER);
        // create JavaFX scene
        Platform.runLater(new Runnable() {
            
            @Override
            public void run() {
                createScene();
            }
        });
    }
    /**
    *
    * @author Chris Taormino
    */
    /**
     * Displays the appropriate screen based on button pressed.
     */
    private void createScene() {
        final BorderPane basePanel = new BorderPane();
        
        final SwingNode menuWrapper = new SwingNode();
        MenuPanel menuPane = new MenuPanel();
        final SwingNode titleWrapper = new SwingNode();
        TitlePanel titlePane = new TitlePanel();
        final SwingNode loginWrapper = new SwingNode();
        LoginPanel loginPane = new LoginPanel();
        final SwingNode createAcntWrapper = new SwingNode();
        CreateAcntPanel acntPane = new CreateAcntPanel();
        final SwingNode leaderWrapper = new SwingNode();
        LeaderboardPanel leaderPane = new LeaderboardPanel();
        final SwingNode rulesWrapper = new SwingNode();
        RulesPanel rulesPane = new RulesPanel();
        /*****addition*****/
        final SwingNode guessWrapper = new SwingNode();
        GuesserPanel guessPane = new GuesserPanel();
        final SwingNode readyWrapper = new SwingNode();
        readyPanel readyPane = new readyPanel();
        final SwingNode drawWrapper = new SwingNode();
        final DrawerPanel drawPane = new DrawerPanel();
        
        
        loginWrapper.setContent(loginPane);
        titleWrapper.setContent(titlePane);
        menuWrapper.setContent(menuPane);
        createAcntWrapper.setContent(acntPane);
        leaderWrapper.setContent(leaderPane);
        rulesWrapper.setContent(rulesPane);
        /*****addition*****/
        guessWrapper.setContent(guessPane);
        readyWrapper.setContent(readyPane);
        drawWrapper.setContent(drawPane);
        
        for(Component c: loginPane.getComponents()){
            if(c.getName().equals("loginButton")){
                ((JButton)c).addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(java.awt.event.ActionEvent e) {
                        Platform.runLater(new Runnable(){
                            @Override
                            public void run() {
                                basePanel.setCenter(menuWrapper);
                            }
                        });
                    }
                });
            }
            
            if(c.getName().equals("createAcntButton")){
                ((JButton)c).addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(java.awt.event.ActionEvent e) {
                        Platform.runLater(new Runnable(){
                            @Override
                            public void run() {
                                basePanel.setCenter(createAcntWrapper);
                            }
                        });
                    }
                });
            }
        }
        
        for(Component c: acntPane.getComponents()){
            if(c.getName().equals("createButton")){
                ((JButton)c).addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(java.awt.event.ActionEvent e) {
                        Platform.runLater(new Runnable(){
                            @Override
                            public void run() {
                                basePanel.setCenter(menuWrapper);
                            }
                        });
                    }
                });
            }
        }
        
        for(Component c: menuPane.getComponents()){
            if(c.getName().equals("joinButton")){
                ((JButton)c).addActionListener(new ActionListener() {
                    @SuppressWarnings("restriction")
					@Override
                    public void actionPerformed(java.awt.event.ActionEvent e) {
                        Platform.runLater(new Runnable(){
                            @Override
                            public void run() {
                            	try{
                            		String address = InetAddress.getLocalHost().getHostAddress();
	                            	Socket s = new Socket(address, 8081);
	                            	PrintWriter p= new PrintWriter(new OutputStreamWriter(s.getOutputStream()));
	                            	p.write("<name>Chris</name><ip>"+address+"</ip><portNum>8082</portNum><action>join game</action><data></data>");
	                            	p.flush();
	                            	p.close();
	                            	s.close();
	                            	ServerSocket sSock = new ServerSocket(8082);
	                            	s = sSock.accept();
	                            	BufferedReader reader = new BufferedReader(new InputStreamReader(s.getInputStream()));
	                            	String message = reader.readLine();
	                            	s.close();
	                            	sSock.close();
	                            	reader.close();
	                            	if(message.contains("drawer")){
	                            		basePanel.setCenter(drawWrapper);
	                            		sSock = new ServerSocket(8082);
		                            	s = sSock.accept();
		                            	reader = new BufferedReader(new InputStreamReader(s.getInputStream()));
		                            	message = reader.readLine();
		                            	s.close();
		                            	sSock.close();
		                            	String[] categories = message.substring(message.indexOf("<data>")+6, message.indexOf("</data>")).split(",");
		                            	String selection = (String)JOptionPane.showInputDialog(null, "Select a Category:\n", "Category Selection", JOptionPane.PLAIN_MESSAGE, null, categories, categories[0]);
		                            	s = new Socket(address, 8081);
		                            	p= new PrintWriter(new OutputStreamWriter(s.getOutputStream()));
		                            	p.write("<name>Chris</name><ip>"+address+"</ip><portNum>8082</portNum><action>category selection</action><data>" + selection.trim() + "</data>");
		                            	p.flush();
		                            	p.close();
		                            	s.close();
		                            	sSock = new ServerSocket(8082);
		                            	s = sSock.accept();
		                            	reader = new BufferedReader(new InputStreamReader(s.getInputStream()));
		                            	message = reader.readLine();
		                            	s.close();
		                            	sSock.close();
		                            	String word = message.substring(message.indexOf("<data>")+6, message.indexOf("</data>"));
		                            	drawPane.updateWord(word);
		                            	sSock = new ServerSocket(8082);
		                            	s = sSock.accept();
		                            	reader = new BufferedReader(new InputStreamReader(s.getInputStream()));
		                            	message = reader.readLine();
		                            	s.close();
		                            	sSock.close();
		                            	String[] times = message.substring(message.indexOf("<data>")+6, message.indexOf("</data>")).split(",");
		                            	String time = (String)JOptionPane.showInputDialog(null, "Select a Time:\n", "Time Selection", JOptionPane.PLAIN_MESSAGE, null, times, times[0]);
		                            	drawPane.updateTime(Integer.parseInt(time.split(" ")[0])*60);
		                            	Thread endGame = new Thread(new EndGameListener(new ServerSocket(8082)));
		                            	endGame.start();
		                            	
		                            	try{
		                            		Thread.sleep(10000);
	                            		}catch(InterruptedException e){
	                            			System.err.println("Interrupted while sending updates");
	                            		}
		                            	
		                            	while(endGame.isAlive()){
		                            		try{
			                            		Thread.sleep(1000);
			                            		drawPane.updateTime(drawPane.getTime()-1);
			                            		s = new Socket(address, 8081);
				                            	p= new PrintWriter(new OutputStreamWriter(s.getOutputStream()));
				                            	p.write("<name>Chris</name><ip>"+address+"</ip><portNum>8082</portNum><action>game update</action><data>time:" + drawPane.getTime() + "</data>");
				                            	p.flush();
				                            	p.close();
				                            	s.close();
				                            	WritableImage newImage = new WritableImage(600, 250);
				                                drawPane.getCanvas().snapshot(null, newImage);
				                                s = new Socket(address, 23);
				                                ImageIO.write(SwingFXUtils.fromFXImage(newImage, null), "png", s.getOutputStream());
				                            	s.close();
		                            		}catch(InterruptedException e){
		                            			System.err.println("Interrupted while sending updates");
		                            		}
		                            	}

	                            	}else{
	                            		basePanel.setCenter(guessWrapper);
	                            		boolean isRunning = true;
	                            		sSock = new ServerSocket(8082);
	                            		while(isRunning){
	                            			s = sSock.accept();
	                            			try{
	                            				Socket sock = sSock.accept();
	                            				reader = new BufferedReader(new InputStreamReader(sock.getInputStream()));
	                            				String rawData = reader.readLine();
	                            				sock.close();
	                            	        	sSock.close();
	                            	        	
	                            	        	EndGameLogic eGLogic = new EndGameLogic();
	                            	        	
	                            	        	String action = rawData.substring(rawData.indexOf("<action>")+8, rawData.indexOf("</action>"));
	                            	        	String data = rawData.substring(rawData.indexOf("<data>")+6, rawData.indexOf("</data>"));
	                            	        	switch(action){
	                            	        		case "game time out":
	                            	        			eGLogic.timeOut();
	                            	        			break;
	                            	        		
	                            	        		case "game over":
	                            	        			eGLogic.gameOver(data);
	                            	        			break;
	                            	        			
	                            	        		case "drawer left":
	                            	        			eGLogic.noDrawer();
	                            	        			break;
	                            	        		
	                            	        		case "no more guessers":
	                            	        			eGLogic.noGuessers();
	                            	        			break;
	                            	        			
	                            	        		case "update game":
	                            	        			drawPane.updateTime(Integer.parseInt(data.substring(5)));
	                            	        			sock = sSock.accept();
	                            	        			BufferedImage loadedImage = ImageIO.read(ImageIO.createImageInputStream((sock.getOutputStream())));
	                            	        			drawPane.getCanvas().getGraphicsContext2D().drawImage((Image)SwingFXUtils.toFXImage(loadedImage, new WritableImage(600, 250)), 0, 0);
	                            	        			break;
	                            	        	}
	                            				
	                            			}catch(IOException e){
	                            				System.err.println(e.getMessage());
	                            			}
	                            			
	                            		}
	                            	}
                            	}catch(IOException e){
                            		System.err.println("Issue connecting with server");
                            		System.err.println(e.getMessage());
                            	}
                            }
                        });
                    }
                });
            }
            
            if(c.getName().equals("rulesButton")){
                ((JButton)c).addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(java.awt.event.ActionEvent e) {
                        Platform.runLater(new Runnable(){
                            @Override
                            public void run() {
                                basePanel.setCenter(rulesWrapper);
                            }
                        });
                    }
                });
            }
            
            if(c.getName().equals("leaderButton")){
                ((JButton)c).addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(java.awt.event.ActionEvent e) {
                        Platform.runLater(new Runnable(){
                            @Override
                            public void run() {
                                basePanel.setCenter(leaderWrapper);
                            }
                        });
                    }
                });
            }
            
            if(c.getName().equals("logoutButton")){
                ((JButton)c).addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(java.awt.event.ActionEvent e) {
                        Platform.runLater(new Runnable(){
                            @Override
                            public void run() {
                                basePanel.setCenter(loginWrapper);
                            }
                        });
                    }
                });
            }
        }
        
        for(Component c: rulesPane.getComponents()){
            if(c.getName().equals("backButton")){
                ((JButton)c).addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(java.awt.event.ActionEvent e) {
                        Platform.runLater(new Runnable(){
                            @Override
                            public void run() {
                                basePanel.setCenter(menuWrapper);
                            }
                        });
                    }
                });
            }
        }
        
        for(Component c: leaderPane.getComponents()){
            if(c.getName().equals("backButton")){
                ((JButton)c).addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(java.awt.event.ActionEvent e) {
                        Platform.runLater(new Runnable(){
                            @Override
                            public void run() {
                                basePanel.setCenter(menuWrapper);
                            }
                        });
                    }
                });
            }
        }
        
        /*****addition******/
        for(Component c: guessPane.getComponents()){
            if(c.getName().equals("quitButton")){
                ((JButton)c).addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(java.awt.event.ActionEvent e) {
                        Platform.runLater(new Runnable(){
                            @Override
                            public void run() {
                                basePanel.setCenter(menuWrapper);
                            }
                        });
                    }
                });
            }
            
            if(c.getName().equals("logoutButton")){
                ((JButton)c).addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(java.awt.event.ActionEvent e) {
                        Platform.runLater(new Runnable(){
                            @Override
                            public void run() {
                                basePanel.setCenter(loginWrapper);
                            }
                        });
                    }
                });
            }
            
            if(c.getName().equals("guessButton")){
                ((JButton)c).addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(java.awt.event.ActionEvent e) {
                        Platform.runLater(new Runnable(){
                            @Override
                            public void run() {
                                basePanel.setCenter(readyWrapper);
                            }
                        });
                    }
                });
            }
        }
        /*****addition******/
        for(Component c: readyPane.getComponents()){
            if(c.getName().equals("continueButton")){
                ((JButton)c).addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(java.awt.event.ActionEvent e) {
                        Platform.runLater(new Runnable(){
                            @Override
                            public void run() {
                                basePanel.setCenter(guessWrapper);
                            }
                        });
                    }
                });
            }
            if(c.getName().equals("declineButton")){
                ((JButton)c).addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(java.awt.event.ActionEvent e) {
                        Platform.runLater(new Runnable(){
                            @Override
                            public void run() {
                                basePanel.setCenter(menuWrapper);
                            }
                        });
                    }
                });
            }
            
        }
        
        
        basePanel.setTop(titleWrapper);
        basePanel.setCenter(loginWrapper);
        
        Scene baseScene = new Scene(basePanel);
        fxContainer.setScene(baseScene);
    }
}