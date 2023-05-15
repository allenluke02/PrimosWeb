package com.bi.views;

import com.fasterxml.jackson.annotation.JsonView;

@JsonView(TemplateDataView.class)
public interface FeedbackMailView {

    public interface BasicView extends FeedbackView.BasicView{};
}
