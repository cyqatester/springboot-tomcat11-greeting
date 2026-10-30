package com.example.greeting.web.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class GreetingForm {

    @NotBlank(message = "First name is required.")
    @Size(max = 80, message = "First name must be 80 characters or fewer.")
    private String firstName = "";

    @NotBlank(message = "Last name is required.")
    @Size(max = 80, message = "Last name must be 80 characters or fewer.")
    private String lastName = "";

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
