package com.comcast.model;

import java.util.Date;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AdCampaignRequestResource extends ResourceSupport {

	@JsonProperty("partner_id")
	private String partnerId;

	private Integer duration;

	@JsonProperty("ad_content")
	private String adContent;

	private Date createdDate;

	/**
	 * @return the partnerId
	 */
	public String getPartnerId() {
		return partnerId;
	}

	/**
	 * @param partnerId
	 *            the partnerId to set
	 */
	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

	/**
	 * @return the duration
	 */
	public Integer getDuration() {
		return duration;
	}

	/**
	 * @param duration
	 *            the duration to set
	 */
	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	/**
	 * @return the adContent
	 */
	public String getAdContent() {
		return adContent;
	}

	/**
	 * @param adContent
	 *            the adContent to set
	 */
	public void setAdContent(String adContent) {
		this.adContent = adContent;
	}

	/**
	 * @return the createdDate
	 */
	public Date getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate
	 *            the createdDate to set
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public AdCampaignRequest toAdCampaignRequest() {
		AdCampaignRequest adCampaignRequest = new AdCampaignRequest();
		adCampaignRequest.setAdContent(adContent);
		adCampaignRequest.setCreatedDate(createdDate);
		adCampaignRequest.setDuration(duration);
		adCampaignRequest.setPartnerId(partnerId);
		return adCampaignRequest;
	}
}
