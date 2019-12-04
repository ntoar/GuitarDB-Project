package com.guitar.database;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class dbQueryParser {
	
	private String query;
	private String command;
	private String name;
	private String document;
	
	public dbQueryParser(String command,String name,String document) {
		this.command=command;
		this.name=name;
		this.document=document;
		
	}
	
	public dbQueryParser(String query) {
		this.query=query;
		
		
	}
	
	public String getCommand() {
		return this.command;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getDocument() {
		return this.document;
	}
	
	
	/*
	 * Method to parse a query
	 * Queries are : "COMMAND DOC-NAME"
	 * So far just to statements implemented
	 * INSERT : to insert a document to the storage
	 * GET : to get an existing document from the storage;
	 */
	
	
	public static dbQueryParser parseQuery(String query) throws QueryException {
		
		String command="";
		String name="";
		String document="";
		
		String[] splitQuery = query.split(" ");
		
		
		// check whether first string is the command
		
		if(splitQuery.length >= 2) {
		
			switch(splitQuery[0]){
				case "INSERT":
					command="insert";
					String documentString="";
					for(int i=2;i<splitQuery.length;i++) {
						documentString+=splitQuery[i];
					}
					try {
						// check for json syntax
						document=documentString;
						new Gson().fromJson(documentString,Gson.class);
					}catch(JsonSyntaxException e) {
						e.printStackTrace();
						throw new QueryException("Can't parse JSON Document");
					}
					break;
				
				case "GET":
					command="get";
					break;
				
					// just to test the result TEST DB
				case "TEST":
					throw new QueryException("Test command");
				
				default:
					throw new QueryException("Undefined Command");
			}
		
			// check whether second string is the name
			if(splitQuery[1].isEmpty()) throw new QueryException("Undefined document name");
		
		}
		
		else 
			if(splitQuery.length == 1 && splitQuery[0].contentEquals("stop")) {
				System.exit(0);
			}
		else
			throw new QueryException("Undefined Query");
		
		return new dbQueryParser(command,name,document);
		
	}
	

}
