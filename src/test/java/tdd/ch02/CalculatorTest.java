package tdd.ch02;

import org.junit.jupiter.api.Test;
import tdd.ch02.Calculator;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 계산기 테스트 p24
 */
public class CalculatorTest {

  @Test
  void plus(){
    int result = Calculator.plus(1, 2);
    assertEquals(3, result);
    assertEquals(5, Calculator.plus(4,1));
  }
}
