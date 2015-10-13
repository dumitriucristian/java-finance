
package budget;

interface Recurrence{

	static final int DAILY = 1;

	public void registerDailyRecurrence(int associated_key, int user_id , String start_date, String end_date, int frequency_id, String frequency_value);
		
}