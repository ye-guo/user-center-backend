package model.domain.request.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserRegisterDTO implements Serializable {
    private static final long serialVersionUID = 9176920610730912571L;
    String userAccount;
    String userPassword;
    String checkPassword;

}
