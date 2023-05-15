package com.bi.services;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bi.exception.EntityNotFoundException;
import com.bi.model.ContactUs;
import com.bi.model.Feedback;
import com.bi.model.QFeedback;
import com.bi.repositories.FeedbackRepository;
import com.bi.services.event.NotificationService;
import com.bi.utils.PaginationConfig;
import com.bi.vo.ApiListResponseVo;

@Service
public class FeedbackServiceImpl extends SearchServiceImpl<Feedback, FeedbackRepository> implements FeedbackService {

	private static final Logger LOGGER = LoggerFactory.getLogger(FeedbackServiceImpl.class);

	@Autowired
	private FeedbackRepository feedbackRepository;

	@Autowired
	private MessageByLocaleService messageByLocaleService;

	@Autowired
	private NotificationService notificationService;

	@Override
	public Feedback createFeedback(Feedback feedback) {
		Feedback feedback1 = feedbackRepository.save(feedback);
		notificationService.submitFeedbackNotification(feedback);
		return feedback1;
	}

	@Override
	public ApiListResponseVo<Feedback> getAllFeedbacks(String search, PaginationConfig paginationConfig) {
		List<Feedback> feedbackList= search(search, Feedback.class, paginationConfig, null, null,null);
		ApiListResponseVo<Feedback> resp= new ApiListResponseVo<Feedback>();
		if(!CollectionUtils.isEmpty(feedbackList)) {
			resp.setData(feedbackList).setSuccess(Boolean.TRUE).setTotal(count(search, Feedback.class, null, null,null));
		}else {
			resp.setData(feedbackList).setSuccess(Boolean.FALSE).setTotal(0l);
		}
		return resp;
	}

	@Override
	public Feedback getFeedbacksById(Long id) {
		return feedbackRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(
				messageByLocaleService.getMessage("err.feedback.not.found", id.toString())));
	}

	@Override
	public Feedback update(Feedback feedback) {
		return feedbackRepository.save(feedback);
	}

	@Override
	public void deleteFeedbacksById(Long id) {
		Feedback feedback = getFeedbacksById(id);
		feedbackRepository.delete(feedback);
	}

}
