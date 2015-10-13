package budget;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class Income implements Recurrence{

    public void addIncome(int user_id, String income_name, Double value, String income_date , int income_type_id, int income_preset){
            User user =  new User();
            Income income = new Income();
            if( user.checkUserId(user_id) && income.validateIncomeType(income_type_id) ){
                    String sql ="Insert into income (user_id, incom_name, value, income_date, income_type_id,recurrent,income_preset ) value  (?,?,?,?,?,?,?)";

                    PreparedStatement prepared = Sql.getPrepared(sql);
                    try{

                            prepared.setInt(1, user_id);
                            prepared.setString(2, income_name );
                            prepared.setDouble(3, value);
                            prepared.setString(4, income_date);
                            prepared.setInt(5, income_type_id);
                            prepared.setInt(6, 0); // default recurrence 0 - no recurrence
                            prepared.setInt(7,income_preset); //choice 1 ,2,3
                            prepared.addBatch();
                            prepared.executeBatch();



                    }catch(SQLException e){
                                    System.out.println("Add income:  " + e);
                    }catch(Exception e){
                            System.out.println("Add income:  " + e);
                    }	

            }else{
                    System.out.println("checking data is false");
            }

    }
	
	//income_type_id is the cateogy of the income
    public void addIncome(int user_id, String income_name, Double value, String income_date , int income_type_id, int income_preset, int recurrence_type){


            User user =  new User();
            Income income = new Income();
            if( user.checkUserId(user_id) && income.validateIncomeType(income_type_id) ){
                    String sql ="Insert into income (user_id, incom_name, value, income_date, income_type_id, recurrent, income_preset ) value  (?,?,?,?,?,?,?)";

                    PreparedStatement prepared = Sql.getPrepared(sql);
                    try{

                            prepared.setInt(1, user_id);
                            prepared.setString(2, income_name );
                            prepared.setDouble(3, value);
                            prepared.setString(4, income_date);
                            prepared.setInt(5, income_type_id);
                            prepared.setInt(6, 1);
                            prepared.setInt(7, income_preset); // can be 1|2|3


                            prepared.addBatch();
                            prepared.executeBatch();

                            // prepared.executeUpdate();
                            // Sql.getKeys();

                            ResultSet rs = prepared.getGeneratedKeys();
                               if(rs.next()){

                                      int  auto_id = rs.getInt(1);
                                      //register recurrence
                                      System.out.println("id-ul pzi: " + auto_id);
                                    }

                    }catch(SQLException e){
                            System.out.println("Add income with recc:  " + e);
                    }catch(Exception e){
                            System.out.println("Add income rec:  " + e);
                    }	

            }else{
                    System.out.println("checking data is false");
            }

    }

    public boolean validateIncomeType(int income_type_id){

        String sql = "select income_type_id from income_type where income_type_id = ?";
            PreparedStatement prepared = Sql.getPrepared(sql);
            try{

                    prepared.setInt(1, income_type_id);
                    ResultSet rs = prepared.executeQuery();

                            return rs.first();

            }catch(SQLException e){
                                    System.out.println("Income Exception:  " + e);
            }catch(Exception e){
                    System.out.println("Income Exception:  " + e);
            }	

            return false;

    }

    @Override
    public void registerDailyRecurrence(int income_id, int user_id , String start_date, String end_date, int frequency_id, String frequency_value){
            String sql ="Insert into income_recurrence("
                                    +" income_id,"
                                    +" user_id,"
                                    +" start_date,"
                                    +" end_date,"
                                    +" frequency_id,"
                                    +" frequency_value"
                                    +")values(?,?,?,?,?,?)";

            PreparedStatement prepared = Sql.getPrepared(sql);
            try{

                    prepared.setInt(1, income_id);
                    prepared.setInt(2, user_id);
                    prepared.setString(3,start_date);
                    prepared.setString(4, end_date);
                    prepared.setInt(5, 1); //frequency daily type
                    prepared.setString(6,frequency_value);

                    prepared.addBatch();
                    prepared.executeBatch();
                            ResultSet rs = prepared.getGeneratedKeys();
                               if(rs.next()){
                                      int  auto_id = rs.getInt(1);
                                      //register recurrence
                                      System.out.println("id-ul pzi: " + auto_id);
                                    }

            }catch(SQLException e){
                    System.out.println("Add income with recc:  " + e);
            }catch(Exception e){
                    System.out.println("Add income rec:  " + e);
            }			
    };
    
    public ResultSet getUserIncomes( int user_id) throws SQLException{
        
        System.out.println("the user id is tested as: " +user_id);
      
        String sql = "select  *  from income where user_id =  ? ";
        PreparedStatement prepared = Sql.getPrepared(sql);
        prepared.setInt( 1, user_id );
        ResultSet rs =  prepared.executeQuery( );
           return rs;
           
    }
    
    public void getTotalIncome(int user_id) throws SQLException{
    }
    
    public static Map <Integer, String>  getIncomeTypes()throws SQLException{
        String sql = "select * from income_type";
        PreparedStatement prepared = Sql.getPrepared(sql);
      
             ResultSet rs = prepared.executeQuery();
             Map <Integer,String> incomeType = new HashMap<>();
             while(rs.next()){
                
                 int incomeTypeId = rs.getInt("income_type_id");
                 String incomeTypeName =rs.getString("name"); 
                  incomeType.put(incomeTypeId, incomeTypeName);
           
              //   System.out.println("id Income: " + incomeTypeId + "\n incomeTypeName: " + incomeTypeName);
             }
             return  incomeType;
     
    }
  
    
}