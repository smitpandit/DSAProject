package DSC;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
 
public class clientThread extends Thread{

	Socket alpha = null;
	Socket beta = null;
	Socket gamma = null;
	String name_one = null;
	String name_two = null;
	String name_three= null;
	String add_one="192.168.0.6";
	String add_two="192.168.0.6";
	String add_three="192.168.0.6";
	int port_one=7000;
	int port_two=7000;
	int port_three=7000;
	int flag_alpha=0,flag_beta=0,flag_gamma=0;
	/*read from file the IP and port of the other clients */
	
	
	
		
	
	
	ArrayList <clientConnection> cC = new ArrayList<clientConnection>();
	public void run()
	{
		/**initialize sockets and ips from file*******/
		List<userinfo> my_list = mainMenu.user_list;
		for (userinfo uinfo : my_list) {
			if (uinfo.getProcessId() == 2) 
			{
				
				port_one = uinfo.port;
				add_one= uinfo.ip;
				name_one = uinfo.username;
				
				//System.out.println(uinfo.processId + " " + uinfo.username + " " + uinfo.ip + " " + uinfo.port);
				//System.out.println();
			}
			if (uinfo.getProcessId() == 3) 
			{
				
				port_two = uinfo.port;
				add_two= uinfo.ip;
				name_two = uinfo.username;
				
				//System.out.println(uinfo.processId + " " + uinfo.username + " " + uinfo.ip + " " + uinfo.port);
				//System.out.println();
			}
			if (uinfo.getProcessId() == 3) 
			{
				
				port_two = uinfo.port;
				add_two= uinfo.ip;
				name_two = uinfo.username;
				
				//System.out.println(uinfo.processId + " " + uinfo.username + " " + uinfo.ip + " " + uinfo.port);
				//System.out.println();
			}
			if (uinfo.getProcessId() == 4) 
			{
				
				port_three = uinfo.port;
				add_three= uinfo.ip;
				name_three = uinfo.username;
				
				//System.out.println(uinfo.processId + " " + uinfo.username + " " + uinfo.ip + " " + uinfo.port);
				//System.out.println();
			}
		}
		while(true)
		{
			
		
			if(alpha==null && flag_alpha==0)
			{
				try
				{
				alpha = new Socket(add_one, port_one);
				clientConnection c_alpha = new clientConnection(alpha, name_one);
				c_alpha.start();
				cC.add(c_alpha);
				flag_alpha=1;
				}
				catch(IOException e)
				{
				//	System.out.println("Server not connected at add " + add_one + " port " + port_one);
				}
			}
			if(beta==null && flag_beta==0)
			{
				try
				{
				beta = new Socket(add_two, port_two);
				clientConnection c_beta = new clientConnection(beta, name_two);
				c_beta.start();
				cC.add(c_beta);
				flag_beta=1;
				}
				catch(IOException e)
				{
				//	System.out.println("Server not connected at add " + add_two + " port " + port_two);
				}
			}
			if(gamma==null && flag_gamma==0)
			{
				try
				{
					gamma = new Socket(add_three, port_three);
					clientConnection c_gamma = new clientConnection(gamma, name_three);
					c_gamma.start();
					cC.add(c_gamma);
					flag_gamma=1;
				}
				catch(IOException e)
					{
		//				System.out.println("Server not connected at add " + add_three + " port " + port_three);
					}
			}
		
			if(alpha==null)
				flag_alpha=0;
			if(gamma==null)
				flag_gamma=0;
			if(beta==null)
				flag_beta=0;
		
		}
	}
	
	
}
