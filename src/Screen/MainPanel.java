package Screen;

import java.awt.Component;
import java.awt.Container;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import GraphicComponents.BootstrapPanel;
import GraphicPanels.TitlePanel;
import Main.Colors;
import Main.Fonts;
import Multithreading.Buffer;
import Multithreading.Consumer;
import Multithreading.Producer;

/** @Author Kevin O. Cabrera */

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
	private BootstrapPanel noConsumers, timeProducers, numProducers, timeConsumers, bufferSize, minValues, maxValues;

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

	// Labels
	private JLabel remaining, completed, completedCounter, remainingCounter, remainingDividedByBufferSize,
			remainingDividedByBufferSizeLabel;

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
		createBootstrapComponents();
		createBootstrapButtons();
		addRemainingOpsPanel();
		addCompletedOpsPanel();
		remainingDividedByBuffer();
	}

	// Set Remaining v.s. Buffer Component
	public void remainingDividedByBuffer() {
		int width = 200;
		int height = 100;
		int x = 850;
		int y = 120;

		// Remaining division Label
		remainingDividedByBufferSizeLabel = new JLabel("Remaining/Buffer Size");
		remainingDividedByBufferSizeLabel.setBounds(x + width / 2, y + height, width, height);
		remainingDividedByBufferSizeLabel.setFont(Fonts.helv_15);
		remainingDividedByBufferSizeLabel.setForeground(Colors.dark_gray);

		// Remaining Label
		remainingDividedByBufferSize = new JLabel("00/00");
		remainingDividedByBufferSize.setBounds(x + 50 + width / 2, y * 2 + 50, width, height);
		remainingDividedByBufferSize.setFont(Fonts.helv_20_bold);
		remainingDividedByBufferSize.setForeground(Colors.dark_gray);

		// Add components
		content.add(remainingDividedByBufferSize);
		content.add(remainingDividedByBufferSizeLabel);
	}

	// Set Remaining Tasks Component
	public void addRemainingOpsPanel() {
		/** @Todo move remaining to visible place */
		// Remaining Label
		remaining = new JLabel("Remaining tasks");
		remaining.setBounds(100, 220, 150, 100);
		remaining.setFont(Fonts.helv_15);
		remaining.setForeground(Colors.dark_gray);

		modelRemainingTasks = new DefaultListModel<>();

		// Remaining Task List Panel
		listRemainingOps = new JList<>(modelRemainingTasks);
		remainingOpsPanel = new JScrollPane(listRemainingOps);
		remainingOpsPanel.setBounds(100, 280, 400, 160);

		// Remaining List Results
		remainingCounter = new JLabel();
		remainingCounter.setBounds(220, 220, 400, 100);
		remainingCounter.setFont(Fonts.helv_15);
		remainingCounter.setForeground(Colors.dark_gray);

		// Add components
		content.add(remaining);
		content.add(remainingOpsPanel);
		content.add(remainingCounter);
	}

	// Completed Task Component
	public void addCompletedOpsPanel() {

		int x = 540;

		// Completed Label
		completed = new JLabel("Completed tasks");
		completed.setBounds(x, 220, 150, 100);
		completed.setFont(Fonts.helv_15);
		completed.setForeground(Colors.dark_gray);

		modelCompletedTasks = new DefaultListModel<>();
		listCompletedOps = new JList<>(modelCompletedTasks);

		// Completed Scroll
		completedOpsPanel = new JScrollPane(listCompletedOps);
		completedOpsPanel.setBounds(x, 280, 400, 160);

		// Completed Counter Label
		completedCounter = new JLabel();
		completedCounter.setBounds(x + 130, 220, 400, 100);
		completedCounter.setFont(Fonts.helv_15);
		completedCounter.setForeground(Colors.dark_gray);

		// Add Components
		content.add(completed);
		content.add(completedOpsPanel);
		content.add(completedCounter);
	}

	// Buttons
	public void createBootstrapButtons() {
		btnStart = new JButton("Start");
		btnStop = new JButton("Stop");

		// Button start style
		btnStart.setOpaque(true);
		btnStart.setBounds(100, 460, 520, 50);
		btnStart.setBackground(Colors.blue);
		btnStart.setForeground(Colors.white);
		btnStart.setFont(Fonts.helv_15_bold);
		btnStart.setBorderPainted(false);

		// Button stop style
		btnStop.setOpaque(true);
		btnStop.setBounds(630, 460, 520, 50);
		btnStop.setBackground(Colors.red);
		btnStop.setForeground(Colors.white);
		btnStop.setFont(Fonts.helv_15_bold);
		btnStop.setBorderPainted(false);

		content.add(btnStart);
		content.add(btnStop);
	}

	// Create Text Inputs and Set Sizes
	public void createBootstrapComponents() {
		// Set Sizes
		int width = 500;
		int height = 40;
		int y = -70;
		int x = 100;
		int x2 = x + width + 50;

		// Instantiate
		minValues = new BootstrapPanel(null, "Min value (0-9)");
		maxValues = new BootstrapPanel(null, "Max value (0-9)");
		bufferSize = new BootstrapPanel(null, "Buffer Size");
		noConsumers = new BootstrapPanel(null, "# of Consumers");
		numProducers = new BootstrapPanel(null, "# of Producers");
		timeProducers = new BootstrapPanel(null, "Time to produce (ms)");
		timeConsumers = new BootstrapPanel(null, "Time to consume (ms)");

		bufferSize.setBounds(x, 0, (width * 2 + width / 10), height);
		numProducers.setBounds(x, (y + -y * 2), width, height);
		noConsumers.setBounds(x2, (y + -y * 2), width, height);
		timeProducers.setBounds(x, (y + -y * 3), width, height);
		timeConsumers.setBounds(x2, (y + -y * 3), width, height);
		minValues.setBounds(x, (y + -y * 4), width, height);
		maxValues.setBounds(x2, (y + -y * 4), width, height);

		// Add Components
		content.add(maxValues);
		content.add(minValues);
		content.add(bufferSize);
		content.add(noConsumers);
		content.add(numProducers);
		content.add(timeConsumers);
		content.add(timeProducers);
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

					int n = Integer.parseInt(minValues.getText());
					int m = Integer.parseInt(maxValues.getText());

					if (producersQuantity <= 0 || consumersQuantity <= 0 || waitTimeProducers <= 0
							|| waitTimeConsumers <= 0 || bufferLength <= 0 || n <= 0 || m <= 0) {
						JOptionPane.showMessageDialog(null, "Type only Integer positive digits");

					} else {
						new Thread(() -> {
							createProducer(producersQuantity, waitTimeProducers, n, m, waitTimeProducers);
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

	public boolean createProducer(int sizeProducers, int timeProducers, int n, int m, int sleepTime) {
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

	public void addRemainingDividedByBufferSize(int remainingOps, int bufferLength) {
		remainingDividedByBufferSize.setText(remainingOps + "/" + bufferLength);
	}
}