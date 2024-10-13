package lk.ijse.NoteTakerV2.jwtModels;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SignIn {
    private String email;
    private String password;

}
