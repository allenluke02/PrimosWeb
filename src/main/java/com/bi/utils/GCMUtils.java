package com.bi.utils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.jayway.jsonpath.JsonPath;

@Component
public class GCMUtils {
	
	// URL Separator
	public String URL_SEP = "/";
	
	// FIVE MAX VAL
	private int FIVEDIGITMAX = 99999;
	
	// FIVE MIN VAL
	private int FIVEDIGITMIN = 10000;
	
	// 2Factor URL String	
	final String URL = "https://2factor.in/API/V1";
	
	// 2Factor API KEY
	final String API_KEY = "65e62d3b-3661-11e8-a895-0200cd936042";
	
	// 2Factor SMS TYPE 
	final String VENDOR_TYPE_SMS 	= "SMS";
	
	// 2Factor SMS VERIFY TYPE
	final String VENDOR_TYPE_VERIFY = "VERIFY";
	
	/**
	 * Random Number with realeted to 
	 * @param FIVEDIGITMIN and @param FIVEDIGITMAX
	 * @return
	 */
	public int getRandomNumberInRange() {

		if (this.FIVEDIGITMIN >= this.FIVEDIGITMAX) {
			throw new IllegalArgumentException("max must be greater than min");
		}

		Random r = new Random();
		return r.nextInt((this.FIVEDIGITMAX - this.FIVEDIGITMIN) + 1) + this.FIVEDIGITMIN;
	}
	
	/**
	 * Register SMS Service and transfer self otp to 2Factor 
	 * @param uri
	 * @return
	 */
	public String registerSMSViaVendor(String uri){
		
		RestTemplate restTemplate = new RestTemplate();
	    String result = restTemplate.getForObject(uri, String.class);
	    String details = JsonPath.parse(result).read("$.Details");

	    System.out.println("JSON"+details);
	    
	    return details;
	}
	
	/**
	 * Gnerate Otp URL to Register Otp Service
	 * @param phoneNumber
	 * @param RandomOtp
	 * @return
	 */
	public String generateOtpURL(String phoneNumber,String RandomOtp) {
		// https://2factor.in/API/V1/65e62d3b-3661-11e8-a895-0200cd936042/SMS/9968302318/1234
		String finalURL = this.URL+
				this.URL_SEP+
				this.API_KEY+
				this.URL_SEP+
				this.VENDOR_TYPE_SMS+
				this.URL_SEP+
				phoneNumber+
				this.URL_SEP+
				RandomOtp;
		return finalURL;
	}
	
	/**
	 * Verify Otp given by user and validate against 
	 * details(otp @param) or SESSION_ID given by 2Factor during Otp Register
	 * @param details
	 * @param InputOtp
	 * @return
	 */
	public String generateVerifyOtpURL(String details,String InputOtp) {
	// https://2factor.in/API/V1/65e62d3b-3661-11e8-a895-0200cd936042/SMS/VERIFY/{{SESSION_ID}}/1234
		String finalURL = this.URL+
				this.URL_SEP+
				this.API_KEY+
				this.URL_SEP+
				this.VENDOR_TYPE_SMS+
				this.URL_SEP+
				this.VENDOR_TYPE_VERIFY+
				this.URL_SEP+
				details+
				this.URL_SEP+
				InputOtp;
		return finalURL;
	}	
	
	public static String formatDateTime(String format, Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}
	
	public static Date formatDateTime(String format, String dateStringInSameFormat) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.parse(dateStringInSameFormat);
	}

	public String getNotNullString(String str) {
		return str != null ? str : "";
	}
	
	public String concatIgnoreNull(String seperator, String... str) {
		StringBuilder builder = new StringBuilder();
		int count = 0;
		for (String stringVal : str) {
			if(count == 0 && stringVal != null) {
				count++;
				builder.append(stringVal);
			} else if(stringVal != null){
				builder.append(seperator);
				builder.append(stringVal);
			}
		}
		return builder.toString();
	}
	
	public static void main(String[] args) throws ParseException {
		String[] strArr = new String[] {null, null, "s1", null, "s2", null};
		System.out.println(new GCMUtils().concatIgnoreNull(",", strArr));
	}
	

	/**
	 * For global searches, check if at least a criteria exists
	 * @param id of entity
	 * @param firstName of entity
	 * @param lastName of entity
	 * @param mobile of entity
	 * @param email of entity
	 * @return <code>true</code>
	 */
//	public static boolean isSearchCriteriaExists(int id, String firstName, String lastName, String mobile, String email){
//		return id>0 || firstName != null || lastName != null || mobile!=null || email != null;
//	}
	


	public DayOfWeek getDayOfWeek()
	{
		Calendar calender = Calendar.getInstance();
		calender.setTime(new Date());
		
		switch( calender.get(Calendar.DAY_OF_WEEK)) {
		case 1:
			 return DayOfWeek.SUNDAY;
		case 2:
			return DayOfWeek.MONDAY;
		case 3:
			 return DayOfWeek.TUESDAY;
		case 4:
			 return DayOfWeek.WEDNESDAY;
		case 5:
			 return DayOfWeek.THURSDAY;
		case 6:
			 return DayOfWeek.FRIDAY;
		case 7:
			 return DayOfWeek.SATURDAY;
		
		default:
			return null;
		}
		
	}
	
	public int getWeekOfMonth()
	{
		Calendar calender = Calendar.getInstance();
		calender.setTime(new Date());
		
		return calender.get(Calendar.WEEK_OF_MONTH);
	}
}
