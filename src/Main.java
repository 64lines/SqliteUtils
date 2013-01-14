import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;


public class Main {

	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String databaseName = "default.db";

		SqliteUtils.showData(databaseName, "select id, numero, nombre from contactos");
	}

}
