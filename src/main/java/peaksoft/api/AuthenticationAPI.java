package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import peaksoft.dto.dtoAuth.AuthenticationResponse;
import peaksoft.dto.dtoAuth.SignInRequest;
import peaksoft.dto.dtoAuth.SignUpRequest;
import peaksoft.service.AuthenticationService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationAPI {

    private final AuthenticationService service1;
    @PostMapping("/signUp")
    AuthenticationResponse signUp(@RequestBody SignUpRequest signUpRequest){
        return service1.signUp(signUpRequest);
    }

    @PostMapping("/signIn")
    public AuthenticationResponse signIn(@RequestBody SignInRequest signInRequest){
        return service1.signIn(signInRequest);
    }

}
