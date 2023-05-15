package com.bi.controllers;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class SchedulerController{
//	/*
//	private static final Logger logger = LoggerFactory.getLogger(SchedulerController.class);
//
//    /*@Autowired
//    private Scheduler scheduler;*/
//    @Autowired
//    //private ScheduleService scheduleService;
//
//    @ApiResponses(value = {
//			@ApiResponse(code = 200, message = "Schedule successfully crated"),
//			@ApiResponse(code = 401, message = "You are not authorized to add schedule"),
//			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
//			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
//			@ApiResponse(code = 400, message = "Bad Request")
//	} 
//			)
//    @PostMapping("/schedule")
//    public void scheduleJob(@Valid @RequestBody Schedule schedule) throws SchedulerException, GMServiceException {
//    	scheduleService.addSchedule(schedule);
//    }
//    
//    @GetMapping("/pause")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void pause() throws SchedulerException {
//     // log.info("SCHEDULER - PAUSE COMMAND");
//     // scheduler.standby();
//    	scheduleService.doPause();
//    	
//    
//    }
//    @GetMapping("/resume")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void resume() throws SchedulerException {
//    	/*//schdlr2.resumeTrigger(triggerKey);
//    	Scheduler schdlr2=	schedulerFactoryBean.getScheduler();
//    	List<JobExecutionContext> context=schdlr2.getCurrentlyExecutingJobs();
//    	for (JobExecutionContext jobExecutionContext : context) {
//    		if(jobExecutionContext.getJobDetail().getKey().getName().equals("testjob")){
//    			//jobExecutionContext
//    		}
//    		//jobExecutionContext.getNextFireTime();
//    	}*/
//    }
//    @GetMapping("/jobs")
//    @JsonView(TriggerDetailBasicView.class)
//    public List<GMJobDetail> getJobList() throws SchedulerException {
//    	return scheduleService.getAllJob();
//   
//    }
}
