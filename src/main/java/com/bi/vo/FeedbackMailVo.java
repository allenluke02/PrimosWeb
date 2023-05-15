package com.bi.vo;

import com.bi.model.Feedback;
import com.bi.views.FeedbackMailView;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FeedbackMailVo {

    @JsonView(FeedbackMailView.BasicView.class)
    private UserVo  USER;

    @JsonView(FeedbackMailView.BasicView.class)
    private Feedback feedback;

}
