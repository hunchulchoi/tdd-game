package tdd.ch08.daily;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

public class DailyBatchLoader {

  final private String basePath = "/Users/hunchulchoi/test/";
  private Times times = new Times();

  public void setTimes(Times times) {
    this.times = times;
  }

  public int load() throws IOException {
    LocalDate date = times.today();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    Path batchPath = Paths.get(basePath, date.format(formatter), "log");

    String lines = Files.readString(batchPath).replace(System.lineSeparator(), "");

    return Arrays.asList(lines.split(" ")).stream().mapToInt(Integer::parseInt).sum();
  }

  class Times{

    public LocalDate today(){
      return LocalDate.now();
    }
  }
}
