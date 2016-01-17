package edu.crypto.secretsharing.controller;

import edu.crypto.secretsharing.dto.SplitRequestDTO;
import edu.crypto.secretsharing.exception.ServerException;
import edu.crypto.secretsharing.factory.PolynomialFactory;
import edu.crypto.secretsharing.transformer.SecretTransformer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class Controller {

    @RequestMapping(method = RequestMethod.GET, value = "/hello")
    public ResponseEntity<String> hello() {
        return new ResponseEntity<String>("hello spring", HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/split", consumes = "application/json")
    public ResponseEntity<String> split(@RequestBody SplitRequestDTO splitRequest) {
        try {
            return new ResponseEntity<>(PolynomialFactory.buildPolynomial(SecretTransformer.transformSecret(splitRequest.getType(), splitRequest.getSecret()) , splitRequest.getT()).toString(),
                    HttpStatus.OK);
        } catch (ServerException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
