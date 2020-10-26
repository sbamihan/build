package com.aboitiz.subscriptionservice.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "subscription", schema = "customer")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "Subscription", description = "Data object for a Subscription.")
public class Subscription {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	@Schema(description = "Unique identifier of the subscription.", example = "1", required = false, readOnly = true)
	private Integer id;

	@Column(name = "subscribe")
	@Schema(description = "Whether to subscribe or not.", example = "Y", allowableValues = { "Y",
			"N" }, required = true, readOnly = false)
	private Character subscribe;

	@NotNull
	@Column(name = "stat_flg")
	@Schema(description = "The status of subscription.", example = "20", allowableValues = { "20", "30",
			"40" }, required = true, readOnly = false)
	private String statFlg;

	@JoinColumn(name = "account_id", referencedColumnName = "account_id")
	@ManyToOne(optional = false, cascade = CascadeType.MERGE)
	@JsonIgnore
	@Schema(description = "The account where the subscription is to be applied.", example = "1000000001", required = true, readOnly = false)
	private Account account;

	@JoinColumn(name = "subscription_type", referencedColumnName = "type_code")
	@ManyToOne(optional = false)
	private SubscriptionType subscriptionType;

}
