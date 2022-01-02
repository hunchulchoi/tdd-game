package tdd.ch03;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.time.LocalDate;

@Data
@Builder
public class PayData{
  private LocalDate firstBillingDate;
  @NonNull
  private LocalDate billingDate;
  @NonNull
  private int payAmount;
}