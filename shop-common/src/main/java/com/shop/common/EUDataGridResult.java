package com.shop.common;

import java.io.Serializable;
import java.util.List;

/**
 * @author N
 * @create 2018/12/22 -- 0:17
 * @email 554197854@qq.com
 */
//easyUI datagrid控件需要返回的json格式类
public class EUDataGridResult implements Serializable {
    private long total;
    private List<?> rows;

    public EUDataGridResult() {
    }

    public EUDataGridResult(long total, List<?> rows) {
        this.total = total;
        this.rows = rows;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }
}
