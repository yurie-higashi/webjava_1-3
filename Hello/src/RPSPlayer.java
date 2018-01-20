import java.util.Random;

public class RPSPlayer extends RPSPlayerBase {

  @Override
  public RPSType go() {
    Random random = new Random();
    int ran = random.nextInt(3);

    RPSType rpsType;

    if (ran == 0) {
      rpsType = RPSType.ROCK;
    } else if (ran == 1) {
      rpsType = RPSType.SCISSOR;
    } else {
      rpsType = RPSType.PAPER;
    }

    return rpsType;
  }

  @Override
  public void onResult(boolean isWinner) {

  }
}
