package com.application.bookstore.controller;

import com.application.bookstore.model.Customer;
//import com.application.bookstore.service.AuthenticationService;
import com.application.bookstore.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(LoginController.class)
//@SpringBootTest
class LoginControllerTest {


    @Autowired
    LoginController customerController;
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    @MockBean
    private PasswordEncoder passwordEncoder;

    private Customer mockCustomer;

//        @BeforeEach
//        public void setUp() {
//            mockCustomer = new Customer();
//            mockCustomer.setUsername("testUser");
//            mockCustomer.setPassword("$2a$10$4ImXT2uvI9he/I0fjFbGNe1O/fEfSa5ON3YZsIlDh.vEY9Zn.QUWW");
//        }

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }
//@MockBean
    // private CustomerService customerService;
//    @MockBean
//    private AuthenticationService authenticationService;

    @BeforeEach
    public void init() {
        mockCustomer = new Customer();
        mockCustomer.setUsername("testUser");
        mockCustomer.setPassword("$2a$10$4ImXT2uvI9he/I0fjFbGNe1O/fEfSa5ON3YZsIlDh.vEY9Zn.QUWW");
    }


    @Test
    void checkIfShowLoginPageDisplayed() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/login"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("login-form"));

    }


    @Test
    void checkIfShowLoginErrorDisplayed() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/login?error=true"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("login-form"))
//                .andExpect(MockMvcResultMatchers.model().attributeExists("error"))
//                .andExpect(MockMvcResultMatchers.model().attribute("error", "Invalid username or password"))
;
    }

    @Test
    void checkIfLoggingInRedirectsToMain() throws Exception {


        //when
        when(customerService.findByUsername("testUser")).thenReturn(mockCustomer);
        when(passwordEncoder.matches("testPassword", mockCustomer.getPassword())).thenReturn(true);

        //then
        mockMvc.perform(post("/login")
                        .param("username", "testUser")
                        .param("password", "testPassword"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/main"));
    }

    @Test
    void checkIfLoggingWithWrongPasswordRedirectsToLoginError() throws Exception {
        when(customerService.findByUsername("testUser")).thenReturn(mockCustomer);
        when(passwordEncoder.matches("wrongPassword", mockCustomer.getPassword())).thenReturn(false);

        mockMvc.perform(MockMvcRequestBuilders.post("/login")
                        .param("username", "testUser")
                        .param("password", "wrongPassword"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/login?error=true"));

    }
}
