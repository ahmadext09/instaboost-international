package com.amdck.instaboost.international;

import com.amdck.instaboost.international.model.PurchaseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class AppController {

    private final AppService appService;

    @Autowired
    public AppController(AppService appService) {
        this.appService = appService;

    }

    @PostMapping("/verify")
    public String verifyPurchase(@RequestBody PurchaseData purchaseData) {
        String message;

        try {
            message = appService.startVerification(purchaseData);
            return message;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/verify-token")
    public String verifyToken(@RequestParam("packageName") String packageName, @RequestParam("productId") String product, @RequestParam("purchaseToken") String token) {
        String message;
        try {
            PurchaseData purchaseData = new PurchaseData(packageName, product, token);
            message = appService.startVerification(purchaseData);
            return message;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
