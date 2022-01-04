package tdd.mock;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.mock;

public class GameGenMockTest {

  @Test
  void mockTest() {

    // mock 생성
    GameNumGen genMock = mock(GameNumGen.class);

    // stub 설정
    given(genMock.generate(GameLevel.EASY)).willReturn("123");

    // stub 메소드 실행
    String num = genMock.generate(GameLevel.EASY);

    assertEquals("123", num);
  }

  @Test
  void mockThrowTest() {

    // mock 생성
    GameNumGen genMock = mock(GameNumGen.class);

    // stub 설정
    given(genMock.generate(null)).willThrow(IllegalArgumentException.class);

    assertThrows(IllegalArgumentException.class,()-> genMock.generate(null));
  }

  @Test
  void voidMethodWillThrow() {
    List<String> mockList = mock(List.class);

    // void 메소드에 will throw를 stubbing하기 위해서는 BDDMockito.willThrow를 사용한다.
    willThrow(UnsupportedOperationException.class).given(mockList).clear();

    assertThrows(UnsupportedOperationException.class, () -> mockList.clear());
  }

  @Test
  void anyMatchTest() {
    GameNumGen genMock = mock(GameNumGen.class);

    given(genMock.generate(any())).willReturn("456");

    assertEquals("456", genMock.generate(null));
    assertEquals("456", genMock.generate(GameLevel.EASY));
    assertEquals("456", genMock.generate(GameLevel.NORMAL));
  }

  @Test
  void anyMatchAndEqTest() {

    List<String> mockList = mock(List.class);

    given(mockList.set(anyInt(), eq("123"))).willReturn("456");

    assertEquals("456", mockList.set(1, "123"));
  }

  @Test
  void 행위검증(){
    GameNumGen genMock = mock(GameNumGen.class);

    Game game = new Game(genMock);
    game.init(GameLevel.EASY);

    // game.init(level)을 실행했을때 mock객체의 generate(level)객체가 실행되었는지 검증한다.
    then(genMock).should().generate(GameLevel.EASY);

    // mock 객체의 메소드가 정확하게 실행되었는지 확인 only
    then(genMock).should(only()).generate(GameLevel.EASY);
  }

  @Test
  void 인자캡처() {

    GameNumGen genMock = mock(GameNumGen.class);

    Game game = new Game(genMock);
    game.init(GameLevel.NORMAL);

    // mock 객체의 메소드를 호출할때 사용한 인자를 캡처한다.
    ArgumentCaptor<GameLevel> captor = ArgumentCaptor.forClass(GameLevel.class);

    // 캡처 객체를 인자로 전달
    then(genMock).should().generate(captor.capture());

    assertEquals(GameLevel.NORMAL, captor.getValue());
  }
}
