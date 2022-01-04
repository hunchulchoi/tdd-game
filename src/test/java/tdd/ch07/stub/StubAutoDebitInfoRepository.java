package tdd.ch07.stub;

import tdd.ch07.AutoDebitInfo;
import tdd.ch07.AutoDebitInfoRepository;

public class StubAutoDebitInfoRepository implements AutoDebitInfoRepository {

  @Override
  public void save(AutoDebitInfo info) {

  }

  @Override
  public AutoDebitInfo findOne(String userId) {
    return null;
  }
}
