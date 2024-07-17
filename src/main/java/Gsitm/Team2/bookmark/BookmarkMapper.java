package Gsitm.Team2.bookmark;

import Gsitm.Team2.data.EventListResponseDto;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
@Mapper
public interface BookmarkMapper {
    List<EventListResponseDto> findBookmark(String uid) ;
}
