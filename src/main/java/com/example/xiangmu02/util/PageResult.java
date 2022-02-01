package com.example.xiangmu02.util;

import java.util.ArrayList;
import java.util.List;


public class PageResult<T> {
    
    private Long total = 0L;
    
    private List<T> result = new ArrayList<>();

    public PageResult(){

    }
    public PageResult(Long total, List<T> result) {
        this.total = total;
        this.result = result;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }
}
