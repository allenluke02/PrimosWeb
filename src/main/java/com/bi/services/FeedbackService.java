package com.bi.services;

import com.bi.model.Feedback;
import com.bi.utils.PaginationConfig;
import com.bi.vo.ApiListResponseVo;

public interface FeedbackService {

	Feedback createFeedback(Feedback feedback);

	ApiListResponseVo<Feedback> getAllFeedbacks(String search, PaginationConfig paginationConfig);

	Feedback getFeedbacksById(Long id);

	Feedback update(Feedback feedback);

	void deleteFeedbacksById(Long id);

}
