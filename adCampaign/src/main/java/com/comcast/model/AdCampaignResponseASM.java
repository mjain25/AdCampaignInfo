package com.comcast.model;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import com.comcast.resource.AdCampaignController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AdCampaignResponseASM extends ResourceAssemblerSupport<AdCampaignRequest, AdCampaignRequestResource> {

	/**
	 * @param controllerClass
	 * @param resourceType
	 */
	public AdCampaignResponseASM() {
		super(AdCampaignController.class, AdCampaignRequestResource.class);
	}

	@Override
	public AdCampaignRequestResource toResource(AdCampaignRequest adCampaignRequest) {
		AdCampaignRequestResource res = new AdCampaignRequestResource();

		res.setAdContent(adCampaignRequest.getAdContent());
		res.setCreatedDate(adCampaignRequest.getCreatedDate());
		res.setDuration(adCampaignRequest.getDuration());
		res.setPartnerId(adCampaignRequest.getPartnerId());
		return res;
	}

}
