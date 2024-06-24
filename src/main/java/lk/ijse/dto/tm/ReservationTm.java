package lk.ijse.dto.tm;



import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode

public class ReservationTm {
    private String id;
    private String guestName;
    private String checkInDate;
    private String checkOutDate;
    private String roomType;
    private String numGuests;
    private String roomId;
}
