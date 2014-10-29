package uma.caosd.amqp.utils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 * Read and write XML files with generic content from .xml to object and from object to .xml.
 * 
 * @author UMA
 * @date   17/09/2013
 *
 */
public class XMLUtils {
	public static final String XML_EXTENSION = ".xml";
	
	/**
	 * Reads an XML file and returns the content as an object of the specified class. 
	 * @param file					XML file.
	 * @param typeParameterClass	Class of the main object in the XML file.
	 * @return						Content as an object of the specified class.
	 */
	@SuppressWarnings("unchecked")
	public static <T> T read(File file, Class<T> typeParameterClass) {
		T content = null;
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(typeParameterClass);
	 
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			//jaxbUnmarshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
			
			content = (T) jaxbUnmarshaller.unmarshal(file);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return content;
	}
	
	/**
	 * Creates a new XML file and writes the content of the specified object into the XML file. 
	 * @param filepath				Path to the XML file.
	 * @param content				Content as an object of the specified class.
	 * @param typeParameterClass	Class of the object with the content.
	 * @return						File.
	 */
	public static <T> File write(String filepath, T content, Class<T> typeParameterClass) {
		File file = null;
		try {
			file = new File(filepath);
			if (!file.exists()) {
				file.createNewFile();
			}
			file = write(file, content, typeParameterClass);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return file;
	}
	
	/**
	 * Writes the content of the specified object into the specified XML file.
	 * @param filename				Path to the XML file.
	 * @param content				Content as an object of the specified class.
	 * @param typeParameterClass	Class of the object with the content.
	 * @return						File.
	 */
	public static <T> File write(File file, T content, Class<T> typeParameterClass) {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(typeParameterClass);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			// output pretty printed
			//jaxbMarshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			jaxbMarshaller.marshal(content, file);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return file;
	}
	
	/**
	 * Creates a new temporal XML file and writes the content of the specified object into the XML file. 
	 * @param filename				name of the temporal XML file.
	 * @param content				Content as an object of the specified class.
	 * @param typeParameterClass	Class of the object with the content.
	 * @return						File.
	 */
	public static <T> File writeTemp(String filename, T content, Class<T> typeParameterClass) {
		File fileTemp = null;
		try {
			fileTemp = File.createTempFile(filename, XML_EXTENSION);
			fileTemp = write(fileTemp, content, typeParameterClass);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fileTemp;
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T read(String input, Class<T> typeParameterClass) {
		T content = null;
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(typeParameterClass);
	 
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			//jaxbUnmarshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
			InputStream is = new ByteArrayInputStream(input.getBytes());
			content = (T) jaxbUnmarshaller.unmarshal(is);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return content;
	}
	
	public static <T> String write(T content, Class<T> typeParameterClass) {
		ByteArrayOutputStream baos = null;
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(typeParameterClass);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			// output pretty printed
			//jaxbMarshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			
			baos = new ByteArrayOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(baos, "UTF-8");
			jaxbMarshaller.marshal(content, osw);
		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return baos != null ? baos.toString() : null;
	}
	
/*	public static String readFile(String path, Charset encoding) { 
		String res = null;
		try {
			byte[] encoded = Files.readAllBytes(Paths.get(path));
			res = new String(encoded, encoding);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return res;
	}*/
	
	public static String readFile(File file) throws IOException {
	    BufferedReader reader = new BufferedReader(new FileReader(file));
	    String         line = null;
	    StringBuilder  stringBuilder = new StringBuilder();
	    String         ls = System.getProperty("line.separator");

	    while( ( line = reader.readLine() ) != null ) {
	        stringBuilder.append( line );
	        stringBuilder.append( ls );
	    }
	    reader.close();
	    return stringBuilder.toString();
	}
	
	public static void writeToFile(File file, String content) {
		try {
			PrintWriter out = new PrintWriter(file);
			out.println(content);
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
