package Gsitm.Team2.bookmark;

import Gsitm.Team2.data.Data;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.checkerframework.common.aliasing.qual.NonLeaked;

@Entity
@NoArgsConstructor
@Getter
public class Bookmark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean isActive;

    @ManyToOne
    private Data data;

    private String uid;

    @Builder
    public Bookmark(Long id, boolean isActive, Data data, String uid){
        this.id = id;
        this.isActive = isActive;
        this.data = data;
        this.uid = uid;
    }

    public void toggle(){
        this.isActive=!isActive;
    }
}
