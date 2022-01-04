package tdd.ch07.regist;

/**
 * password 강도 체크를 Stub으로 구현
 */
public class StubWeakPasswordChecker implements WeakPasswordChecker{

  private boolean weak;

  public void setWeak(boolean weak) {
    this.weak = weak;
  }

  @Override
  public boolean checkPasswordWeak(String pw) {
    return weak;
  }
}
