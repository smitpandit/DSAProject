package DSC;

import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
//import com.sun.org.apache.bcel.internal.generic.NEW;

public class logSendReceive {
	public void add(log incoming_log, log my_log, userinfo my_info) throws JsonIOException, IOException
	{	int incoming_process = 0;
		incoming_process = incoming_log.processId;
		System.out.println(incoming_log.processId);
		String in_process_name = null;
		List<userinfo> my_list =  new ArrayList<userinfo>();
		my_list = mainMenu.user_list;
		logReadWrite lrw = new logReadWrite();
		for(userinfo uinfo: my_list)
		{
			if (uinfo.getProcessId() == incoming_process) 
			{
				in_process_name = uinfo.username;
				
				//System.out.println("Incoming "+ uinfo.processId + " " + in_process_name );
				//System.out.println();
			}
		}
		
		//List<String> itemsList = my_info.dictionary.get(in_process_name);
		
		
		for(content content : incoming_log.conlist)
		{
			my_log.conlist.add(content);
			if(content.optype.equals("block"))
			{
				if (my_info.dictionary.get(in_process_name) == null) {
				    my_info.dictionary.put(in_process_name, new ArrayList<String>());
				}
				my_info.dictionary.get(in_process_name).add(content.message);
			}
			else if(content.optype.equals("unblock"))
			{
				if(my_info.dictionary.get(in_process_name).contains(content.message))
				{
					my_info.dictionary.get(in_process_name).remove(content.message);
				}
			}
		}
		lrw.writetolog(my_log);
		
		
		
	}
	

	public void receive_compare(String inputStream) throws IOException{
		System.out.println("Entered receive_compare");
		List<userinfo> my_u_list= new ArrayList<userinfo>();
		my_u_list = mainMenu.user_list;
		userinfo my_info = null;
		for (userinfo uinfo : my_u_list) {
			if (uinfo.getProcessId() == 1) 
			{
				my_info = uinfo;
				
				System.out.println(uinfo.processId + " " + uinfo.username + " " + uinfo.ip + " " + uinfo.port);
				System.out.println();
			}
		}
		
		//System.out.println(inputStream);
		logReadWrite lrw = new logReadWrite();	
		System.out.println("Read from Log entered");
		String my_logString= lrw.readfromlog();
		Type conType = new TypeToken<log>(){}.getType();
		Type conType2 = new TypeToken<log>(){}.getType();
		Gson gson = new Gson();
		Gson gson2 = new Gson();
		JsonReader reader = new JsonReader(new StringReader(inputStream));
		reader.setLenient(true);
		JsonReader my_reader = new JsonReader(new StringReader(my_logString));
		my_reader.setLenient(true);
		
//
		

		
		
//		
		log incoming_log = gson.fromJson(reader, conType);	
		log my_log = gson2.fromJson(my_reader, conType2);
		
		if(my_log == null)
		{
			my_log = incoming_log;
		}
//		else if(my_log.eventTable[my_info.processId][incoming_log.processId] > incoming_log.eventTable[incoming_log.processId][my_info.processId])
//		{	
//			send(my_log, my_info);	
//		}
		else if(my_log.eventTable[my_info.processId][incoming_log.processId] < incoming_log.eventTable[incoming_log.processId][my_info.processId])
		{
			my_log.eventTable[my_info.processId][incoming_log.processId] = incoming_log.eventTable[incoming_log.processId][my_info.processId];
			add(incoming_log, my_log,my_info);
		}
		else {
			return;
		}
	}
	
}
