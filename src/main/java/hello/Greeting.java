package hello;

public class Greeting {

    protected String receiver;
	protected String message;
	protected String sender;
	
    public Greeting() {
		
	}
	
	public Greeting(String receiver, String message, String sender) {
        this.receiver = receiver;
        this.message = message;
		this.sender = sender;
    }

	public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
	
	public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
}