package Gsitm.Team2;

import Gsitm.Team2.bookmark.BookmarkService;
import Gsitm.Team2.bookmark.BookmarkRequestDto;
import Gsitm.Team2.data.Data;
import Gsitm.Team2.data.DataRepository;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserRecord;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import java.util.NoSuchElementException;

import static Gsitm.Team2.MockGoogleCredentials.getMockCredentialOptions;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class BookmarkTest {

    @Autowired
    private BookmarkService bookmarkService;

    @Autowired
    private DataRepository dataRepository;

    @MockBean
    private FirebaseAuth firebaseAuth;


    FirebaseApp firebaseApp = FirebaseApp.initializeApp(getMockCredentialOptions(), "myApp");

    @Test
    public void saveBookmark() throws Exception {

        UserRecord.CreateRequest request = new UserRecord.CreateRequest()
                .setEmail("user@example.com")
                .setEmailVerified(false)
                .setPassword("secretPassword")
                .setPhoneNumber("+11234567890")
                .setDisplayName("John Doe")
                .setPhotoUrl("http://www.example.com/12345678/photo.png")
                .setDisabled(false);

        UserRecord userRecord = FirebaseAuth.getInstance().createUser(request);

        // 가짜 이벤트 데이터 생성 및 저장
        Data data = Data.builder()
                .event_id(1L)
                .title("제목")
                .guname("강남구")
                .place("어쩌구아트홀")
                .org_link("https://www.example.com")
                .build();
        dataRepository.save(data);

        // saveBookmark 메서드 호출
        BookmarkRequestDto dto = new BookmarkRequestDto(userRecord.getUid(), data.getEvent_id());


        // 응답 확인
//        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
//        assertThat(response.getBody()).isEqualTo("북마크가 저장되었습니다. id:" + 1L);

    }

}
