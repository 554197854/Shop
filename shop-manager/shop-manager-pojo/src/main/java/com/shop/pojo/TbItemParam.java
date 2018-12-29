package com.shop.pojo;

import java.io.Serializable;
import java.util.Date;

public class TbItemParam implements Serializable {
    private Long id;

    private Long itemCatId;

    private Date created;

    private Date updated;

    private String paramData;

    private TbItemCat tbItemCat;

    public TbItemCat getTbItemCat() {
        return tbItemCat;
    }

    @Override
    public String toString() {
        return "TbItemParam{" +
                "id=" + id +
                ", itemCatId=" + itemCatId +
                ", created=" + created +
                ", updated=" + updated +
                ", paramData='" + paramData + '\'' +
                ", tbItemCat=" + tbItemCat +
                '}';
    }

    public void setTbItemCat(TbItemCat tbItemCat) {
        this.tbItemCat = tbItemCat;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getItemCatId() {
        return itemCatId;
    }

    public void setItemCatId(Long itemCatId) {
        this.itemCatId = itemCatId;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public String getParamData() {
        return paramData;
    }

    public void setParamData(String paramData) {
        this.paramData = paramData == null ? null : paramData.trim();
    }
}