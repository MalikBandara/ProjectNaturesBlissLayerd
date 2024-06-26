package lk.ijse.dto.tm;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
public class ServiceTm {
    private String serviceId;
    private String name;
    private String description;
    private String employeeId;
}
