package com.bi.controllers;

import com.bi.model.ContactUs;
import com.bi.model.FundRaising;
import com.bi.model.location.PickupPoint;
import com.bi.services.FundRaisingService;
import com.bi.utils.PaginationConfig;
import com.bi.views.FundRaisingView;
import com.bi.vo.ApiListResponseVo;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class FundRaisingController {

    private static final Logger logger = LoggerFactory.getLogger(FundRaisingController.class);

    @Autowired
    private FundRaisingService fundRaisingService;


    @ApiOperation(value = "create a new Fund-Raising")
    @PostMapping(value = "/fundRaising", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(FundRaisingView.BasicView.class)
    public FundRaising createFundRaising(@Valid @RequestBody FundRaising fundRaising) {
        fundRaising.setId(null);
        return fundRaisingService.createFundRaising(fundRaising);
    }

    @GetMapping("/fundRaising")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 500, message = "Internal server error") })
    @JsonView(FundRaisingView.BasicView.class)
    @ApiOperation(value = "Get all Fund Raising")
    public ApiListResponseVo<FundRaising> getAllFundRaisings(
            @RequestParam(value = "isActive", required = false) Boolean isActive,
            @RequestParam(value = "pageSize", required = false,defaultValue = "10") Integer pageSize,
            @RequestParam(value = "pageNumber", required = false,defaultValue = "0") Integer pageNumber,
            @RequestParam(value = "search", required = false) String search,
            @RequestParam(value = "sort", required = false) String sortDirection,
            @RequestParam(value = "sortOn", required = false) String sortOn) {
        PaginationConfig paginationConfig = new PaginationConfig(pageSize, pageNumber, sortDirection, sortOn);
        return fundRaisingService.getAllFundRaising(search,isActive,paginationConfig);
    }

    @GetMapping("/fundRaising/{id}")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 500, message = "Internal server error") })
    @JsonView(FundRaisingView.BasicView.class)
    @ApiOperation(value = "Get Fund-Raising with id")
    public FundRaising getFundRaisingById(@PathVariable("id") Long id) {
        return fundRaisingService.getFundRaisingById(id);
    }

    @ApiOperation(value = "Update a new Fund-Raising ")
    @PutMapping(value = "/fundRaising/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(FundRaisingView.BasicView.class)
    public FundRaising update(@Valid @PathVariable("id") Long fundRaisingId, @RequestBody FundRaising fundRaising) {
        fundRaising.setId(fundRaisingId);
        return fundRaisingService.update(fundRaising);
    }

    @ApiOperation(value = "Delete a Fund-Raising ")
    @ApiResponses({ @ApiResponse(code = 200, message = "ok"),
            @ApiResponse(code = 500, message = "Internal server error") })
    @DeleteMapping("/fundRaising/{id}")
    public void deleteFundRaisingById(@PathVariable("id") Long id) {
        fundRaisingService.deleteFundRaisingById(id);
    }
    
    @ApiOperation(value = "Approve/Disapprove FundRaising ")
	@ApiResponses({ @ApiResponse(code = 200, message = "ok"),
			@ApiResponse(code = 500, message = "Internal server error") })
	@PutMapping("/fundRaising/{id}")
	@PreAuthorize("hasAuthority('UPDATE_FUNDRAISING_STATUS')")
	public void changeFundRaisingStatus(@PathVariable("id") Long id,
			 @RequestParam(value = "isApproved") Boolean isApproved) {
    	fundRaisingService.changeFundRaisingStatus(id,isApproved);
	}


}
