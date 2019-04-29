package Screen;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.ArrayList;

import javax.swing.*;

import GraphicComponents.CustomPanel;
import Main.Colors;
import Main.Fonts;
import Multithreading.Buffer;
import Multithreading.Consumer;
import Multithreading.Producer;

/** @Author Kevin O. Cabrera */
/** @Author Valentin Ochoa */

public class MainPanel extends JFrame implements ActionListener {

	// Auto-generated
	private static final long serialVersionUID = 1L;

	// Container
	static Container leadContainer;

	// Buttons
	private JButton btnStop, btnStart;

	// Panel
	private JPanel content;
	private TitlePanel titlePanel;

	// Bootstrap Panel
	private CustomPanel noConsumers, 
	timeProducers, 
	numProducers, 
	timeConsumers, 
	bufferSize;


	// Buffer related
	private Buffer buffer;

	// Consumer/Producer related
	private ArrayList<Producer> producers;
	private ArrayList<Consumer> consumers;

	// Remaining Panel
	private DefaultListModel<String> modelRemainingTasks, modelCompletedTasks;

	// JList
	private JList<String> listRemainingOps, listCompletedOps;

	// JScroll
	private JScrollPane completedOpsPanel, remainingOpsPanel;

	//JProgressBar
	private JProgressBar bufferBar;

	// Labels
	private JLabel remaining, completed, 
	completedCounter, remainingCounter, 
	remainingBuffer, remainDivBuffer;
	
	// Lables for Inputs
	private JLabel lBuff, lProd, 
	lCons, lTimeProd, lTimeCons;

	public MainPanel() {
		super("Programming Languages");
		// Screen optimized for Macbook Pro Retina early 2015
		setSize(1300, 700);

		// Set window properties
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);

		// Set Container
		leadContainer = getContentPane();
		leadContainer.setLayout(null);

		// Setup producers/consumers
		this.producers = new ArrayList<>();
		this.consumers = new ArrayList<>();

		initComponents();
		addButtonEvents();

		// Set container
		this.addComponentListener(new ComponentListener() {
			// Auto Generated Methods
			@Override
			public void componentHidden(ComponentEvent arg0) {
			}

			@Override
			public void componentMoved(ComponentEvent arg0) {
			}

			@Override
			public void componentShown(ComponentEvent arg0) {
			}

			@Override
			public void componentResized(ComponentEvent arg0) {
				// Set Container for each Component
				for (Component c : leadContainer.getComponents()) {
					if (c.getClass().getSimpleName().equals("TitlePanel")
							|| c.getClass().getSimpleName().equals("BottomPanel")) {
						// If the class exist set their containers
						c.setSize(getWidth(), c.getHeight());
					}
				}
			}
		});
	}

	// Create Components
	public void initComponents() {
		// Create title panel - Dark Blue
		titlePanel = new TitlePanel(new Rectangle(0, 0, 800, 100));
		leadContainer.add(titlePanel);

		// Create title panel - Soft Blue
		content = new JPanel();
		content.setBounds(new Rectangle(0, 135, 1200, 600));
		content.setLayout(null);
		leadContainer.add(content);

		// Call function
		createComponents();
		createButtons();
		addRemainingOpsPanel();
		addCompletedOpsPanel();
		remainingDividedByBuffer();
		createBufferBar();
	}

	// Set Remaining v.s. Buffer Component
	public void remainingDividedByBuffer() {
		int width  = 200;
		int height = 100;

		// Remaining division Label
		remainDivBuffer = new JLabel("Remaining / Buffer Size");
		remainDivBuffer.setBounds(100, 360, width, height);
		remainDivBuffer.setFont(Fonts.helv_15);
		remainDivBuffer.setForeground(Colors.dark_gray);

		// Remaining Label
		remainingBuffer = new JLabel("0/0");
		remainingBuffer.setBounds(0, 0, 0, 0);
		remainingBuffer.setFont(Fonts.helv_20_bold);
		remainingBuffer.setForeground(Colors.dark_gray);

		// Add components
		content.add(remainDivBuffer);
	}

	// Set Remaining Tasks Component
	public void addRemainingOpsPanel() {
		int x = 100;
		int y = 160;
		
		// Remaining Label
		remaining = new JLabel("Remaining tasks");
		remaining.setBounds(x, y, 150, 100);
		remaining.setFont(Fonts.helv_15);
		remaining.setForeground(Colors.dark_gray);

		modelRemainingTasks = new DefaultListModel<>();

		// Remaining Task List Panel
		listRemainingOps = new JList<>(modelRemainingTasks);
		remainingOpsPanel = new JScrollPane(listRemainingOps);
		remainingOpsPanel.setBounds(x, y + 60, 500, 160);

		// Remaining List Results
		remainingCounter = new JLabel();
		remainingCounter.setBounds(x * 2 + 20, y, 500, 100);
		remainingCounter.setFont(Fonts.helv_15);
		remainingCounter.setForeground(Colors.dark_gray);

		// Add components
		content.add(remaining);
		content.add(remainingOpsPanel);
		content.add(remainingCounter);
	}

	// Completed Task Component
	public void addCompletedOpsPanel() {
		int x = 650;
		int y = 160;

		// Completed Label
		completed = new JLabel("Completed tasks");
		completed.setBounds(x, y, 150, 100);
		completed.setFont(Fonts.helv_15);
		completed.setForeground(Colors.dark_gray);

		modelCompletedTasks = new DefaultListModel<>();
		listCompletedOps = new JList<>(modelCompletedTasks);

		// Completed Scroll
		completedOpsPanel = new JScrollPane(listCompletedOps);
		completedOpsPanel.setBounds(x, y + 60, 500, 160);

		// Completed Counter Label
		completedCounter = new JLabel();
		completedCounter.setBounds(x + 130, y, 500, 100);
		completedCounter.setFont(Fonts.helv_15);
		completedCounter.setForeground(Colors.dark_gray);

		// Add Components
		content.add(completed);
		content.add(completedOpsPanel);
		content.add(completedCounter);
	}

	// Buttons
	public void createButtons() {
		int x = 100;
		int y = 460;
		int height = 50;
		int width = 520;
		
		btnStart = new JButton("Start");
		btnStop  = new JButton("Stop");

		// Button start style
		btnStart.setOpaque(true);
		btnStart.setBounds(x, y, width, height);
		btnStart.setBackground(Colors.blue);
		btnStart.setForeground(Colors.white);
		btnStart.setFont(Fonts.helv_15_bold);
		btnStart.setBorderPainted(false);

		// Button stop style
		btnStop.setOpaque(true);
		btnStop.setBounds(630, y, width, height);
		btnStop.setBackground(Colors.red);
		btnStop.setForeground(Colors.white);
		btnStop.setFont(Fonts.helv_15_bold);
		btnStop.setBorderPainted(false);

		content.add(btnStart);
		content.add(btnStop);
	}

	// Progress Bar
	public void createBufferBar(){
		int x = 100;
		int y = 420;
		int width = (this.getWidth() - 120);
		int height = 20;
		
		this.bufferBar = new JProgressBar();
		this.bufferBar.setValue(0);
		this.bufferBar.setStringPainted(true);

		this.bufferBar.setBorderPainted(true);
		this.bufferBar.setBounds(x ,y , width, height);

		content.add(this.bufferBar);
	}

	// Create Text Inputs and Set Sizes
	public void createComponents() {
		// Set Sizes
		int width  = 500;
		int height = 40;
		int y      = -70;
		int x      = 100;
		int x2     = x + width + 50;

		// Instantiate
		bufferSize    = new CustomPanel("Buffer Size");
		noConsumers   = new CustomPanel("# of Consumers");
		numProducers  = new CustomPanel("# of Producers");
		timeProducers = new CustomPanel("Time to produce (ms)");
		timeConsumers = new CustomPanel("Time to consume (ms)");
		
		// Labels 
		lBuff = new JLabel("Buffer Size [1-10]");
		lProd = new JLabel("Number of Producers [1-10]");
		lCons = new JLabel("Number of Consumers [1-10]");
		lTimeProd = new JLabel("Time to produce (ms) [1-10,000]"); 
		lTimeCons = new JLabel("Time to produce (ms) [1-10,000]");

		bufferSize.setBounds(x, 0, (width * 2 + width / 10), height);
		numProducers.setBounds(x, (y + -y * 2), width, height);
		noConsumers.setBounds(x2, (y + -y * 2), width, height);
		timeProducers.setBounds(x, (y + -y * 3), width, height);
		timeConsumers.setBounds(x2, (y + -y * 3), width, height);

		lBuff.setBounds(x, -30, (width * 2 + width / 10), height);
		lProd.setBounds(x, (y + -y * 2) - 30, width, height);
		lCons.setBounds(x2, (y + -y * 2) - 30, width, height);
		lTimeProd.setBounds(x, (y + -y * 3) - 30, width, height);
		lTimeCons.setBounds(x2, (y + -y * 3) -30, width, height);

		// Add Components
		content.add(bufferSize);
		content.add(noConsumers);
		content.add(numProducers);
		content.add(timeConsumers);
		content.add(timeProducers);
		
		content.add(lBuff);
		content.add(lProd);
		content.add(lCons);
		content.add(lTimeProd);
		content.add(lTimeCons);
	}

	// ----------- Element handling Section --------------
	public void addElementToRemainingList(String remainingElement) {
		try {
			modelRemainingTasks.addElement(remainingElement);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void removeElementOfRemainingList() {
		try {
			int indexToRemove = 1;
			if (modelRemainingTasks.getElementAt(0) != null) {
				indexToRemove = 0;
			}
			modelRemainingTasks.removeElementAt(indexToRemove);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void addElementToCompletedList(String completedTask) {
		try {
			modelCompletedTasks.addElement(completedTask);
		} catch (Exception e) {
			System.out.println("error " + e);
		}
	}

	public void addButtonEvents() {
		// Buttons Action Listener
		btnStart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int producersQuantity = Integer.parseInt(numProducers.getText());
					int consumersQuantity = Integer.parseInt(noConsumers.getText());
					int waitTimeProducers = Integer.parseInt(timeProducers.getText());
					int waitTimeConsumers = Integer.parseInt(timeConsumers.getText());

					int bufferLength = Integer.parseInt(bufferSize.getText());
					buffer = new Buffer(bufferLength, MainPanel.this);
					
					
					// Check for zeros and max values;
					if ((producersQuantity <= 0 && producersQuantity <= 10) ||
						(consumersQuantity <= 0 && consumersQuantity <= 10) || 
						(waitTimeProducers <= 0 && waitTimeProducers <= 10000)  ||
						(waitTimeConsumers <= 0 && waitTimeConsumers <= 10000)  || 
						bufferLength <= 0 && bufferLength <= 100) {
						JOptionPane.showMessageDialog(null, "Type only values in the accepted range");
					} else {
						System.out.println("Hey");
						System.out.println(producersQuantity);
						new Thread(() -> {
							createProducer(producersQuantity, waitTimeProducers, waitTimeProducers);
						}).start();

						new Thread(() -> {
							createConsumer(consumersQuantity, waitTimeConsumers, waitTimeConsumers);
						}).start();
					}
				} catch (Exception except) {
					JOptionPane.showMessageDialog(null, "Type only Integer positive digits");
				}

			}
		});

		btnStop.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {
				for (Producer producer : producers) {
					producer.stop();
				}
				for (Consumer consumer : consumers) {
					consumer.stop();
				}
			}
		});
	}

	public boolean createProducer(int sizeProducers, int timeProducers, int sleepTime) {
		while (sizeProducers != 0) {
			Producer producer = new Producer(buffer, this, sleepTime);
			producers.add(producer);
			producer.start();

			try {
				Thread.sleep(timeProducers);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				return false;
			}
			sizeProducers--;
		}
		return sizeProducers == 0 ? true : false;
	}

	public boolean createConsumer(int sizeConsumers, int timeConsumers, int sleepTime) {
		while (sizeConsumers != 0) {
			Consumer consumer = new Consumer(buffer, this, sleepTime);
			consumers.add(consumer);
			consumer.start();
			try {
				Thread.sleep(timeConsumers);
			} catch (Exception e) {
				return false;
			}
			sizeConsumers--;
		}
		return sizeConsumers == 0 ? true : false;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}

	public void addCompletedCounter(int completedOps) {
		completedCounter.setText("= " + completedOps);
	}

	public void addRemainingCounter(int remainingOps) {
		remainingCounter.setText("= " + remainingOps);
	}

	public void addremainingBuffer(int remainingOps, int bufferLength) {
		remainingBuffer.setText(remainingOps * 100/ bufferLength + "%");
	}
	public void setBufferBarValue(int remainingOps, int bufferLength){
		this.bufferBar.setValue(remainingOps * 100/ bufferLength );
	}
}