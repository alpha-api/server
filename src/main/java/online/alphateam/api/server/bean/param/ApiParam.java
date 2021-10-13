package online.alphateam.api.server.bean.param;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ApiParam {

    public interface SaveGroup {};

    public interface UpdateGroup extends SaveGroup {}

    @NotNull(message = "id不能为空", groups = {UpdateGroup.class})
    private Integer id;

    @NotBlank(message = "API编号不能为空", groups = {UpdateGroup.class})
    @Length(max = 32, message = "API编号长度不能超过32个字条", groups = {UpdateGroup.class})
    private String code;

    @NotBlank(message = "API名称不能为空", groups = {UpdateGroup.class})
    @Length(max = 16, message = "API名称长度不能超过16个字条", groups = {UpdateGroup.class})
    private String name;

    @NotNull(message = "API类型不能为空", groups = {SaveGroup.class})
    @Min(value = 1, message = "API类型错误", groups = {SaveGroup.class})
    @Max(value = 3, message = "API类型错误", groups = {SaveGroup.class})
    private Integer type;

    @Length(max = 128, message = "备注长度不能超过128个字符", groups = {UpdateGroup.class})
    private String remark;

    @NotNull(message = "所属模块id不能为空", groups = {SaveGroup.class})
    private Integer sysModuleId;

    @NotNull(message = "数据源id不能为空", groups = {SaveGroup.class})
    private Integer sysDatasourceId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getSysModuleId() {
        return sysModuleId;
    }

    public void setSysModuleId(Integer sysModuleId) {
        this.sysModuleId = sysModuleId;
    }

    public Integer getSysDatasourceId() {
        return sysDatasourceId;
    }

    public void setSysDatasourceId(Integer sysDatasourceId) {
        this.sysDatasourceId = sysDatasourceId;
    }

}
