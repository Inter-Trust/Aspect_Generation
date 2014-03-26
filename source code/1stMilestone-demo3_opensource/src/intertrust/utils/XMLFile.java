package intertrust.utils;

import java.io.File;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 * Read and write XML files with generic content from .xml to object and
 * from object to .xml.
 * 
 * @author UMA
 * @date   17/09/2013
 *
 */
public class XMLFile {

	private static final String XML_EXTENSION = ".xml";
	
	/**
	 * Reads an XML file and returns the content as an object of the specified class. 
	 * 
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
			content = (T) jaxbUnmarshaller.unmarshal(file);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		
		return content;
	}
	
	/**
	 * Creates a new XML file and writes the content of the specified object into the XML file. 
	 * 
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
	 * 
	 * @param filename				Path to the XML file.
	 * @param content				Content as an object of the specified class.
	 * @param typeParameterClass	Class of the object with the content.
	 * @return						File.
	 */
	private static <T> File write(File file, T content, Class<T> typeParameterClass) {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(typeParameterClass);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			jaxbMarshaller.marshal(content, file);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return file;
	}
	
	
	/**
	 * Creates a new temporal XML file and writes the content of the specified object into the XML file. 
	 * 
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
}
