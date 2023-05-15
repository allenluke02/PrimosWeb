package com.bi.serializer;

import com.bi.exception.BIAPIException;
import com.bi.utils.BIConstant;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DateDeserializer extends JsonDeserializer<Date> {
	
	@Override
	public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)throws IOException, JsonProcessingException {

		List<String> dateFormats = new ArrayList<>();
		
		dateFormats.add(BIConstant.DATE_FORMAT_YYYY_MM_DD_HH_MM_SS);
		dateFormats.add(BIConstant.DATE_FORMAT_YYYY_MM_DD_T_hh_mm_ss_sss_z);
		dateFormats.add(BIConstant.DATE_FORMAT_YYYY_MM_DD_T_hh_mm_ss_Z);
		dateFormats.add(BIConstant.DATE_FORMAT_YYYY_MM_DD);
		dateFormats.add(BIConstant.DATE_FORMAT_dd_MMM_yyyy_WITH_TIME);
		ParseException exception = null;

		for (String format : dateFormats) {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			String date = jsonParser.getText();
			try {
				return sdf.parse(date);
			} catch (ParseException e) {
				exception = e;
			}
		}

		throw new BIAPIException(HttpStatus.UNPROCESSABLE_ENTITY, exception.getMessage());
	}

}