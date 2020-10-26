package com.aboitiz.billstager.model;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubscriptionType {

	@Id
	@NotNull
	private String typeCode;

	private String description;

	@JsonIgnore
	private List<Subscription> subscriptionList;

}
