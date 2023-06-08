package peaksoft.dto.dtoAuth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import peaksoft.enums.Role;
@Data
@Builder
@AllArgsConstructor
public class AuthenticationResponse {
    private String token;
    private String email;
    private Role role;
}
