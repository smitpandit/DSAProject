package DSC;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.sun.org.apache.bcel.internal.generic.NEW;

public class userinfo {
String username;
String ip;
int processId;
int port;
int n;
HashMap<String, List<String>> dictionary = new HashMap<String, List<String>>();
ArrayList<String> blocked = new ArrayList<String>();
int [][] event_table = new int[6][6];

public userinfo(int processId, String username, String ip, int port) {
	// TODO Auto-generated constructor stub
	this.username = username;
	this.processId = processId;
	this.ip = ip;
	this.port = port; 	
	
}

public int getProcessId() {
	return processId;
}

public void setProcessId(int processId) {
	this.processId = processId;
}

public ArrayList<String> blocked_list(String blocked_user)
{
	
	blocked.add(blocked_user);
	return blocked; 
	
}
public ArrayList<String> getBlocked() {
	return blocked;
}

public void setBlocked(ArrayList<String> blocked) {
	this.blocked = blocked;
}

public int getN() {
	return n;
}

public void setN(int n) {
	this.n = n;
}

public int[][] getEvent_table() {
	return event_table;
}

public void setEvent_table(int[][] event_table) {
	this.event_table = event_table;
}

public HashMap<String, List<String>> getDictionary() {
	return dictionary;
}

public void setDictionary(HashMap<String, List<String>> dictionary) {
	this.dictionary = dictionary;
}


}
