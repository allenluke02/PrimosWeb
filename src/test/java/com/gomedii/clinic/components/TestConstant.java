package com.gomedii.clinic.components;

import java.util.HashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public class TestConstant
{
	
	
	
	public interface Validation {
		boolean isLength();
		boolean isNotNull();
		boolean isNotEmpty();
		boolean isMin();
		boolean isMax();
		int length();
		boolean isEmailValidation();
		boolean isPastDateValidation();
	}
	
	public static class MaxValidation extends MinMaxValidation {

		public MaxValidation(int length) {
			super(length);
		}
		
		@Override
		public boolean isMax() {
			// TODO Auto-generated method stub
			return true;
		}

		@Override
		public boolean isEmailValidation() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean isPastDateValidation() {
			// TODO Auto-generated method stub
			return false;
		}
		
	}
	
	public static class MinValidation extends MinMaxValidation {

		public MinValidation(int length) {
			super(length);
		}
		
		@Override
		public boolean isMin() {
			// TODO Auto-generated method stub
			return true;
		}

		@Override
		public boolean isEmailValidation() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean isPastDateValidation() {
			// TODO Auto-generated method stub
			return false;
		}
		
	}
	
	public static abstract class MinMaxValidation implements Validation {
		
		int length;

		public MinMaxValidation(int length) {
			super();
			this.length = length;
		}


		@Override
		public boolean isLength() {
			return true;
		}

		@Override
		public boolean isNotNull() {
			// TODO Auto-generated method stub
			return false;
		}


		@Override
		public boolean isNotEmpty() {
			// TODO Auto-generated method stub
			return false;
		}


		@Override
		public boolean isMin() {
			// TODO Auto-generated method stub
			return false;
		}


		@Override
		public boolean isMax() {
			// TODO Auto-generated method stub
			return false;
		}


		@Override
		public int length() {
			// TODO Auto-generated method stub
			return length;
		}
		
	}
	
	
	public static class NotNullValidation implements Validation {

		@Override
		public boolean isLength() {
			return false;
		}

		@Override
		public boolean isNotNull() {
			// TODO Auto-generated method stub
			return true;
		}

		@Override
		public boolean isNotEmpty() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean isMin() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean isMax() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public int length() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public boolean isEmailValidation() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean isPastDateValidation() {
			// TODO Auto-generated method stub
			return false;
		}
		
	}
	
	public static class NotEmptyValidation implements Validation {

		@Override
		public boolean isLength() {
			return false;
		}


		@Override
		public boolean isNotNull() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean isNotEmpty() {
			// TODO Auto-generated method stub
			return true;
		}

		@Override
		public boolean isMin() {
			// TODO Auto-generated method stub
			return false;
		} 

		@Override
		public boolean isMax() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public int length() {
			// TODO Auto-generated method stub
			return 0;
		}


		@Override
		public boolean isEmailValidation() {
			// TODO Auto-generated method stub
			return false;
		}


		@Override
		public boolean isPastDateValidation() {
			// TODO Auto-generated method stub
			return false;
		}
		
	}
	
	public static class EmailValidation implements Validation
	{

		@Override
		public boolean isLength() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean isNotNull() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean isNotEmpty() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean isMin() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean isMax() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public int length() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public boolean isEmailValidation() {
			// TODO Auto-generated method stub
			return true;
		}

		@Override
		public boolean isPastDateValidation() {
			// TODO Auto-generated method stub
			return false;
		}
		
	}
	
	public static class PastDateValidation implements Validation{

		@Override
		public boolean isLength() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean isNotNull() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean isNotEmpty() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean isMin() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean isMax() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public int length() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public boolean isEmailValidation() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean isPastDateValidation() {
			// TODO Auto-generated method stub
			return true;
		}

		
		
	}
	@Getter@Setter
	public static class ValidationAttributes {
		private String dataType;
		private String errorKey;
		private Validation validation;
		public ValidationAttributes(String errorKey, Validation validationType) {
			this(null, errorKey,  validationType);
		}

		public ValidationAttributes(String dataType, String errorKey, Validation validationType) {
			super();
			this.dataType = dataType;
			this.errorKey = errorKey;
			this.validation = validationType;
		}
		
		
	}
	
}
