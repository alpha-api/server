package online.alphateam.api.server.bean.dto;

import online.alphateam.api.server.bean.po.SysApi;
import java.util.List;

public class SysApiDTO <T> extends SysApi {

    private String moduleName;

    private String moduleCode;

    private List<String> requestMethods;

    private List<T> details;

    private String url;

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public List<String> getRequestMethods() {
        return requestMethods;
    }

    public void setRequestMethods(List<String> requestMethods) {
        this.requestMethods = requestMethods;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    public List<T> getDetails() {
        return details;
    }

    public void setDetails(List<T> details) {
        this.details = details;
    }
}
