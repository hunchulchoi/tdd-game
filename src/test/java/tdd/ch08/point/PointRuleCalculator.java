package tdd.ch08.point;

import lombok.Setter;
import tdd.ch07.regist.User;

import java.time.LocalDate;

@Setter
public class PointRuleCalculator {

  private PointRule pointRule = new PointRule();
  private SubscriptionDao subscriptionDao;
  private ProductDao productDao;

  public int calculatePoint(User user){
    Subscription s = subscriptionDao.selectByUser(user.id());
    if (s == null) throw new NoSubscriptionException();

    Product p = productDao.selectById(s.getProductId());
    LocalDate now = LocalDate.now();
    return pointRule.calculate(s, p, now);
  }

}