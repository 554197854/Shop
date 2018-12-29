package com.shop.common;

import java.io.Serializable;

/**
 * @author N
 * @create 2018/12/26 -- 15:50
 * @email 554197854@qq.com
 */
public class EUPicUploadResult implements Serializable {
    private Integer error;
    private String url;
    private String message;

    public Integer getError() {
        return error;
    }

    public void setError(Integer error) {
        this.error = error;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
