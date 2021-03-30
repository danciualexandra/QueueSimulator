package model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Simulator extends Thread {
	private int minArrivalTime;
	private int maxArrivalTime;
	private int minServiceTime;
	private int maxServiceTime;
	private int numberOfQueues;
	private int numberOfClients;
    public Queue [] queues;
    int timpReal;
   BufferedWriter out;
    //
    private static int timpAsteptare = 0;
    private ArrayList<Client> clients = new ArrayList<>();

	//public Random random = new Random();
	private int simulationTime;
	private int timer;//timp real actualizat
	public Simulator(int clients,int numberOfQueues,int simulation,int minArrival,int maxArrival,int minService,int maxService,BufferedWriter out) {
		numberOfClients=clients;
		this.numberOfQueues=numberOfQueues;
		simulationTime=simulation;
		minArrivalTime=minArrival;
		maxArrivalTime=maxArrival;
		minServiceTime=minService;
		maxServiceTime=maxService;
		this.out=out;
		//createQueues(numberOfQueues);
this.queues = new Queue[numberOfQueues];
		
		for(int i = 0 ; i < numberOfQueues ; i++)
			queues[i] = new Queue();

	}
	


	public void setMinArrivalTime(int minArrivalTime) {
		this.minArrivalTime = minArrivalTime;
	}

	public int getMaxArrivalTime() {
		return maxArrivalTime;
	}

	public void setMaxArrivalTime(int maxArrivalTime) {
		this.maxArrivalTime = maxArrivalTime;
	}


	public int getMinServiceTime() {
		return minServiceTime;
	}


	public void setMinServiceTime(int minServiceTime) {
		this.minServiceTime = minServiceTime;
	}


	public int getMaxServiceTime() {
		return maxServiceTime;
	}


	public void setMaxServiceTime(int maxServiceTime) {
		this.maxServiceTime = maxServiceTime;
	}


	public int getNumberOfQueues() {
		return numberOfQueues;
	}


	public void setNumberOfQueues(int numberOfQueues) {
		this.numberOfQueues = numberOfQueues;
	}


	public int getNumberOfClients() {
		return numberOfClients;
	}


	public void setNumberOfClients(int numberOfClients) {
		this.numberOfClients = numberOfClients;
	}


	public Queue[] getQueues() {
		return queues;
	}


	public void setQueues(Queue[] queues) {
		this.queues = queues;
	}


	public int getSimulationTime() {
		return simulationTime;
	}


	public void setSimulationTime(int simulationTime) {
		this.simulationTime = simulationTime;
	}


	public int getTimer() {
		return timer;
	}


	public void setTimer(int timer) {
		this.timer = timer;
	}


	public void createQueues(int number) {
		//creez obiectul de tip Queue intr-un vector de Queue
		
      this.queues = new Queue[number];
	///dimensiunea vectorului reprezinta numarul de cozi din fisierul input.txt
		for(int i = 0 ; i < number ; i++)
			queues[i] = new Queue();
		//this.queues=new Queue[]
	}
	public void showQueues() throws IOException  {
	
		
		int i = 0;
		String s = "";
		while(i < numberOfQueues)
		{
			
			System.out.println("( Queue" + (i+1) + " ) "+"\n");
			out.write("( Queue" + (i+1) + " ) "+"\n");
			
			for(Client clients : queues[i].getQueue() )
			{
				
				s=s+clients.getId() + "[ Sos:" + clients.getArrivalTime() + " Serv:" + clients.getServiceTime() +" ] ";
			System.out.println(clients.getId() + "[ Sos:" + clients.getArrivalTime() + " Serv:" + clients.getServiceTime() +" ]");
			out.write(clients.getId() + "[ Sos:" + clients.getArrivalTime() + " Serv:" + clients.getServiceTime() +" ]");
			out.newLine();
			}
			
			
			s=s+"\n";
			i++;
			
		}
		
	}
	public void createClients() {
		Random random=new Random();
		
		int arrival=0;
		int service=0;
		int i=0;
		Client customer;
		while(i<numberOfClients) {
			arrival=random.nextInt(maxArrivalTime-minArrivalTime)+minArrivalTime;
			service=random.nextInt(maxServiceTime-minServiceTime)+minServiceTime;
			customer=new Client(i,arrival,service);
			clients.add(customer);
			
			i++;
		}
		int j=0;
		while(j<numberOfQueues) {
			new Thread(queues[j]).start();
			j++;
		}
		
	}

	public int getCoadaMinima()
	{
		int nrCoada=0;
		int timeOne=0;
		int timeTwo = 0;
		
		int minim = queues[0].size();
		ArrayList<Client> min2 = queues[0].getQueue() ;
		
		for(int i=1 ; i<queues.length ; i++)
		{
			if(queues[i].size()< minim)
				{	
					minim = queues[i].size();
					min2 = queues[i].getQueue();
					nrCoada = i;
				}
			if(queues[i].size() == minim)
			{
				for(int j = 0 ; j < queues[i].getQueue().size() ; j++)
					timeOne = timeOne + queues[i].getQueue().get(j).getServiceTime();
				for(int k = 0 ; k < minim ; k++)
					timeTwo = timeTwo + min2.get(k).getServiceTime();
				
				if(timeOne < timeTwo)
					nrCoada = i;
			}
		}
		return nrCoada;
	}
	
	public void run() 
	{
		
		createClients();
		for(timpReal = 1 ; timpReal <= simulationTime ; timpReal++)
		{
		try {
				out.write("\n->Time: " + timpReal + "\n");
			} catch (IOException e2) {
				e2.printStackTrace();
			}

				for(Client client:clients)
			{
				
				if(client.getArrivalTime() == timpReal)
					///daca un client are timpul cu cel min real,il punem la cea mai mica coada
				{
										int coadaMin = getCoadaMinima();
					queues[coadaMin].addClient(client);

					System.out.println("Clientul " + client.getId()
					+ " a juns la ora " + client.getArrivalTime() 
					+ " in coada  " + (coadaMin +1 )+ " si are service time "
					+ client.getServiceTime() + " secunde" +  "\n");///
					/// 
					try {
						out.write("Clientul " + client.getId()/** Se adauga la textA fiecare sosire*/
						+ " a juns la ora " + client.getArrivalTime() 
						+ " in coada  " + (coadaMin +1 )+ " si are service time "
						+ client.getServiceTime() + " secunde" +  "\n");
						out.newLine();
					} catch (IOException e) {
						e.printStackTrace();
					}
					try {
						out.newLine();
						
					} catch (IOException e) {

						e.printStackTrace();
					}

				}
				
			}
			try {
				sleep(1000);
				//sleep 1 sec pt a putea itera 
				
				
			} catch (InterruptedException e1) {

				e1.printStackTrace();
			}
			
			try {
				sleep(1000);
				///sleep pana la urm iteratie a timpului
				//real

				showQueues();
				///afisam ce avem in cozi
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			//
 catch (IOException e) {

				e.printStackTrace();
			}	
		}
		try {
			out.close();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

}

