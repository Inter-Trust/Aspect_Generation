package evoting.core.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JTextArea;

public class EVotingServer implements EVotingServerInt {

	static final int SERVER_PORT = 4444 ; 

	private ServerSocket serverSocket;
	// Client
	private Socket clientSocket;
	private BufferedReader in;
	private PrintWriter out;
	private int serverPort;

	public EVotingServer() {
		this.serverPort = SERVER_PORT;
	}
	public EVotingServer(int serverPort) {
		this.serverPort = serverPort;
	}
	
	public void initServer() {
		try {
			serverSocket = new ServerSocket(serverPort);
			//System.out.println(">>EVOTING SERVER initiated: " + serverSocket);
			eVotingServerView.showInformation(">>EVOTING SERVER initiated: " + serverSocket);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getClientVote() {		
		String line = null;
		try {
			clientSocket = serverSocket.accept();
			//System.out.println("...");
			//System.out.println(">>New client: " + clientSocket);
			eVotingServerView.showInformation("...");
			eVotingServerView.showInformation(">>New client: " + clientSocket);
			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			line = in.readLine();
			//in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return line;
	}
	
	public void sendResponse() {
		try {
			out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream())), true);
			out.println("Server>>Vote received.");
			out.close();
			in.close();
			clientSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/*
	public void server() {
		try {
			// Create ServerSocket
			ServerSocket serverSocket = new ServerSocket(serverPort);
			//System.out.println(">>EVOTING SERVER initiated: " + serverSocket);
			eVotingServerView.showInformation(">>EVOTING SERVER initiated: " + serverSocket);
			
			while (true) {
				Socket clientSocket = serverSocket.accept();
				//System.out.println("...");
				//System.out.println(">>New client: " + clientSocket);
				eVotingServerView.showInformation("...");
				eVotingServerView.showInformation(">>New client: " + clientSocket);
				
				BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream())), true);
				
				String line = in.readLine();
				if (line != null) {
					receiveVote(line);
					out.println("Server>>Vote received.");
				}
				in.close();
				out.close();
				clientSocket.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}*/
	
	public void receiveVote(String vote) {
		//System.out.println(">>Client vote received: " + vote);
		eVotingServerView.showInformation(">>Client vote received: " + vote);
	}
	
	public static void main(String[] args) {
		initializeGUI();
		initialize();
	}
	
	public static void initialize() {
		EVotingServer eVotingServer = new EVotingServer();
		//ApplicationContext appContext = new ClassPathXmlApplicationContext(new String[]{"beans.xml"});
		//EVotingServer eVotingServer = (EVotingServer) appContext.getBean("EVotingServer");
		eVotingServer.initServer();
		while (true) {
			String vote = eVotingServer.getClientVote();	
			eVotingServer.receiveVote(vote);
			eVotingServer.sendResponse();
		}
	}
	
	public static void initializeGUI() {
		eVotingServerView = new EVotingServerPane();
		
		JFrame eVotingClientWindows = new JFrame("EVoting Server");
		eVotingClientWindows.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		eVotingClientWindows.setContentPane(eVotingServerView);
		eVotingClientWindows.pack();
		eVotingClientWindows.setLocationRelativeTo(null);
		eVotingClientWindows.setVisible(true);
	}
	
	private static EVotingServerPane eVotingServerView;
	
	public static JTextArea getConsole() {
		return eVotingServerView.getTextAreaInformation();
	}
	
}