package com.bi.views;

import com.fasterxml.jackson.annotation.JsonView;

@JsonView(TemplateDataView.class)
public interface ContactUsView {

    public interface BasicView extends TemplateDataView,ApiListResponseView.BasicView {};

}
