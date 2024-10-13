package lk.ijse.NoteTakerV2.dao;

import lk.ijse.NoteTakerV2.entity.NoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository  /* spring wisin manage karanawaa*/
public interface NoteDao  extends JpaRepository<NoteEntity,String /*<---  primary key eke data type eka */> {

}
