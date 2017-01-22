package com.comcast.service.impl;

import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ActiveProfiles;

import com.comcast.exception.DataNotFoundException;
import com.comcast.model.AdCampaignRequest;
import com.comcast.model.AdCampaignRequestResource;
import com.comcast.repository.CampaignInfoRepository;

@ActiveProfiles("unit-test")
public class AdCampaignServiceImplTest {

	@Mock
	CampaignInfoRepository repository;

	@InjectMocks
	AdCampaignServiceImpl service;

	@Mock
	AdCampaignRequest request;

	List<AdCampaignRequest> adCamplaignRequestList;
	List<AdCampaignRequestResource> adCamplaignResourceList;

	private String partnerId = "1";

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		adCamplaignRequestList = getCampaignList();
		adCamplaignResourceList = getResourceList();
	}

	private List<AdCampaignRequest> getCampaignList() {
		List<AdCampaignRequest> resource = new ArrayList<>();
		Date date = new Date();
		date.setTime(System.currentTimeMillis());
		AdCampaignRequest request = new AdCampaignRequest();
		request.setAdContent("adContent");
		request.setDuration(5000000);
		request.setPartnerId("1");
		request.setCreatedDate(date);
		resource.add(request);

		return resource;
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
		service.adCampaign(request);
		verify(repository).save(request);
	}

	@Test
	public void testGetActiveCampaign() {
		Mockito.when(repository.findByPartnerId(partnerId)).thenReturn(adCamplaignRequestList);

		Assert.assertNotNull(service.getCampaign(partnerId));
	}

	@Test(expected = DataNotFoundException.class)
	public void testGetCampaign_Null() {
		Mockito.when(repository.findByPartnerId(partnerId)).thenReturn(null);

		service.getCampaign(partnerId);
	}

	@Test(expected = DataNotFoundException.class)
	public void testGetActiveCampaign_Null() {
		adCamplaignRequestList.get(0).setDuration(0);
		Mockito.when(repository.findByPartnerId(partnerId)).thenReturn(adCamplaignRequestList);

		service.getCampaign(partnerId);
	}

}