package intertrust.modules.pi_simulator;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

/**
 * 
 * @author UMA
 * @date   10/09/2013
 *
 */
public class PolicyInterpreter_SimulatorPane extends JFrame {

	
	private JComboBox cmbBoxSDSs;
	private JButton btnVisualize;
	private JButton btnNotify;
	
	private SortedMap<String, File> cmbBoxModel;
	
	
	// Actions
	public static final String ACTION_VISUALIZE = "VISUALIZE";
	public static final String ACTION_NOTIFY = "NOTIFY";
	private JTextArea textAreaNotifications;
	
	
	/**
	 * Launch the application.
	 */
/*	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PolicyInterpreter_SimulatorPane window = new PolicyInterpreter_SimulatorPane();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the application.
	 */
	public PolicyInterpreter_SimulatorPane(String title) {
		initialize(title);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String title) {
		setTitle("Policy Interpreter Simulator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblPolicyInterpreterSimulator = new JLabel("Policy Interpreter Simulator");
		lblPolicyInterpreterSimulator.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPolicyInterpreterSimulator.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(lblPolicyInterpreterSimulator, BorderLayout.NORTH);
		
		JPanel panelCentro = new JPanel();
		getContentPane().add(panelCentro, BorderLayout.CENTER);
		panelCentro.setLayout(new BorderLayout(0, 0));
		
		JPanel panelNotifications = new JPanel();
		panelCentro.add(panelNotifications, BorderLayout.NORTH);
		
		JLabel lblNotifications = new JLabel("Notifications:");
		panelNotifications.add(lblNotifications);
		
		textAreaNotifications = new JTextArea();
		textAreaNotifications.setWrapStyleWord(true);
		textAreaNotifications.setRows(4);
		textAreaNotifications.setLineWrap(true);
		textAreaNotifications.setColumns(20);
		textAreaNotifications.setEditable(false);
		
		JScrollPane scrollPaneNotifications = new JScrollPane(textAreaNotifications);
		panelNotifications.add(scrollPaneNotifications);
		
		JPanel panelSdss = new JPanel();
		panelCentro.add(panelSdss);
		FlowLayout fl_panelSdss = new FlowLayout(FlowLayout.CENTER, 5, 5);
		panelSdss.setLayout(fl_panelSdss);
		
		JLabel lblSDSs = new JLabel("SDSs:");
		panelSdss.add(lblSDSs);
		
		cmbBoxSDSs = new JComboBox();
		panelSdss.add(cmbBoxSDSs);
		cmbBoxSDSs.setMaximumRowCount(20);
		
		btnVisualize = new JButton("Visualize");
		panelSdss.add(btnVisualize);
		
		btnNotify = new JButton("Notify");
		getContentPane().add(btnNotify, BorderLayout.SOUTH);
		
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	/**
	 * Register a controller for the actions defined for the graphical components.
	 */
	public void registerController(ActionListener controller) {
		btnVisualize.setActionCommand(ACTION_VISUALIZE);
		btnVisualize.addActionListener(controller);
		
		btnNotify.setActionCommand(ACTION_NOTIFY);
		btnNotify.addActionListener(controller);
	}
	
	public void setSDSs(Object[] sdss) {
		cmbBoxModel = new TreeMap<String, File>();
		for (Object o : sdss) {
			File f = (File) o;
			cmbBoxModel.put(f.getName(), f);
		}
		cmbBoxSDSs.setModel(new DefaultComboBoxModel(cmbBoxModel.keySet().toArray()));
		pack();
	}
	
	public File getSelectedSDS() {
		String filename = (String) cmbBoxSDSs.getSelectedItem();
		return cmbBoxModel.get(filename);
	}
	
	public void writeNotifications(String notification) {
		textAreaNotifications.append(notification + "\n");
	}
}
