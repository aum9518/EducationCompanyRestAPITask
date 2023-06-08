package peaksoft.service;

import peaksoft.dto.dtoAuth.AuthenticationResponse;
import peaksoft.dto.dtoAuth.SignInRequest;
import peaksoft.dto.dtoAuth.SignUpRequest;

public interface AuthenticationService {

    AuthenticationResponse signUp(SignUpRequest signUpRequest);
    AuthenticationResponse signIn(SignInRequest signInRequest );
}
