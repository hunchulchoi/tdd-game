package ch03;

import java.time.LocalDate;

public class ExpiryDateCalculator {


  /**
   * 납부액에 따른 서비스 만료일 계산
   *
   * @param payData@return
   */
  public LocalDate calculateExpiryDate(PayData payData) {

    int monthsToAdd = payData.getPayAmount()==100_000?12:payData.getPayAmount()/10_000;

    if(payData.getFirstBillingDate()!=null){
      return calculateExpiryDateUsingFirstBillingDate(payData, monthsToAdd);
    }

    return payData.getBillingDate().plusMonths(monthsToAdd);

  }

  /**
   * 최초 납부일이 있을때 서비스 졸요일 계산
   * @param payData
   * @param monthsToAdd
   * @return 서비스만료일
   */
  private LocalDate calculateExpiryDateUsingFirstBillingDate(PayData payData, int monthsToAdd) {

      final int firstBillingDayOfMonth = payData.getFirstBillingDate().getDayOfMonth();
      LocalDate candidate = payData.getBillingDate().plusMonths(monthsToAdd);

      int dayOfCandiMon = candidate.getDayOfMonth();
      int dayLenOfCandiMon = candidate.lengthOfMonth();

      if(dayOfCandiMon != firstBillingDayOfMonth){
        return candidate.withDayOfMonth(Math.min(firstBillingDayOfMonth, dayLenOfCandiMon));
      }

      return candidate;

  }
}
