package intertrust;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextArea;

import evoting.core.server.EVotingServer;

/**
 * Provides the initialization files to the server.
 * 
 * @author UMA
 * @date   23/09/2013
 *
 */
public abstract aspect Intertrust_Initialization_EVotingServerFiles extends Intertrust_Initialization {

	public static final String[] SDS_FILESPATH = {"serverFiles" + File.separator + "scenario1Server.xml",
		 										  "serverFiles" + File.separator + "scenario2Server.xml",
												  "serverFiles" + File.separator + "scenario3Server.xml"};
	
	public static final String INITIALSDS_FILEPATH = "serverFiles" + File.separator + "initialSdsServer.xml";
	private static final String LAF_FILEPATH = "serverFiles" + File.separator + "listAspectFunctionalityServer.xml";
	
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
		return EVotingServer.getConsole();
	}
}
