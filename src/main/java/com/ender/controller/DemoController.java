package com.ender.controller;

import com.ender.entity.BaseEntity;
import com.ender.entity.DemoEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Demo接口
 * Created by ender on 2017/3/14.
 */
@RestController
@RequestMapping("/demo")
public class DemoController extends AbstractBaseAction {

    @RequestMapping(value = "entity", method = RequestMethod.POST)
    public BaseEntity getEntity(@RequestBody DemoEntity demoEntity) {
        return BaseEntity.success(demoEntity);
    }

    @RequestMapping(value = "path/{apid}", method = RequestMethod.POST)
    public BaseEntity getUrl(@PathVariable Long apid) {
        return BaseEntity.success();
    }

    @RequestMapping(value = "{apid}/konwledge", method = RequestMethod.GET)
    public BaseEntity getknowledgeInterceptor(@PathVariable Long apid) {
        log.info("this is a test try");
        return BaseEntity.success();
    }

    @RequestMapping(value = "entitys", method = RequestMethod.POST)
    public BaseEntity getEntitys(@RequestBody DemoEntity demoEntity) {
        DemoEntity dtemp = new DemoEntity();
        List<DemoEntity> list = new ArrayList<>();
        //list.add(dtemp);
        list.add(demoEntity);
        return BaseEntity.success(list);
    }
}
