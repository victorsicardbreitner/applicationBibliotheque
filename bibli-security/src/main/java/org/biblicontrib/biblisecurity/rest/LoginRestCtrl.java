package org.biblicontrib.biblisecurity.rest;


import org.biblicontrib.biblisecurity.dto.LoginRequest;
import org.biblicontrib.biblisecurity.dto.LoginResponse;
import org.biblicontrib.biblisecurity.util.JwtTokenProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@CrossOrigin("*")
@Profile("withSecurity")
@RequestMapping(value="/api-login/public/login" , headers="Accept=application/json")
public class LoginRestCtrl {
	
	private static Logger logger = LoggerFactory.getLogger(LoginRestCtrl.class);
	
	@Autowired
    private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	//http://localhost:8080/..../api-login/public/login
	//avec { "username" : "user1" , "password" : "pwd1" }
	//en retour { "message" :  "..." , "token" ; "..." , ...}
	
	@PostMapping("")
	public ResponseEntity<LoginResponse> postLogin(@RequestBody LoginRequest loginRequest){
		LoginResponse loginResponse = new LoginResponse();
		loginResponse.setUsername(loginRequest.getUsername());
		/* if(loginRequest.getUsername().equals("user1") && 
				loginRequest.getPassword().equals("pwd1")) */
		try {
			Authentication authentication = null;

			UsernamePasswordAuthenticationToken upat = null;
			upat = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());
			
			authentication=authenticationManager.authenticate(upat);//authenticate() soulève une exception si mauvais username ou password
			
			
			loginResponse.setOk(true);
			loginResponse.setMessage("successful login");
			loginResponse.setToken(jwtTokenProvider.generateToken(authentication)); //envoie le token vers le JSON grace au login.js
		}catch(Exception ex) {
			
			loginResponse.setOk(false);
			loginResponse.setMessage("echec auth : login failed");
			loginResponse.setToken(null);
			return new ResponseEntity<LoginResponse>(loginResponse,HttpStatus.UNAUTHORIZED);
		}
		return new ResponseEntity<LoginResponse>(loginResponse,HttpStatus.OK);
	}
	



}

