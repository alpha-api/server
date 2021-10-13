package online.alphateam.api.server.bean.param;

import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class DatasourceParam {

    public interface SaveGroup {}

    public interface UpdateGroup extends SaveGroup {}

    @NotNull(message = "id不能为空", groups = {UpdateGroup.class})
    private Integer id;

    @NotBlank(message = "数据库名称不能为空", groups = {SaveGroup.class})
    @Length(max = 16, message = "数据库名称不能超过16个字条", groups = {SaveGroup.class})
    private String name;

    @NotBlank(message = "数据库驱动类名不能为空", groups = {SaveGroup.class})
    private String driverClassName;

    @NotBlank(message = "数据库URL不能为空", groups = {SaveGroup.class})
    @Length(max = 256, message = "数据库名称不能超过256个字条", groups = {SaveGroup.class})
    private String url;

    @NotBlank(message = "用户名不能为空", groups = {SaveGroup.class})
    @Length(max = 64, message = "用户名不能超过64个字条", groups = {SaveGroup.class})
    private String username;

    @Length(max = 128, message = "用户密码不能超过128个字条", groups = {SaveGroup.class})
    private String password;

    @Length(max = 128, message = "备注内容不能超过128个字条", groups = {SaveGroup.class})
    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
