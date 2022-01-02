package tdd.ch06;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * 외부 상황에 대한 테스트
 */
public class MathUtilTest {

  final private String tempPath = "/Users/hunchulchoi/test";

  @Test
  void 파일이_없을때_예외() {

    final String badPath = "badpath.txt";
    givenNoFile(badPath);

    final File dataFile = new File(badPath);
    assertThrows(IllegalArgumentException.class, ()-> MathUtil.sum(dataFile));

  }

  @Test
  void 파일이_있을때_합계출력(){
    final String path = "data.txt";

    givenDataFile(path, "1", "2", "3", "4");

    assertEquals(10L, MathUtil.sum(new File(path)));
  }

  /**
   * 테스트에서 파일이 없는 경우를 보장하기 위한 파일
   * 파라미터로 받은 경로에 파일이 존재하는 경우 삭제한다.
   * @param path
   */
  private void givenNoFile(String path) {

    Path path1 = Paths.get(tempPath, path);

    if(Files.exists(path1)) {
      try {
        Files.delete(path1);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

  }

  /**
   * 테스트에서 파일이 있는 경우를 보장하기 위해
   * 기존에 파일이 있는 경우 삭제하고 새로 파일을 생성한다.
   * @param path
   * @param lines
   */
  private void givenDataFile(String path, String... lines){
    try{
      Path dataPath = Paths.get(tempPath, path);
      if( Files.exists(dataPath)){
        Files.delete(dataPath);
      }
      Files.write(dataPath, Arrays.asList(lines));
    }catch (IOException e){
      throw new RuntimeException(e);
    }
  }
}

