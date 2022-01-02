package tdd.ch02;

import lombok.extern.slf4j.Slf4j;

import java.util.regex.Pattern;

/**
 * 암호 측정기
 * 문자열을 검사해서 규칙을 준수하는지에 따라 암호를 '강함', '보통', '약함'으로 구분
 * * 검사 규칙
 *   1. 길이가 8글자 이상
 *   2. 0-9사이의 숫자를 포함
 *   3. 대문자 포함
 */
@Slf4j
public class PasswordStrengthMeter {

  public PasswordStrength meter(String s) {

    if(s==null || s.isEmpty()) return PasswordStrength.INVALID;
    //log.debug("{}:length:{}", s, s.length());

    int metCounts = getMetCriteriaCounts(s);

    if(metCounts<=1) return PasswordStrength.WEEK;
    if(metCounts==2) return  PasswordStrength.NORMAL;
    return PasswordStrength.STRONG;
  }

  private int getMetCriteriaCounts(String s) {
    int metCounts = 0;

    if(s.length() >= 8) metCounts++;
    if(meetsContaingNumberCrietia(s)) metCounts++;
    if(meetsContainingUpperCaseCrietia(s)) metCounts++;
    return metCounts;
  }

  private boolean meetsContaingNumberCrietia(String s) {
    return Pattern.matches(".*\\d+.*", s);
  }

  private boolean meetsContainingUpperCaseCrietia(String s){
    return Pattern.matches(".*[A-Z]+.*", s);
  }


}
