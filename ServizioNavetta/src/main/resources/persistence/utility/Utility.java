package persistence.utility;

public class Utility {


	private Utility() {}

	public static java.util.Date convertToJava(java.sql.Date date){

		java.util.Date javaDate = null;
		
		long secs = date.getTime();

		javaDate = new java.util.Date(secs);

		return javaDate;
	}

	public static java.sql.Date convertToSql(java.util.Date date){

		java.sql.Date sqlDate = null;
		
		long secs = date.getTime();
		
		sqlDate = new java.sql.Date(secs); 

		return sqlDate;
	}
	
	public static String deleteArrayElements(String retrieved) {
		String result = "";
		for(int i=1;i<retrieved.length();i+=2) {
			result = result.concat(""+retrieved.charAt(i));
		}
		return result;
	}
}
