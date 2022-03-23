package com.mongodb_demo.controller;


import com.mongodb_demo.pojo.SlaveInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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




}
