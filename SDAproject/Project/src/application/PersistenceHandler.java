package application;

import com.mysql.jdbc.ResultSet;

public interface PersistenceHandler {

	abstract void connectDB();
	abstract void saveRecord(String s);
	abstract void updateRecord(String s);
	abstract ResultSet readRecord(String s);
	abstract boolean matchRecord(String s);
	abstract void deleteRecord(String s);
}
