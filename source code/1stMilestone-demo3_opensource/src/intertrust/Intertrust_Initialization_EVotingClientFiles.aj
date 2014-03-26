package intertrust;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextArea;

import evoting.core.client.EVotingClient;

/**
 * Provides the initialization files to the client.
 * 
 * @author UMA
 * @date   23/09/2013
 *
 */
public abstract aspect Intertrust_Initialization_EVotingClientFiles extends Intertrust_Initialization {

	private static final String[] SDS_FILESPATH = {"clientFiles" + File.separator + "scenario1Client.xml",
		 										 "clientFiles" + File.separator + "scenario2Client.xml",
		 										 "clientFiles" + File.separator + "scenario3Client.xml"};
	
	private static final String INITIALSDS_FILEPATH = "clientFiles" + File.separator + "initialSdsClient.xml";
	private static final String LAF_FILEPATH = "clientFiles" + File.separator + "listAspectFunctionalityClient.xml";

	
	@Override
	public List<URL> getSecurityDeploymentSpecificationFilesPath() {
		ArrayList<URL> urls = new ArrayList<URL>();
		for (String s : SDS_FILESPATH) {
			try {
				File file = new File(s);
				urls.add(file.toURI().toURL());
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}
		return urls;
	}

	@Override
	public URL getInitialSdsFilePath() {
		try {
			File file = new File(INITIALSDS_FILEPATH);
			return file.toURI().toURL();
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public URL getListAspectFunctionalityFilePath() {
		try {
			File file = new File(LAF_FILEPATH);
			return file.toURI().toURL();
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public JTextArea getConsole() {
		return EVotingClient.getConsole();
	}
}
