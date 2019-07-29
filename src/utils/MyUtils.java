package utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;
import java.util.StringTokenizer;

public class MyUtils {
	    //receives: theName, a String to properly format
		// returns:  a String formatted with first character of each word in uppercase if first char is a letter
		//      all letters after first letter in word in lowercase
		//      all extra spaces removed before and after each word
		public static String properFormat(String theName)
		{
			String temp = "";
			boolean atSpace=true; // so that first letter gets capitalized...
			theName = theName.trim();
			for(int i=0; i< theName.length(); i++)
			{
				if(Character.isWhitespace(theName.charAt(i)) && !atSpace)
				{
					atSpace=true;
					temp += ' ';
				}
				else if (atSpace == true)
				{
					if(!Character.isWhitespace(theName.charAt(i)))
					{
						temp += Character.toUpperCase(theName.charAt(i));
						atSpace = false;
					}
				}
				else
				{
					temp += Character.toLowerCase(theName.charAt(i));
					atSpace = false;
				}
			}// end for
			return temp;
		}
	
		
		//receives: a String, data
		//returns: the number of newlines in data
		public static int numberLines(String data)
		{
			int count=0;
			for(int i=0; i<data.length(); i++)
			{
				if (data.charAt(i) =='\n')
					count++;
			}
			return count;
		}
		
		public static String dateToString(GregorianCalendar date)
		// receives: a date as a GregorianCalendar instance
		// returns: received date as a string in format mm/dd/yyyy
		{  
			String temp="";
			int month = date.get(Calendar.MONTH);
			month++; // add 1 due to zero-based months
			int day = date.get(Calendar.DAY_OF_MONTH);
			int year = date.get(Calendar.YEAR);
			temp = month + "/" + day + "/" + year;
			return temp;
		}
		public static GregorianCalendar stringToDate(String theDate)
		// receives: theDate as a String in format mm/dd/yyyy
		//  pre:  theDate is in format mm/dd/yyyy
		// returns: received date as a correct GregorianCalendar object
		{
			StringTokenizer tokenizer = new StringTokenizer(theDate, "/");
			String temp = tokenizer.nextToken().trim();  // grabs up to "/"
			int month=0, day=1, year=2000;  // default date values
			try {
				month = Integer.parseInt(temp);
				month--;  // zero-based months
				temp = tokenizer.nextToken().trim();
				day = Integer.parseInt(temp);
				temp = tokenizer.nextToken().trim();
				year = Integer.parseInt(temp);
			}
			catch(NumberFormatException e) {
				System.out.println("error extracting date from: " + theDate + " using default date 1/1/2000");
				return new GregorianCalendar(2000, 0, 1); // default instance returned
			}
			return new GregorianCalendar(year, month, day); // uses month/day/year parsed out of String
		}
		//receives: a string to remove spaces from
		// returns a string with all spaces removed from theId as received.
		public static String stripSpaces(String theId) {
			// TODO Auto-generated method stub
			String temp="";
			int count=0;
			for(int i=0; i< theId.length(); i++)
			{
				if(!Character.isWhitespace(theId.charAt(i)))
						{
						temp += theId.charAt(i);
						count ++;
						if(count == 6)
							break;
						}
			}
			return temp;
		}
		//receives: aString to check chars in
		// returns true if the string received has only letters or digits, false otherwise
		public static boolean isValid(String aString)
		{
			for(int i=0; i< aString.length(); i++)
			{
				if(!Character.isLetterOrDigit(aString.charAt(i)))
						return false;
			}
			return true;
		}
		
		
       //receives: a descriptor to print with the current time stamp 
	   // task: prints timeStamp to std output with date(yyyy-mm-dd) and time (HH:mm:ss) and received descriptor
	   //        descriptor is begins or ends or currently or some such indicator of what the time is describing
	   public static void printTimeStamp(String descriptor)
	   {
		   String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date());
			System.out.println("Execution " + descriptor + "  at: " + timeStamp);
	   }
	   //receives: nothing
	   // returns name of student entered from keyboard
	   // proper formats name and returns it
	   public static String getNameFromStudent()
	   {
	 	  Scanner in = new Scanner(System.in);
	 	  System.out.print("Enter your name: " );
	 	  String s1 = in.nextLine();
	 	  s1 = utils.MyUtils.properFormat(s1).trim();
	 	  if(s1.equals(""))
	 		  s1 = "No Name Entered";
	 	  return s1;
	   }
}