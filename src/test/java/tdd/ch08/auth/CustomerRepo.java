package tdd.ch08.auth;

public interface CustomerRepo {
  Customer findOne(String id);
}
