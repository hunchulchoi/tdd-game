package tdd.ch08.auth;

import lombok.Setter;

public class LoginService{

  private String authKey;
  private CustomerRepo customerRepo;

  @Setter
  private AuthService authService;

  public LoginResult login(String id, String password){
    int resp = 0;
    resp = authService.authenticate(id, password);

    if(resp == -1){
      return LoginResult.BAD_AUTH_KEY;
    }

    if(resp==1){
      Customer customer = customerRepo.findOne(id);
      return  LoginResult.SUCCESS;
    }

    return LoginResult.FAIL;

  }


}
