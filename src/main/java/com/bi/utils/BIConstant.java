package com.bi.utils;

import java.util.regex.Pattern;

public class BIConstant {
	
	public static final String ASC = "ASC";
	public static final String DESC = "DESC";
	public static final String PRESCRIPTION = "Prescription";

	public enum EntityType{
		PERSON,CLINIC,ENCOUNTER, UNKNOWN
	}
	
	public enum DocumentType{
		PROFILE,OTHERS
	}

	// Date Formats
	public static final String TIME_FORMAT_HH_MM = "HH:mm";
	public static final String DATE_FORMAT_YYYY_MM_DD = "yyyy-MM-dd";
	public static final String DATE_FORMAT_YYYY_MM_DD_T_hh_mm_ss_sss_z = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
	public static final String DATE_FORMAT_YYYY_MM_DD_T_hh_mm_ss_Z = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
	public static final String DATE_FORMAT_dd_MMM_yyyy_WITH_TIME = "dd MMM yyyy HH:mm a";
	public static final String DATE_FORMAT_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

	public static final String BIURI_FOR_DOWNLOAD = "/downloadFile/";
	public static final long UPLOAD_FILE_MAX_SIZE = 10485760; // 10*1024*1024
	public static final Pattern CSV_FILE_EXTN_PATTERN = Pattern.compile("([^\\s]+(\\.(?i)(csv))$)");
	
	public static final String MODE_DRIVING = "driving";
	public static final String LANG_ENG = "en-EN";
	public static final int LOCATION_SEARCH_WINDOW = 2; 
	

}
