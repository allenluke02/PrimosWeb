package com.bi.utils;

public class GMCAPIJSONInput {
	public static final String API_USER_CREATE="{\n" + 
			"\"otp\": \"97656\",\n" + 
			"\"password\": \"string\",\n" + 
			"\"phone\": \"8802205751\",\n" + 
			"\"isDoctor\":true,\n" + 
			"\"specialistIn\":{\"id\":1}\n" + 
			"}";
	public static final String API_resetPassword_CREATE="{\n" + 
			"\"otp\": \"81733\",\n" + 
			"\"password\": \"string123456\",\n" + 
			"\"loginName\": \"9891262969\"}";

	public static final String API_CLINIC_CREATE="{\n" + 
			"\"gstin\": \"gstn\",\n" + 
			"\"address\": {\n" + 
			"\"address1\": \"address 1\",\n" + 
			"\"address2\": null,\n" + 
			"\"city\": {\n" + 
			"\"id\": 1,\n" + 
			"\"state\": {\n" + 
			"\"country\": {\n" + 
			"\"id\": 1\n" + 
			"},\n" + 
			"\"id\": 1\n" + 
			"}\n" + 
			"},\n" + 
			"\"zip\": \"201301\"},\n" + 
			"\"email\": \"go@gomedssii.com\",\n" + 
			"\"clinicFacilityType\":\"SINGLE\",\n" + 
			"\"specialities\": [\n" + 
			"{\n" + 
			"\"id\": 1\n" + 
			"}\n" + 
			"],\n" + 
			"\"phone\": \"1236547899\",\n" + 
			"\"landLine\": \"011-123456\",\n" + 
			"\"description\": \"forties noida sec 62\",\n" + 
			"\"domainName\": \"goomedii\",\n" + 
			"\"logo\": null,\n" + 
			"\"name\": \"Clinic 1\",\n" + 
			"\"portalURL\": \"http://test\",\n" + 
			"\"registrationId\": \"rfdfl\"}";

	public static final String API_OTP_CREATE="{\n" + 
			"\"otpFor\": \"9958711322\"" + 
			"}";
	public static final String API_forgetPassword_CREATE="{\n" + 
			"\"otpFor\": \"9958711322\"}";
	public static final String API_healthRecord_CREATE="{\n" + 
			"\"otpFor\":\"9958711322\"}";
	public static final String API_changePassword_CREATE="{\n" + 
			"\"loginName\": \"9891262969\",\n" + 
			"\"newPassword\": \"string123457\",\n" + 
			"\"oldPassword\": \"string123456\"}";

	public static final String API_FACILITY_CREATE = "{\n" + 
			"\"address\": {\n" + 
			"\"address1\": \"address 1\",\n" + 
			"\"address2\": \"\",\n" + 
			"\"city\": {\n" + 
			"\"id\": 1\n" + 
			"},\n" + 
			"\"zip\": \"201301\"" + 
			"},\n" + 
			"\"description\": \"forties noida sec 62\",\n" + 
			"\"email\": \"forties@gmail.com\",\n" + 
			"\"name\": \"forties62\",\n" + 
			"\"phone\": \"7896541233\",\n" + 
			"\"landLine\": \"011-123456\",\n" +
			"\"specialities\": [\n" + 
			"{\n" + 
			"\"id\": 1\n" + 
			"}\n" + 
			"]\n" + 
			"}";

	public static final String API_STAFFSCHEDULE_CREATE="{\n" + 
			"\"startDate\": \"2018-07-30\",\n" + 
			"\"endDate\": \"2018-05-14\",\n" + 
			"\"scheduleTimings\": {\n" + 
			"\"MONDAY\": [\n" + 
			"{\n" + 
			"\"dayOfWeek\": \"MONDAY\",\n" + 
			"\"startTime\": \"09:00\",\n" + 
			"\"endTime\": \"14:00\",\n" + 
			"\"monthWeekAvailablities\": {\n" + 
			"      \"firstWeek\": true,\n" + 
			"      \"secondWeek\": false,\n" + 
			"      \"thirdWeek\": true,\n" + 
			"      \"fourthWeek\": false,\n" + 
			"      \"fifthWeek\": true\n" + 
			"    }\n" + 
			"  }\n" + 
			"]\n" + 
			"}\n" + 
			"}";
	public static final String API_STAFFSCHEDULE_UPDATE="{\n" + 
			"  \"startDate\": \"2018-07-14\",\n" + 
			"  \"endDate\": \"2018-10-30\",\n" + 
			"  \"scheduleTimings\": {\n" + 
			"    \"MONDAY\": [\n" + 
			"      {\n" + 
			"        \"id\": 1,\n" + 
			"        \"dayOfWeek\": \"MONDAY\",\n" + 
			"        \"startTime\": \"09:00\",\n" + 
			"        \"endTime\": \"19:00\",\n" + 
			"        \"monthWeekAvailablities\": {\n" + 
			"          \"id\": 1,\n" + 
			"          \"firstWeek\": true,\n" + 
			"          \"secondWeek\": false,\n" + 
			"          \"thirdWeek\": true,\n" + 
			"          \"fourthWeek\": false,\n" + 
			"          \"fifthWeek\": true\n" + 
			"        }\n" + 
			"      }\n" + 
			"    ]\n" + 
			"  }\n" + 
			"}";

	public static final String API_DOCTOR_CREATE= "{\n" + 
			"  \"title\": \"Mr\",\n" + 
			"  \"staffType\": \"DOCTOR\",\n" + 
			"  \"age\": \"25\",\n" + 
			"  \"firstName\": \"himanshu\",\n" + 
			"  \"lastName\": null,\n" + 
			"  \"email\": null,\n" + 
			"  \"gender\": \"M\",\n" + 
			"  \"practiceLicense\": \"derays\",\n" + 
			"  \"qualification\": null,\n" + 
			"  \"occupation\": null,\n" + 
			"  \"bloodGroup\": null,\n" + 
			"  \"signature\": \"abc\",\n" + 
			"  \"logo\": null,\n" + 
			"  \"credential\": \"surabhi13\",\n" + 
			"  \"specialistIn\": {\n" + 
			"    \"id\": 1,\n" + 
			"    \"name\": \"Clinical Laboratory Sciences\",\n" + 
			"    \"description\": null\n" + 
			"  },\n" + 
			"  \"physicalExams\": [\n" + 
			"    {\n" + 
			"      \"name\": \"Height\",\n" + 
			"      \"unit\": \"ft\",\n" + 
			"      \"id\": 1\n" + 
			"    },\n" + 
			"    {\n" + 
			"      \"id\": 2,\n" + 
			"      \"name\": \"BP\",\n" + 
			"      \"unit\": \"mmHg\"\n" + 
			"    }\n" + 
			"  ],\n" + 
			"  \"dob\": \"1993-10-21T18:30:00.000Z\",\n" + 
			"  \"phone\": \"9854496276\",\n" + 
			"  \"alterPhone\": null,\n" + 
			"  \"address\": null,\n" + 
			"  \"loginName\": \"himnsh42\",\n" + 
			"  \"document\": []\n" + 
			"}";

	public static final String API_DOCTOR_UPDATE="{\n" + 
			"  \"title\": \"Mr\",\n" + 
			"  \"firstName\": \"Kishan\",\n" + 
			"  \"lastName\": \"Tanwar\",\n" + 
			"  \"phone\": \"9654655952\",\n" + 
			"  \"alterPhone\": null,\n" + 
			"  \"address\": null,\n" + 
			"  \"gender\": \"M\",\n" + 
			"  \"email\": null,\n" + 
			"  \"bloodGroup\": null,\n" + 
			"  \"occupation\": null,\n" + 
			"  \"clinic\": {\n" + 
			"    \"id\": 1,\n" + 
			"    \"name\": \"Max Group\",\n" + 
			"    \"description\": \"This is max\",\n" + 
			"    \"email\": \"max@gmail.com\",\n" + 
			"    \"domainName\": \"max_1\",\n" + 
			"    \"phone\": \"9958487009\",\n" + 
			"    \"landLine\": null,\n" + 
			"    \"gstin\": \"\",\n" + 
			"    \"registrationId\": \"Clinic1\",\n" + 
			"    \"logo\": \"https://gmstorageqaind.blob.core.windows.net/gmcdocument/logo_265.png\",\n" + 
			"    \"portalURL\": \"www.max.com\",\n" + 
			"    \"address\": {},\n" + 
			"    \"document\": [],\n" + 
			"    \"clinicFacilityType\": \"MULTIPLE\",\n" + 
			"    \"specialities\": [\n" + 
			"      {\n" + 
			"        \"id\": 2,\n" + 
			"        \"name\": \"Clinical Neurophysiology\",\n" + 
			"        \"description\": null\n" + 
			"      },\n" + 
			"      {\n" + 
			"        \"id\": 4,\n" + 
			"        \"name\": \"Emergency Medicine\",\n" + 
			"        \"description\": null\n" + 
			"      }\n" + 
			"    ]\n" + 
			"  },\n" + 
			"  \"isDeleted\": false,\n" + 
			"  \"document\": [],\n" + 
			"  \"staffType\": \"DOCTOR\",\n" + 
			"  \"logo\": \"https://gmstorageqaind.blob.core.windows.net/gmcdocument/images%20(1)_60.jpg\",\n" + 
			"  \"user\": null,\n" + 
			"  \"age\": \"28\",\n" + 
			"  \"dob\": \"1990-08-13T18:30:00.000+0000\",\n" + 
			"  \"practiceLicense\": null,\n" + 
			"  \"qualification\": null,\n" + 
			"  \"specialistIn\": {\n" + 
			"    \"id\": 5,\n" + 
			"    \"name\": \"Endocrinology\",\n" + 
			"    \"description\": null\n" + 
			"  },\n" + 
			"  \"physicalExams\": [\n" + 
			"    \"BP\",\n" + 
			"    \"Height\",\n" + 
			"	\"Weight\"\n" + 
			"  ]\n" + 
			"}";


	public static final String API_STAFF_CREATE= "{\n" + 
			"\"staffType\": \"STAFF\",\n" + 
			"\"credential\":\"paswd12\",\n" + 
			"\"clinic\": {\n" + 
			"\"id\": 1\n" + 
			"},\n" + 
			"\"signature\": \"qwerty\",\n" + 
			"\"title\":\"Mr\",\n" + 
			"\"firstName\": \"Himanshu\",\n" + 
			"\"lastName\": \"Swarnakar\",\n" + 
			"\"phone\": \"9891262969\",\n" + 
			"\"alterPhone\": null,\n" + 
			"\"address\": {\n" + 
			"\"address1\": \"ghaziabad\",\n" + 
			"\"address2\": \"up\",\n" + 
			"\"city\": {\n" + 
			"\"id\": 1,\n" + 
			"\"state\": {\n" + 
			"\"id\": 1,\n" + 
			"\"country\": {\n" + 
			"\"id\": 1\n" + 
			"}\n" + 
			"}\n" + 
			"},\n" + 
			"\"zip\": \"201301\"" + 
			"},\n" + 
			"\"dob\": \"2012-03-12T18:30:00.000+0000\",\n" + 
			"\"age\":\"22\",\n" + 
			"\"gender\": \"M\",\n" + 
			"\"email\": \"himanshu@gmail.com\",\n" + 
			"\"bloodGroup\": \"A+\",\n" + 
			"\"logo\": \"Staff logo\",\n" +
			"\"occupation\": \"Doctor\"" + 
			"}";

	public static final String API_STAFF_UPDATE="{\n" + 
			"\"title\": \"Mr\",\n" + 
			"\"firstName\": \"Kishan\",\n" + 
			"\"lastName\": \"Tanwar\",\n" + 
			"\"age\":\"24\",\n" + 
			"\"phone\": \"9654655952\",\n" + 
			"\"alterPhone\": null,\n" + 
			"\"address\": {\n" + 
			"\"id\": 1,\n" + 
			"\"address1\": \"address 1\",\n" + 
			"\"address2\": null,\n" + 
			"\"city\": {\n" + 
			"\"id\": 1,\n" + 
			"\"name\": null\n" + 
			"},\n" + 
			"\"zip\": \"201301\"" + 
			"},\n" + 
			"\"dob\": \"2012-03-12T18:30:00.000+0000\",\n" + 
			"\"gender\": \"M\",\n" + 
			"\"email\": \"kishan.mca.du@gmail.com\",\n" + 
			"\"bloodGroup\": \"A+\",\n" + 
			"\"occupation\": \"Doctor\",\n" + 
			"\"isDeleted\": false,\n" + 
			"\"staffType\": \"STAFF\",\n" + 
			"\"credential\":\"passwd12\",\n" +
			"\"logo\": \"Staff logo\",\n" +
			"\"signature\": \"qwerty\"" + 
			"}";

	public static final String API_CLINIC_UPDATE="{\n" + 
			"\"gstin\": \"gstn\",\n" + 
			"\"address\": {\n" + 
			"\"address1\": \"address 1\",\n" + 
			"\"address2\": null,\n" + 
			"\"city\": {\n" + 
			"\"id\": 1,\n" + 
			"\"state\": {\n" + 
			"\"country\": {\n" + 
			"\"id\": 1\n" + 
			"},\n" + 
			"\"id\": 1\n" + 
			"}\n" + 
			"},\n" + 
			"\"zip\": \"201301\""+
			"},\n" + 
			"\"email\": \"gomedii@gomedii.com\",\n" + 
			"\"phone\": \"1236547899\",\n" + 
			"\"domainName\": \"gomedii.com\",\n" + 
			"\"logo\": null,\n" + 
			"\"name\": \"Clinic 1\",\n" + 
			"\"portalURL\": \"http://test\",\n" + 
			"\"registrationId\":  \"rfdfl\"" + 
			"}";

	public static final String API_FACILITY_UPDATE = "{\"name\": \"arya\",\n" + 
			"\"description\": \"abcas\",\n" + 
			"\"specialities\": [\n" + 
			"{\n" + 
			"\"id\": 1,\n" + 
			"\"name\": \"Clinical Laboratory Sciences\",\n" + 
			"\"description\": \"aaabc\"" + 
			"}\n" + 
			"],\n" + 
			"\"email\": \"xra@gmail.comm\",\n" + 
			"\"phone\": \"1234616600\",\n" + 
			"\"address\": {\n" + 
			"\"id\": 3,\n" + 
			"\"address1\": \"address 3311\",\n" + 
			"\"address2\": \"abvc\",\n" + 
			"\"city\": {\n" + 
			"\"id\": 1,\n" + 
			"\"name\": \"Noidaa\"" + 
			"},\n" + 
			"\"zip\": \"201301\"" + 
			"}\n" + 
			"}";

	public static final String API_PATIENT_CREATE = "{\n" + 
			"\"title\":\"Mr\",\n" + 
			"\"firstName\":\"himanshu\",\n" + 
			"\"lastName\":\"swarnakar\",\n" + 
			"\"phone\":\"9891262969\",\n" + 
			"\"alterPhone\": null,\n" + 
			"\"address\": {\n" + 
			"\"address1\": \"noida sec-15\",\n" + 
			"\"address2\": \"up\",\n" + 
			"\"city\": {\n" + 
			"\"id\": 1\n" + 
			"},\n" + 
			"\"zip\": \"201301\"" + 
			"},\n" + 
			"\"dob\":\"2012-03-12\",\n" + 
			"\"age\":\"25\",\n" + 
			"\"gender\":\"M\",\n" + 
			"\"email\":\"himanshu@gmail.com\",\n" + 
			"\"bloodGroup\":\"O–\",\n" + 
			"\"occupation\":\"BusinessMan\"" + 
			"}";
	public static final String API_APPOINTMENT_CREATE = "{\n" + 
			"\"appointmentDate\":\"2018-06-05\",\n" + 
			"\"appointmentStatus\":\"CONFIRMED\",\n" + 
			"\"startTime\":\"09:30\",\n" + 
			"\"patient\":{\"id\":2}\n" + 
			"}";
	public static final String API_APPOINTMENT_UPDATE="{\n" + 
			"  \"creationDate\": \"2018-10-23T05:27:29.000+0000\",\n" + 
			"  \"id\": 470,\n" + 
			"  \"encounter\": {\n" + 
			"    \"complaints\": [],\n" + 
			"    \"doctor\": {\n" + 
			"      \"id\": 69\n" + 
			"    },\n" + 
			"    \"diagnosis\": [],\n" + 
			"    \"notes\": \"\",\n" + 
			"    \"medicines\": [],\n" + 
			"    \"observations\": [],\n" + 
			"    \"investigations\": [],\n" + 
			"    \"physicalExams\": [\n" + 
			"      {\n" + 
			"        \"metaPhysicalExam\": {\n" + 
			"          \"id\": 1,\n" + 
			"          \"name\": \"Height\",\n" + 
			"          \"unit\": \"ft\"\n" + 
			"        },\n" + 
			"        \"observedValue\": \"1212\"\n" + 
			"      }\n" + 
			"    ],\n" + 
			"    \"document\": []\n" + 
			"  },\n" + 
			"  \"facility\": {\n" + 
			"    \"creationDate\": \"2018-10-22T12:05:40.000+0000\",\n" + 
			"    \"id\": 15\n" + 
			"  },\n" + 
			"  \"doctor\": {\n" + 
			"    \"creationDate\": \"2018-10-22T12:01:36.000+0000\",\n" + 
			"    \"id\": 69,\n" + 
			"    \"title\": \"Mr\",\n" + 
			"    \"firstName\": \"bharat\",\n" + 
			"    \"lastName\": null,\n" + 
			"    \"phone\": \"9995554440\",\n" + 
			"    \"alterPhone\": null,\n" + 
			"    \"gender\": \"M\",\n" + 
			"    \"email\": null,\n" + 
			"    \"bloodGroup\": null,\n" + 
			"    \"occupation\": null,\n" + 
			"    \"clinic\": {\n" + 
			"      \"creationDate\": \"2018-10-22T12:05:40.000+0000\",\n" + 
			"      \"document\": []\n" + 
			"    },\n" + 
			"    \"isDeleted\": false,\n" + 
			"    \"document\": [],\n" + 
			"    \"age\": null,\n" + 
			"    \"dob\": null,\n" + 
			"    \"loginName\": null\n" + 
			"  },\n" + 
			"  \"appointmentDate\": \"2018-10-23\",\n" + 
			"  \"appointmentStatus\": \"INPROGRESS\",\n" + 
			"  \"startTime\": \"11:00\",\n" + 
			"  \"endTime\": null,\n" + 
			"  \"patient\": {\n" + 
			"    \"creationDate\": \"2018-10-22T12:12:25.000+0000\",\n" + 
			"    \"id\": 72,\n" + 
			"    \"title\": \"Mr\",\n" + 
			"    \"firstName\": \"patient\",\n" + 
			"    \"lastName\": \"one\",\n" + 
			"    \"phone\": \"3344556622\",\n" + 
			"    \"alterPhone\": null,\n" + 
			"    \"gender\": \"F\",\n" + 
			"    \"email\": null,\n" + 
			"    \"bloodGroup\": null,\n" + 
			"    \"occupation\": null,\n" + 
			"    \"clinic\": {\n" + 
			"      \"creationDate\": \"2018-10-22T12:05:40.000+0000\",\n" + 
			"      \"document\": []\n" + 
			"    },\n" + 
			"    \"isDeleted\": false,\n" + 
			"    \"document\": [],\n" + 
			"    \"uhid\": \"1065-1001\",\n" + 
			"    \"age\": \"23\",\n" + 
			"    \"dob\": \"1995-10-21T18:30:00.000+0000\"\n" + 
			"  },\n" + 
			"  \"appointmentType\": \"OFFLINE\",\n" + 
			"  \"onlineId\": null\n" + 
			"}";
	
	public static final String API_META_UPDATE="{\n" + 
			"  \n" + 
			"  \"name\": \"abcaa\",\n" + 
			"  \"description\": \"abcaa\"" + 
			"}";
	
	public static final String API_META_CREATE ="{\n" + 
			"\"name\": \"abca\",\n" + 
			"\"description\": \"abc\"" + 
			"}";
	
	public static final String API_ENCOUNTER_UPDATE="{\n" + 
			"\"complaints\": [" + 
			"\"payment fail\"" + 
			"],\n" + 
			"\"doctor\": {\n" + 
			"\"id\":1\n" + 
			"},\n" + 
			"\"initiatedBy\": {\n" + 
			"\"id\":1\n" + 
			"},\n" + 
			"\"startTime\": null,\n" + 
			"\"endTime\": null,\n" + 
			"\"physicalExams\": [\n" + 
			"{\n" + 
			"\"name\": \"BP\",\n" + 
			"\"observedValue\": 120,\n" + 
			"\"Observation\": \"sdfg\",\n" + 
			"\"Impression\": \"acb\",\n" + 
			"\"time\": \"2010-11-30T18:30:00.000+0000\"" + 
			"}\n" + 
			"],\n" + 
			"\"diagnosis\": [\n" + 
			"\"hello\"" + 
			"],\n" + 
			"\"notes\":\"abc\",\n" + 
			"\"medicines\": [\n" + 
			"{\n" + 
			"\"name\": \"abc\",\n" + 
			"\"frequency\":\"abc\", \n" + 
			"\"schedule\": [\n" + 
			"\"abc\"" + 
			"],\n" + 
			"\"quantity\":\"abc\",\n" + 
			"\"time\":[\n" + 
			"\"iday\"" + 
			"],\n" + 
			"\"duration\":\"abca\"\n" + 
			"}\n" + 
			"]\n" + 
			"}";
	
	public static final String API_ENCOUNTER_CREATE="{\n" + 
			"\"complaints\": [\n" + 
			"\"payment fail\"" + 
			"]\n" + 
			",\"doctor\": {\n" + 
			"\"id\":1\n" + 
			"},\n" + 
			"\"startTime\": null,\n" + 
			"\"endTime\": null,\n" + 
			"\"physicalExams\": [\n" + 
			"{\n" + 
			"\"name\": \"BP\",\n" + 
			"\"observedValue\": 120,\n" + 
			"\"Observation\": \"sdfg\",\n" + 
			"\"Impression\": \"acb\",\n" + 
			"\"time\": \"2010-11-30T18:30:00.000+0000\"" + 
			"}\n" + 
			"],\n" + 
			"\"diagnosis\": [" + 
			"\"hello\"" + 
			"],\n" + 
			"\"notes\":\"abc\",\n" + 
			"\"medicines\": [\n" + 
			"{\n" + 
			"\"name\": \"abc\",\n" + 
			"\"frequency\":\"abc\", \n" + 
			"\"schedule\": [" + 
			"\"abc\"" + 
			"],\n" + 
			"\"quantity\":\"abc\",\n" + 
			"\"time\":[\n" + 
			"\"1day\"\n" + 
			"],\n" + 
			"\"duration\":\"abc\"\n" + 
			"}\n" + 
			"]\n" + 
			"}";
	public static final String API_PUBLIC_DOCTOR_PROFILE_CREATE="{\n" + 
			"\"name\": \"Dr. Kishan Tanwar\",\n" + 
			"\"description\": \"Specialist\",\n" + 
			"\"header1\": \"A1\",\n" + 
			"\"header2\": \"a2\",\n" + 
			"\"address\": \"New Delhi\",\n" + 
			"\"availableDay\": \"MON-TUE\",\n" + 
			"\"designation\": \"CMO\",\n" + 
			"\"isPartner\": \"false\",\n" + 
			"\"speciality\": \"ENT\",\n" + 
			"\"email\":\"arya.singh@gomedii.com\",\n" + 
			"\"experience\": \"5\",\n" + 
			"\"gender\": \"M\",\n" + 
			"\"qualification\": \"mbbs\",\n" + 
			"\"phone\": \"7725067542\",\n" + 
			"\"consultationCharge\": \"5000\",\n" + 
			"\"consultaionsProvided\": [\n" + 
			"\"Monday\",\n" + 
			"\"Delhi\"\n" + 
			"],\n" + 
			"\"timing\": \"14:00-16:00\",\n" + 
			"\"clinics\": [\n" + 
			"{\n" + 
			"\"docId\": 1\n" + 
			"}\n" + 
			"],\n" + 
			"\"logo\": [\n" + 
			"{\n" + 
			"\"documentDescription\": \"Dr2\",\n" + 
			"\"documentType\": \"image/jpeg\",\n" + 
			"\"URI\": \"https://gmstorageqaind.blob.core.windows.net/gmcdocument/dr_132.jpg\",\n" + 
			"\"GMCURI\": \"/downloadFile/132\",\n" + 
			"\"fileName\": \"dr_132.jpg\"\n" + 
			"}\n" + 
			"],\n" + 
			"\"bannerImage\": [\n" + 
			"{\n" + 
			"\"documentDescription\": \"Dr\",\n" + 
			"\"documentType\": \"image/jpeg\",\n" + 
			"\"URI\": \"https://gmstorageqaind.blob.core.windows.net/gmcdocument/2_131.jpg\",\n" + 
			"\"GMCURI\": \"/downloadFile/131\",\n" + 
			"\"fileName\": \"2_131.jpg\"\n" + 
			"}\n" + 
			"],\n" + 
			"\"gallery\": [\n" + 
			"{\n" + 
			"\"documentDescription\": \"Dr2\",\n" + 
			"\"documentType\": \"image/jpeg\",\n" + 
			"\"URI\": \"https://gmstorageqaind.blob.core.windows.net/gmcdocument/dr_132.jpg\",\n" + 
			"\"GMCURI\": \"/downloadFile/132\",\n" + 
			"\"fileName\": \"?dr_132.jpg\"\n" + 
			"}\n" + 
			"],\n" + 
			"\"info\": [\n" + 
			"{\n" + 
			"\"details\": \"test\",\n" + 
			"\"header\": \"Hello\",\n" + 
			"\"additionalInfoDocument\": [\n" + 
			"{\n" + 
			"\"documentDescription\": \"test2\",\n" + 
			"\"documentType\": \"image/jpeg\",\n" + 
			"\"URI\": \"https://gmstorageqaind.blob.core.windows.net/gmcdocument/2_133.jpg\",\n" + 
			"\"GMCURI\": \"/downloadFile/133\",\n" + 
			"\"fileName\": \"2_133.jpg\",\n" + 
			"\"additionalInfoDocumentType\":\"IMAGE\",\n" + 
			"\"entityType\": \"aa\"\n" + 
			"}\n" + 
			"]\n" + 
			"}\n" + 
			"],\n" + 
			"\"news\":[\n" + 
			"  {\n" + 
			"    \"text\":\"patient\",\n" + 
			"\"pubDocNewsDocument\":[\n" + 
			"    {\n" + 
			"    \"documentDescription\": \"document\",\n" + 
			"\"documentType\": \"image/jpeg\",\n" + 
			"\"URI\": \"https://gmstorageqaind.blob.core.windows.net/gmcdocument/2_133.jpg\",\n" + 
			"\"GMCURI\": \"/downloadFile/133\",\n" + 
			"\"fileName\": \"2_133.jpg\",\n" + 
			"\"additionalInfoDocumentType\":\"IMAGE\",\n" + 
			"\"entityType\": \"aa\"\n" + 
			"  }\n" + 
			"  ]\n" + 
			"}\n" + 
			"],\n" + 
			"\"profileImage\": [\n" + 
			"{\n" + 
			"\"documentDescription\": \"Dr\",\n" + 
			"\"documentType\": \"image/jpeg\",\n" + 
			"\"URI\": \"https://gmstorageqaind.blob.core.windows.net/gmcdocument/2_131.jpg\",\n" + 
			"\"GMCURI\": \"/downloadFile/131\",\n" + 
			"\"fileName\": \"2_131.jpg\"\n" + 
			"\n" + 
			"}\n" + 
			"]\n" + 
			"}";
	
	public static final String API_PLAN_CREATE="{\n" + 
			"\"name\" : \"Gold\",\n" + 
			"\"description\":\"2 months\",\n" + 
			"\"rate\":\"2000\",\n" + 
			"\"startDate\":\"2018-09-05\",\n" + 
			"\"duration\":\"3\",\n" + 
			"\"durationUnit\":\"Months\",\n" + 
			"\"offer\":\"50% Discount\",\n" + 
			"\"isActive\":\"true\",\n" + 
			"\"isApproved\":\"Y\",\n" + 
			"\"approvedBy\":\"gomedii\",\n" + 
			"\"termsConditions\":\"Gomedii Terms Conditions\",\n" + 
			"\"features\":[\"Medical Store\", \"Gomedii\"]\n" + 
			"}";
	public static final String API_ONLINE_APPOINTMENT_CREATE = "{\r\n" + 
			"\"appointmentDate\":\"2018-12-05\",\r\n" + 
			"\"appointmentStatus\":\"CONFIRMED\",\r\n" + 
			"\"startTime\":\"18:30\",\r\n" + 
			"\"patientFistName\":\"himanshiiiii\",\r\n" + 
			"\"phone\":\"9654655952\",\r\n" + 
			"\"email\":\"abc@gmail.com\",\r\n" + 
			"\"gmToken\":\"1234567890\",\r\n" + 
			"\"otp\":\"\"\r\n" + 
			"}";

	
	public static final String API_SUBSCRIPTION_CREATE="{\n" + 
			"\"subscriptionId\":\"101\",\n" + 
			"\"clinic\":{\"id\":1},\n" + 
			"\"subscribedPlan\":{\"id\":1},\n" + 
			"\"startDate\":\"2018-10-05\",\n" + 
			"\"isSubscriptionActive\":true\n" + 
			"}";
	
	public static final String API_SUBSCRIPTION_UPDATE="{\n" + 
			"\"subscriptionId\":\"22\",\n" + 
			"\"clinic\":{\"id\":2},\n" + 
			"\"subscribedPlan\":{\"id\":2},\n" + 
			"\"startDate\":\"2018-11-05\",\n" + 
			"\"isSubscriptionActive\":false\n" + 
			"}";
	public static final String API_EMAIL_TEMPLATE_CREATE="{\n" + 
			"  \"eventGenerator\": {\n" + 
			"    \"generatorType\": \"SYSTEM\",\n" + 
			"    \"event\": {\n" + 
			"      \"id\": 1,\n" + 
			"      \"eventName\": \"ONLINE_APPOINTMENT\"\n" + 
			"    }\n" + 
			"  },\n" + 
			"  \"template\": {\n" + 
			"    \"templateData\": \"vbnvbnvbn\",\n" + 
			"    \"templateName\": \"  nbvbnvbnvb\"\n" + 
			"  },\n" + 
			"  \"type\": \"DOCTOR\",\n" + 
			"  \"subjectLine\": \"vbnvbn\"\n" + 
			"}"	;
	public static final String API_TEMPLATE_CREATE="{\n" + 
			"  \"eventGenerator\": {\n" + 
			"    \"generatorType\": \"SYSTEM\",\n" + 
			"    \"event\": {\n" + 
			"      \"id\": 1,\n" + 
			"      \"eventName\": \"ONLINE_APPOINTMENT\"\n" + 
			"    }\n" + 
			"  },\n" + 
			"  \"template\": {\n" + 
			"    \"templateData\": \"vbnvbnvbn\",\n" + 
			"    \"templateName\": \"  nbvbnvbnvb\"\n" + 
			"  },\n" + 
			"  \"type\": \"DOCTOR\",\n" + 
			"  \"subjectLine\": \"vbnvbn\"\n" + 
			"}"	;
	
	public static final String API_PRINTPREFERENCE_CREATE="{\r\n" + 
			" \"print\":\"ABC\",\r\n" + 
			" \"paperSize\":\"10\",\r\n" + 
			" \"pageOrientation\":\"LandScape\",\r\n" + 
			" \"scale\":\"Normal\",\r\n" + 
			" \"margin\":\"Normal\",\r\n" + 
			" \"formatting\":\r\n" + 
			"   {\r\n" + 
			"   \"showGridlines\":true,\r\n" + 
			"   \"showNotes\":true,\r\n" + 
			"   \"pageOrder\":\"Over_then_down\",\r\n" + 
			"   \"alignmentHorizontal\":\"Right\",\r\n" + 
			"   \"alignmentVertical\":\"Top\"\r\n" + 
			" },\r\n" + 
			" \"doctor\":\r\n" + 
			"   {\r\n" + 
			"     \"id\":1,\r\n" + 
			"     \"name\":\"arya\"\r\n" + 
			"   },\r\n" + 
			"   \"headersAndFooters\":\r\n" + 
			"     {\r\n" + 
			"     \"pageNumber\":true,\r\n" + 
			"     \"sheetName\":true,\r\n" + 
			"     \"workBookTitle\":true,\r\n" + 
			"     \"currentDatePr\":true,\r\n" + 
			"     \"currentTimePr\":false\r\n" + 
			"   },\r\n" + 
			"   \"marginTop\":\"Top\",\r\n" + 
			"   \"marginRight\":\"Right\",\r\n" + 
			"   \"marginBottom\":\"Bottom\",\r\n" + 
			"   \"marginLeft\":\"Left\",\r\n" + 
			"   \"scalePercentage\":\"120\"\r\n" + 
			"}";
	public static final String API_PRINTPREFERENCE_UPDATE="{\r\n" + 
			"  \"id\": 3,\r\n" + 
			"  \"print\": \"ABC\",\r\n" + 
			"  \"paperSize\": \"10\",\r\n" + 
			"  \"pageOrientation\": \"LandScape\",\r\n" + 
			"  \"scale\": \"Normal\",\r\n" + 
			"  \"margin\": \"Normal\",\r\n" + 
			"  \"formatting\": {\r\n" + 
			"    \"id\": 3,\r\n" + 
			"    \"showGridlines\": true,\r\n" + 
			"    \"showNotes\": true,\r\n" + 
			"    \"pageOrder\": \"Over_then_down\",\r\n" + 
			"    \"alignmentHorizontal\": \"Right\",\r\n" + 
			"    \"alignmentVertical\": \"Top\"\r\n" + 
			"  },\r\n" + 
			"  \"doctor\": {\r\n" + 
			"    \"id\": 1,\r\n" + 
			"    \"firstName\": \"7725067542\"\r\n" + 
			"  },\r\n" + 
			"  \"headersAndFooters\": {\r\n" + 
			"    \"id\": 3,\r\n" + 
			"    \"pageNumber\": true,\r\n" + 
			"    \"workBookTitle\": true,\r\n" + 
			"    \"sheetName\": true,\r\n" + 
			"    \"currentDatePr\": true,\r\n" + 
			"    \"currentTimePr\": false\r\n" + 
			"  },\r\n" + 
			"  \"marginTop\": \"Top\",\r\n" + 
			"  \"marginRight\": \"Right\",\r\n" + 
			"  \"marginBottom\": \"Bottom\",\r\n" + 
			"  \"marginLeft\": \"Left\",\r\n" + 
			"  \"scalePercentage\": \"120\"\r\n" + 
			"}";
	}