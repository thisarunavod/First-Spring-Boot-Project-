package lk.ijse.NoteTakerV2.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

@Entity
@Table(name = "notes")
public class NoteEntity implements SuperEntity {

    @Id
    private String noteId;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private UserEntity user;

    private String noteTitle;
    private String noteDesc;
    private String priorityLevel;
    private String creatDate;


}
