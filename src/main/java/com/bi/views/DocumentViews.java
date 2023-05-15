package com.bi.views;

public class DocumentViews {
	
	public interface DocumentBasicView  {}
	public interface DocumentPersonView extends DocumentBasicView, PersonViews.PersonBasicView {}

}
