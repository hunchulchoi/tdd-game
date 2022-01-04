package tdd.ch07.regist;

import java.util.Optional;

public interface UserRepository {
  int save(User user);

  Optional<User> findById(String id);
}
