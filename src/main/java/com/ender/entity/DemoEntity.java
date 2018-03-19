package com.ender.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by ender on 2017/3/14.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DemoEntity implements Serializable {

    private Integer integerDemo;

    private Date dateDemo;

    private Float aFloatDemo;

    private String stringDemo;

    private List<DemoEntity> listDemo;

    public Integer getIntegerDemo() {
        return integerDemo;
    }

    public void setIntegerDemo(Integer integerDemo) {
        this.integerDemo = integerDemo;
    }

    public Date getDateDemo() {
        return dateDemo;
    }

    public void setDateDemo(Date dateDemo) {
        this.dateDemo = dateDemo;
    }

    public Float getaFloatDemo() {
        return aFloatDemo;
    }

    public void setaFloatDemo(Float aFloatDemo) {
        this.aFloatDemo = aFloatDemo;
    }

    public String getStringDemo() {
        return stringDemo;
    }

    public void setStringDemo(String stringDemo) {
        this.stringDemo = stringDemo;
    }

    public List<DemoEntity> getListDemo() {
        return listDemo;
    }

    public void setListDemo(List<DemoEntity> listDemo) {
        this.listDemo = listDemo;
    }
}
