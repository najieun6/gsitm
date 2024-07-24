package Gsitm.Team2.bookmark;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface BookmarkRepository extends JpaRepository<Bookmark,Long> {
    @Query("SELECT b FROM Bookmark b WHERE b.uid = :uid AND b.data.event_id = :event_id")
    Optional<Bookmark> findByUserIdAndEventId(@Param("uid") String uid, @Param("event_id") Long event_id);

    List<Bookmark> findByUid(String uid);
}
