package lk.ijse.NoteTakerV2.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data

@Entity
@Table(name = "user")
public class UserEntity implements SuperEntity, UserDetails {

    @Id
    private String userId;

    private String firstName;

    private String lastName;

    @Column(unique = true)
    private String email;

    private String password;

    @Column(columnDefinition = "LONGTEXT")
    private String profilePic;

    @OneToMany(mappedBy = "user")
    private List<NoteEntity> notes;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }
}
