package model.domain.request.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserLoginDTO implements Serializable {
    private static final long serialVersionUID = 6528293462505507741L;

    String userAccount;
    String userPassword;

}
