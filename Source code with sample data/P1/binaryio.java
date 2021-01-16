package P1;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * A boundary class used to store and retrieve entity class objects to and from the files used.
 * Uses the methods readObject and writeObject of the ObjectInputStream and ObjectOutputStream classes respectively.
 * 
 */
public class binaryio {
	/**
	 * A public static method, to retrieve objects from the files.
	 * It can be called without creating and object of this class.
	 * 
	 * @param filename : the file to be read from.
	 * @return ArrayList of objects retrieved from the mentioned file.
	 */
	public static ArrayList<Object> readSerializedObject(String filename) {
		ArrayList<Object> Details = new ArrayList<Object>();
		Object obj=null;
		try {
			ObjectInputStream o = new ObjectInputStream(new FileInputStream(filename));
			while ((obj = o.readObject()) != null) {
	            Details.add(obj);
	        }
			o.close();
		} catch (IOException ex) {
			System.out.print("");
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		return Details;
	}
	
	/**
	 * A public static method, to store additional object to the file.
	 * Retrieves objects from the files, add the new objects to the 
	 * retrieved array and stores the new array to the file.
	 * 
	 * @param filename : the file to be written to.
	 * @param write    : the object to be added to the file.
	 */
	public static void writeSerializedObject(String filename, Object write) {
		ArrayList<Object> Details = new ArrayList<Object>();
		Object obj = null;
		try {
			ObjectInputStream o = new ObjectInputStream(new FileInputStream(filename));
			while ((obj = o.readObject()) != null) {
	            Details.add(obj);
	        }
			o.close();
		} catch (IOException ex) {
			System.out.print("");
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		try {
			Details.add(write);
			ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream(filename));
			for (int i = 0 ; i <Details.size() ; i++) {
				o.writeObject((Object)Details.get(i));
			}
			o.close();
		} catch (IOException ex) {
			System.out.print("");
		}
		
	}
	/**
	 * A public static method, to overwrite the file with new array of objects.
	 * 
	 * @param filename : the file to be overwritten.
	 * @param Details  : ArrayList of objects to be stored in the file.
	 */
	public static void clearwriteSerializedObject(String filename, ArrayList<Object> Details) {
		try {
			ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream(filename));
			for (int i = 0 ; i <Details.size() ; i++) {
				o.writeObject((Object)Details.get(i));
			}
			o.close();
		} catch (IOException ex) {
			System.out.print("");
		}
	}
}
