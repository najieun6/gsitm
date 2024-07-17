package Gsitm.Team2.data;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Data {

    @Id
    private Long event_id;
    private String org_name;
    private String use_fee;
    private String player;
    private String org_link;
    private String guname;
    private String main_img;
    private String themecode;
    private String date;
    private String etc_desc;
    private String end_date;
    private String title;
    private String codename;
    private String user_trgt;
    private String program;
    private String start_date;
    private String place;
    private String is_free;





}
