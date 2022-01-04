package tdd.ch07.regist;

import lombok.Getter;

/**
 * 이메일을 발송기 스파이 double 구현
 */
@Getter
public class SpyEmailNotifier implements EmailNotifier{

  private boolean called;
  private String email;

  @Override
  public void sendRegisterEmail(String email) {
    this.email = email;
    this.called = true;
  }
}
