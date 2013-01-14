import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class SqliteUtils {

	private static String SQLITE_CLASS_NAME = "org.sqlite.JDBC";
	private static String SQLITE_CONNECTION = "jdbc:sqlite:";
	
	/**
	 * This method recibes databaseName ("default.db") and an update statement
	 * like 'INSERT INTO EMPLOYEES VALUES (1, 'Robert', 'Smith', 34234222)'.
	 * @param databaseName String database name
	 * @param updateStatement String update statement
	 */
	public static void sqliteUpdate(String databaseName, String updateStatement){		
		try {
		     Class.forName(SQLITE_CLASS_NAME);
		     Connection conn = DriverManager.getConnection(SQLITE_CONNECTION + databaseName);
		     
		     Statement stat = conn.createStatement();
		     stat.executeUpdate(updateStatement);
		     
		     stat.close();
		     conn.close();
		}catch(ClassNotFoundException ex){
		     ex.printStackTrace();
		}catch (SQLException ex){
		     ex.printStackTrace();
		}
	}
	
	/**
	 * This method return a list of hashmaps that contains rows of
	 * select statements.
	 * @param databaseName String database name  like ("default.db")
	 * @param selectStatement String select statement like 'SELECT id FROM USERS'
	 * 
	 * Warning: This method is only used for specific select data, this don't work
	 * well with * querys or things like that.
	 * 
	 * @return List<Map<String, String>>
	 */
	public static List<Map<String, String>> sqliteQuery(String databaseName, String selectStatement){
		List<Map<String, String>> listQuery = new ArrayList<Map<String,String>>();	
		try {
		     Class.forName(SQLITE_CLASS_NAME);
		     Connection conn = DriverManager.getConnection(SQLITE_CONNECTION + databaseName);
		     Statement stat = conn.createStatement();
		     ResultSet rs = stat.executeQuery(selectStatement);
		     
		     String selectPart = selectStatement.split("from")[0];
		     String fieldsPart = selectPart.split("select")[1];
		     String vecFields[] = fieldsPart.split(",");
		     
		     while(rs.next()) {
		    	 Map<String, String> hashMapRow = new HashMap<String, String>();
		    	 
		    	 for(int i = 0; i < vecFields.length; i++) {
		    		 String field;
		    		 field = vecFields[i];
		    		 field = field.trim();
		    		 
		    		 hashMapRow.put(field, rs.getString(field));
		    	 }
		    	 listQuery.add(hashMapRow);
		     }
		     stat.close();
		     conn.close();
		}catch(ClassNotFoundException ex){
		     ex.printStackTrace();
		}catch (SQLException ex){
		     ex.printStackTrace();
		}
		return listQuery;
	}
	
	public static void showData(String databaseName, String selectStatement) {
		List<Map<String, String>> listQuery = sqliteQuery(databaseName, selectStatement);
		for (int i = 0; i < listQuery.size(); i++) {
			Map<String, String> fieldsMap = listQuery.get(i);
			String strId = fieldsMap.get("id");
			String strNumber = fieldsMap.get("numero");
			String strName = fieldsMap.get("nombre");
			
			System.out.println("Row -> Id: " + strId + ", Number: " + strNumber + ", Name: " + strName); 
		}
	}
}
