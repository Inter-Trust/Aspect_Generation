package intertrust.modules.aw.gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class AspectWeaverView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;

	// Actions
	private JTextArea txtAreaConsole;
		
	/**
	 * Create the frame.
	 */
	public AspectWeaverView() {
		setTitle("Aspect Weaver");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JLabel lblAspectWeaver = new JLabel("Aspect Weaver");
		lblAspectWeaver.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblAspectWeaver, BorderLayout.NORTH);
		lblAspectWeaver.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		txtAreaConsole = new JTextArea();
		txtAreaConsole.setEditable(false);
		txtAreaConsole.setRows(10);
		txtAreaConsole.setColumns(50);
		txtAreaConsole.setWrapStyleWord(true);
		txtAreaConsole.setTabSize(4);
		txtAreaConsole.setLineWrap(true);
		
		JScrollPane scrollPane = new JScrollPane(txtAreaConsole);
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		pack();
		setLocationRelativeTo(null);
		//setVisible(true);
	}

	/**
	 * Register a controller for the actions defined for the graphical components.
	 */
	public void registerController(ActionListener controller) {		
		
	}
	
	public void writeTextConsole(String text) {
		txtAreaConsole.append(text);
	}

	public JTextArea getTxtAreaConsole() {
		return txtAreaConsole;
	}
	
}
