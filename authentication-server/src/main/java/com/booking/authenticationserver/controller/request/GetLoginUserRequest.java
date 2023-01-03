package com.booking.authenticationserver.controller.request;


 class GetLoginUserRequest {
    String email;
    String password;

     public String getEmail() {
         return email;
     }


     public String getPassword() {
         return password;
     }

     public GetLoginUserRequest(String email, String password) {
         this.email = email;
         this.password = password;
     }
 }
