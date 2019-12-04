package com.guitar.database;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

public class documentStorage {
	private Map<String,String> data;
	
	
	public documentStorage() {
		data = new HashMap<String,String>();
	}
	
	public void addDocument(String name,String document) {
		this.data.put(name, document);
	}
	
	public String getDocument(String name) {
		return this.data.get(name);
	}
	

}
