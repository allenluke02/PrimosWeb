package com.gomedii.clinic.components;

import java.io.IOException;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.rules.ErrorCollector;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gomedii.clinic.components.TestConstant.ValidationAttributes;

//@Component
public class TestUtil {
	
	private void populateObject(Object object, Class<?> clazz, String[] fieldsHierarchy, ValidationAttributes[] validationAttributes, ValidationAttributes attributeToTest) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, InstantiationException {
		Object currentValue = object;
		for(int i =0;i<fieldsHierarchy.length;i++)
		{
			Field field = getField(clazz,fieldsHierarchy[i]); 

			field.setAccessible(true);
			Object value = field.get(currentValue);
			clazz = field.getType();
			if(value == null) {
				value = getValue(clazz, validationAttributes, (i == (fieldsHierarchy.length -1)) ? attributeToTest : null);
				field.set(currentValue, value);
			}
			currentValue = value;
		}
	/*	for (String fieldName : fieldsHierarchy) {

			Field field = getField(clazz,fieldName); 

			field.setAccessible(true);
			Object value = field.get(currentValue);
			clazz = field.getType();
			if(value == null) {
				value = getValue(clazz, validationAttributes, attributeToTest);
				field.set(currentValue, value);
			}
			currentValue = value;
		}*/
	}
	
	public <T> T prepareRequestObject(Class<T> clazz, Map<String,ValidationAttributes[]> modelValidatedFields, String fieldToValidate, ValidationAttributes attributeToTest) 
	{
		T model = null;
		try {
			model = clazz.newInstance();
			for(Entry<String, ValidationAttributes[]> fieldKey: modelValidatedFields.entrySet()) {
				String key = fieldKey.getKey(); 
				if(attributeToTest.getValidation().isNotNull() && key.startsWith(fieldToValidate)) {
					continue;
				}
				String[] fieldsHierarchy = key.split("\\.");
 				if(!key.equalsIgnoreCase(fieldToValidate)) {
					populateObject(model, clazz, fieldsHierarchy, fieldKey.getValue(), null);
				} else {
					populateObject(model, clazz, fieldsHierarchy, fieldKey.getValue(), attributeToTest);
				}
			}
					
//			Collection<Field> fields = getFields(clazz);
//			
//			for (Field field : fields) {
//				String data = map.get(field.getName());
//				if(data != null)
//				{
//					field.setAccessible(true);
//					if(key == null)
//					{
//						key = field.getName();
//					}
//					Package packageName = field.getType().getPackage();
//
//					if(packageName.getName().equals("com.clinic.model") || packageName.getName().equals("com.gm.clinic.vo") )
//					{
//						String data2 = map.get(key);
//						if(data != null)
//						{
//							field.set(model,prepareRequestObject(field.getType(),map,key,fieldToValidate));
//						}
//
//					}
//					else
//					{
//						field.set(model,getValue(field.getType(),key,fieldToValidate));
//					}
//				}
//
//			}
			
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return model;
		
	}
	
	
	private Object getValue(Class<?> type, ValidationAttributes[] validationAttributes, ValidationAttributes attributeToTest) throws InstantiationException, IllegalAccessException
	{
		if (type.isAssignableFrom(Integer.class)) {
			return attributeToTest == null ? convertToInteger(getValidNumericStringValue(validationAttributes)) : convertToInteger(getInvalidNumericValue(attributeToTest));
		} else if (type.isAssignableFrom(Long.class)) {
			return attributeToTest == null ? convertToLong(getValidNumericStringValue(validationAttributes)) : convertToLong(getInvalidNumericValue(attributeToTest));
		} else if (type.isAssignableFrom(Float.class)) {
			return attributeToTest == null ? convertToFloat(getValidNumericStringValue(validationAttributes)) : convertToFloat(getInvalidNumericValue(attributeToTest));
		} else if (type.isAssignableFrom(Double.class)) {
			return attributeToTest == null ? convertToDouble(getValidNumericStringValue(validationAttributes)) : convertToDouble(getInvalidNumericValue(attributeToTest));
		}/* else if (type.isAssignableFrom(Boolean.class)) {
			return false;
		}*/ else if (type.isAssignableFrom(String.class)) {
			return attributeToTest == null ? getValidStringValue(validationAttributes) : getInvalidStringValue(attributeToTest);
		} 
		else if(type.isAssignableFrom(Date.class))
		{
			return attributeToTest == null ? getValidDate(validationAttributes) :getInValidDate(attributeToTest);
		}
		else 
		{
			try
			{
				if(type.isAssignableFrom(type.asSubclass(Enum.class)))
				{
					return attributeToTest == null ? getEnumValue(type) : null;
				}
			}
			catch (Exception e)
			{

			}
			return attributeToTest == null ? type.newInstance() : null;
		}
	}
	
	private Object getInValidDate(ValidationAttributes attributeToTest) {

		Date value = null;
			if(attributeToTest.getValidation().isPastDateValidation()) 
			{
				 DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		            Date myDate = new Date(System.currentTimeMillis());
		 
		             value = new Date(myDate.getTime() + (10 * 24 * 60 * 60 * 1000));
				
			}
			return value;
		
	}

	private Object getValidDate(ValidationAttributes[] validationAttributes) {
		
	
		Date value = null;
		for (int i = 0; i < validationAttributes.length; i++) {
			if(validationAttributes[i].getValidation().isPastDateValidation()) {
				//value = "abc";
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Date myDate = new Date(System.currentTimeMillis());

				value = new Date(myDate.getTime() - (10 * 24 * 60 * 60 * 1000));
				System.out.println("PastDate:"+value);

			}
			else if(validationAttributes[i].getValidation().isNotNull())
			{
				return new Date();

			}
		}
		return value;
	}

	private Enum<?> getEnumValue(Class<?> type)
	{
		Enum<?>[] enums = (Enum<?>[]) type.getEnumConstants();
		if(enums != null && enums.length > 0)
		{
			return enums[0];
		}
		else
		{
			return null;
		}
	}
	private Long convertToLong(String numericString) {
		return numericString == null ? null : new Long(numericString);
	}
	
	private Integer convertToInteger(String numericString) {
		return numericString == null ? null : new Integer(numericString);
	}
	private Float convertToFloat(String numericString) {
		return numericString == null ? null : new Float(numericString);
	}
	
	private Double convertToDouble(String numericString) {
		return numericString == null ? null : new Double(numericString);
	}
	
	private String getInvalidStringValue(ValidationAttributes attributeToTest) {
		String value = null;
		if(attributeToTest.getValidation().isNotEmpty()) {
			value = "";
		} else  if(attributeToTest.getValidation().isNotNull()) {
			value = null;
		} else  if(attributeToTest.getValidation().isMin()) {
			char[] chars = new char[attributeToTest.getValidation().length() - 1];
			for (int j = 0; j < chars.length; j++) {
				chars[j] = 'a';
			}
			value = new String(chars);
		} else  if(attributeToTest.getValidation().isMax()) {
			char[] chars = new char[attributeToTest.getValidation().length() + 1];
			for (int j = 0; j < chars.length; j++) {
				chars[j] = 'a';
			}
			value = new String(chars);
		}
		else if(attributeToTest.getValidation().isEmailValidation())
		{
			value = "abcgomedi.com";
		}
		return value;
	}
	
	
	private String getInvalidNumericValue(ValidationAttributes attributeToTest) {
		String value = null;
		if(attributeToTest.getValidation().isNotNull()) {
			value = null;
		} else  if(attributeToTest.getValidation().isMin()) {
			char[] chars = new char[attributeToTest.getValidation().length() - 1];
			for (int j = 0; j < chars.length; j++) {
				chars[j] = '1';
			}
			value = new String(chars);
		} else  if(attributeToTest.getValidation().isMax()) {
			char[] chars = new char[attributeToTest.getValidation().length() + 1];
			for (int j = 0; j < chars.length; j++) {
				chars[j] = '1';
			}
			value = new String(chars);
		}
		return value;
	}
	
	private String getValidStringValue(ValidationAttributes[] validationAttributes) {
		String value = null;
		for (int i = 0; i < validationAttributes.length; i++) {
			if(validationAttributes[i].getValidation().isNotEmpty() && (value == null || "".equals(value))) {
				value = "abc";
			} else  if(validationAttributes[i].getValidation().isNotNull() && (value == null)) {
				value = "abc";
			} else  if(validationAttributes[i].getValidation().isMin() || validationAttributes[i].getValidation().isMax()) {
				char[] chars = new char[validationAttributes[i].getValidation().length()];
				for (int j = 0; j < chars.length; j++) {
					chars[j] = 'a';
				}
				value = new String(chars);
			}
			else if(validationAttributes[i].getValidation().isEmailValidation())
			{
				value = "abc@gomedi.com";

			}
		}
		return value;
	}
	
	private String getValidNumericStringValue(ValidationAttributes[] validationAttributes) {
		String value = null;
		for (int i = 0; i < validationAttributes.length; i++) {
			if(validationAttributes[i].getValidation().isNotNull() && (value == null)) {
				value = "111";
			}  else  if(validationAttributes[i].getValidation().isMin() || validationAttributes[i].getValidation().isMax()) {
				char[] chars = new char[validationAttributes[i].getValidation().length()];
				for (int j = 0; j < chars.length; j++) {
					chars[j] = '1';
				}
				value = new String(chars);
			}
		}
		return value;
	}
	
	
	public static Map prepareValidationJsonMap(String jsonRequest) 
	{
		ObjectMapper mapper = new ObjectMapper();
		Map obj = null;
		try {
			obj = mapper.readValue(jsonRequest, new TypeReference<Map<String, String>>(){});
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;
		
	}
	
	
	/**
	   * Get all fields of a class.
	   * 
	   * @param clazz The class.
	   * @return All fields of a class.
	   */
	  public static Collection<Field> getFields(Class<?> clazz) {
//	    if (log.isDebugEnabled()) {
	  //    log.debug("getFields(Class<?>) - start");
	    //}

	    Map<String, Field> fields = new HashMap<String, Field>();
	    while (clazz != null) {
	      for (Field field : clazz.getDeclaredFields()) {
	        if (!fields.containsKey(field.getName())) {
	          fields.put(field.getName(), field);
	        }
	      }

	      clazz = clazz.getSuperclass();
	    }

	    Collection<Field> returnCollection = fields.values();
	  //  if (log.isDebugEnabled()) {
	    //  log.debug("getFields(Class<?>) - end");
	  //  }
	    return returnCollection;
	  }
	  

/*	  public static void main(String[] args) throws JsonProcessingException {
		  
//		  long i = v;
//		  System.out.println(i);
		  ObjectMapper mapper = new ObjectMapper();
		  User user = new TestUtil().prepareRequestObject(User.class, TestConstant.USER_REGISTER_API_MAP, "phone", new ValidationAttributes("err.user.phone.empty", new MinValidation(10)));
		  System.out.println(mapper.writeValueAsString(user));
		  user = new TestUtil().prepareRequestObject(User.class, TestConstant.USER_REGISTER_API_MAP, "specialistIn.id", new ValidationAttributes("err.user.spaciality.empty", new NotNullValidation()));
		  System.out.println(mapper.writeValueAsString(user));
		  
	}*/
	  public void testData(String apiName, int expextedStutusCode,String expectedMsg,ResponseEntity<String> responseEntity,String key,ErrorCollector collecter) throws JsonParseException, JsonMappingException, IOException
		{
			int actualValue = responseEntity.getStatusCodeValue();

			ObjectMapper mapper = new ObjectMapper();
			ErrorResponse errorResponse = mapper.readValue(responseEntity.getBody(), ErrorResponse.class);
			String message = null;
			if(errorResponse != null && errorResponse.getErrorMessages() != null && !errorResponse.getErrorMessages().isEmpty())
			{
				message = errorResponse.getErrorMessages().get(0);
			}
			collecter.checkThat(apiName + " " + key + " status code", expextedStutusCode, org.hamcrest.core.IsEqual.equalTo(actualValue));
			collecter.checkThat(apiName + " " + key + " body", expectedMsg, org.hamcrest.core.IsEqual.equalTo(message));
		}
	  
	  
	  public  Field getField(Class<?> clazz, String name) {
		    Field field = null;
		    while (clazz != null && field == null) {
		        try {
		            field = clazz.getDeclaredField(name);
		        } catch (Exception e) {
		        }
		        clazz = clazz.getSuperclass();
		    }
		    return field;
		}
	
}
