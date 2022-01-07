package tdd.ch08.auth;

import lombok.Setter;
import org.junit.jupiter.api.Test;

public class AuthService {

  private String authKey = "secret";

  public int authenticate(String id, String password) {
    int resp;
    boolean authorized = AuthUtil.authorize(authKey);
    if (authorized) {
      resp = AuthUtil.authenticate(id, password);
    }else{
      resp = -1;
    }
    return resp;
  }
}
