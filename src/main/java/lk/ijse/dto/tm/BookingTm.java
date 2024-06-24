package lk.ijse.dto.tm;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
public class BookingTm {
    private String bookingId;
    private String packageId;
    private String identityDetails;
    private String bookingDate;
    private String payId;
    private String roomId;

}
