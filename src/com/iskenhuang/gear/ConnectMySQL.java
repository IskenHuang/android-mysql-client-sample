package com.iskenhuang.gear;

/**
 * Connect to api.
 *
 * @author Isken Huang (c) 2011
 * @version 1.4.0 
 */

import java.sql.DriverManager;
import java.util.ArrayList;

import android.util.Log;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;

public class ConnectMySQL {
	private final String PAGETAG = "ConnectMySQL";
	
	private Connection connect = null;
	private Statement statement = null;
	private ResultSet resultSet = null;

	public static final String MYSQL_IP = "10.0.1.102";
	public static final String MYSQL_DBNAME = "Test";
	public static final String MYSQL_USERNAME = "isken";
	public static final String MYSQL_PASSWORD = "isken";

	public ArrayList<MyObj> getList() throws Exception {
		ArrayList<MyObj> results = new ArrayList<MyObj>();
		try {
				String script = "SELECT * FROM Pad";

				// This will load the MySQL driver, each DB has its own driver
				Class.forName("com.mysql.jdbc.Driver");
				
				String path = "jdbc:mysql://"+MYSQL_IP+"/"+MYSQL_DBNAME+"?"+ "user="+MYSQL_USERNAME+"&password="+MYSQL_PASSWORD;
				Log.e(PAGETAG, path);

				// Setup the connection with the DB
				connect = (Connection) DriverManager.getConnection(path);

				Log.e(PAGETAG, "connection is success");

				// Statements allow to issue SQL queries to the database
				statement = (Statement) connect.createStatement();

				// Result set get the result of the SQL query
				resultSet = (ResultSet) statement.executeQuery(script);

				while (resultSet.next()) {
					MyObj obj = new MyObj();
					String id = resultSet.getString("id");
					String name = resultSet.getString("name");
					String chip = resultSet.getString("chip");
					String display = resultSet.getString("display");
					String camera = resultSet.getString("camera");
					String weight = resultSet.getString("weight");
					String color = resultSet.getString("color");
					String wireless = resultSet.getString("wireless");
					String battery = resultSet.getString("battery");
					String price = resultSet.getString("price");
					
					obj.id = Integer.parseInt(id);
					obj.name = name;
					obj.chip = chip;
					obj.display = display;
					obj.camera = camera;
					obj.weight = Float.parseFloat(weight);
					obj.color = color;
					obj.wireless = wireless;
					obj.battery = battery;
					obj.price = Float.parseFloat(price);
					results.add(obj);
				}
				
				Log.e(PAGETAG, "results size = "+results.size());
		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}
		
		return results;
	}
	
	/* close connect */
	private void close() {
		try {
			if (resultSet != null) {
				resultSet.close();
			}

			if (statement != null) {
				statement.close();
			}

			if (connect != null) {
				connect.close();
			}
		} catch (Exception e) {
			
		}
	}
	
	public class MyObj{
		int id = 0;
		String name = "";
		String chip = "";
		String display = "";
		String camera = "";
		float weight = 0;
		String color = "";
		String wireless = "";
		String battery = "";
		float price = 0;
	}
}
