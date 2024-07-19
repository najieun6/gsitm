//package Gsitm.Team2.bookmark;
//
//import Gsitm.Team2.data.EventListResponseDto;
//import com.google.firebase.auth.FirebaseAuthException;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/bookmark")
//public class BookmarkController {
//    private final BookmarkService bookmarkService;
//    public BookmarkController(BookmarkService bookmarkService) {
//        this.bookmarkService = bookmarkService;
//    }
//
//    @PostMapping("/")
//    public ResponseEntity<String> saveBookmark(@RequestBody BookmarkRequestDto dto) throws FirebaseAuthException{
//        return bookmarkService.saveBookmark(dto);
//    }
//
//    @PatchMapping("/")
//    public String deleteBookmark(@RequestBody DeleteBookmarkDto dto) throws FirebaseAuthException {
//        return bookmarkService.deleteBookmark(dto);
//    }
//
//    @GetMapping("/all")
//    public List<EventListResponseDto> findUserBookmark(@RequestHeader String idToken) throws FirebaseAuthException {
//        return bookmarkService.findUserBookmark(idToken);
//    }
//
//}
