
import java.net.*;
import java.util.*;
import java.io.*;
import java.util.concurrent.*;

public class  Server
{
	public static final int DEFAULT_PORT = 15001;

    // construct a thread pool for concurrency	
	private static final Executor exec = Executors.newCachedThreadPool();
	
	public static void main(String[] args) throws IOException {
		ServerSocket sock = null;
		Vector<String> vect = new Vector<String>();
		ArrayList<BufferedWriter> writeList = new ArrayList<BufferedWriter>();
		Runnable broadcast = new BroadcastThread(vect,writeList);
		exec.execute(broadcast);
		try {
			// establish the socket
			sock = new ServerSocket(DEFAULT_PORT);
			
			while (true) {
				Socket client = sock.accept();
				BufferedWriter toClient = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
				writeList.add(toClient);
				Runnable task = new Connection(client, vect, writeList);
				exec.execute(task);
			}
		}
		catch (IOException ioe) { System.err.println(ioe); }
		finally {
			if (sock != null) {
				sock.close();
			}
		}
	}
}
