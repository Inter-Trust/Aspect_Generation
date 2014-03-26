package intertrust.modules.ag.gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class AspectGenerationView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JButton btnVisualizeSecurityAspect;
	private JButton btnGenerateSecurityAdaptation;
	private JButton btnVisualizeSecurityAdaptation;
	private JButton btnAdaptationRequest;

	// Actions
	public static final String ACTION_VISUALIZEMAPPING = "VISUALIZE_MAPPING";
	public static final String ACTION_GENERATEADAPTATION = "GENERATE_ADAPTATION";
	public static final String ACTION_VISUALIZEADAPTATION = "VISUALIZE_ADAPTATION";
	public static final String ACTION_ADAPTATIONREQUEST = "ADAPTATION_REQUEST";
	public static final String EXAMINE_ASPECTS = "EXAMINE_ASPECTS";
	public static final String ADD_NEW_ASPECT = "ADD_NEW_ASPECT";
	
	private JButton btnAddNewAspect;
	private JTextField textFieldNewAspect;
	private JButton btnExamine;
	private JPanel panel_5;
	private JLabel lblInformation;
		
	/**
	 * Create the frame.
	 */
	public AspectGenerationView() {
		setTitle("Aspect Generation");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 437, 161);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(6, 1, 0, 0));
		
		JPanel panel_0 = new JPanel();
		contentPane.add(panel_0);
		
		JLabel lblAspectGeneration = new JLabel("Aspect Generation");
		lblAspectGeneration.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel_0.add(lblAspectGeneration);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1);
		
		btnVisualizeSecurityAspect = new JButton("Visualize Security Aspectual Knowledge");
		panel_1.add(btnVisualizeSecurityAspect);
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2);
		
		btnGenerateSecurityAdaptation = new JButton("Generate Security Adaptation Plan");
		panel_2.add(btnGenerateSecurityAdaptation);
		
		btnVisualizeSecurityAdaptation = new JButton("Visualize Security Adaptation Plan");
		btnVisualizeSecurityAdaptation.setEnabled(false);
		panel_2.add(btnVisualizeSecurityAdaptation);
		
		panel_5 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_5.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		contentPane.add(panel_5);
		
		JLabel lblToDo = new JLabel("To do:");
		lblToDo.setFont(new Font("Dialog", Font.BOLD, 12));
		lblToDo.setForeground(Color.BLACK);
		panel_5.add(lblToDo);
		
		lblInformation = new JLabel("Generate security adaptation plan!");
		lblInformation.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblInformation.setForeground(Color.RED);
		panel_5.add(lblInformation);
		
		JPanel panel_3 = new JPanel();
		panel_3.setVisible(false); // not open source
		contentPane.add(panel_3);
		
		btnAddNewAspect = new JButton("Add new aspect");
		btnAddNewAspect.setEnabled(false);
		panel_3.add(btnAddNewAspect);
		
		textFieldNewAspect = new JTextField();
		panel_3.add(textFieldNewAspect);
		textFieldNewAspect.setColumns(20);
		
		btnExamine = new JButton("examine...");
		btnExamine.setEnabled(false);
		panel_3.add(btnExamine);
		
		JPanel panel_4 = new JPanel();
		contentPane.add(panel_4);
		
		btnAdaptationRequest = new JButton("Adaptation Request");
		btnAdaptationRequest.setEnabled(false);
		panel_4.add(btnAdaptationRequest);
		
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	/**
	 * Register a controller for the actions defined for the graphical components.
	 */
	public void registerController(ActionListener controller) {		
		btnVisualizeSecurityAspect.setActionCommand(ACTION_VISUALIZEMAPPING);
		btnVisualizeSecurityAspect.addActionListener(controller);
		
		btnGenerateSecurityAdaptation.setActionCommand(ACTION_GENERATEADAPTATION);
		btnGenerateSecurityAdaptation.addActionListener(controller);
		
		btnVisualizeSecurityAdaptation.setActionCommand(ACTION_VISUALIZEADAPTATION);
		btnVisualizeSecurityAdaptation.addActionListener(controller);
		
		btnAdaptationRequest.setActionCommand(ACTION_ADAPTATIONREQUEST);
		btnAdaptationRequest.addActionListener(controller);
		
		btnAddNewAspect.setActionCommand(ADD_NEW_ASPECT);
		btnAddNewAspect.addActionListener(controller);
		
		btnExamine.setActionCommand(EXAMINE_ASPECTS);
		btnExamine.addActionListener(controller);
	}
	
	public void setBtnVisualizeSecurityAdaptationEnabled(boolean enabled) {
		btnVisualizeSecurityAdaptation.setEnabled(enabled);
	}
	
	public void setBtnAdaptationRequestEnabled(boolean enabled) {
		btnAdaptationRequest.setEnabled(enabled);
	}
	
	public void setBtnAddNewAspectEnabled(boolean enabled) {
		btnAddNewAspect.setEnabled(enabled);
	}
	
	public void setBtnExamineEnabled(boolean enabled) {
		btnExamine.setEnabled(enabled);
	}
	
	public void setNewAspectPath(String path) {
		textFieldNewAspect.setText(path);
	}
	
	public void setTextInformation(String text) {
		lblInformation.setText(text);
	}
}
