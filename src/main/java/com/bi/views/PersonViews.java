package com.bi.views;

public class PersonViews {
	
	public interface None {}
	public interface PersonSummaryView {}
	public interface PersonBasicView extends PersonSummaryView {}
//	public interface PersonView extends PersonBasicView, PersonAddressBasicView ,DocumentBasicView{}
//	public interface PersonAddressView extends PersonBasicView, PersonAddressBasicView{}
	public interface DocumentList extends AuditableViews.AuditableBasicView {}

}
