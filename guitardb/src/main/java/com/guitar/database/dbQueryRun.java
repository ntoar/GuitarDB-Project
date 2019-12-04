package com.guitar.database;

public class dbQueryRun {
	private dbQueryParser queryParser;
	private documentStorage storage;
	
	public dbQueryRun(dbQueryParser queryParser, documentStorage storage) {
		this.queryParser=queryParser;
		this.storage=storage;
	}
	
	public void queryInsert() {
		this.storage.addDocument(queryParser.getName(), queryParser.getDocument());
	}
	
	public String queryGet() {
		return this.storage.getDocument(this.queryParser.getName());
	}

}
