package com.comcast.resource;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.comcast.IntegrationTest;
import com.comcast.model.AdCampaignRequest;
import com.comcast.model.AdCampaignRequestResource;

public class AdCampaignResourceIntegrationTest extends IntegrationTest {

	@Autowired
	private AdCampaignController controller;

	private AdCampaignRequest adCampaignRequest;

	@Before
	public void setup() throws Exception {
		adCampaignRequest = new AdCampaignRequest();
		adCampaignRequest.setAdContent("Campaign Content");
		adCampaignRequest.setDuration(100000);
		adCampaignRequest.setPartnerId("1");
	}

	@Test
	public void testAddCampaign() {
		AdCampaignRequest createdCampaign = controller.adCampaign(adCampaignRequest);

		List<AdCampaignRequestResource> resourceList = controller.getCampaign("1");

		List<AdCampaignRequestResource> allResourceList = controller.getAllCampaigns();

		assertEquals(createdCampaign.getPartnerId(), adCampaignRequest.getPartnerId());
		assertEquals(resourceList.get(0).getPartnerId(), "1");
		assertEquals(allResourceList.get(0).getPartnerId(), "1");
	}
}
