package evoting.core.server;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class EVotingServerPane extends JPanel {
	
	private JTextArea textAreaInformation;
	
	/**
	 * Create the panel.
	 */
	public EVotingServerPane() { 
		designInterface();
	}

	/**
	 * Design the graphical interface.
	 */
	private void designInterface() {
		setLayout(new BorderLayout(0, 0));
		
		JLabel lblEvotingServer = new JLabel("e-Voting Server");
		lblEvotingServer.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblEvotingServer.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblEvotingServer, BorderLayout.NORTH);
		
		textAreaInformation = new JTextArea();
		textAreaInformation.setEditable(false);
		textAreaInformation.setColumns(50);
		textAreaInformation.setTabSize(4);
		textAreaInformation.setRows(10);
		textAreaInformation.setWrapStyleWord(true);
		textAreaInformation.setLineWrap(true);
		
		JScrollPane scrollPane = new JScrollPane(textAreaInformation);
		add(scrollPane, BorderLayout.CENTER);
	}
	
	/**
	 * Register a controller for the actions defined for the graphical components.
	 */
	public void registerController(ActionListener controller) {
		
	}
	
	public void showInformation(String info) {
		textAreaInformation.append(info + "\n");
	}

	public JTextArea getTextAreaInformation() {
		return textAreaInformation;
	}
	
	
}
