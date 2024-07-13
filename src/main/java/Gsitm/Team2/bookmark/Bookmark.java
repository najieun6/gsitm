package Gsitm.Team2.bookmark;

import Gsitm.Team2.data.Data;
import Gsitm.Team2.user.User;
import jakarta.persistence.*;

@Entity
public class Bookmark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean isActive;

    @ManyToOne
    private Data data;

    @ManyToOne
    private User user;

}
