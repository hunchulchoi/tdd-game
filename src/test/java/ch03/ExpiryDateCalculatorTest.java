package ch03;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 서비스 만료일 계산기 테스트 
 * 1. 서비스를 사용하려면 매달 1만원을 선불로 납부한다. 납부일 기준으로 한달뒤가 서비스 만료일이다.
 * 2. 2개월 이상 요금을 납부할수 있다.
 * 3. 10만원을 납부하면 서비스를 1년 제공한다.
 */
public class ExpiryDateCalculatorTest {
  
  @Test
  void 만원을_납부하면_한달뒤가_만료일이다(){
    PayData payData1 = PayData.builder()
            .billingDate(LocalDate.of(2021, 12, 30))
            .payAmount(10_000).build();
    assertExpiryDate(payData1, LocalDate.of(2022, 1, 30));
  }

  @Test
  void 납부일과_만료일의_일자가_같지않음() {

    PayData payData1 = PayData.builder()
        .billingDate(LocalDate.of(2019, 1, 31))
        .payAmount(10_000).build();

    assertExpiryDate(payData1, LocalDate.of(2019, 2, 28));

    PayData payData2 = PayData.builder()
        .billingDate(LocalDate.of(2019, 5, 31))
        .payAmount(10_000).build();

    assertExpiryDate(payData2, LocalDate.of(2019, 6, 30));

    PayData payData3 = PayData.builder()
        .billingDate(LocalDate.of(2020, 1, 31))
        .payAmount(10_000).build();

    assertExpiryDate(payData3, LocalDate.of(2020, 2, 29));
  }

  @Test
  void 첫납부일과_만료일_일자가_다를때_만원납부(){
    PayData payData = PayData.builder()
        .firstBillingDate(LocalDate.of(2019, 1, 31))
        .billingDate(LocalDate.of(2019, 2, 28))
        .payAmount(10_000)
        .build();

    assertExpiryDate(payData, LocalDate.of(2019, 3, 31));

    PayData payData2 = PayData.builder()
        .firstBillingDate(LocalDate.of(2019, 1, 30))
        .billingDate(LocalDate.of(2019, 2, 28))
        .payAmount(10_000)
        .build();

    assertExpiryDate(payData2, LocalDate.of(2019, 3, 30));

    PayData payData3 = PayData.builder()
        .firstBillingDate(LocalDate.of(2019, 5, 31))
        .billingDate(LocalDate.of(2019, 6, 30))
        .payAmount(10_000)
        .build();

    assertExpiryDate(payData3, LocalDate.of(2019, 7, 31));
  }

  @Test
  void 이만원_이상_납부하면_비례해서_만료일_계산() {
    PayData payData = PayData.builder()
        .billingDate(LocalDate.of(2019, 3, 1))
        .payAmount(20_000)
        .build();

    assertExpiryDate(payData, LocalDate.of(2019, 5, 1));

    PayData payData1 = PayData.builder()
        .billingDate(LocalDate.of(2019, 3, 1))
        .payAmount(30_000)
        .build();

    assertExpiryDate(payData1, LocalDate.of(2019, 6, 1));
  }

  @Test
  void 첫납부일과_만료일_일자가_다를때_이만원이상_납부() {
    assertExpiryDate(PayData.builder()
                      .firstBillingDate(LocalDate.of(2019, 1, 31))
                      .billingDate(LocalDate.of(2019, 2, 28))
                      .payAmount(20_000)
                      .build(),
                      LocalDate.of(2019, 4, 30));
  }

  @Test
  void 십만원을_납부하면_1년_제공() {
    assertExpiryDate(PayData.builder()
                      .billingDate(LocalDate.of(2019, 1, 28))
                      .payAmount(100_000)
                      .build()
        , LocalDate.of(2020, 1, 28)
    );
  }

  private void assertExpiryDate(PayData payData, LocalDate expectedExpiryDate) {

    final ExpiryDateCalculator cal = new ExpiryDateCalculator();
    final LocalDate expiryDate = cal.calculateExpiryDate(payData);

    assertEquals(expectedExpiryDate, expiryDate);
  }
}
