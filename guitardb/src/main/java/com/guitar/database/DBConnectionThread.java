package com.guitar.database;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import com.google.gson.Gson;

public class DBConnectionThread implements Runnable{
	private Socket dbThreadSocket;
	private documentStorage storage;
	
	public DBConnectionThread(Socket dbThreadSocket,documentStorage storage) {
		//new dbQueryParser();
		this.dbThreadSocket=dbThreadSocket;
		this.storage = storage;
	}
	
	
	public void run() {
		try {
			//PrintWriter out = new PrintWriter(this.dbThreadSocket.getOutputStream(),true);
			
			// The server socket implemented with BufferReader, could be implemented with DataInputStream
			
			BufferedReader in = new BufferedReader(new InputStreamReader(this.dbThreadSocket.getInputStream()));
			// read the query
			String inputLine;
			String query="";
			while ((inputLine = in.readLine()) != null) {
				query+=inputLine;
			}
			
			//parse the query
			dbQueryParser queryParser=dbQueryParser.parseQuery(query);
			if(queryParser.getCommand().equals("insert")) {
				// run insert command
				new dbQueryRun(queryParser,this.storage).queryInsert();
			}
			else {
				// run get command
				String document=new dbQueryRun(queryParser,this.storage).queryGet();
				PrintWriter out = new PrintWriter(this.dbThreadSocket.getOutputStream(),true);
				out.println(document.toString());
			}
			
			
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		catch(QueryException e) {
			e.printStackTrace();
		}
		
	}
	

}
