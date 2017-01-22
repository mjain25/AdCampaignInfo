package com.comcast.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@ApiModel(value = "Represents the AdCampaign Details")
@Entity
@NoArgsConstructor
@Table(name = "CAMPAIGN_INFO", uniqueConstraints = { @UniqueConstraint(columnNames = { "generatedId" }) })
@JsonIgnoreProperties(ignoreUnknown = true)
@EqualsAndHashCode
@Builder
@AllArgsConstructor
public class AdCampaignRequest {

	@Id
	@GeneratedValue
	@JsonIgnore
	private Long generatedId;

	@ApiModelProperty("Partner Identifier.")
	@NotNull
	@JsonProperty("partner_id")
	private String partnerId;

	@ApiModelProperty("Duration for Campaign")
	private Integer duration;

	@ApiModelProperty("Campaign Content for the partner")
	@JsonProperty("ad_content")
	private String adContent;

	@ApiModelProperty("Entry Creation Date")
	private Date createdDate;

	@PrePersist
	void createdAt() {
		this.createdDate = new Date();
	}
}
