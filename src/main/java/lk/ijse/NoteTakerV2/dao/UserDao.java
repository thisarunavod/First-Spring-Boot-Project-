package lk.ijse.NoteTakerV2.dao;

import lk.ijse.NoteTakerV2.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository< UserEntity , String > {
//    @Query()
    UserEntity getUserEntityByUserId(String userId);
}
