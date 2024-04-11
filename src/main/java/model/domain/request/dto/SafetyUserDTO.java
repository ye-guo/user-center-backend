package model.domain.request.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class SafetyUserDTO implements Serializable {

    private static final long serialVersionUID = 583070173765301195L;

    private Long id;
    private String username;
    private String userAccount;
    private String avatarUrl;
    private Integer gender;
    private String phone;
    private String email;
    private Integer userStatus;
    private Date createTime;
    private Integer userRole;


}
