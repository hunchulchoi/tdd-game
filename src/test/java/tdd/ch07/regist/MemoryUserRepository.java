package tdd.ch07.regist;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
public class MemoryUserRepository implements UserRepository {

  Map<String, User> userMap = new HashMap<>();

  @Override
  public int save(User user) {
    userMap.put(user.id(), user);
    return 1;
  }

  @Override
  public Optional<User> findById(String id) {
    log.debug("2111");
    return Optional.ofNullable(userMap.get(id));
  }
}
