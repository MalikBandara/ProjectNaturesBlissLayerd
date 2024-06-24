package lk.ijse.dto.tm;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode

public class AdminTm {
    private String email;
    private String username;
    private String password;
    private String type;
}
