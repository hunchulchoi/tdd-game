package tdd.ch08.daily;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.Time;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class DailyBatchLoaderTest {

  @Test
  void loadTest() throws IOException {

    DailyBatchLoader loader = new DailyBatchLoader();

    DailyBatchLoader.Times timesMock = mock(DailyBatchLoader.Times.class);
    given(timesMock.today()).willReturn(LocalDate.of(2022, 1, 5));

    loader.setTimes(timesMock);

    assertEquals(6, loader.load());
  }
}