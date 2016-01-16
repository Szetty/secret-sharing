package edu.crypto.secretsharing.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HelloController {

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, value = "/hello")
    public ResponseEntity<String> hello() {
        return new ResponseEntity<String>("hello spring", HttpStatus.OK);
    }

}
