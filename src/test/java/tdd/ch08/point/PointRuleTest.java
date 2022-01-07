package tdd.ch08.point;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class PointRuleTest {

  @Test
  void 만료전_GOLD_등급은_130포인트() {
    PointRule rule = new PointRule();
    PointRuleCalculator pointRule = new PointRuleCalculator();
    Subscription s = new Subscription("id", LocalDate.of(2019, 5, 5), Grade.GOLD);

    Product p = new Product();
    p.setDefaultPoint(20);

    int point  = rule.calculate(s, p, LocalDate.of(2019, 5, 1));

    assertEquals(130, point);
  }
}
