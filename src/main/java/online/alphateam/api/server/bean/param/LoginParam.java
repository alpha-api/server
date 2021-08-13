package online.alphateam.api.server.bean.param;

import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotBlank;

public class LoginParam {

    @NotBlank(message = "用户编号不能为空")
    @Length(max = 16, message = "用户编号不能超过16个字符")
    private String code;

    @NotBlank(message = "密码不能为空")
    private String password;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
