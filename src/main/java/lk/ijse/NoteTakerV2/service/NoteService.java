package lk.ijse.NoteTakerV2.service;

import lk.ijse.NoteTakerV2.customObj.NoteResponse;
import lk.ijse.NoteTakerV2.dto.impl.NoteDTO;

import java.util.List;

public  interface NoteService  {

    void saveData(NoteDTO noteDTO);
    void updateNote(String noteId,NoteDTO noteDTO);
    void deleteNote( String noteId );
    NoteResponse getSelectedNote(String noteId);
    List<NoteDTO> getAllNotes();



}
