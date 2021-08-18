package online.alphateam.api.server.bean.param;

import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ModuleParam {

    public interface SaveGroup {}

    public interface UpdateGroup extends SaveGroup {}

    @NotNull(message = "更新模块，模块id不能为空", groups = {UpdateGroup.class})
    private Integer id;

    @Length(max = 16, message = "模块名称长度不能超过16个字符", groups = {SaveGroup.class})
    @NotBlank(message = "模块名称不能为空", groups = {SaveGroup.class})
    private String name;

    @Length(max = 32, message = "模块编号长度不能超过32个字符", groups = {SaveGroup.class})
    @NotBlank(message = "模块编号不能为空", groups = {SaveGroup.class})
    private String code;

    @Length(max = 128, message = "模块说明长度不能超过128个字符", groups = {SaveGroup.class})
    private String remark;

    @NotNull(message = "模块状态不能为空", groups = {SaveGroup.class})
    private Integer isUse;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getIsUse() {
        return isUse;
    }

    public void setIsUse(Integer isUse) {
        this.isUse = isUse;
    }
}
