package lk.ijse.NoteTakerV2.service;

import jakarta.transaction.Transactional;
import lk.ijse.NoteTakerV2.Util.AppUtil;
import lk.ijse.NoteTakerV2.Util.Mapping;
import lk.ijse.NoteTakerV2.customObj.UserErrorResponse;
import lk.ijse.NoteTakerV2.customObj.UserResponse;
import lk.ijse.NoteTakerV2.dao.UserDao;
import lk.ijse.NoteTakerV2.dto.impl.UserDTO;
import lk.ijse.NoteTakerV2.entity.UserEntity;
import lk.ijse.NoteTakerV2.exeption.DataPersistFailedException;
import lk.ijse.NoteTakerV2.exeption.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    @Autowired
    private final UserDao userDao;

    @Autowired
    private Mapping mapping;

    @Override
    public void saveUser(UserDTO userDTO) {

        userDTO.setUserId(AppUtil.createUserID());
        UserEntity saveUser = userDao.save(mapping.convertToUserEntity(userDTO));
        if (saveUser == null  &&  saveUser.getUserId() == null) throw new DataPersistFailedException( "Cannot Data Saved");

    }

    @Override
    public void updateUser(String userId, UserDTO userDTO) {

        Optional<UserEntity> tmpUserEntity = userDao.findById(userId);
        if (!tmpUserEntity.isPresent()) { throw  new UserNotFoundException("User Not Found !!"); }
        else{
            tmpUserEntity.get().setFirstName(userDTO.getFirstName());
            tmpUserEntity.get().setLastName(userDTO.getLastName());
            tmpUserEntity.get().setEmail(userDTO.getEmail());
            tmpUserEntity.get().setPassword(userDTO.getPassword());
            tmpUserEntity.get().setProfilePic(userDTO.getProfilePic());
        }

    }

    @Override
    public void deleteUser(String userId) {
        Optional<UserEntity> tmpEntity = userDao.findById(userId);
        if (  !tmpEntity.isPresent() ) {
            throw new UserNotFoundException("User Not Found");
        } else {
            userDao.deleteById(userId);
        }
    }

    @Override
    public UserResponse getSelectedUser(String userId) {

        if (userDao.existsById(userId)){
            UserEntity userEntityByUserId = userDao.getUserEntityByUserId(userId);
            return mapping.convertToUserDTO(userEntityByUserId);
        } else {
            return new UserErrorResponse(0, "User not found");
        }

    }

    @Override
    public List<UserDTO> getAllUser() {
        return mapping.convertToUserDTOList(userDao.findAll());
    }

    @Override
    public UserDetailsService userDetailsService() {
        return email ->
                userDao.findByEmail(email)
                        .orElseThrow(()-> new UserNotFoundException("User Not Found "));
    }

}
