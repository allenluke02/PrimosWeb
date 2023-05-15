package com.bi.utils;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;

public class RepositoryUtil {
	
	private static final int pageSize = 2;
	
	public static PageRequest getPages(int pageNumber, int pageSize, String ascDesc, String ...orderBy)
    {
		@SuppressWarnings("deprecation")
		PageRequest request = PageRequest.of(pageNumber,pageSize,getAscDesc(ascDesc),orderBy);
        return request;
    }
	
	public static PageRequest getPages(int pageNumber,String ascDesc, String ...orderBy)
    {
		@SuppressWarnings("deprecation")
		PageRequest request = PageRequest.of(pageNumber,pageSize,getAscDesc(ascDesc),orderBy);
        return request;
    }
	
//	public static PageRequest getPages(int pageNumber, String ...orderBy)
//    {
//		return getPages(pageNumber, GMConstant.ASC,orderBy);
//    }
	
	private static Direction getAscDesc(String ascDescStr){
		if(ascDescStr.equalsIgnoreCase(BIConstant.ASC)){
			return Direction.ASC;
		}
		else if(ascDescStr.equalsIgnoreCase(BIConstant.DESC)){
			return Direction.DESC;
		}
		else{
			return Direction.ASC;
		}
	}
	
	public static <T> List<T> convertToList(Iterable<T> items) {
		List<T> itemList = new ArrayList<T>();
		for (T st : items) {
			itemList.add(st);
		}

		return itemList;

	}

}
