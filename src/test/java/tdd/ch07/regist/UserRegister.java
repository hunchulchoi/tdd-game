package tdd.ch07.regist;

import lombok.AllArgsConstructor;

/**
 * 회원가입
 */
@AllArgsConstructor
public class UserRegister {

  private WeakPasswordChecker passwordChecker;
  private UserRepository userRepository;
  private EmailNotifier emailNotifier;

  public void register(User user) {
    if(passwordChecker.checkPasswordWeak(user.pw())){
      throw new WeakPasswordException();
    }

    if(userRepository.findById(user.id()).isPresent()){
      throw new DuplicateIdException();
    }
    userRepository.save(user);
    emailNotifier.sendRegisterEmail(user.email());
  }
}
