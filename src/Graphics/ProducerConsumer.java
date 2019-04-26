package Graphics;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

public final class ProducerConsumer {
	
	// Frame and Panel
    private JFrame frame;
    private JPanel panel;
    
    // Buttons
    private JButton btnProduce;
    private JButton btnConsume;
    private JButton startProducerConsumer;
    
    // JLabels
    private JLabel produceLabel;
    private JLabel consumeLabel;
    private JLabel bufferSize;
    private JLabel rangeBufferSize;
    private JLabel waitingTime;
    private JLabel bufferQuantity;
 
    // JTextFields
    private JTextField inputBufferSize;
    private JTextField inputWaitingTimeProducers;
    private JTextField inputWaitingTimeConsumers;
    
    // Constructor
    public ProducerConsumer() {
        createComponents();
        setComponentsFeatures();
        addComponents();
        setFrameFeatures();      
    }
    
    public void createComponents() {
    	// Frame and Panel
        frame = new JFrame("Producer Consumer");    
        panel = new JPanel();  
        
        // Button
        btnProduce = new JButton("Producer"); 
        btnConsume = new JButton("Consumer");
        startProducerConsumer = new JButton("Start");
        
        // Label
        produceLabel 	= new JLabel("Producers");
        consumeLabel 	= new JLabel("Consumers");
        bufferSize 	 	= new JLabel("Buffer size");
        rangeBufferSize = new JLabel("Range buffer size");
        bufferQuantity  = new JLabel("Quantity");
        waitingTime 	= new JLabel("Waiting Time");
        
        // Text Fields
        inputBufferSize 		  = new JTextField();
        inputWaitingTimeProducers = new JTextField();
        inputWaitingTimeConsumers = new JTextField();
    }
    
    public void setComponentsFeatures() {
    	// Panel
        panel.setBounds(0,0,500,500);
        panel.setBackground(Color.gray); 
        
        //Button
        btnProduce.setBounds(50, 50,80,30);   
        btnProduce.setBackground(Color.blue); 
        btnConsume.setBounds(150,50,120,30);  
        btnConsume.setBackground(Color.green);
      
        produceLabel.setBounds(20, 100, 100, 50);
        consumeLabel.setBounds(20, 150, 100 , 50);
        bufferSize.setBounds(20, 200, 100, 50);
        rangeBufferSize.setBounds(20, 250, 120, 50);
        inputBufferSize.setBounds(120, 210, 50, 30);
        inputWaitingTimeProducers.setBounds(200, 105, 50, 30);
        inputWaitingTimeConsumers.setBounds(200, 155, 50, 30);
        startProducerConsumer.setBounds(100, 300, 100, 130);   
    }
    
    public void addComponents(){
        this.panel.setLayout(null);
        this.panel.add(btnProduce); 
        this.panel.add(btnConsume); 
        this.panel.add(produceLabel);
        this.panel.add(consumeLabel);
        this.panel.add(bufferSize);
        this.panel.add(rangeBufferSize);
        this.panel.add(inputBufferSize);
        this.panel.add(inputWaitingTimeProducers);
        this.panel.add(startProducerConsumer);
        this.panel.add(inputWaitingTimeConsumers);
        this.panel.add(waitingTime);
        this.panel.add(bufferQuantity);
        this.frame.add(panel);
    }
    
    public void setFrameFeatures() {
        frame.setSize(500, 500);    
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setResizable(false);
    }  
    
}
