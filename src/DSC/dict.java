package DSC;

import java.util.List;
import java.util.Map.Entry;

public class dict {
	//static HashSet<String> blocked = new HashSet<String>();
	// HashMap<String, List<String>> dictionary = new HashMap<String, List<String>>();
	//static List<String> blocked = new ArrayList<String>();
	
	public void populate_dict(userinfo uinfo) {
	
		uinfo.dictionary.put(uinfo.username, uinfo.getBlocked());
		System.out.println("List of people blocked by " + uinfo.username + " are as follows:");
		for (int i = 0; i < uinfo.getBlocked().size(); i++) {
			System.out.println(uinfo.getBlocked().get(i));

		}
		
	
}
	public void unpopulate_dict(userinfo uinfo, String unblock_user)
	{
		for(Entry<String, List<String>> entry: uinfo.dictionary.entrySet()) {
		    
		    if(entry.getKey().equals(uinfo.username))
		    {
		    	entry.getValue().remove(unblock_user);
		    }
		    else
		    {
		    	System.out.println(uinfo.username +" isn't being followed");
		    	
		    }
		}
		
			
				
	}
	
}
