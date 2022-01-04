package tdd.mock;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Game {

  private GameNumGen gameNumGen;

  public void init(GameLevel level) {
    gameNumGen.generate(level);
  }
}
