package com.bi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;

@SpringBootApplication(scanBasePackages = "com.bi")
/*@EnableConfigurationProperties({
    FileStorageProperties.class
})*/
//@EnableScheduling
@RestController
public class FoodOrderingApplication {

	public static void main(String[] args) throws InterruptedException{
		SpringApplication.run(FoodOrderingApplication.class, args);
		System.out.println("...........server started...........");
	}
	
@Bean
public javax.validation.Validator validator() {
    final LocalValidatorFactoryBean factory = new LocalValidatorFactoryBean();
    factory.setValidationMessageSource(messageSource());
    return factory;
}
 
 @Bean
 public ResourceBundleMessageSource messageSource() {
	 	ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("message");
        messageSource.setUseCodeAsDefaultMessage(true);
        //messageSource.setCacheSeconds(3600); //refresh cache once per hour
        return messageSource;
 }
	
//	@Bean
//	public javax.validation.Validator validator() {
//	    final LocalValidatorFactoryBean factory = new LocalValidatorFactoryBean();
//	    factory.setValidationMessageSource(messageSource());
//	    return factory;
//	}
//	 
//	 @Bean
//	 public ResourceBundleMessageSource messageSource() {
//		 	ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
//	        messageSource.setBasename("message");
//	        messageSource.setUseCodeAsDefaultMessage(true);
//	        //messageSource.setCacheSeconds(3600); //refresh cache once per hour
//	        return messageSource;
//	 }
//	 
//	 public Module hibernate5Module() {
//		 return new Hibernate5Module();
//	 }
//
//	/* @Bean
//	 public FilterRegistrationBean<ValidationFilter> queryAppenderFilter(){
//	     FilterRegistrationBean<ValidationFilter> registrationBean 
//	       = new FilterRegistrationBean<>();
//	          
//	     registrationBean.setFilter(new ValidationFilter());
//	     registrationBean.addUrlPatterns("/disable/api/clinics/*");
//	          
//	     return registrationBean;    
//	 }*/
	 
//	 @Bean
//	 public JavaMailSender getJavaMailSender() {
//	     JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
//	     mailSender.setHost("smtp.gmail.com");
//	     mailSender.setPort(587);
//	      
//	     mailSender.setUsername("deepaksharma2005@gmail.com");
//	     mailSender.setPassword("Dee123!!");
//	      
//	     Properties props = mailSender.getJavaMailProperties();
//	     props.put("mail.transport.protocol", "smtp");
//	     props.put("mail.smtp.auth", "true");
//	     props.put("mail.smtp.starttls.enable", "true");
//	     //props.put("mail.smtp.starttls.required", "true");
//	     //props.put("mail.smtp.ssl.enable", "true");
//	     props.put("mail.debug", "true");
//	      
//	     return mailSender;
//	 }
//	 
	
}
