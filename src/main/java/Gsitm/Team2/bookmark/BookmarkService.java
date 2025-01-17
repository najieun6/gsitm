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

    public ResponseEntity<String> saveBookmark(BookmarkRequestDto dto) {
        try {
            FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(dto.idToken());
            String uid = decodedToken.getUid();

            Data data = dataRepository.findById(dto.eventId()).orElse(null);
            if (data == null) {
                throw new NoSuchElementException("요청값을 확인해주세요");
            }

            Bookmark bookmark = Bookmark.builder()
                    .uid(uid)
                    .data(data)
                    .build();

            if (bookmarkRepository.findByUserIdAndEventId(uid, dto.eventId()).isEmpty()) {
                bookmarkRepository.save(bookmark);
            } else {
                throw new IllegalArgumentException("북마크 중복 저장 불가");
            }

            return ResponseEntity.status(201).body("북마크가 저장되었습니다. id:" + bookmark.getId());
        } catch (FirebaseAuthException e) {
            // Firebase 인증 실패하면
            return ResponseEntity.status(401).body("인증을 실패했습니다.: " + e.getMessage());
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(501).body("서버오류: " + e.getMessage());
        }
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
        bookmarkRepository.delete(bookmark);
        return "북마크가 해제되었습니다.";
    }

    @Transactional(readOnly = true)
    public List<EventListResponseDto> findUserBookmark(String idToken) throws FirebaseAuthException {

        FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(idToken);
        String uid = decodedToken.getUid();
        List<Bookmark> bookmarks = bookmarkRepository.findByUid(uid);
        if(bookmarks.isEmpty()){
            throw new NoSuchElementException("북마크를 찾을 수 없습니다");
        }
        List<EventListResponseDto> eventList = bookmarkMapper.findBookmark(uid);
        return eventList;
    }

}
