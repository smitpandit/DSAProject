package DSC;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class clientConnection extends Thread {

	Socket client;
	BufferedReader inp=null;
	String thread_name = null; 	
	public clientConnection (Socket socket, String name)
	{ 
		client = socket;
		thread_name = name;
	}
	
	public void run()
	{
		
		try
		{
			System.out.println("Client Thread Created");
			while(true)
			{
				try
				{
					BufferedReader inp = new BufferedReader(new InputStreamReader(client.getInputStream()));
					/*write to log */
					String message=null;
					if( inp.readLine() != null)
					{
						//code for updating log locally here
						System.out.println("User entered text");
						message = inp.readLine();
						System.out.println(message);
						logSendReceive lsr = new logSendReceive();
						lsr.receive_compare(message);
					}
				}
				catch(IOException e)
				{
					
				}
				
			}
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}
