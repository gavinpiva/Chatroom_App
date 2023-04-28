/**
 * Handler class containing the logic for echoing results back
 * to the client. 
 *
 * This conforms to RFC 862 for echo servers.
 *
 * @author Greg Gagne 
 */

import java.io.*;
import java.util.*;
import java.net.*;

public class Handler
{
	public static final int BUFFER_SIZE = 256;
	
	/**
	 * this method is invoked by a separate thread
	 */
	public void process(Socket client, Vector<String> vect, ArrayList<BufferedWriter> writeList) throws java.io.IOException {
		BufferedReader fromClient = null;
	    
		try {
			
			fromClient = new BufferedReader (new InputStreamReader(client.getInputStream()));
			String line = null;
			
			while ( (line = fromClient.readLine()) != null) {
				if(line.contains("LEAVE")) {
					writeList.remove(client);
				}
				vect.add(line);
			}
		
   		}
		catch (IOException ioe) {
			System.err.println(ioe);
		}
		finally {
			// close streams and socket
			if (fromClient != null)
				fromClient.close();
		}
	}

}
