package online.alphateam.api.server.bean.dto;

import online.alphateam.api.server.bean.po.SysApi;

public class SysApiDTO <T> extends SysApi {

    private T detail;

    public T getDetail() {
        return detail;
    }

    public void setDetail(T detail) {
        this.detail = detail;
    }
}
