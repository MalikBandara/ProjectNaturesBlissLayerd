package lk.ijse.dto.tm;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode

public class PackageTm {

    private String packageId;
    private String name;
    private String type;
    private double price;


}
