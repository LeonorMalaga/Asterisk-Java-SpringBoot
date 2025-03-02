package com.asterisk.basicAPI.controller;

import com.asterisk.basicAPI.dto.ResponseMessage;
import com.asterisk.basicAPI.service.AMIConnectionService;
import models.AMIConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin(origins = "http://localhost:8000/", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class AMIConnectionController {
    @Autowired
    AMIConnectionService amiConnection;
    @PostMapping("/ExtensionsStateList")
    public ResponseEntity<List<Map<String, String>>> ExtensionsStateList(
            @RequestBody AMIConnection request
    ) {
        // Create AMIConnection using request data
        AMIConnection ami = new AMIConnection(request.getIp(), request.getPort(), request.getUser(), request.getPass());

        // Return the response
        return ResponseEntity.ok().body(amiConnection.ExtensionsStateList(ami));
    }
    @GetMapping("/ExtensionsStateList")
    public ResponseEntity<List<Map<String, String>>>ExtensionsStateList(){
        AMIConnection ami = new AMIConnection();
        return ResponseEntity.ok().body(amiConnection.ExtensionsStateList( ami ));
    }
}
