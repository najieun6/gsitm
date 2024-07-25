package Gsitm.Team2.data;

public record EventListResponseDto(
        String title,
        String date,
        String place,
        String main_img,
        Long event_id // event_id 추가함.
) {
}

