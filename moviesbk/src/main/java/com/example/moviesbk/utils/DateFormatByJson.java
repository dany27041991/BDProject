package com.example.moviesbk.utils;

public class DateFormatByJson {
	
	public static final String dateFormattedByJson(String date) {
		String dateReleased;
		switch(date.split(" ")[1]) {
		    case "Jan":
		    	dateReleased = date.split(" ")[2]+"-01-"+date.split(" ")[0];
		    	break;
		    case "Feb":
		    	dateReleased = date.split(" ")[2]+"-02-"+date.split(" ")[0];
		    	break;
		    case "Mar":
		    	dateReleased = date.split(" ")[2]+"-03-"+date.split(" ")[0];
		    	break;
		    case "Apr":
		    	dateReleased = date.split(" ")[2]+"-04-"+date.split(" ")[0];
		    	break;
		    case "May":
		    	dateReleased = date.split(" ")[2]+"-05-"+date.split(" ")[0];
		    	break;
		    case "Jun":
		    	dateReleased = date.split(" ")[2]+"-06-"+date.split(" ")[0];
		    	break;
		    case "Jul":
		    	dateReleased = date.split(" ")[2]+"-07-"+date.split(" ")[0];
		    	break;
		    case "Aug":
		    	dateReleased = date.split(" ")[2]+"-08-"+date.split(" ")[0];
		    	break;
		    case "Sept":
		    	dateReleased = date.split(" ")[2]+"-09-"+date.split(" ")[0];
		    	break;
		    case "Oct":
		    	dateReleased = date.split(" ")[2]+"-10-"+date.split(" ")[0];
		    	break;
		    case "Nov":
		    	dateReleased = date.split(" ")[2]+"-11-"+date.split(" ")[0];
		    	break;
		    case "Dec":
		    	dateReleased = date.split(" ")[2]+"-12-"+date.split(" ")[0];
		    	break;
		    default:
		    	dateReleased = "2000-01-01";
		}
		return dateReleased;
	}
}
