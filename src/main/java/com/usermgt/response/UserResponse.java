package com.usermgt.response;

import com.usermgt.entity.User;
import lombok.Data;

@Data
public class UserResponse {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    public static UserResponse from(User user){

        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setEmail(user.getEmail());
        return response;
    }
}
