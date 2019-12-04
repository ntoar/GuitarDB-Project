package com.guitar.database;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DBConnectionManagerTest {
	
	@Test
	public void newDBConnectionManager() {
		DBConnectionManager connectionManager=new DBConnectionManager(1313);
		
		assertEquals(1313,connectionManager.getPort());
		assertEquals(false,connectionManager.getListening());
	}

}
