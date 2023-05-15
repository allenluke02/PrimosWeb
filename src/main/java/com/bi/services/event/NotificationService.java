package com.bi.services.event;

import com.bi.model.ContactUs;
import com.bi.model.Feedback;
import com.bi.model.FundRaising;
import com.bi.model.catering.Catering;
import com.bi.model.franchise.Franchise;
import com.bi.model.franchise.SiteSubmitter;
import com.bi.model.hire.Career;
import org.springframework.stereotype.Service;

@Service
public interface NotificationService {

    void submitFeedbackNotification(Feedback feedback);

    void createContactUs(ContactUs contactUs);

    void submitCareer(Career career);

    void franchiseRequest(Franchise franchise);

    void siteSubmitterRequest(SiteSubmitter siteSubmitter);

    void fundRaisingRequest(FundRaising fundRaising);

	void cateringRequest(Catering catering);

	void approveFundRaisingRequest(FundRaising fundRaising);

	void disapproveFundRaisingRequest(FundRaising fundRaising);
}
