package com.leadexperience.chefio.models.body;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class LoginRequestBody {

    private String username;

    private String password;




}
