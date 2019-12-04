package com.guitar.database;

public class guitarDB {
	private DBConnectionManager connManager;
	private documentStorage storage;
	
	public guitarDB() {
		this.connManager = new DBConnectionManager(1313);
		this.storage=new documentStorage();
	}
	
	public guitarDB(int portNumber) {
		this.connManager = new DBConnectionManager(portNumber);
		this.storage=new documentStorage();
	}
	
	public void startDB() {
		this.connManager.run(storage);
	}
	
	public void addDocument(String name, String document) {
		this.storage.addDocument(name, document);
	}
	
	public String getDocument(String name) {
		return this.storage.getDocument(name);
	}

}
