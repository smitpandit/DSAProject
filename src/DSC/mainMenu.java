package DSC;

import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.Type;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.PriorityQueue;
import java.util.concurrent.CopyOnWriteArrayList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
public class mainMenu {
	CopyOnWriteArrayList <serverClients> clients = new CopyOnWriteArrayList <serverClients>();
	
	ServerSocket server = null; //server socket initialization
	Socket listen = null; // socket for server to listen
	
	public static int n = 0;
	public static List<userinfo> user_list =  new ArrayList<userinfo>();
	  @SuppressWarnings("deprecation")
	public static String menu() throws IOException {
		  
	      String selection;
	      DataInputStream input = new DataInputStream(System.in);
	      
	    	System.out.println();
	        System.out.println("Choose from these choices");
	        System.out.println("-------------------------\n");
	        System.out.println("1 - Tweet");
	        System.out.println("2 - View");
	        System.out.println("3 - Block");
	        System.out.println("4 - Unblock");
	        
	        selection = input.readLine();
	        return selection;
	        
	      
	    }
	public static void main(String[] args) throws IOException {
		
		
		  mainMenu mM =new mainMenu();
		 
		int port = 0;
		
		String ip =  null;
		String sent = "no";
		String username = null;
		log syncup = new log();
		int processId = 0;
		DataInputStream input = new DataInputStream(System.in);
		File filename;
		userinfo my_info = null;
		filename=new File("input.txt");
		ParseFile pFile = new ParseFile();
		user_list = pFile.fileparse(filename);
		int start = 1;
		
		for (userinfo uinfo : user_list) {
			if (uinfo.getProcessId() == 1) 
			{
				my_info = uinfo;
				port = uinfo.port;
				ip = uinfo.ip;
				username = uinfo.username;
				processId = uinfo.processId;
				//System.out.println(uinfo.processId + " " + uinfo.username + " " + uinfo.ip + " " + uinfo.port);
				//System.out.println();
			}
		}
		mM.serverLog(my_info.port);
		
		clientThread cT = new clientThread();
		cT.start();
		
		

//		username = input.readLine();
//		userinfo my_info = new userinfo(username);
		
		ArrayList<content> con = new ArrayList<content>();
		log loglist = new log();
		logReadWrite logrw = new logReadWrite();
		logSendReceive lsr = new logSendReceive();
//		if(con.isEmpty())
//		{
//			syncup = logrw.readfromlog();
//			con = syncup.conlist;
//		}
		
while(true)
{	
	//System.out.println("Back in Main");
		
		String userchoice = menu();
		String tweet = null;
		String message = null;
		long time = 0;
		String optype = null;
		String blocked_user = null;
	    
	    
	    //content con = new content();
	    dict dict = new dict();
	    logReadWrite lrw = new logReadWrite();

	    
	    if(userchoice.equals("Tweet") || userchoice.equals("tweet") || userchoice.equals("1")) 
	    {	
	    	
	    	int [][] temptable = my_info.getEvent_table();
			   for (int i = 1; i < my_info.event_table.length; i++) {
				   temptable[my_info.processId][i]++;
			   }
			   my_info.setEvent_table(temptable);
			   
	    	optype = "tweet";
	    	System.out.println("Enter the Tweet: ");
	    	tweet = input.readLine();
	    	
	    	//convert the log into json here and send using send message
	    	//check for blocked before sending
	    	//sC.clientName for checking the client name
	    	
	    	time = System.currentTimeMillis();
	    	content newcon = new content(username,tweet,optype,time,sent);
	    	con.add(newcon);
	    	log log = new log(my_info.processId, con, my_info.getEvent_table());
	    	lrw.writetolog(log);
	    	if(mM.clients.size()>0)
        	{
				serverClients sC = new serverClients();
				for(int index=0; index<mM.clients.size(); ++index)
				{
					sC = mM.clients.get(index);
					if(my_info.blocked.contains(sC.clientName))
					{
						continue;
					}
					else
					{
						message = lrw.readfromlog();
						sC.sendMessage(message);
					}
					
					//compare sC.clientName with blocked list before
					//tweet is string
					
				}
        }
	    	//lsr.receive_compare();
	    	
	   } 
	    else if (userchoice.equals("View") || userchoice.equals("view") || userchoice.equals("2")) 
	    {
	       Gson gson = new Gson();
	       Type conType = new TypeToken<log>(){}.getType();
		   message = lrw.readfromlog();
		   JsonReader reader = new JsonReader(new StringReader(message));
		   reader.setLenient(true);	
			
			log log = gson.fromJson(reader, conType);
		   
		   if(log != null)
			{
			//System.out.println("conlist isn't null");	
			for(content content : log.conlist)
			{
				if(content.optype.equals("tweet")){
					Calendar c = Calendar.getInstance();
					c.setTimeInMillis(content.time);
					String timetweet = c.get(Calendar.HOUR_OF_DAY)+":"+c.get(Calendar.MINUTE)+":"+c.get(Calendar.SECOND);
			System.out.println(content.username + " : " + content.message + " : "+ timetweet);
			
			}
			}
			}
		  
	   }
	    else if (userchoice.equals("Block") || userchoice.equals("block") || userchoice.equals("3")) {
		  
	       optype = "block";
		   System.out.println("Enter username you want to block: ");
		   blocked_user = input.readLine();
		   tweet = blocked_user;
		   //tweet += " is blocked";
		   //con.setMessage(blocked_user + "is blocked");
		   time = System.currentTimeMillis();
		   content newcon = new content(username,tweet,optype,time,sent);
		  
		   
		   if(my_info.getBlocked().contains(blocked_user))
		   {
			   System.out.println(blocked_user + " is already blocked");
		   }
		   else {
			   int [][] temptable = my_info.getEvent_table();
			   for (int i = 1; i < my_info.event_table.length; i++) {
				   temptable[my_info.processId][i]++;
			   }
			   my_info.setEvent_table(temptable);
			   
			   System.out.println();
			   con.add(newcon);
			   log log = new log(my_info.processId, con, my_info.getEvent_table());
			   lrw.writetolog(log);
			   my_info.blocked_list(blocked_user);
			   dict.populate_dict(my_info);
		}
		   
	   } else if(userchoice.equals("Unblock") || userchoice.equals("unblock") || userchoice.equals("4")){
	       
		   optype = "unblock";
	       System.out.println("Enter username you want to unblock: ");
		   blocked_user = input.readLine();
		   tweet = blocked_user;
		   //tweet += " is unblocked";
		   time = System.currentTimeMillis();
		   content newcon = new content(username,tweet,optype,time,sent);
		   
//		   con.setMessage(blocked_user + "is unblocked");
		   
		   if(!my_info.getBlocked().contains(blocked_user))
		   {
			   System.out.println(blocked_user + " is already unblocked");
		   }
		   else
		   {
			   int [][] temptable = my_info.getEvent_table();
			   for (int i = 1; i < my_info.event_table.length; i++) {
				   temptable[my_info.processId][i]++;
			   }
			   my_info.setEvent_table(temptable);
			   
			   
			   con.add(newcon);
			   log log = new log(my_info.processId, con, my_info.getEvent_table());
			   lrw.writetolog(log);
			   dict.unpopulate_dict(my_info, blocked_user);
		   }
	   }
	   else{
		   System.out.println("Incorrect input try again");
		   continue;
	}
}

}
	public void serverLog(int port)
	{
				try
				{
					server = new ServerSocket(port);
				}
				catch(IOException e)
				{
					System.out.println(e);
				}
	
		Thread t = new Thread() 
		{
			public void run()
			{
					try
					{
						System.out.println("Server Started");
						while(true)
						{
						listen = server.accept();
						InetAddress add = listen.getInetAddress();
						String name = add.toString();
						String clientName = null;
						for(userinfo uinfo : user_list)
						{
							if(uinfo.ip.equals(name))
							{
								clientName = uinfo.username;
								break;
							}
						}
						serverClients sC = new serverClients(listen, this, clientName);
						sC.start();
						clients.add(sC);
						}				
					}
					catch(IOException e)
					{
						System.out.println(e);
					}
						
			}	
		};
		t.start();
	}	

}
