package com.fsd.filesystem;

import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

public class filesystem {
	
	public static void main(String args[]) {
		int i = 0;
		filesystem NewFile = new filesystem();
		System.out.println("---Welcome to LockedMe.com---");
		System.out.println("Developed By: Manasbrata Das"+'\n');
		do {
			try
			{
			System.out.println("Enter any 1 of the below Options "+'\n');
			System.out.println(" 1 -> Show Current Files in Root Directory<Ascending Order>");
			System.out.println(" 2 -> Perform File Operations < Add, Search, Delete >");
			System.out.println(" 3 -> Close Application ");

			Scanner user_input = new Scanner(System.in);
	
			i = user_input.nextInt();
			switch (i) {
			case 1:
				String test1 = NewFile.Fetch_File("",true);
				Boolean flag3 = NewFile.Exit_App();
				if (!flag3)
					i = 3;
				break;
			case 2:
				NewFile.File_Operations();
				Boolean flag1 = NewFile.Exit_App();
				if (!flag1)
					i = 3;
				break;
			case 3:

				break;

			default:
				System.out.println("Invalid Entry, Please choose the valid Option..!!"+'\n');

				}
			}catch(Exception e) {
				System.out.println("Invalid Entry, Please choose the valid Option..!!"+'\n');				
				}
		} while (i != 3);
		if (i == 3)
			System.out.println("Closing Application,Thank you for using LockedMe.com ");
	}
	
public void File_Operations() {
		String file_Name = "";
		Boolean flag=false;
		System.out.println("Select File Operation you want to perform");
		System.out.println("A. Add New File");
		System.out.println("S. Search Specific File");
		System.out.println("D. Delete Specific File");
		System.out.println("X. Delete All Existing Files");
		Scanner user_input=new Scanner(System.in);
		boolean test=false;
		String response=user_input.next();
	     try {
	    	 switch(response) {
	    	 case "A":
	    			System.out.println("Enter File Name to add  ");
					user_input = new Scanner(System.in);
					file_Name = user_input.next();
					test = Add_NewFile(file_Name);
					break;
	    	 case "a":
	    			System.out.println("Enter File Name to add  ");
					user_input = new Scanner(System.in);
					file_Name = user_input.next();
					test = Add_NewFile(file_Name);
					break;
	    	 case "S":
	    		 System.out.println("Enter File Name ");
					user_input = new Scanner(System.in);
					if (user_input.hasNextLine()) {
						file_Name = user_input.next();
					}

					file_Name = Fetch_File(file_Name,true);
	    	 case "s":
	    		 System.out.println("Enter File Name ");
					user_input = new Scanner(System.in);
					if (user_input.hasNextLine()) {
						file_Name = user_input.next();
					}

					String test1 = Fetch_File(file_Name,true);
					break;
	    	 case "D":
	    		    System.out.println("Enter File Name to delete  ");
					user_input = new Scanner(System.in);
					file_Name = user_input.next();
					test = Delete_File(file_Name);
					break;
	    	 case "d":
	    		 System.out.println("Enter File Name to delete  ");
					user_input = new Scanner(System.in);
					file_Name = user_input.next();
					test = Delete_File(file_Name);
					break;
	    	 case "X":
					String test5 = Delete_All();
					break;
	    	 case "x":
				    String test6 = Delete_All();
					break;
	    		 default:
	    			 System.out.println("Please enter the valid data");
	    			 File_Operations();
	    			 break;
	    	 }
	     }catch (Exception e) {
	    	System.out.println("Please enter the valid data");
	    	 File_Operations();
		}
	}
	
	public Boolean Exit_App() {
		Boolean flag = Boolean.FALSE;
		System.out.println("Do you want to continue ");
		System.out.println("Y/N");
		Scanner user_input = new Scanner(System.in);
		String user_pref = user_input.next();
		if (user_pref.equalsIgnoreCase("Y")) {
			flag = true;
		} else if (user_pref.equalsIgnoreCase("N")) {
			flag = false;
		} else {
			System.out.println("Invalid Entry,Please Enter either Y or N");
			flag = Exit_App();
		}
		return flag;
	}

	public Boolean Delete_File(String File_name) {

		String Is_File_exist = Fetch_File(File_name,false);

		if (Is_File_exist.equals("File Not Found")) {

			System.out.println("File does not exist to delete ");
			return false;
		} else {
			File myfile = new File("C:\\Users\\manasbrata_das\\Downloads\\FSD_project\\FSD_project\\file_directory",
					File_name + ".txt");
			try {
				myfile.delete();
				System.out.println("File deleted Successfully ");
				return true;
			} catch (Exception ex) {
				System.out.println("Error Occured");
				return false;
			}
		}

	}

	public Boolean Add_NewFile(String File_name) {

		String Is_File_exist = Fetch_File(File_name,false);
		if (Is_File_exist.equals("File Not Found")) {

			File myfile = new File("C:\\Users\\manasbrata_das\\Downloads\\FSD_project\\FSD_project\\file_directory",
					File_name + ".txt");
			try {
				boolean create_new_file = myfile.createNewFile();
				if (create_new_file == true) {

					System.out.println("Added New File to Existing Directory successfully");
					return true;
				}else
				System.out.println("File already Exists");

			} catch (Exception e) {
				System.out.println("Unable to Add File");
			}

			return true;

		} else {

			System.out.println("File already Exists ");
			return false;
		}
	}

	public String stripExtension(String str) {
		if (str == null)
			return null;

		int pos = str.lastIndexOf(".");

		if (pos == -1)
			return str;

		return str.substring(0, pos);
	}

	public int binary_search(String[] File_list, String File_name) {

		int x = 0, y = File_list.length - 1;

		while (x <= y) {
			int z = x + (y - x) / 2;
			String orginal_file = stripExtension(File_list[z]);
			int res = File_name.compareTo(orginal_file);
			if (res == 0)
				return z;
			if (res > 0)
				x = z + 1;
			else
				y = z - 1;

		}

		return -1;
	}

	public String Delete_All()
    {
		try {
			File folder = new File("C:\\Users\\manasbrata_das\\Downloads\\FSD_project\\FSD_project\\file_directory");
			String[] File_list = folder.list();
			if(File_list.length!=0) {
			for(String fileName: File_list) {
				fileName=stripExtension(fileName);
				File myfile = new File("C:\\Users\\manasbrata_das\\Downloads\\FSD_project\\FSD_project\\file_directory",
						fileName + ".txt");
				try {
					myfile.delete();
				} catch (Exception ex) {
					System.out.println("no permission to delete this file");
				}
			}
			
			System.out.println("All Files from the deleted successfully from Existing Directory..!!");
			}
			else
				System.out.println("No files to delete");
		}catch(Exception e) {
			System.out.println("Unable to delete the file");
		}
		return "";
	}
	
	
	public String Fetch_File(String File_name,boolean flag) {
		File folder = new File("C:\\Users\\manasbrata_das\\Downloads\\FSD_project\\FSD_project\\file_directory");
		String[] File_list = folder.list();
		Arrays.sort(File_list);
		if (File_name.equals("")) {
			if(File_list.length!=0) {
				System.out.println("Below are the Files in the Directory in Ascending Order ");

				for (int i = 0; i < File_list.length; i++) {
					System.out.println(File_list[i]);

				}
			}else {
				System.out.println("The directory is empty!!");
			}
		

			return File_name;
		} else {

			int Is_file_exists = binary_search(File_list, File_name);

			if (Is_file_exists == -1) {
				if(flag)
				System.out.println("Entered File Not  Found ");
				return "File Not Found";
			} else {
                if(flag)
				System.out.println("File Found ");
				return File_name;
			}
		}

	}
}
