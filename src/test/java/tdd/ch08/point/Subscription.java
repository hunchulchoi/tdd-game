package tdd.ch08.point;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class Subscription {

  private String userId;
  private String productId;
  private LocalDate expiryDate;
  private Grade grade;

  public Subscription(String userId, LocalDate expiryDate, Grade grade) {
    this.userId = userId;
    this.expiryDate = expiryDate;
    this.grade = grade;
  }

  public boolean isFinished(LocalDate now) {
    return now.isAfter(expiryDate);
  }
}
