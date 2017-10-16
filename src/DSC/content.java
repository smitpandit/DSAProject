package DSC;

public class content {
	String message = null;
	String username = null;
	long time = 0;
	String optype = null;
	String sent;
	public content() {
		
	}
	public content(String username, String message ,String optype, long time, String sent) {
	super();
	this.username = username;
	this.message = message;
	this.time = time;
	this.optype = optype;
	this.sent = sent;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	public String getOptype() {
		return optype;
	}
	public void setOptype(String optype) {
		this.optype = optype;
	}
	public String getSent() {
		return sent;
	}
	public void setSent(String sent) {
		this.sent = sent;
	}
	
}
