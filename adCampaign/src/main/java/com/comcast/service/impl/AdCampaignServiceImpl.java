package com.comcast.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.comcast.exception.DataNotFoundException;
import com.comcast.model.AdCampaignRequest;
import com.comcast.model.AdCampaignRequestResource;
import com.comcast.model.AdCampaignResponseASM;
import com.comcast.repository.CampaignInfoRepository;
import com.comcast.service.AdCampaignService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AdCampaignServiceImpl implements AdCampaignService {

	@Autowired
	CampaignInfoRepository repository;

	@Override
	public AdCampaignRequest adCampaign(AdCampaignRequest adCampaignRequest) {

		return repository.save(adCampaignRequest);
	}

	@Override
	@HystrixCommand(ignoreExceptions = { DataNotFoundException.class })
	public List<AdCampaignRequestResource> getCampaign(String partnerId) {

		return getActiveAdCampaigns(partnerId);
	}

	private List<AdCampaignRequestResource> getActiveAdCampaigns(String partnerId) {

		List<AdCampaignRequest> adCamplaigns = repository.findByPartnerId(partnerId);
		List<AdCampaignRequestResource> adCampaignRequestResourceList = new ArrayList<>();
		if (null != adCamplaigns && !adCamplaigns.isEmpty()) {
			for (AdCampaignRequest adCampaignRequest : adCamplaigns) {
				if (adCampaignRequest.getCreatedDate().getTime() + adCampaignRequest.getDuration() >= System
						.currentTimeMillis()) {

					AdCampaignRequestResource adCampaignResource = new AdCampaignResponseASM()
							.toResource(adCampaignRequest);
					adCampaignRequestResourceList.add(adCampaignResource);
				}
			}
		} else {
			throw new DataNotFoundException("Campaign details not found for partner_id >>> :: " + partnerId);
		}
		if (adCampaignRequestResourceList.isEmpty()) {
			throw new DataNotFoundException("No Active Campaign Details found for partner_id >>> :: " + partnerId);
		}
		return adCampaignRequestResourceList;

	}

}
