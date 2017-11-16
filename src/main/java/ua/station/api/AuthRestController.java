package ua.station.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import ua.station.security.JwtAuthenticationRequest;
import ua.station.security.JwtAuthenticationResponse;
import ua.station.util.JwtTokenUtil;

/**
 * Created by sa on 10.11.17.
 */
@RestController
@RequestMapping("/api/v1/token")
public class AuthRestController {
    private static final String header = "Authorization";

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;


    @RequestMapping(method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationRequest jwtAuthRequest) throws AuthenticationException {

        //JwtAuthenticationRequest jwtAuthRequest=new JwtAuthenticationRequest(name,pass);
        // Perform the security
        try {


        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        jwtAuthRequest.getUsername(),
                        jwtAuthRequest.getPassword()
                )
        );
        //SecurityContextHolder.getContext().setAuthentication(authentication); //statless

        // Reload password post-security so we can generate token
        final UserDetails userDetails = userDetailsService.loadUserByUsername(jwtAuthRequest.getUsername());
        final String token = JwtTokenUtil.generateToken(userDetails);

        // Return the token
        return ResponseEntity.ok(new JwtAuthenticationResponse(token));
        }catch (AuthenticationException ex){
            return ResponseEntity.status(401).body("");
        }

    }
}
