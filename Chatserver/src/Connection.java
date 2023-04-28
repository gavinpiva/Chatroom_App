/**
 * This is the separate thread that services each
 * incoming echo client request.
 *
 * @author Greg Gagne 
 */

import java.net.*;
import java.util.*;
import java.io.*;

public class Connection implements Runnable
{
	private Socket	client;
	private static Handler handler = new Handler();
	private Vector<String> vect;
	private ArrayList<BufferedWriter> writeList;
	
	public Connection(Socket client, Vector<String> vect, ArrayList<BufferedWriter> writeList){
		this.client = client;
		this.vect = vect;
		this.writeList = writeList;
		
	}

    /**
     * This method runs in a separate thread.
     */	
	public void run() { 
		try {
			handler.process(client, vect, writeList);
		}
		catch (java.io.IOException ioe) {
			System.err.println(ioe);
		}
	}
}

