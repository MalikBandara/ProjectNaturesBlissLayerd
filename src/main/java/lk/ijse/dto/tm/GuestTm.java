package lk.ijse.dto.tm;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode

public class GuestTm {

    private String identityDetails;
    private String name;
    private String password;
    private String email;

}
