package com.mongodb_demo.controller;


import com.mongodb_demo.pojo.SlaveInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/service/SlaveInfo")
public class SlaveInfoController {

    @Autowired
    private MongoTemplate mongoTemplate;

    @ApiOperation(value = "根据ID查询")
    @GetMapping("/findId/{id}")
    public SlaveInfo findId(@PathVariable String id) {

        SlaveInfo slaveInfo = mongoTemplate.findById(id, SlaveInfo.class);
        return slaveInfo;
    }

    @ApiOperation(value = "查询全部")
    @GetMapping("/findAll")
    public List<SlaveInfo> findAll() {


        List<SlaveInfo> all = mongoTemplate.findAll(SlaveInfo.class);
        for (SlaveInfo slaveInfo : all) {
            String s = slaveInfo.toString();
            System.out.println(s);
        }
        return all;
    }

    @ApiOperation(value = "插入")
    @PostMapping("/save")
    public SlaveInfo inserSlaveInfo(@RequestBody SlaveInfo slaveInfo) {

        //可以用insert代替
        SlaveInfo save = mongoTemplate.save(slaveInfo);
        return save;
    }

    @ApiOperation(value = "通过id删除")
    @GetMapping("/delete/{id}")
    public SlaveInfo deleteSlaveInfo(@PathVariable String id) {

        SlaveInfo slaveInfo = mongoTemplate.findById(id, SlaveInfo.class);
        mongoTemplate.remove(slaveInfo);
        return slaveInfo;
    }

    @ApiOperation(value = "根据ID修改")
    @PostMapping("/update")
    public SlaveInfo updateSlave(@RequestBody SlaveInfo slaveInfo) {

        SlaveInfo slave = mongoTemplate.save(slaveInfo);
        return slave;
    }


    @ApiOperation(value = "分页查询")
    @PostMapping("/findPage/{page}/{size}")
    public Map<String, Object> findPage(@PathVariable Integer page, @PathVariable Integer size) {


        Query query = new Query();
        //添加查询条件
        //query.addCriteria(Criteria.where("_id").is("w-20-001"));
        //query.addCriteria(Criteria.where("CPU").is(100));
        long count = mongoTemplate.count(query, SlaveInfo.class);

        query.skip((page - 1) * size).limit(size);

        List<SlaveInfo> slaveInfos = mongoTemplate.find(query, SlaveInfo.class);
        Map<String, Object> map = new HashMap<>();

        map.put("size", count);
        map.put("slaveInfo", slaveInfos);

        return map;
    }


}
