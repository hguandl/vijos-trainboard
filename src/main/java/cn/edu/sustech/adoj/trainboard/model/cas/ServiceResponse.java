package cn.edu.sustech.adoj.trainboard.model.cas;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ServiceResponse {
    private AuthenticationSuccess authenticationSuccess;
    private String code;
    private String authenticationFailure;
}
