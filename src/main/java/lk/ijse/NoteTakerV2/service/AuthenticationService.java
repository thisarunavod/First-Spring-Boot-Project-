package lk.ijse.NoteTakerV2.service;

import lk.ijse.NoteTakerV2.dto.impl.UserDTO;
import lk.ijse.NoteTakerV2.jwtModels.JwtAuthResponse;
import lk.ijse.NoteTakerV2.jwtModels.SignIn;

public interface AuthenticationService {
    JwtAuthResponse signIn(SignIn signIn);
    JwtAuthResponse signUp(UserDTO signUp);
    JwtAuthResponse refreshToken(String accessToken);

}
