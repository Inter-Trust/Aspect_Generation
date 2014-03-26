package evoting.core.client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JFrame;
import javax.swing.JTextArea;

public class EVotingClient implements EVotingInt {

	static final int SERVER_PORT = 4444;
	static final String SERVER_HOST = null;
	
	private String serverHost;
	private int serverPort;
	
	public EVotingClient() {
		this.serverHost = SERVER_HOST;
		this.serverPort = SERVER_PORT;
	}
	
	public EVotingClient(String serverHost, int serverPort) {
		this.serverHost = serverHost;
		this.serverPort = serverPort;
	}
	
	public String vote(String vote) {
		String response = null;
		
		try {
			Socket socket = new Socket(serverHost, serverPort);
			//System.out.println("Socket: " + socket);
			//eVotingClientView.showInformation("Socket: " + socket);
			
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
			
			// send
			out.println(vote);
			response = in.readLine();
			//eVotingClientView.showInformation(response);
			
			in.close();
			out.close();
			socket.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return response;
	}
	
	public static void main(String[] args) {
		initializeGUI();
		initialize();
	}
	
	public static void initialize() {
		EVotingClient eVotingClient = new EVotingClient();
		EVotingClientController eVotingClientController = new EVotingClientController(eVotingClient, eVotingClientView);
	}
	
	public static void initializeGUI() {
		eVotingClientView = new EVotingClientPane();
		
		JFrame eVotingClientWindows = new JFrame("EVoting Client");
		eVotingClientWindows.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		eVotingClientWindows.setContentPane(eVotingClientView);
		eVotingClientWindows.pack();
		eVotingClientWindows.setLocationRelativeTo(null);
		eVotingClientWindows.setVisible(true);	
	}
	
	private static EVotingClientPane eVotingClientView;
	
	public static JTextArea getConsole() {
		return eVotingClientView.getTextAreaInformation();
	}
		
}
