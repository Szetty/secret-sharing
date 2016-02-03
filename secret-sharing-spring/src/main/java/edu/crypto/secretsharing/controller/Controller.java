package edu.crypto.secretsharing.controller;

import edu.crypto.secretsharing.domain.Share;
import edu.crypto.secretsharing.dto.SplitRequestDTO;
import edu.crypto.secretsharing.exception.ServerException;
import edu.crypto.secretsharing.repository.ShareRepository;
import edu.crypto.secretsharing.shamir.SecretSplitter;
import edu.crypto.secretsharing.shamir.ShamirSecretReconstructor;
import edu.crypto.secretsharing.shamir.dto.ShamirReconstructRequestDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/secret-sharing")
@CrossOrigin
public class Controller {

    @RequestMapping(method = RequestMethod.POST, value = "/split", consumes = "application/json")
    public ResponseEntity<String> split(@RequestBody SplitRequestDTO splitRequest) {
        try {
            List<Share> shares = SecretSplitter.generateShares(splitRequest);
            ShareRepository.buildRepository(shares);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ServerException e) {
            return treatException(e);
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/shares/{id}", produces = "application/json")
    public ResponseEntity getShare(@PathVariable int id){
        try {
            return new ResponseEntity<>(
                    ShareRepository.getInstance().getShare(id),
                    HttpStatus.OK
            );
        } catch (ServerException ex) {
            return treatException(ex);
        }

    }

    @RequestMapping(method = RequestMethod.POST, value = "/reconstruct/shamir", consumes = "application/json")
    public ResponseEntity<String> reconstructShamir(@RequestBody ShamirReconstructRequestDTO reconstructRequest){
        try {
            return new ResponseEntity<>(
                    ShamirSecretReconstructor.reconstruct(reconstructRequest),
                    HttpStatus.OK
            );
        } catch (ServerException ex) {
            return treatException(ex);
        }
    }

    private ResponseEntity<String> treatException(ServerException ex) {
        ex.printStackTrace();
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
