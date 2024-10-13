package lk.ijse.NoteTakerV2.dto.impl;

import lk.ijse.NoteTakerV2.customObj.NoteResponse;
import lk.ijse.NoteTakerV2.dto.SuperDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class NoteDTO implements SuperDTO , NoteResponse {

    private String noteId;
    private String noteTitle;
    private String noteDesc;
    private String priorityLevel;
    private String creatDate;
    private String userId;

}
