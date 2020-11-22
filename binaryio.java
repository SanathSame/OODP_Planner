package P1;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class binaryio {
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
