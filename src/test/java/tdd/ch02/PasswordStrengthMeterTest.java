package tdd.ch02;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import tdd.ch02.PasswordStrength;
import tdd.ch02.PasswordStrengthMeter;

import java.util.Arrays;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 암호 검사기 p.30
 */
public class PasswordStrengthMeterTest {

  private final PasswordStrengthMeter meter = new PasswordStrengthMeter();

  private void assertStrength(String s, PasswordStrength expStrength) {
    PasswordStrength result = meter.meter(s);
    assertEquals(expStrength, result);
  }

  @Test
  void name(){
  }

  @Test
  void 암호가_모든_조건을_충족하면_STRONG(){
    assertStrength("ab12!@AB", PasswordStrength.STRONG);
    assertStrength("abc1!Add", PasswordStrength.STRONG);
  }

  @Test
  void 길이가_8미만이고_나머지_조건을충족하는_경우_NORMAL(){
    assertStrength("ab12!@A", PasswordStrength.NORMAL);
    assertStrength("ab12!@As", PasswordStrength.STRONG);
  }

  @Test
  void 숫자를_포함하지않고_나머지조건을_충족하는경우_NORMAL(){
    assertStrength("ab!ABqwer", PasswordStrength.NORMAL);
    assertStrength("ab!ABqwer1", PasswordStrength.STRONG);
  }

  @Test
  void 대문자를_포함하지않고_나머지조건을_출족하는경우_NORMAL(){
    assertStrength("ab12!@df", PasswordStrength.NORMAL);
    assertStrength("ab12!@Df", PasswordStrength.STRONG);
  }

  @Test
  void 길이가_8글자_이상인_조건만충족_WEEK(){
    assertStrength("abcdefghi", PasswordStrength.WEEK);
  }

  @Test
  void 숫자포함_조건만_충족_WEEK(){
    assertStrength("1", PasswordStrength.WEEK);
    assertStrength("12345678", PasswordStrength.NORMAL);
  }

  @Test
  void 대문자포함_조건만_충족_WEEK(){
    assertStrength("A", PasswordStrength.WEEK);
    assertStrength("ABCDEFGHI", PasswordStrength.NORMAL);
  }

  @Test
  void 아무것도_충족하지않았을때_WEEK() {
    assertStrength("abc", PasswordStrength.WEEK);
  }

  @Test
  void 입력이_null일때_INVALID(){
    assertStrength(null, PasswordStrength.INVALID);
  }

  @Test
  void 빈문자열일때_INVALID(){
    assertStrength("", PasswordStrength.INVALID);
  }

  @Test
  @Disabled
  void 정규식_숫자테스트(){
    Pattern pattern = Pattern.compile(".*\\d+.*");
    Arrays.asList("abcd", "1", "1a", "1aa", "a1a", "a1", "a11", "11", "*").stream()
        .forEach(s -> System.out.println(String.format("%s=>%s", s, String.valueOf(pattern.matcher(s).find()))));
  }

  @Test
  @Disabled
  void 정규식_대문자테스트(){
    Pattern pattern = Pattern.compile(".*[A-Z]+.*");
    Arrays.asList("abcd", "1", "1aA", "A1aa", "Aa1a", "a1A", "a1A1", "11", "&A*").stream()
        .forEach(s -> System.out.println(String.format("%s=>%s", s, String.valueOf(pattern.matcher(s).find()))));
  }

}
