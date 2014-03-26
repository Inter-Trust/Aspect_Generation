package evoting.core.client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EVotingClientController implements ActionListener {

	private EVotingClient modelClient;
	private EVotingClientPane viewClient;
	
	public EVotingClientController(EVotingClient modelClient, EVotingClientPane viewClient) {
		this.modelClient = modelClient;
		this.viewClient = viewClient;
		
		this.viewClient.registerController(this);
	}
	
	public void actionPerformed(ActionEvent event) {
		String command = event.getActionCommand();
		if (command.equals(viewClient.ACTION_QUIT)) {	
			System.exit(0);
		} else if (command.equals(viewClient.ACTION_VOTE)) {
			String vote = viewClient.getVote();
			viewClient.showInformation(">>Voting... " + vote);
			
			String response = modelClient.vote(vote);
			viewClient.showInformation(response);
		}		
	}
	
}
