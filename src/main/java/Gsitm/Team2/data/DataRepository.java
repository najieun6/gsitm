package Gsitm.Team2.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DataRepository extends JpaRepository <Data, Long> {
    List<Data> findAllByTitle(String title);

}
