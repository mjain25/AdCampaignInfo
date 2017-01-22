package com.comcast.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.comcast.model.AdCampaignRequest;

public interface CampaignInfoRepository extends JpaRepository<AdCampaignRequest, Long> {

	List<AdCampaignRequest> findByPartnerId(String partnerId);

}
