package com.application.bookstore.controller;

import com.application.bookstore.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RegistrationController.class)
class RegistrationControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    CustomerService customerService;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new RegistrationController(customerService)).build();
    }

    @Test
    @WithMockUser(username = "testuser", roles = "USER")
    void checkIfRegistrationFormShown() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/register"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("registration-form"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("customer"));
    }

    @Test
    void checkIfCustomerRegistered() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/register")
                        .param("name", "a")
                        .param("surname", "a")
                        .param("email", "a")
                        .param("username", "a")
                        .param("password", "a")
                        .param("confirmPassword", "a"))
                .andExpect(status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/login"));
    }
}