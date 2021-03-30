package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class PdfWriter {

	
	
	
	public static void main(String[]args) throws IOException {
		BufferedWriter out = new BufferedWriter(new FileWriter("out-test-1"));
	
		String line1=Files.readAllLines(Paths.get("in-test-1.txt")).get(0);//nr clienti
		String line2=Files.readAllLines(Paths.get("in-test-1.txt")).get(1);//nr cozi
		String line3=Files.readAllLines(Paths.get("in-test-1.txt")).get(2);//simulare
		String line4=Files.readAllLines(Paths.get("in-test-1.txt")).get(3);//arrival
		String line5=Files.readAllLines(Paths.get("in-test-1.txt")).get(4);//service
		String[] words4 = line4.split("\\W+");
		String[] words5 = line5.split("\\W+");
		Integer clients=Integer.parseInt(line1);
		Integer queues=Integer.parseInt(line2);
		Integer time=Integer.parseInt(line3);
		Integer minArrival=Integer.parseInt(words4[0]);
		Integer maxArrival=Integer.parseInt(words4[1]);
		Integer minService=Integer.parseInt(words5[0]);
		Integer maxService=Integer.parseInt(words5[1]);
		
		Simulator ctrl  = new Simulator(clients,queues,time,minArrival,maxArrival,minService,maxService,out);
		ctrl.start();
		
		
		
		///out1
	
		BufferedWriter out1 = new BufferedWriter(new FileWriter("out-test-2"));
		
		String l1=Files.readAllLines(Paths.get("in-test-2.txt")).get(0);//nr clienti
		String l2=Files.readAllLines(Paths.get("in-test-2.txt")).get(1);//nr cozi
		String l3=Files.readAllLines(Paths.get("in-test-2.txt")).get(2);//simulare
		String l4=Files.readAllLines(Paths.get("in-test-2.txt")).get(3);//arrival
		String l5=Files.readAllLines(Paths.get("in-test-2.txt")).get(4);//service
		String[] wordsLine4 = l4.split("\\W+");
		String[] wordsLine5 = l5.split("\\W+");
		Integer c=Integer.parseInt(l1);
		Integer q=Integer.parseInt(l2);
		Integer t=Integer.parseInt(l3);
		Integer minA=Integer.parseInt(wordsLine4[0]);
		Integer maxA=Integer.parseInt(wordsLine4[1]);
		Integer minS=Integer.parseInt(wordsLine5[0]);
		Integer maxS=Integer.parseInt(wordsLine5[1]);
		
		Simulator simulator2 = new Simulator(c,q,t,minA,maxA,minS,maxS,out1);
		
		simulator2.start();
		///out2
		
BufferedWriter out2 = new BufferedWriter(new FileWriter("out-test-3"));
		
		String string1=Files.readAllLines(Paths.get("in-test-3.txt")).get(0);//nr clienti
		String string2=Files.readAllLines(Paths.get("in-test-3.txt")).get(1);//nr cozi
		String string3=Files.readAllLines(Paths.get("in-test-3.txt")).get(2);//simulare
		String string4=Files.readAllLines(Paths.get("in-test-3.txt")).get(3);//arrival
		String string5=Files.readAllLines(Paths.get("in-test-3.txt")).get(4);//service
		String[] wLine4 = string4.split("\\W+");
		String[] wLine5 = string5.split("\\W+");
		Integer value1=Integer.parseInt(string1);
		Integer value2=Integer.parseInt(string2);
		Integer value3=Integer.parseInt(string3);
		Integer value4=Integer.parseInt(wLine4[0]);
		Integer value5=Integer.parseInt(wLine4[1]);
		Integer value6=Integer.parseInt(wLine5[0]);
		Integer value7=Integer.parseInt(wLine5[1]);
		
		Simulator simulator3 = new Simulator(value1,value2,value3,value4,value5,value6,value7,out2);
		
		simulator3.start();
		
		
	}

}
