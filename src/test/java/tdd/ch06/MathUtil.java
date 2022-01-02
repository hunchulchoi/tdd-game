package tdd.ch06;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class MathUtil {
  public static long sum(File dataFile) {

    try {
      return Files.lines(dataFile.toPath(), StandardCharsets.UTF_8).mapToLong(Long::parseLong).sum();
    } catch (IOException e) {
      throw new IllegalArgumentException("파일 없음:" + dataFile.getName(), e);
    }
  }
}
