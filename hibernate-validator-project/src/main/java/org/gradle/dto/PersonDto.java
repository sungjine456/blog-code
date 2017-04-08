package org.gradle.dto;

import org.hibernate.validator.constraints.NotEmpty;

public class PersonDto {
	@NotEmpty(message="빈값이면 안됩니다.")
    private String name;
	
    public String getName() {
        return name;
    }
	public void setName(String name) {
		this.name = name;
	}
}
