package online.alphateam.api.server.bean.param;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class AlphaParam {

    @NotNull(message = "明细id不能为空", groups = {ApiParam.UpdateGroup.class})
    private Integer id;

    @NotBlank(message = "请求方法不能为空", groups = {ApiParam.SaveGroup.class})
    private String requestMethod;

    @NotBlank(message = "SQL不能为空", groups = {ApiParam.SaveGroup.class})
    private String sql;

    @NotNull(message = "状态不能为空", groups = {ApiParam.SaveGroup.class})
    private Integer isUse;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public Integer getIsUse() {
        return isUse;
    }

    public void setIsUse(Integer isUse) {
        this.isUse = isUse;
    }
}
