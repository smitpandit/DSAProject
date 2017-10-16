package DSC;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import java.util.ArrayList;

public class logReadWrite {
	ArrayList<content> con;
	public logReadWrite() {
		// TODO Auto-generated constructor stub
	}
	public void writetolog(log log) throws JsonIOException, IOException 
	{
		BufferedWriter bWriter;
		Gson gson = new Gson();
		//this.con = con;
		String outString = gson.toJson(log);
		System.out.println("Writing in file");
		bWriter = new BufferedWriter(new FileWriter("out.txt"));
		bWriter.write(outString);
		bWriter.close();
		
	}
	
	public String readfromlog() throws JsonIOException, IOException
	{
		
		System.out.println("Read from Log entered");
		//ArrayList<content> conlist = new ArrayList<content>();
		
		String tweet_contentString = null;
		String line = null;
		@SuppressWarnings("resource")
		BufferedReader bReader = new BufferedReader(new FileReader("out.txt"));
		while((line = bReader.readLine()) != null)
		{
			if(tweet_contentString == null)
			{
				tweet_contentString = line;
			}
			else
			{
			tweet_contentString += line;
			}
		}
		return tweet_contentString;
		//contentList cList = gson.fromJson(tweet_contentString, contentList.class);
		
		

		
		
	}

}
