package model;

import java.util.ArrayList;

public class Queue extends Thread{
	private ArrayList<Client> queue;
public Queue() {
	queue=new ArrayList<Client>();
}
	public ArrayList<Client> getQueue() {
		return queue;
	}

	public void setQueue(ArrayList<Client> queue) {
		this.queue = queue;
	}
	public void addClient(Client client) {
		this.queue.add(client);
	}
	public void removeClient(Client client) {
		this.queue.remove(client);
	}
  public int isEmpty() {
	  if(this.queue.size()==0) {
		  return 1;
	  }
	  else return 0;
  }
	public int size() {
		return queue.size();
	}
	
	

}
