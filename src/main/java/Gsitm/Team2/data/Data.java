package Gsitm.Team2.data;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
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

    @Builder
    public Data(Long event_id,
     String org_name,
     String use_fee,
     String player,
     String org_link,
     String main_img,
     String themecode,
     String date,
     String guname,
     String etc_desc,
     String end_date,
     String title,
     String codename,
     String user_trgt,
     String program,
     String place,
     String start_date,
     String is_free){
        this.event_id = event_id;
        this.org_name = org_name;
        this.use_fee = use_fee;
        this.player = player;
        this.org_link = org_link;
        this.guname = guname;
        this.main_img = main_img;
        this.themecode = themecode;
        this.date = date;
        this.etc_desc = etc_desc;
        this.end_date = end_date;
        this.title = title;
        this.codename = codename;
        this.user_trgt = user_trgt;
        this.program = program;
        this.start_date = start_date;
        this.place = place;
        this.is_free = is_free;
    }

    public String getPlace() {
        return place;
    }

    public String getTitle() {
        return title;
    }

    public String getMain_img() {
        return main_img;
    }

    public String getDate() {
        return date;
    }
}
