package cn.edu.sustech.adoj.trainboard.model.cas;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AuthenticationSuccess {
    private String user;
    private Attributes attributes;
}
