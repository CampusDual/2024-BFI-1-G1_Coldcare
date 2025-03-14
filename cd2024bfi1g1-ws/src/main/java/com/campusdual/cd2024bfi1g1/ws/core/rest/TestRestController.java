package com.campusdual.cd2024bfi1g1.ws.core.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.campusdual.cd2024bfi1g1.openapi.core.service.ITestApi;

@RestController
@RequestMapping("/test")
public class TestRestController implements ITestApi {

	@Value("${firebase.serviceAccountKey}")
	private String FIREBASE_SERVICE_ACCOUNT_KEY;

	@Override
	public ResponseEntity<String> testRest() {
		return new ResponseEntity<>("It Works!", HttpStatus.OK);
	}

	@RequestMapping("/test")
	public ResponseEntity<String> testsRest() {
		System.out.println(FIREBASE_SERVICE_ACCOUNT_KEY);
		return new ResponseEntity<>("It Works!", HttpStatus.OK);
	}




}
