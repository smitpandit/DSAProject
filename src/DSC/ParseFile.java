package DSC;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ParseFile {
	
	public ArrayList<userinfo> fileparse(File filename) throws IOException {

	String storeLine;
	String regex = "\\|"; // Escape sequence required for using pipe as
							// pattern
	String splitArray[];
	ArrayList<userinfo> store_user = new ArrayList<userinfo>();
	FileReader read = new FileReader(filename);
	Closeable resource = read;
	
	try {

		@SuppressWarnings("resource")
		BufferedReader bufferedRead = new BufferedReader(read);
		resource = bufferedRead;
		while (bufferedRead.ready()) {
			storeLine = bufferedRead.readLine();
			if(storeLine.equals("\n"))
				continue;
			else {
				splitArray = storeLine.split(regex);					
																		//File read and parsed
													  					//Assuming the process
													  					//format in the file as
													       				//per project
													  					//description
													 
				 int processID = Integer.parseInt(splitArray[0]);
				 String username = splitArray[1];
				 String ip = splitArray[2];
				 int port = Integer.parseInt(splitArray[3]);
				 mainMenu.n++;
				userinfo newUser = new userinfo(processID, username, ip, port);
				store_user.add(newUser);							//User objects created with their attributes
			}
		//	bufferedRead.close();
		}
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} finally {
		resource.close();
	}

	return store_user;

}

	
	
}
