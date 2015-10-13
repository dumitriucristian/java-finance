
package budget;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.Date;


//http://christianpf.com/basic-personal-budget-categories/

public class User{

                private int userId ;
                private String firstName;
                private String lastName;
	private String email;
                private String password;
                private Date registrationDate;

	public User(){
                       userId = 8;
                       getUserData(userId);
	}
        
                public User(int userId){
                    setUserId(userId);
                }


                public int getUserId(){
                    return userId;
                }

                private void setUserId(int userId){
                    this.userId = userId;
                }
               
                
                public void getUserData(int userId){
                        String sql = "select firstname,lastname,email, password,reg_date  from users  where id = ?";
		PreparedStatement prepared = Sql.getPrepared(sql);
		try{
			
                                        prepared.setInt(1, userId);
                                        ResultSet rs = prepared.executeQuery();
                                       while(rs.next()){
                                                this.firstName = rs.getString("firstname");
                                                this.lastName = rs.getString("lastname");
                                                this.email = rs.getString("email");
                                                this. password = rs.getString("password");
                                                this.registrationDate = rs.getDate("reg_date");
                                            }
                                            
			
		}catch(SQLException e){
                                        System.out.println("Income Exception:  " + e);
		}catch(Exception e){
                                	System.out.println("Income Exception:  " + e);
		}
                 }   
                    
                public String getFirstName(){
                    return firstName;
                }   
                
                public Date getRegistrationDate(){
                    return registrationDate;
                }
                
	public ResultSet listUsers(){
	
		String sql = "Select * from users";		
		ResultSet rs = Sql.getSQL( sql );
		return rs;
	
	}

	public void addUser(String firstName, String lastName, String email, String password){

		try{
			String sql = "Insert into users (firstname, lastname, email, password) values (?,?,?,?)";

			PreparedStatement prepared =  Sql.getPrepared(sql);
			prepared.setString(1, firstName);
			prepared.setString(2, lastName);
			prepared.setString(3, email);
			prepared.setString(4, password);
			prepared.addBatch();
			prepared.executeBatch();

		}catch(SQLException e){
			System.out.println("addUser Exception:  " + e);
		}catch(Exception e){	
			System.out.println("addUser Eception:  " + e);
		}finally{
			Sql.endSql();
		}	

	}

	public void deleteUser(int user_id){

		try{
			String sql = "Delete from users where id = ?";
			PreparedStatement prepared = Sql.getPrepared(sql);
			prepared.setInt(1, user_id);
			prepared.addBatch();
			prepared.executeBatch();

		}catch(SQLException e){
			System.out.println("delUser Exception:  " + e);
		}catch(Exception e){
			System.out.println("delUser Exception:  " + e);

		}

	}

	public void updatePassword(int user_id , String password){

		try{

			String sql = "Update users set password = ? where id = ?";

			PreparedStatement prepared = Sql.getPrepared(sql);
			prepared.setString(1, password);
			prepared.setInt(2, user_id);
			prepared.addBatch();
			prepared.executeBatch();

		}catch(SQLException e){
			System.out.println("updateError " + e);

		}catch(Exception e){
			System.out.println("updateError " + e);

		}
	}
	
	public boolean checkUserId(int user_id){

		String sql = "select id from users  where id = ?";
		PreparedStatement prepared = Sql.getPrepared(sql);
		try{
			
                                        prepared.setInt(1, user_id);
                                        ResultSet rs = prepared.executeQuery();

                                        return rs.first();
			
		}catch(SQLException e){
					System.out.println("Income Exception:  " + e);
		}catch(Exception e){
			System.out.println("Income Exception:  " + e);
		}	
		
		return false;

	}

	public boolean loginUser(String email, String password){
		String sql = "Select email, password from users where email = ?  and password = ?";
		PreparedStatement prepared = Sql.getPrepared(sql);
		try{
			prepared.setString(1, email);
			prepared.setString(2, password);

			ResultSet rs = prepared.executeQuery();
			return rs.first();
		}catch(SQLException e){
			System.out.println("Login Exception:  " + e);
		}catch(Exception e){
			System.out.println("Login Exception:  " + e);
		}	

		return false;
	}		
}