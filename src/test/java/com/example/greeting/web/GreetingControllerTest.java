package com.example.greeting.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(GreetingController.class)
class GreetingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void homeDisplaysEmptyNameFields() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("home"))
                .andExpect(model().attributeExists("greetingForm"))
                .andExpect(content().string(containsString("First name")))
                .andExpect(content().string(containsString("Last name")))
                .andExpect(content().string(containsString("Submit")));
    }

    @Test
    void submitDisplaysGreeting() throws Exception {
        mockMvc.perform(post("/greet")
                        .param("firstName", "Ada")
                        .param("lastName", "Lovelace"))
                .andExpect(status().isOk())
                .andExpect(view().name("greeting"))
                .andExpect(model().attribute("firstName", "Ada"))
                .andExpect(model().attribute("lastName", "Lovelace"))
                .andExpect(content().string(containsString("Hello Ada Lovelace!")))
                .andExpect(content().string(containsString("Return to Home Page")));
    }

    @Test
    void trimsLeadingAndTrailingWhitespace() throws Exception {
        mockMvc.perform(post("/greet")
                        .param("firstName", "  Ada  ")
                        .param("lastName", " Lovelace "))
                .andExpect(status().isOk())
                .andExpect(model().attribute("firstName", "Ada"))
                .andExpect(model().attribute("lastName", "Lovelace"));
    }

    @Test
    void rejectsBlankNames() throws Exception {
        mockMvc.perform(post("/greet")
                        .param("firstName", " ")
                        .param("lastName", ""))
                .andExpect(status().isOk())
                .andExpect(view().name("home"))
                .andExpect(model().attributeHasFieldErrors(
                        "greetingForm", "firstName", "lastName"));
    }

    @Test
    void returnHomeRedirectsToFreshForm() throws Exception {
        mockMvc.perform(post("/home"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }
}
