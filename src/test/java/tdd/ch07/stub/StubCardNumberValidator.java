package tdd.ch07.stub;

import lombok.Setter;
import tdd.ch07.CardNumberValidator;
import tdd.ch07.CardValidity;

@Setter
public class StubCardNumberValidator extends CardNumberValidator {

  private String invalidNo;
  private String theftNo;

  @Override
  public CardValidity validate(String cardNumber) {

    if(invalidNo!=null && invalidNo.equals(cardNumber)){
      return CardValidity.INVALID;
    }

    if (theftNo != null && theftNo.equals(cardNumber)) {
      return CardValidity.THEFT;
    }
    return CardValidity.VALID;
  }
}
