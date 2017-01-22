package com.comcast.service;

import java.util.List;

import com.comcast.model.AdCampaignRequest;
import com.comcast.model.AdCampaignRequestResource;

public interface AdCampaignService {

	public AdCampaignRequest adCampaign(AdCampaignRequest adCampaignRequest);

	public List<AdCampaignRequestResource> getCampaign(String partnerId);

}
