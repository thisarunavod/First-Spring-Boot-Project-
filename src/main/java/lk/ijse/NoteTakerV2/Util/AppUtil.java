package lk.ijse.NoteTakerV2.Util;

import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.UUID;

@Component
public class AppUtil {

    public static String createNoteID(){
        return "NOTE "+UUID.randomUUID();
    }
    public static String createUserID(){
        return "USER "+UUID.randomUUID();
    }
    public static String toBase64ProfilePic(byte[] profilePic){
        // base64 formatt ekata ape file format eka convert karaa  //
        return Base64.getEncoder().encodeToString(profilePic);

    }

}
