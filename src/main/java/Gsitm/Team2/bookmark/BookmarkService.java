package Gsitm.Team2.bookmark;
import Gsitm.Team2.data.Data;
import Gsitm.Team2.data.DataRepository;
import Gsitm.Team2.data.EventListResponseDto;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import jakarta.validation.constraints.Max;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class BookmarkService {
    private final DataRepository dataRepository;
    private final BookmarkMapper bookmarkMapper;
    private final BookmarkRepository bookmarkRepository;

    public BookmarkService(DataRepository dataRepository, BookmarkMapper bookmarkMapper, BookmarkRepository bookmarkRepository) {
        this.dataRepository = dataRepository;
        this.bookmarkMapper = bookmarkMapper;
        this.bookmarkRepository = bookmarkRepository;
    }

    public ResponseEntity<String> saveBookmark(BookmarkRequestDto dto) throws FirebaseAuthException {
        FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(dto.idToken());
        String uid = decodedToken.getUid();
        Data data = dataRepository.findById(dto.eventId()).orElse(null);
        if(data==null||uid==null){
            throw new NoSuchElementException("요청값을 확인해주세요");
        }
        Bookmark bookmark = Bookmark.builder()
                .uid(uid)
                .data(data)
                .isActive(true)
                .build();
        if(bookmarkRepository.findByUserIdAndEventId(uid, dto.eventId()).isEmpty()){
            bookmarkRepository.save(bookmark);
        } else {
            return ResponseEntity.status(405).body("중복저장불가");
        }
        return ResponseEntity.status(201).body("북마크가 저장되었습니다. id:" + bookmark.getId());
    }


    @Transactional
    public String deleteBookmark(DeleteBookmarkDto deleteBookmarkDto) throws FirebaseAuthException {
        FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(deleteBookmarkDto.idToken());
        String uid = decodedToken.getUid();
        Data data = dataRepository.findById(deleteBookmarkDto.eventId()).orElse(null);
        if(data==null||uid==null){
            throw new NoSuchElementException("해당하는 행사 / 유저 정보가 존재하지 않습니다");
        }
        Bookmark bookmark = bookmarkRepository.findByUserIdAndEventId(uid,deleteBookmarkDto.eventId()).orElse(null);
        if(bookmark==null){
            throw new NoSuchElementException("해당하는 북마크가 없습니다");
        }
        Bookmark bookmarkBuilder = Bookmark.builder()
                .isActive(false)
                .build();
        bookmarkRepository.save(bookmarkBuilder);
        return "북마크가 해제되었습니다.";
    }

    @Transactional(readOnly = true)
    public List<EventListResponseDto> findUserBookmark(String idToken) throws FirebaseAuthException {
        FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(idToken);
        String uid = decodedToken.getUid();
        Optional<Bookmark> bookmarks = bookmarkRepository.findByUid(uid);
        if(bookmarks.isEmpty()){
            throw new NoSuchElementException("북마크를 찾을 수 없습니다");
        }
        List<EventListResponseDto> eventList = bookmarkMapper.findBookmark(uid);
        return eventList;
    }




}
