
public class Message {
	Process sender;
	Process receiver;
	String msg_type;
	int node_count=0;
	int hop_count=0;
	
	public Message(Process sender, Process receiver, String msg_type) {
		this.sender = sender;
		this.receiver = receiver;
		this.msg_type = msg_type;
		//this.hop_count = hop_count;
		//this.node_count = node_count;
		Logger.Level.log(Logger.Level.TRACE,"Sender:"+sender.uid+"|Receiver:"+receiver.uid+"|Type:"+msg_type+"|node_count:"+node_count);
	}

	public Message(Process sender, Process receiver, String msg_type, int node_count) {
		this.sender = sender;
		this.receiver = receiver;
		this.msg_type = msg_type;
		this.node_count = node_count;
		Logger.Level.log(Logger.Level.TRACE,"Sender:"+sender.uid+"|Receiver:"+receiver.uid+"|Type:"+msg_type+"|node_count:"+node_count);
	}
	/*
	public Message(Process sender, Process receiver, String msg_type, int hop_count) {
		this.sender = sender;
		this.receiver = receiver;
		this.msg_type = msg_type;
		this.hop_count = hop_count;
		System.out.println("Sender:"+sender.uid+"|Receiver:"+receiver.uid+"|Type:"+msg_type+"|hop_count:"+hop_count);
	}
	*/
}
