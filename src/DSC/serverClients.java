package DSC;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

public class serverClients extends Thread{
	
	Thread server;
	Socket listen = null;
	DataInputStream inp;
	PrintStream out;
	public String clientName;
	public serverClients(Socket socket, Thread thread, String name)
	{
		listen = socket;
		this.server = thread;
		clientName = name;
	}
	public serverClients()
	{
		
	}
	public void sendMessage(String text)
	{
		/*This is just the send of the message. Will be sending it 
		 * through this initially to test the working of echoserver.
		 */
		try
		{
			out.println(text);
			out.flush();
		}
		catch(NullPointerException e)
		{
			System.out.println(e);
		}
		
	}	
	public void run()
	{
		try
		{
				inp = new DataInputStream(listen.getInputStream());
				out = new PrintStream(listen.getOutputStream());
		//		sendMessage("Hello, I am AKshay");
		}
			
		catch(IOException e)
		{
			System.out.println(e);
		}
		
	}
}
