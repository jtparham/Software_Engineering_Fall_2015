/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pictionary;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.JFXPanel;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;

import javax.swing.JApplet;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.paint.Color;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
/**
*
* @author Chris Taormino
*/
public class DrawerPanel extends JFXPanel {
	
    private static final int JFXPANEL_WIDTH_INT = 600;
    private static final int JFXPANEL_HEIGHT_INT = 525;
    private String currShape = "line";
    private Coordinate lastClick = null;
    private Label wordLabel;
    private Label timeLabel;
    private Canvas canvas;

    /**
     * Creates new form DrawerPanel
     */
    public DrawerPanel() {
        initComponents();
    }

    /**
     * Initialize components
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
    	
    	Separator drawSeperator = new Separator();
    	
    	wordLabel = new Label("Word:");
    	timeLabel = new Label("");
    	
    	Button lineButton = new Button();
        lineButton.setText("Line");
        lineButton.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            /* setting the current shape */
            public void handle(ActionEvent event) {
                currShape = "line";
            }
        });
        
        Button rectangleButton = new Button();
        rectangleButton.setText("Rectangle");
        rectangleButton.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            /* setting the current shape */
            public void handle(ActionEvent event) {
                currShape = "rectangle";
            }
        });
        
        Button circleButton = new Button();
        circleButton.setText("Circle");
        circleButton.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            /* setting the current shape */
            public void handle(ActionEvent event) {
                currShape = "circle";
            }
        });
        
        /* setting up the color selection combo box */
        ObservableList<DrawColor> colorOptions = 
        FXCollections.observableArrayList();
        colorOptions.add(new DrawColor(Color.BLACK, "Black"));
        colorOptions.add(new DrawColor(Color.BLUE, "Blue"));
        colorOptions.add(new DrawColor(Color.RED, "Red"));
        colorOptions.add(new DrawColor(Color.GREEN, "Green"));
        colorOptions.add(new DrawColor(Color.YELLOW, "Yellow"));
        
        final ComboBox colorCombo = new ComboBox(colorOptions);
        colorCombo.getSelectionModel().selectFirst();

        /* setting up the filled/empty selection */
        ObservableList<String> filledOption = 
        FXCollections.observableArrayList(
            "Filled",
            "Empty"
        );
        final ComboBox filledCombo = new ComboBox(filledOption);
        filledCombo.getSelectionModel().select("Filled");
                
        /* setting up the canvas */
        this.canvas = new Canvas(600, 250);
        final GraphicsContext gc = this.canvas.getGraphicsContext2D();
        this.canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            /* the action listener for the actual drawing */
            public void handle(MouseEvent t) {
                if(lastClick == null){
                    lastClick = new Coordinate(t.getSceneX(), t.getSceneY());
                }
                else{
                    gc.setStroke(((DrawColor)colorCombo.getSelectionModel().getSelectedItem()).getColor());
                    if(filledCombo.getSelectionModel().getSelectedItem().equals("Filled")){
                        gc.setFill(((DrawColor)colorCombo.getSelectionModel().getSelectedItem()).getColor());
                    }
                    else{
                        gc.setFill(Color.WHITE);
                    }
                    
                    if(currShape.equals("line")){
                        gc.setLineWidth(2);
                        gc.strokeLine(lastClick.getX(), lastClick.getY(), t.getSceneX(), t.getSceneY());
                    }
                    else{
                        if(currShape.equals("rectangle")){
                             if(filledCombo.getSelectionModel().getSelectedItem().equals("Filled")){
                                if(lastClick.getX()<t.getSceneX() && lastClick.getY()<t.getSceneY()){
                                    gc.fillRect(lastClick.getX(), lastClick.getY(), t.getSceneX()-lastClick.getX(), t.getSceneY()-lastClick.getY());
                                }
                                if(lastClick.getX()>t.getSceneX() && lastClick.getY()<t.getSceneY()){
                                    gc.fillRect(t.getSceneX(), lastClick.getY(), lastClick.getX()-t.getSceneX(), t.getSceneY()-lastClick.getY());
                                }
                                if(lastClick.getX()<t.getSceneX() && lastClick.getY()>t.getSceneY()){
                                    gc.fillRect(lastClick.getX(), t.getSceneY(), t.getSceneX()-lastClick.getX(), lastClick.getY()-t.getSceneY());
                                }
                                if(lastClick.getX()>t.getSceneX() && lastClick.getY()>t.getSceneY()){
                                    gc.fillRect(t.getSceneX(), t.getSceneY(), lastClick.getX()-t.getSceneX(), lastClick.getY()-t.getSceneY());
                                }
                            }
                            else{
                                if(lastClick.getX()<t.getSceneX() && lastClick.getY()<t.getSceneY()){
                                    gc.strokeRect(lastClick.getX(), lastClick.getY(), t.getSceneX()-lastClick.getX(), t.getSceneY()-lastClick.getY());
                                }
                                if(lastClick.getX()>t.getSceneX() && lastClick.getY()<t.getSceneY()){
                                    gc.strokeRect(t.getSceneX(), lastClick.getY(), lastClick.getX()-t.getSceneX(), t.getSceneY()-lastClick.getY());
                                }
                                if(lastClick.getX()<t.getSceneX() && lastClick.getY()>t.getSceneY()){
                                    gc.strokeRect(lastClick.getX(), t.getSceneY(), t.getSceneX()-lastClick.getX(), lastClick.getY()-t.getSceneY());
                                }
                                if(lastClick.getX()>t.getSceneX() && lastClick.getY()>t.getSceneY()){
                                    gc.strokeRect(t.getSceneX(), t.getSceneY(), lastClick.getX()-t.getSceneX(), lastClick.getY()-t.getSceneY());
                                }                            
                             }
                        }
                        else{
                            if(currShape.equals("circle")){
                                if(filledCombo.getSelectionModel().getSelectedItem().equals("Filled")){
                                    if(lastClick.getX()<t.getSceneX() && lastClick.getY()<t.getSceneY()){
                                        gc.fillOval(lastClick.getX(), lastClick.getY(), t.getSceneX()-lastClick.getX(), t.getSceneY()-lastClick.getY());
                                    }
                                    if(lastClick.getX()>t.getSceneX() && lastClick.getY()<t.getSceneY()){
                                        gc.fillOval(t.getSceneX(), lastClick.getY(), lastClick.getX()-t.getSceneX(), t.getSceneY()-lastClick.getY());
                                    }
                                    if(lastClick.getX()<t.getSceneX() && lastClick.getY()>t.getSceneY()){
                                        gc.fillOval(lastClick.getX(), t.getSceneY(), t.getSceneX()-lastClick.getX(), lastClick.getY()-t.getSceneY());
                                    }
                                    if(lastClick.getX()>t.getSceneX() && lastClick.getY()>t.getSceneY()){
                                        gc.fillOval(t.getSceneX(), t.getSceneY(), lastClick.getX()-t.getSceneX(), lastClick.getY()-t.getSceneY());
                                    }                                 
                                }
                                else{
                                    if(lastClick.getX()<t.getSceneX() && lastClick.getY()<t.getSceneY()){
                                        gc.strokeOval(lastClick.getX(), lastClick.getY(), t.getSceneX()-lastClick.getX(), t.getSceneY()-lastClick.getY());
                                    }
                                    if(lastClick.getX()>t.getSceneX() && lastClick.getY()<t.getSceneY()){
                                        gc.strokeOval(t.getSceneX(), lastClick.getY(), lastClick.getX()-t.getSceneX(), t.getSceneY()-lastClick.getY());
                                    }
                                    if(lastClick.getX()<t.getSceneX() && lastClick.getY()>t.getSceneY()){
                                        gc.strokeOval(lastClick.getX(), t.getSceneY(), t.getSceneX()-lastClick.getX(), lastClick.getY()-t.getSceneY());
                                    }
                                    if(lastClick.getX()>t.getSceneX() && lastClick.getY()>t.getSceneY()){
                                        gc.strokeOval(t.getSceneX(), t.getSceneY(), lastClick.getX()-t.getSceneX(), lastClick.getY()-t.getSceneY());
                                    }                                 
                                }
                            }
                        }
                    }
                    
                    lastClick = null;
                }
            }
        });
        
        /* adding everything to the scene */
        Scene baseScene = new Scene(VBoxBuilder.create()
                .children(wordLabel, timeLabel, this.canvas, drawSeperator, lineButton, rectangleButton, circleButton, colorCombo, filledCombo)
                .alignment(Pos.BASELINE_LEFT)
                .padding(new Insets(10))
                .build(), 600, 750, Color.WHITE);
        this.setScene(baseScene);
    }// </editor-fold>//GEN-END:initComponents
    
    public void updateTime(int time){
    	this.timeLabel.setText("" + time + "");
    }
    
    public int getTime(){
    	return Integer.parseInt(this.timeLabel.getText());
    }
    
    public void updateWord(String word){
    	this.wordLabel.setText("Word: "+ word);
    }
    
    public String getWord(){
    	return this.wordLabel.getText().substring(6);
    }
    
    public Canvas getCanvas(){
    	return this.canvas;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
