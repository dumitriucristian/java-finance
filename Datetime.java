package budget;

import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public final class Datetime{

	static private Date parsed;
	static public String date;
	static public int frequency;


	public static Date stringToDate( String date, String form){

		DateFormat df = new SimpleDateFormat(form);
		try{
			Date parsed =df.parse(date);
			
			return parsed;

		}catch(Exception e){
			System.out.println(e);
		}
		return parsed;
		
	};
	
	static public String timeToString(Long milisecondsDate, String form){

		
		DateFormat df = new SimpleDateFormat(form);
		try{
			Date  d = new Date(milisecondsDate);
			String date = df.format(d);
			return date;



		}catch(Exception e){
			System.out.println("dateToString exception: " + e);
		}
		return date;

	}

	static public  List <String> getAllDates(Long milisecondsStart, Long milisecondsStop, int frequency){
		//calculate difference
		Long between = milisecondsStart - milisecondsStop;

		//generate a new list of dates
		List <String> recurringDates = new ArrayList <>();

		while(milisecondsStart < milisecondsStop){
			recurringDates.add(Datetime.timeToString(milisecondsStart,"yyyy/MM/dd kk:mm"));
			milisecondsStart = milisecondsStart + frequency;
			
		}

		return recurringDates;

	}
	
}