package intertrust.utils;

import java.awt.BorderLayout;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import org.apache.commons.io.FileUtils;

import net.boplicity.xmleditor.XmlTextPane;

/**
 * Graphic viewer for XML files.
 * 
 * @author UMA
 * @date   11/09/2013
 *
 */
public class XMLViewer extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private XmlTextPane editorPane;
	
	/**
	 * Creates an empty XML visor in a new window.
	 */
	public XMLViewer() {
		makeGUI();
	}
    
	/**
	 * Creates an XML visor in a new window and opens the file specified.
	 * 
	 * @param filepath	Path to the XML file.
	 */
	public XMLViewer(String filepath) {
		setTitle("XMLViewer: " + filepath);
		makeGUI();
		setXML(new File(filepath));
	}
	
	/**
	 * Creates an XML visor in a new window and opens the file specified.
	 * 
	 * @param file	XML file.
	 */
	public XMLViewer(File file) {
		setTitle("XMLViewer: " + file.getPath());
		makeGUI();
		setXML(file);
	}
	
	private void makeGUI() {
		editorPane = new XmlTextPane();
		editorPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		editorPane.setLayout(new BorderLayout(0, 0));
		editorPane.setEditable(false);
		
		JScrollPane editorScrollPane = new JScrollPane(editorPane);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setContentPane(editorScrollPane);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	private void setXML(File file) {
		try {
			String text = readFile(file, Charset.forName("UTF-8"));
			editorPane.setText(text);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private String readFile(File file, Charset encoding) throws IOException {
		//byte[] encoded = Files.readAllBytes(Paths.get(path)); // Java 1.7
		byte[] encoded = FileUtils.readFileToByteArray(file);
		return encoding.decode(ByteBuffer.wrap(encoded)).toString();
	}
}
