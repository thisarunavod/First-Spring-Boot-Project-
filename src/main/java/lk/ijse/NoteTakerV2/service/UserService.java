package lk.ijse.NoteTakerV2.service;

import lk.ijse.NoteTakerV2.customObj.UserResponse;
import lk.ijse.NoteTakerV2.dto.impl.UserDTO;

import java.util.List;

public interface UserService {
    void saveUser(UserDTO userDTO);
    void updateUser(String userId,UserDTO userDTO) ;
    void deleteUser( String userId );
    UserResponse getSelectedUser(String userId);
    List<UserDTO> getAllUser();
}
