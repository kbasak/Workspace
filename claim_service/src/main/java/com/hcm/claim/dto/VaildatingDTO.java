package com.hcm.claim.dto;
import javax.persistence.Id;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

@Component
public class VaildatingDTO {
    @Id
    @JsonProperty
    private boolean validStatus;

	public boolean isValidStatus() {
		return validStatus;
	}

	public void setValidStatus(boolean validStatus) {
		this.validStatus = validStatus;
	}

	public VaildatingDTO(boolean validStatus) {
		super();
		this.validStatus = validStatus;
	}

	public VaildatingDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
        
}
