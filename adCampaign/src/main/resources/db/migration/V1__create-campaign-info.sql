create table IF NOT EXISTS campaign_info (generated_id bigint not null auto_increment, partner_id varchar(255), ad_content varchar(40), duration integer not null, created_date datetime)
