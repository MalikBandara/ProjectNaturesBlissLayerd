package lk.ijse.dto.tm;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
public class VehicleTm {
    private String vehicleId;
    private String numberOfSeats;
    private String status;
    private String employeeId;

}
