package tdd.ch08.auth;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class LoginServiceTest {

  private static LoginService loginService = new LoginService();

  final private static String id = "id";
  final private static String[] password = {"baskey", "fail", "success"};

  @BeforeAll
  static void setUp(){

    AuthService authService = mock(AuthService.class);

    given(authService.authenticate(id, password[0])).willReturn(-1);
    given(authService.authenticate(id, password[1])).willReturn(0);
    given(authService.authenticate(id, password[2])).willReturn(1);

    loginService.setAuthService(authService);
  }

  @Test
  void authKey_실패() {
    assertEquals(LoginResult.BAD_AUTH_KEY, loginService.login(id, password[0]));
  }

  @Test
  void 로그인_실패() {
    assertEquals(LoginResult.FAIL, loginService.login(id, password[1]));
  }
}