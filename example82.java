/*Write a Java program to count characters, words and lines in a text file.*/
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import java.util.Scanner;
public class example82 {

	public static void main(String[] args) {
		
		Scanner scan=new Scanner(System.in);
		
		try {
			
			BufferedReader fr=new BufferedReader(new FileReader("example2.txt"));
			String str1 = fr.readLine();
			int ch=0;
			int alpa=0;
			int line=0;
			//String str=null;
			while(str1!=null) {
				line++;
				String str[] = str1.split(" ");
				alpa = alpa + str.length;
				for(String w: str) {
					ch = ch +w.length();
				}
				str1=fr.readLine();
			}
			
			
			System.out.println("Number of characters :"+ch);
			System.out.println("Number of lines :"+line);
			System.out.println("Number of lines :"+alpa);
		}
		catch (Exception e) {
			
			
			System.out.println(e);
		}
	}
}
