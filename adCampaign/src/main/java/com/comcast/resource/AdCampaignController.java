package com.comcast.resource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.HttpURLConnection;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.comcast.model.AdCampaignRequest;
import com.comcast.model.AdCampaignRequestResource;
import com.comcast.model.ErrorMessage;
import com.comcast.service.AdCampaignService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@RestController
@Api(value = "Ad Campaign Resource")
@Slf4j
public class AdCampaignController {

	@Autowired
	AdCampaignService adCampaignService;

	@ApiOperation(value = "Create an ad campaign(POST)")
	@RequestMapping(value = "/ad", method = RequestMethod.POST)
	@ApiResponses({
			@ApiResponse(code = HttpURLConnection.HTTP_CREATED, message = "Success", response = AdCampaignRequest.class),
			@ApiResponse(code = HttpURLConnection.HTTP_BAD_REQUEST, message = "Bad Request", response = ErrorMessage.class),
			@ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = "Internal Server Error", response = ErrorMessage.class) })
	@ResponseStatus(HttpStatus.CREATED)
	public AdCampaignRequest adCampaign(@Valid @RequestBody AdCampaignRequest adCampaignRequest) {

		return adCampaignService.adCampaign(adCampaignRequest);

	}

	@ApiOperation(value = "Fetch an ad campaign based on PartnerId(GET)")
	@RequestMapping(value = "/ad/{partnerId}", method = RequestMethod.GET)
	@ApiResponses({
			@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Success", response = AdCampaignRequest.class),
			@ApiResponse(code = HttpURLConnection.HTTP_BAD_REQUEST, message = "Bad Request", response = ErrorMessage.class),
			@ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = "Internal Server Error", response = ErrorMessage.class) })
	@ResponseStatus(HttpStatus.OK)
	public List<AdCampaignRequestResource> getCampaign(
			@ApiParam(value = "partnerId", name = "partnerId", required = true) @NotNull @PathVariable("partnerId") String partnerId) {
		List<AdCampaignRequestResource> resourceList = adCampaignService.getCampaign(partnerId);
		// comment
		for (int i = 0; i < resourceList.size(); i++) {
			resourceList.get(i).add(linkTo(methodOn(AdCampaignController.class).getCampaign(partnerId)).withSelfRel());
		}

		return resourceList;

	}

}
