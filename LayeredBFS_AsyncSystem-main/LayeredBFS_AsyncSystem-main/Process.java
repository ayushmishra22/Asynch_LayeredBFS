import java.util.*;
import java.util.concurrent.*;
import java.util.LinkedList;
public class Process extends Thread {
	public int uid;
	public Queue<Message> outbox = new LinkedList<>();
	public volatile int ready_round;
	//public Queue<Message> inbox = new LinkedList<>();
	public ConcurrentLinkedQueue<Message> inbox = new ConcurrentLinkedQueue<Message>();
	public ArrayList<Process> neighbors;
	public int newNodes;
	public int level;
	public Process parent;
	public ArrayList<Process> children = new ArrayList<Process>();
	public List<Integer> convergecast = new ArrayList<Integer>();
	//public int msg_sent_per_level = 0;
	public boolean frontier = false;
	public boolean explored = false;
	public boolean terminate = false;
	
	public Process(int uid) {
		this.uid = uid;	
		this.newNodes = 0;
		this.level = 0;
		this.parent = null;
	}
	
	
	public void run() {
		System.out.println("Started thread: "+uid);
		synchronized (this){
		for(int i=0; i<this.neighbors.size(); i++) {
			System.out.print(this.uid+"///"+this.neighbors.get(i).uid+"-----");
		}
		}
		ready_round = 1;
		try {
			while(true) {
				if(Thread.interrupted()) {
					throw new InterruptedException();
				}
				//System.out.println("Wait till clock = "+ready_round);
				//System.out.println("Generated random number = "+Master.generateRandom());
				//System.out.println("clock="+Master.clock);
				while(true) {
					if(Thread.interrupted()) {
						throw new InterruptedException();
					}
					if (ready_round == Master.clock){
						break;
					}
				}
				// Code to be done before finishing current round to be added here.
				// LayeredBFS algorithm
				
				//send messages - root
				if(this.uid == Master.root && this.level == 0 && this.ready_round == 1) { // Round 1 - Root
					this.explored = true;
					this.frontier = true;
					for(int i=0; i<this.neighbors.size(); i++) {
						if(neighbors.get(i).uid == this.uid) {
							continue;
						}
						
						Message msg = new Message(this, neighbors.get(i), "explore");
						int t = Master.generateRandom();
						Thread.sleep(t);
						neighbors.get(i).inbox.add(msg);
					}
				}
				/*
				//check Inbox
				while(inbox.size() != 0) {
					Message r_msg = inbox.remove();
					if(r_msg.msg_type == "explore") {
						if(parent == null) {
							this.parent = r_msg.sender;
							Message send_ack = new Message(this, this.parent, "ack");
							parent.inbox.add(send_ack);
						}
						else if(r_msg.sender == this.parent) {
								if(this.children.size() != 0) {
									for(int k =0; k<this.children.size();k++) {
										Message send_explore = new Message(this, this.children.get(k), "explore");
										this.children.get(k).inbox.add(send_explore);
										this.msg_sent_per_level += 1;
									}
									this.convergecast = new ArrayList<Integer>();
								}
								else {
									for(int j=0; j<this.neighbors.size();j++) {
										if(neighbors.get(j) == this.parent) {
											continue;
										}
										Message send_explore = new Message(this, this.neighbors.get(j), "explore");
										this.neighbors.get(j).inbox.add(send_explore);
										this.msg_sent_per_level += 1;
									}
									this.convergecast = new ArrayList<Integer>();
									}
								}
						else if(this.neighbors.size() == 1 && this.neighbors.get(0) == this.parent){
							Message send_IamDone = new Message(this, this.parent, "IamDone", this.newNodes);
							parent.inbox.add(send_IamDone);
							this.newNodes = 0;
							this.msg_sent_per_level = 0;
							this.convergecast = new ArrayList<Integer>();
						}
						else {
							Message send_reject = new Message(this, r_msg.sender, "reject");
							r_msg.sender.inbox.add(send_reject);
						}
					}
					if(r_msg.msg_type == "reject") {
						this.convergecast.add(0);
					}
					
					if(r_msg.msg_type == "ack") {
						this.children.add(r_msg.sender);
						this.convergecast.add(1);
						this.newNodes += 1;
						
					}
					
					if(r_msg.msg_type == "IamDone") {
						this.newNodes += r_msg.node_count;
						this.convergecast.add(r_msg.node_count);
					}
					
				}
				

				
				//check convergecast
				
				if(this.msg_sent_per_level == this.convergecast.size() && this.msg_sent_per_level != 0) {
					if(this.parent != null) {
					Message send_IamDone = new Message(this, this.parent, "IamDone", this.newNodes);
					parent.inbox.add(send_IamDone);
					this.newNodes = 0;
					this.msg_sent_per_level = 0;
					this.convergecast = new ArrayList<Integer>();
					}
					else {
						//At root
						if(this.newNodes == 0) {
							//No new nodes were added. Layered BFS completed. Instruct all processes to terminate.
							System.out.println("TERRRRRMMMMMIIIIINNNNNAAAAATTTTEEEEEEEE!");
						} 
						else {
							System.out.println("Going to the next level");
							this.level += 1;
							this.msg_sent_per_level = 0;
							this.convergecast = new ArrayList<Integer>();
							for(int j=0; j<this.children.size(); j++) {
								Message send_explore = new Message(this, this.children.get(j), "explore");
								this.children.get(j).inbox.add(send_explore);
								this.msg_sent_per_level += 1;
							}
						}
					}
				}
				*/
				//check Inbox
				while(inbox.size() != 0) {
					Message r_msg = inbox.remove();
					if(r_msg.msg_type == "explore") {
						if(this.uid == 4 && this.explored == true && this.neighbors.size() == 1) {
							int l = 0;
						}
						if(this.explored == false) {
							this.explored = true;
							this.frontier = true;
							this.parent = r_msg.sender;
							Message send_ack = new Message(this, this.parent, "ack");
							int t = Master.generateRandom();
							Thread.sleep(t);
							parent.inbox.add(send_ack);
						}
						else if(this.explored == true && this.neighbors.size() == 1 && this.neighbors.get(0) == this.parent){
							Message send_IamDone = new Message(this, this.parent, "IamDone", this.newNodes);
							int t = Master.generateRandom();
							Thread.sleep(t);
							parent.inbox.add(send_IamDone);
							this.newNodes = 0;
							this.frontier = false;
							//this.msg_sent_per_level = 0;
							this.convergecast = new ArrayList<Integer>();
						}
						else if(r_msg.sender == this.parent && this.frontier == false && this.explored==true) {
								for(int k =0; k<this.children.size();k++) {
									Message send_explore = new Message(this, this.children.get(k), "explore");
									int t = Master.generateRandom();
									Thread.sleep(t);
									this.children.get(k).inbox.add(send_explore);
									//this.msg_sent_per_level += 1;
								}
								this.convergecast = new ArrayList<Integer>();
						}
						else if(r_msg.sender == this.parent && this.frontier == true && this.explored==true) {
									for(int j=0; j<this.neighbors.size();j++) {
										if(neighbors.get(j) == this.parent) {
											continue;
										}
										Message send_explore = new Message(this, this.neighbors.get(j), "explore");
										int t = Master.generateRandom();
										Thread.sleep(t);
										this.neighbors.get(j).inbox.add(send_explore);
										//this.msg_sent_per_level += 1;
									}
									this.convergecast = new ArrayList<Integer>();
						}
						
						else {
							Message send_reject = new Message(this, r_msg.sender, "reject");
							int t = Master.generateRandom();
							Thread.sleep(t);
							r_msg.sender.inbox.add(send_reject);
						}
					}
					
					
					if(r_msg.msg_type == "reject") {
						this.convergecast.add(0);
						if(this.uid == 1) {
							int g=0;
							int h = 1;
						}
					}
					
					if(r_msg.msg_type == "ack") {
						this.children.add(r_msg.sender);
						this.convergecast.add(1);
						this.newNodes += 1;
					}
					
					if(r_msg.msg_type == "IamDone") {
						this.newNodes += r_msg.node_count;
						this.convergecast.add(r_msg.node_count);
					}
					
					if(r_msg.msg_type == "terminate") {
						int[] print_children = new int[this.children.size()];
						for(int p=0; p<this.children.size(); p++) {
							//System.out.print(this.children.get(p).uid+" ");
							print_children[p] = this.children.get(p).uid;
						}
						Master.BFStree[this.uid-1] = print_children;
						//System.out.println();
						for(int h=0; h<this.children.size(); h++) {
							Message send_terminate = new Message(this, this.children.get(h), "terminate");
							int t = Master.generateRandom();
							Thread.sleep(t);
							this.children.get(h).inbox.add(send_terminate);
						}
						this.terminate = true;
						//throw new InterruptedException();
					}
					
				}
				

				
				//check convergecast
				if(this.explored == true && this.frontier == true) {
					if(this.convergecast.size() == this.neighbors.size()-1 && this.parent != null && this.convergecast.size() != 0) {
						Message send_IamDone = new Message(this, this.parent, "IamDone", this.newNodes);
						int t = Master.generateRandom();
						Thread.sleep(t);
						parent.inbox.add(send_IamDone);
						this.frontier = false;
						this.newNodes = 0;
						this.convergecast = new ArrayList<Integer>();
					}
				}
				
				else if(this.explored == true && this.frontier == false) {
					if(this.convergecast.size() == this.children.size() && this.parent != null && this.children.size() != 0) {
						Message send_IamDone = new Message(this, this.parent, "IamDone", this.newNodes);
						int t = Master.generateRandom();
						Thread.sleep(t);
						parent.inbox.add(send_IamDone);
						this.newNodes = 0;
						this.convergecast = new ArrayList<Integer>();
					}
				}
				
				else {
					
				}
					
				if(this.convergecast.size() == this.neighbors.size() && this.uid == Master.root) {
						this.frontier = false;
						if(this.newNodes == 0) {
								if(!this.terminate) {
								//No new nodes were added. Layered BFS completed. Instruct all processes to terminate.
								System.out.println("TERRRRRMMMMMIIIIINNNNNAAAAATTTTEEEEEEEE!");
								int[] print_children = new int[this.children.size()];
								for(int m=0; m< this.children.size(); m++) {
									print_children[m] = this.children.get(m).uid; 
									Message send_terminate = new Message(this, this.children.get(m), "terminate");
									int t = Master.generateRandom();
									Thread.sleep(t);
									this.children.get(m).inbox.add(send_terminate);
									}
								Master.BFStree[this.uid-1] = print_children;
								this.terminate = true;
							}
							//Master.terminate = true;
							//throw new InterruptedException();
						}
						else {
							System.out.println("Going to the next level");
							this.level += 1;
							//this.msg_sent_per_level = 0;
							this.newNodes = 0;
							this.convergecast = new ArrayList<Integer>();
							for(int j=0; j<this.children.size(); j++) {
								Message send_explore = new Message(this, this.children.get(j), "explore", this.newNodes);
								int t = Master.generateRandom();
								Thread.sleep(t);
								this.children.get(j).inbox.add(send_explore);
								//this.msg_sent_per_level += 1;
							}
						}
					
				}

				
				
				
				
				ready_round += 1;
			}
		}
		catch(InterruptedException e) {
		System.out.println("Interrupted. Exiting: "+this.uid);
		}
	}
	
}
