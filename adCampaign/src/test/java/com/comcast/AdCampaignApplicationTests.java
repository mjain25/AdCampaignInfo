package com.comcast;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AdCampaignApplication.class)
@WebAppConfiguration
@ActiveProfiles("unit-test")
public class AdCampaignApplicationTests {

	@Test
	public void contextLoads() {
	}

}
