package com.tritern.evozspecial.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.tritern.evozspecial.entity.EvozEntity;
import com.tritern.evozspecial.service.EvozService;

@RestController
@RequestMapping("/evoz")
public class EvozController {

    private final EvozService evozservice;

    @Autowired
    public EvozController(EvozService evozservice) {
        this.evozservice = evozservice;
    }

    @PostMapping("/register")
    public String createEvozUser(@RequestBody EvozEntity evozentity) {
        return evozservice.signUp(evozentity);
    }

    @GetMapping("/login")
    public String loginEvozUser(@RequestParam String emailid, @RequestParam String password) {
        return evozservice.loginEvozMUser(emailid, password);
    }

    @GetMapping("/listusers")
    public List<EvozEntity> listAllUsers(@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo) {
        return evozservice.getList(pageNo);
    }

    @GetMapping("/listcount")
    public Integer getListCount() {
        return evozservice.getCount();
    }

    @GetMapping("/search/listusers")
    public List<EvozEntity> searchListUser(@RequestParam(required = false) String keyword,
                                           @RequestParam(required = false, defaultValue = "1") Integer pageNo) {
        return evozservice.getFilter(keyword, pageNo);
    }

    @GetMapping("/search/listcount")
    public Integer getFilterCount(@RequestParam(required = false) String keyword) {
        return evozservice.getSearchCount(keyword);
    }

    @PutMapping("/update/{emailid}")
    public String updateEvozUsers(@PathVariable String emailid,
                                   @RequestBody EvozEntity updatedUser) {
        return evozservice.updateEvozUser(emailid, updatedUser);
    }

    @DeleteMapping("/delete/{emailid}")
    public String deleteEvozUser(@PathVariable String emailid) {
        return evozservice.removeUser(emailid);
    }
}
