package tdd.ch07.stub;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tdd.ch07.AutoDebitRegister;
import tdd.ch07.AutoDebitReq;
import tdd.ch07.CardValidity;
import tdd.ch07.RegisterResult;

import static org.junit.jupiter.api.Assertions.*;

class StubAutoDebitRegisterTest {

  private AutoDebitRegister register;
  private StubCardNumberValidator validator;
  private StubAutoDebitInfoRepository repository;

  @BeforeEach
  void setUp() {
    validator = new StubCardNumberValidator();
    repository = new StubAutoDebitInfoRepository();
    register = new AutoDebitRegister(validator, repository);
  }

  @Test
  void 잘못된_카드번호() {
    final String invalidNo = "111222333";
    validator.setInvalidNo(invalidNo);

    AutoDebitReq req = new AutoDebitReq("user1", invalidNo);
    RegisterResult result = register.register(req);

    assertEquals(CardValidity.INVALID, result.getValidity());
  }

  @Test
  void 도난카드() {
    final String theftNo = "123456000";
    validator.setTheftNo(theftNo);

    AutoDebitReq req = new AutoDebitReq("user1", theftNo);
    RegisterResult result = register.register(req);

    assertEquals(CardValidity.THEFT, result.getValidity());
  }
}