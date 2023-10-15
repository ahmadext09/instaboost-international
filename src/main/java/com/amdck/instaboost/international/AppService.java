package com.amdck.instaboost.international;

import com.amdck.instaboost.international.model.OAuthResponse;
import com.amdck.instaboost.international.model.ProductPurchaseResponse;
import com.amdck.instaboost.international.model.PurchaseData;
import com.amdck.instaboost.international.model.TokenRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;


@Service
public class AppService {
    private final RestTemplate restTemplate;

    @Autowired
    public AppService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String startVerification(PurchaseData purchaseData) throws IOException{
      String accessToken = getAccessToken();
      String message ="";
      ProductPurchaseResponse purchaseDetail = getPurchaseStatus(purchaseData, accessToken);
      if (purchaseDetail.getPurchaseState() == 0 && purchaseDetail.getConsumptionState() == 0) {
          String response = getPurchaseConsumed(purchaseData, accessToken);
          if(response.equals("204 NO_CONTENT")){
               message = "purchase is acknowledged and conseumd ";
          }
          else
               message = "invalid Token";
        }
         return message;
    }
    public String getAccessToken() throws IOException {

        String url = "https://accounts.google.com/o/oauth2/token";

        TokenRequest request = new TokenRequest();
        request.setGrant_type(AppConstant.Const.GRANT_TYPE);
        request.setRefresh_token(AppConstant.Const.REFRESH_TOKEN);
        request.setClient_id(AppConstant.Const.CLIENT_ID);
        request.setClient_secret(AppConstant.Const.CLIENT_SECRET);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<TokenRequest> entity = new HttpEntity<>(request, headers);
        ResponseEntity<OAuthResponse> response = restTemplate.postForEntity(url, entity, OAuthResponse.class);
        OAuthResponse oAuthResponse = response.getBody();
        assert oAuthResponse != null;
        return oAuthResponse.getAccess_token();
    }



    public ProductPurchaseResponse getPurchaseStatus(PurchaseData purchaseData, String accessToken) throws IOException {
        String url = "https://androidpublisher.googleapis.com/androidpublisher/v3/applications/"
                + purchaseData.getPackageName() + "/purchases/products/" + purchaseData.getProductId() + "/tokens/" + purchaseData.getPurchaseToken();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        HttpEntity<Void> entity = new HttpEntity<>(headers);
        ResponseEntity<ProductPurchaseResponse> response = restTemplate.exchange(url, HttpMethod.GET, entity, ProductPurchaseResponse.class);
        ProductPurchaseResponse productPurchaseResponse = response.getBody();
        return productPurchaseResponse;
    }



    public String getPurchaseConsumed(PurchaseData purchaseData, String accessToken){
        String url = "https://androidpublisher.googleapis.com/androidpublisher/v3/applications/"
                + purchaseData.getPackageName() + "/purchases/products/" + purchaseData.getProductId() + "/tokens/" + purchaseData.getPurchaseToken() + ":consume";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + accessToken);


        HttpEntity<Object> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);
        return  response.getStatusCode().toString();

    }


}
