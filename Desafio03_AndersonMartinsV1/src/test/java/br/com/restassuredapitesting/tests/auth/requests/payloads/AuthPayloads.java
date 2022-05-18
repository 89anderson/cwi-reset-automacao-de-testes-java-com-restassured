package br.com.restassuredapitesting.tests.auth.requests.payloads;

import org.json.JSONObject;

public class AuthPayloads {

    public JSONObject jsonAuthLogin() {
        JSONObject payloadLogin = new JSONObject();

        payloadLogin.put("username", "admin");
        payloadLogin.put("password", "password123");

        return payloadLogin;
    }
}
