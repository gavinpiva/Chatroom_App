import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

public class BroadcastThread implements Runnable
{
	private Vector<String> messageQueue;
	private ArrayList<BufferedWriter> writeList;
	public BroadcastThread(Vector<String> messageQueue, ArrayList<BufferedWriter> writeList){
		this.messageQueue = messageQueue;
		this.writeList = writeList;
		
		
	}
    public void run() {
        while (true) {
            // sleep for 1/10th of a second
            try { Thread.sleep(100); } catch (InterruptedException ignore) { }

            /**
             * check if there are any messages in the Vector. If so, remove them
             * and broadcast the messages to the chatroom
             */
//            System.out.println(messageQueue);
            while(messageQueue.isEmpty() == false) {
            	String message = messageQueue.get(0);
            	messageQueue.remove(0);
            	

            	for (BufferedWriter client : writeList) {
            		try {
            			client.write(message + "\r\n");
            			client.flush();
            		
            			
            		}catch (IOException e) {
            			e.printStackTrace();
            			
            		}
            		
            	}
            }
        }
    }
} 