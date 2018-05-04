/*
 * Anish Prasad
 * Date.java
 * Basic Date class for 461 Project
 */
public class Date {
	
	private int day = 0;
	private int month = 0;
	private int year = 0;

	public Date(int newMonth, int newYear) {
		if(newMonth > 0 && newMonth < 13) {
			month = newMonth;
		}
		else {
			System.err.println("Month must be between 1 and 12");
		}
		
		
		if(newYear >= 2000) {
			year = newYear;
		}
		else {
			System.err.println("Year must be greater than 2000");
		}
	}
	
	public Date(int newDay, int newMonth, int newYear) {
		if(newDay > 0 && newDay < 32) {
			if(newMonth == 1 || newMonth == 3 || newMonth == 5 || newMonth ==  7 ||
					newMonth ==  8 ||newMonth == 10 ||newMonth == 12) {
				if(newDay > 0 && newDay < 31) {
					day = newDay;
				}
			}
			else if(newMonth == 2) {
				//leap years?
				if(newDay > 0 && newDay < 29) {
					day = newDay;
				}
			}
			else {
				day = newDay;
			}
		}
		else {
			System.err.println("Month must be between 1 and 12");
		}
		
		
		if(newMonth > 0 && newMonth < 13) {
			month = newMonth;
		}
		else {
			System.err.println("Month must be between 1 and 12");
		}
		
		
		if(newYear >= 2000) {
			year = newYear;
		}
		else {
			System.err.println("Year must be greater than 2000");
		}
	}
	
	public int getDay() {
		return day;
	}
	
	public int getMonth() {
		return month;
	}
	
	public int getYear() {
		return year;
	}
	
	public String toString(int format) {
		//1 is m/d/y
		//2 is d/m/y
		String date = null;
	
		if(format == 2) {
			date = this.getDay() + "/" + this.getMonth() + "/" + this.getYear();
		}
		else {
			//assume m/d/y if bad input
			date = this.getMonth() + "/" + this.getDay() + "/" + this.getYear();
		}
		
		return date;
	}
}
