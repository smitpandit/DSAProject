package DSC;

import java.util.ArrayList;

public class log {
	ArrayList<content> conlist;
	int[][] eventTable;
	int processId = 0;
 		
	public log(){
		
	}
	public log(int processId, ArrayList<content> conlist, int[][] eventTable){
		super();
		this.processId = processId;
		this.conlist = conlist;
		this.eventTable = eventTable;
	}
}
