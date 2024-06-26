package com.application.HairSalonManagementSystem.controllers;

import com.application.HairSalonManagementSystem.dto.AuthenticationRequest;
import com.application.HairSalonManagementSystem.entities.User;
import com.application.HairSalonManagementSystem.repositories.HairdresserRepository;
import com.application.HairSalonManagementSystem.repositories.UserRepository;
import com.application.HairSalonManagementSystem.utils.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Optional;

@RestController
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;


    @Autowired
    private UserRepository userRepository;
    private HairdresserRepository hairdresserRepository;

    public static final String TOKEN_PREFIX = "Bearer";
    public static final String HEADER_STRING = "Authorization";

    @PostMapping("/authenticate")
    public void createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest, HttpServletResponse response) throws IOException, JSONException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword()));
        } catch (BadCredentialsException e) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Login failed: Incorrect Username or password");
            return;
        } catch (DisabledException e) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "User is not created");
            return;
        }


        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
        Optional<User> optionalUser = userRepository.findFirstByEmail(userDetails.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails.getUsername());

        if (optionalUser.isPresent()) {
            JSONObject responseJson = new JSONObject();
            responseJson.put("userId", optionalUser.get().getId());
            responseJson.put("role", optionalUser.get().getRole());
            responseJson.put("hairdresser_id", optionalUser.get().getHairdresserId());
            responseJson.put("email",optionalUser.get().getEmail());
            responseJson.put("phoneNumber",optionalUser.get().getPhoneNumber());
            response.getWriter().write(responseJson.toString());
        }


        response.setHeader("Access-Control-Expose-Headers", "Authorization");
        response.setHeader("Access-Control-Allow-Headers", "Authorization, X-Pingother,Origin,X-Requested-With,Content-Type,Accept, X-Custom-header");
        response.setHeader(HEADER_STRING, TOKEN_PREFIX + jwt);

    }
}