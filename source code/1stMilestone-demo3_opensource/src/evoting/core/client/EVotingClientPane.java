package evoting.core.client;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class EVotingClientPane extends JPanel {
	
	// Interface components
	private JTextField textFieldVote;
	private JTextArea textAreaInformation;
	private JButton btnQuit;
	private JButton btnVote;
	
	// Actions
	public static final String ACTION_QUIT = "QUIT";
	public static final String ACTION_VOTE = "VOTE";
	
	/**
	 * Create the panel.
	 */
	public EVotingClientPane() { 
		designInterface();
	}

	/**
	 * Design the graphical interface.
	 */
	private void designInterface() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {165, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{25, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE, 0.0, 0.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblEvotingClient = new JLabel("e-Voting Client");
		lblEvotingClient.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblEvotingClient.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblEvotingClient = new GridBagConstraints();
		gbc_lblEvotingClient.gridwidth = 3;
		gbc_lblEvotingClient.insets = new Insets(0, 0, 5, 5);
		gbc_lblEvotingClient.anchor = GridBagConstraints.NORTH;
		gbc_lblEvotingClient.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblEvotingClient.gridx = 1;
		gbc_lblEvotingClient.gridy = 0;
		add(lblEvotingClient, gbc_lblEvotingClient);
		
		JLabel lblChoice = new JLabel("Choice:");
		GridBagConstraints gbc_lblChoice = new GridBagConstraints();
		gbc_lblChoice.anchor = GridBagConstraints.WEST;
		gbc_lblChoice.insets = new Insets(0, 0, 5, 5);
		gbc_lblChoice.gridx = 0;
		gbc_lblChoice.gridy = 1;
		add(lblChoice, gbc_lblChoice);
		
		textFieldVote = new JTextField();
		GridBagConstraints gbc_textFieldVote = new GridBagConstraints();
		gbc_textFieldVote.gridwidth = 3;
		gbc_textFieldVote.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldVote.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldVote.gridx = 1;
		gbc_textFieldVote.gridy = 1;
		add(textFieldVote, gbc_textFieldVote);
		textFieldVote.setColumns(10);
		
		JLabel lblInformationConsole = new JLabel("Information console:");
		GridBagConstraints gbc_lblInformationConsole = new GridBagConstraints();
		gbc_lblInformationConsole.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblInformationConsole.insets = new Insets(0, 0, 5, 5);
		gbc_lblInformationConsole.gridx = 0;
		gbc_lblInformationConsole.gridy = 2;
		add(lblInformationConsole, gbc_lblInformationConsole);
		
		textAreaInformation = new JTextArea();
		textAreaInformation.setEditable(false);
		textAreaInformation.setColumns(50);
		textAreaInformation.setTabSize(4);
		textAreaInformation.setRows(10);
		textAreaInformation.setWrapStyleWord(true);
		textAreaInformation.setLineWrap(true);
		GridBagConstraints gbc_textAreaInformation = new GridBagConstraints();
		gbc_textAreaInformation.gridwidth = 3;
		gbc_textAreaInformation.insets = new Insets(0, 0, 5, 5);
		gbc_textAreaInformation.fill = GridBagConstraints.BOTH;
		gbc_textAreaInformation.gridx = 1;
		gbc_textAreaInformation.gridy = 2;
		
		JScrollPane scrollPane = new JScrollPane(textAreaInformation);
		add(scrollPane, gbc_textAreaInformation);
		
		btnVote = new JButton("Vote");
		GridBagConstraints gbc_btnVote = new GridBagConstraints();
		gbc_btnVote.insets = new Insets(0, 0, 5, 0);
		gbc_btnVote.gridx = 4;
		gbc_btnVote.gridy = 1;
		add(btnVote, gbc_btnVote);
		
		btnQuit = new JButton("Quit");
		GridBagConstraints gbc_btnQuit = new GridBagConstraints();
		gbc_btnQuit.gridx = 4;
		gbc_btnQuit.gridy = 3;
		add(btnQuit, gbc_btnQuit);
	}
	
	/**
	 * Register a controller for the actions defined for the graphical components.
	 */
	public void registerController(ActionListener controller) {
		btnQuit.setActionCommand(ACTION_QUIT);
		btnQuit.addActionListener(controller);
		
		btnVote.setActionCommand(ACTION_VOTE);
		btnVote.addActionListener(controller);
	}
	
	public void showInformation(String info) {
		textAreaInformation.append(info + "\n");
	}
	
	public String getVote() {
		return textFieldVote.getText();
	}

	public JTextArea getTextAreaInformation() {
		return textAreaInformation;
	}
	
	
}
