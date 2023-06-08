package peaksoft.service.serviceImpl;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import peaksoft.config.JWTService;
import peaksoft.dto.dtoAuth.AuthenticationResponse;
import peaksoft.dto.dtoAuth.SignInRequest;
import peaksoft.dto.dtoAuth.SignUpRequest;
import peaksoft.entity.User;
import peaksoft.repository.UserRepository;
import peaksoft.service.AuthenticationService;
@Service
@Transactional
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository repository;
    private final JWTService service;
    private final PasswordEncoder passwordEncoder;
    @Override
    public AuthenticationResponse signUp(SignUpRequest signUpRequest) {
        if (repository.existsByEmail(signUpRequest.getEmail())){
            throw new EntityExistsException(String.format("User with email: % is already exist",signUpRequest.getEmail()));
        }

        User user = User.builder()
                .email(signUpRequest.getEmail())
                .password(passwordEncoder.encode(signUpRequest.getPassword()))
                .role(signUpRequest.getRole())
                .build();
        repository.save(user);

        String jwtToken = service.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }

    @Override
    public AuthenticationResponse signIn(SignInRequest signInRequest) {
        User user = repository.getUserByEmail(signInRequest.getEmail()).orElseThrow(() -> new EntityNotFoundException("User with id: " + signInRequest.getEmail() + " is not found"));
        if (signInRequest.getEmail().isBlank()){
           throw new BadCredentialsException("email does not exist...");
       }
        if (!passwordEncoder.matches(signInRequest.getPassword(), user.getPassword())){
            throw new BadCredentialsException("Incorrect password");
        }

        String jwtToken=service.generateToken(user);
        return AuthenticationResponse.builder()
                .email(user.getEmail())
                .role(user.getRole())
                .token(jwtToken)
                .build();
    }
}
