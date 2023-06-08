package peaksoft.dto.dtoAuth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import peaksoft.enums.Role;
@Builder
@Data
@AllArgsConstructor
public class SignUpRequest {
    private String email;
    private String password;
    private Role role;
}
