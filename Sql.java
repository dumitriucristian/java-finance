package budget;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.List;

public class Sql{

	static final private  String URL= "jdbc:mysql://localhost:3306/";
	static final private  String DBNAME = "Budget";
	static final private  String DRIVER = "com.mysql.jdbc.Driver";
	static final private  String USER = "root";
	static final private  String PASS = "admin";

	//private instance
	private static Sql instance = null;

        //params
	public  List <String[]> sqlParam;
	static private Connection conn ;
	static private Statement stmt ;
	static public ResultSet result;
	static private PreparedStatement prepared;
	public String sql;
	
	static {
                        conn = getConnection();
                }

	//no instance allowed outside this class
	private  Sql(){
		//create the instance
                        if(instance ==  null){
                                Sql instance = new Sql();
                        }
	}
	
    static private Connection getConnection(){
    	    	
    	try{
                Connection conn = DriverManager.getConnection(URL+DBNAME, USER, PASS);
                return conn;
					
            }catch(Exception e){
                System.out.println(e);
            }
                System.out.println(conn);
		return conn;
    }

    static private Statement getStatement(){
    	    	
    	try{
    		Statement stmt = conn.createStatement();
    		return stmt;
    	}catch(Exception e){
    		System.out.println(e) ;
    	}
    	return stmt;

    }

    // get all data froma a table
    static public ResultSet getSQL(String sql){
    	try{
    		Statement stmt = getStatement();
    		ResultSet result = stmt.executeQuery(sql);
    		//int resultNr = result.size();
    		//System.out.println("nr de rezultate : " + resultNr );
    		return result;
    	}catch(SQLException e){
    		System.out.println("Sql-exception:" + e) ;
    		
    	}catch(Exception e){
    		System.out.println("Normal-exception" +e) ;
    	}
    	
    	return result;
    }



    static  public PreparedStatement getPrepared(String sql){
    	try{

    		PreparedStatement prepared = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
    		return prepared;
    	}catch(SQLException e){
    		System.out.println("Prepared sql erorr" + e);
    	}catch(Exception e){
    		System.out.println("Prepared error" + e);

    	}
    	return prepared;
    }

   
    static public void endSql(){


    	try{

	    	if (prepared != null) {
					prepared.close();
				}

			if (conn != null) {
				conn.close();
			}
		}catch(Exception e ){
			System.out.println(e);
		}		
    }

}	