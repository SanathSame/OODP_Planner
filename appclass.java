package P1;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class appclass {
	
	
	public static void main(String[] args) throws ParseException {
		LocalDate ld =LocalDate.of(2021, 1, 30); 
		LocalDate ld1 =LocalDate.of(2020, 11, 01); 
		LocalTime st =LocalTime.of(10,43,12);
		LocalTime et =LocalTime.of(10,43,12);
		
		//create objects and save in file
		/*Course foo1=new Course("Cz2002","OOPD");
		Course foo5=new Course("Cz2001","ALGO");		//binaryio.writeSerializedObject("courses.bin",foo1);*/
		User foo2 = new Student("Ankitha","minnu","lolnoice", "k119",  ld1,  ld);
		User foo3 = new Student("Agnesh","agnesh","hahaokok", "uuU118",  ld1,  ld);
		User foo4 = new Student("Agneshee","agneshf","hahaok", "uuU118s",  ld1,  ld);
		User admin1 = new Admin("Alexei", "Alex001", "oopdproject", "aaA118");
		
		binaryio.writeSerializedObject("users.dat", (User)foo2);
		binaryio.writeSerializedObject("users.dat", (User)foo3);
		binaryio.writeSerializedObject("users.dat", (User)foo4);
		binaryio.writeSerializedObject("users.dat", (User)admin1);
		
		binaryio.writeSerializedObject("students.dat", foo2);
		binaryio.writeSerializedObject("students.dat", foo3);
		binaryio.writeSerializedObject("students.dat", foo4);
		binaryio.writeSerializedObject("admins.dat", admin1);
		//read file
		ArrayList<Object> al = binaryio.readSerializedObject("users.dat");
		/*if(al.size()==0) {
			System.out.println("Error reading the file!!");
			return;
		}
		for (int i = 0 ; i <al.size() ; i++) {
			Student xyz  = (Student)al.get(i);
			System.out.println(xyz.getUsername());
			for (int j = 0 ; j <xyz.getIndices().size() ; j++) {
				System.out.println(xyz.getIndices().get(j).getCourseId());
			}
		}*/

		
		//System.out.println("   Course Code\t Course Name ");
		//al.remove(0);
		//System.out.println("-------------------------------------");
		for (int i = 0 ; i <al.size() ; i++) {
			User cour  = (User)al.get(i);
			cour.setPassword(fileController.hash(cour.getPassword()));
			al.remove(i);
			al.add(i,cour);
			//System.out.println(i+1+") "+fileController.hash(cour.getPassword())+"\t    "+cour.getUsername());
		}	
		binaryio.clearwriteSerializedObject("users.dat", al);
		
		
		for (int i = 0 ; i <al.size() ; i++) {
			User cour  = (User)al.get(i);
			System.out.println(cour.getPassword()+"\t    "+cour.getUsername());
		}
	}
}
