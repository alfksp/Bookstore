//package com.application.bookstore.controller;
//
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class RoleController {
//
//    @PreAuthorize("hasRole('ADMIN')")
//    @GetMapping("admin")
//    public String getAdminData(){
//        return "Admin Data";
//    }
//
//    @PreAuthorize("hasRole('USER')")
//    @GetMapping("user")
//    public String getUserData(){
//        return "User Data";
//    }
//}
