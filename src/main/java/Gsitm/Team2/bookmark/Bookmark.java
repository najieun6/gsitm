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

    @ManyToOne
    private Data data;

    private String uid;

    private boolean isActive;
    @Builder
    public Bookmark(Long id, Data data, String uid){
        this.id = id;
        this.data = data;
        this.uid = uid;

    }


}
