package com.asterisk.basicAPI.controller;

import com.asterisk.basicAPI.dto.ResponseMessage;
import com.asterisk.basicAPI.service.AMIConnectionService;
import models.AMIConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin(origins = "http://localhost:8000/", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class AMIConnectionController {
    @Autowired
    AMIConnectionService amiConnection;
    @GetMapping("/ExtensionsStateList")
    public ResponseEntity<ResponseMessage> ExtensionsStateList(){
        AMIConnection ami = new AMIConnection();
        return ResponseEntity.ok().body(new ResponseMessage(amiConnection.ExtensionsStateList( ami )));
    }
}
