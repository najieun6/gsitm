package Gsitm.Team2.data;

import lombok.Getter;
import lombok.NoArgsConstructor;

public record DataResponse (
        String title,
        Long event_id,
        String date,
        String place,
        String main_img

)
{
}
