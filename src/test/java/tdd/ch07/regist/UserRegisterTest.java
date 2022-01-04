package tdd.ch07.regist;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 회원 가입 기능 테스트
 */
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class UserRegisterTest {

  private UserRegister userRegister;
  private StubWeakPasswordChecker stubPasswordChecker = new StubWeakPasswordChecker();
  private MemoryUserRepository fakeRepository = new MemoryUserRepository();
  private SpyEmailNotifier spyEmailNotifier = new SpyEmailNotifier();

  @BeforeEach
  void setUp() {
    userRegister = new UserRegister(stubPasswordChecker, fakeRepository, spyEmailNotifier);
  }

  @Test
  void 약한_암호면_가입실패() {
    stubPasswordChecker.setWeak(true);

    assertThrows(
        WeakPasswordException.class,
        () -> userRegister.register(new User("id", "pw", "email")));
  }

  @Test
  void 이미_같은_ID가_존재하면_가입_실패() {

    final String fakeDupicateId = "id";

    fakeRepository.save(new User(fakeDupicateId, "pw1", "email1@email.com"));

    assertThrows(
        DuplicateIdException.class,
        () -> userRegister.register(new User(fakeDupicateId, "pw2", "email2@email.com")));
  }

  @Test
  void 같은_ID가_없으면_가입_성공(){
    User user = new User("id", "pw3", "email3@email.com");

    userRegister.register(user);

    assertEquals(user, fakeRepository.findById(user.id()).get());
  }

  @Test
  void 가입하면_이메일을_전송함() {

    User user = new User("id", "ps4", "email4@email.com");

    userRegister.register(user);

    assertTrue(spyEmailNotifier.isCalled());
    assertEquals(user.email(), spyEmailNotifier.getEmail());
  }
}
