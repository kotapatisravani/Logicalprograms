/*Write a Java program to read file contents and display on console.*/
import java.io.FileReader;
import java.io.FileWriter;

import java.util.Scanner;
public class example78 {

	public static void main(String[] args) {
		
		Scanner scan=new Scanner(System.in);
		
		try {
			
			FileReader fr=new  FileReader("example1.tx");
			int ch=0;
			while((ch=fr.read())!=-1) {
				System.out.print((char) ch);
			}
		}
		catch (Exception e) {
						
			System.out.println(e);
		}
	}
}
