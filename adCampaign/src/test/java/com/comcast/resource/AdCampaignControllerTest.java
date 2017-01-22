package com.comcast.resource;

import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.comcast.AdCampaignApplication;
import com.comcast.model.AdCampaignRequest;
import com.comcast.model.AdCampaignRequestResource;
import com.comcast.service.AdCampaignService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AdCampaignApplication.class)
@WebAppConfiguration
@ActiveProfiles("unit-test")
public class AdCampaignControllerTest {

	@InjectMocks
	private AdCampaignController controller;

	@Mock
	private AdCampaignService service;

	@Mock
	private AdCampaignRequest adCampaignRequest;

	private List<AdCampaignRequestResource> resourceList;

	private String partnerId = "partnerId";

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		resourceList = getResourceList();
	}

	private List<AdCampaignRequestResource> getResourceList() {
		List<AdCampaignRequestResource> resourceList = new ArrayList<>();
		AdCampaignRequestResource resource = new AdCampaignRequestResource();
		resource.setAdContent("adContent");
		resource.setDuration(50000);
		resource.setPartnerId("1");
		resourceList.add(resource);

		return resourceList;
	}

	@Test
	public void testAdCampaign() {
		Mockito.when(service.adCampaign(adCampaignRequest)).thenReturn(adCampaignRequest);
		Assert.assertNotNull(controller.adCampaign(adCampaignRequest));
		verify(service).adCampaign(adCampaignRequest);
	}

	@Test
	public void testGetActiveCampaignList() {
		Mockito.when(service.getCampaign(partnerId)).thenReturn(resourceList);
		Assert.assertNotNull(controller.getCampaign(partnerId));
		verify(service).getCampaign(partnerId);
	}

	@Test
	public void testGetAllCampaignList() {
		Mockito.when(service.getAllCampaign()).thenReturn(resourceList);
		Assert.assertNotNull(controller.getAllCampaigns());
		verify(service).getAllCampaign();
	}

}

/*
 * Copyright 2016 Capital One Financial Corporation All Rights Reserved.
 * 
 * This software contains valuable trade secrets and proprietary information of
 * Capital One and is protected by law. It may not be copied or distributed in
 * any form or medium, disclosed to third parties, reverse engineered or used in
 * any manner without prior written authorization from Capital One.
 */
