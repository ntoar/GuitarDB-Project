package com.guitar.database;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DBConnectionManager {
	
	private int portNumber;
	private boolean listening;
	private ExecutorService dbConnectionPool;
	
	public DBConnectionManager(int portNumber) {
		this.portNumber=portNumber;
		this.dbConnectionPool=Executors.newFixedThreadPool(10);
		this.listening=false;		
	}
	
	public int getPort() {
		return this.portNumber;
	}
	
	public boolean getListening() {
		return this.listening;
	}
	
	public void run(documentStorage storage) {
		
		try (ServerSocket connManager = new ServerSocket(this.portNumber)){
			
			//print to log file
			this.listening=true;
			
			while(this.listening) {
				// run new thread using thread pools
				this.dbConnectionPool.execute(new DBConnectionThread(connManager.accept(),storage));
				
			}
			
		}catch(IOException e) {
			System.err.println("Could not start listening on port "+this.portNumber);
			System.exit(-1);
		}finally {
			this.listening=false;
		}
		
	}

}
