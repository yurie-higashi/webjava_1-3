import java.util.Scanner;

public class RPSGame {

  public static void main(String[] args) {

    String input = "";
    Boolean isContinue = false;

    System.out.println("こんにちは。");
    do {
      System.out.println("じゃんけんの操作を選択してください。マニュアル(m)/オート(a)/終了(e)");

      @SuppressWarnings("resource")
      Scanner scan = new Scanner(System.in);
      input = scan.next();

      switch (input) {

        case "m":
          isContinue = true;
          manualRPS();
          break;

        case "a":
          isContinue = true;
          autoRPS();
          break;

        case "e":
          isContinue = false;
          System.out.println("じゃんけんゲームを終了しました。");
          break;

        default:
          isContinue = true;
          System.out.println("入力が誤っています。");
          break;
      }
    } while (isContinue);

  }

  private static void manualRPS() {

    int[] totalResult = {0,0,0};
    int vattleTimes = 0;
    RPSPlayer rpsPlayer = new RPSPlayer();
    Boolean isContinue = false;

    do {
      Boolean aiko = false;

      if (!aiko) {
        System.out.println("じゃんけんぽん！グー(1)/チョキ(2)/パー(3)/終了(e)");
      } else {
        System.out.println("あいこでしょ！グー(1)/チョキ(2)/パー(3)/終了(e)");
      }
      @SuppressWarnings("resource")
      Scanner scan = new Scanner(System.in);
      String humanInput = scan.next();

      RPSType pcRps = rpsPlayer.go();
      RPSType humanRps = getRpsTypeName(humanInput);

      if (humanInput.equals("e")) {
        System.out.println("マニュアルモードを終了しました。");
        isContinue = false;
        totalResult(totalResult,0);
        continue;
      } else if (humanRps != null) {
        isContinue = true;
        vattleTimes++;

        String result = vattle(humanRps, pcRps);
        String winner = " あいこ";
        if ("one".equals(result)) {
          winner = " 勝者はあなた";
          totalResult[0]++ ;
          rpsPlayer.onResult(true);
        } else if ("two".equals(result)) {
          winner = " 勝者はコンピュータ";
          totalResult[1]++ ;
          rpsPlayer.onResult(false);
        } else {
          totalResult[2]++;
          aiko = true;
        }

        getResult(vattleTimes,humanRps,pcRps,winner);

      } else {
        System.out.println("入力が誤っています。");
        isContinue = true;
      }

    } while (isContinue);


  }

  private static void autoRPS() {

    int[] totalResult = {0,0,0};
    RPSPlayer rpsPlayer = new RPSPlayer();

    for (int vattleTimes = 1; vattleTimes <= 50; vattleTimes++) {

      RPSType player1 = rpsPlayer.go();
      RPSType player2 = rpsPlayer.go();

      String result = vattle(player1,player2);

      if (result.equals("one")) {
        result = "勝者はコンピュータ１";
        totalResult[0]++;
      } else if (result.equals("two")) {
        result = "勝者はコンピュータ２";
        totalResult[1]++;
      } else {
        result = "あいこ";
        totalResult[2]++;
      }
      getResult(vattleTimes,player1,player2,result);

    }

    System.out.println("オートモードが終了しました。");
    totalResult(totalResult,1);



  }

  private static void getResult(int vattleTimes, RPSType player1, RPSType player2, String winner) {

    System.out.println("第" + vattleTimes + "戦目："
        + getRPSTypeJapanese(player1) + " 対 " + getRPSTypeJapanese(player2) + winner + "！");
    System.out.println("");
    return;

  }

  private static RPSType getRpsTypeName(String id) {

    RPSType rpsType = null;

    switch (id) {
      case "1":
        rpsType = RPSType.ROCK;
        break;

      case "2":
        rpsType = RPSType.SCISSOR;
        break;

      case "3":
        rpsType = RPSType.PAPER;
        break;

      default:
        return null;
    }

    return rpsType;

  }

  private static String getRPSTypeJapanese(RPSType rps) {

    String rpsJapanese;

    if (rps == RPSType.ROCK) {
      rpsJapanese = "グー";
    } else if (rps == RPSType.SCISSOR) {
      rpsJapanese = "チョキ";
    } else {
      rpsJapanese = "パー";
    }

    return rpsJapanese;

  }

  private static String vattle(RPSType player1,RPSType player2) {

    String result = "";
    String one = "one";
    String two = "two";

    if (player1 == player2) {
      result = "aiko";
    } else if (player1 == RPSType.ROCK) {
      if (player2 == RPSType.SCISSOR) {
        result = one;
      } else {
        result = two;
      }
    } else if (player1 == RPSType.SCISSOR) {
      if (player2 == RPSType.PAPER) {
        result = one;
      } else {
        result = two;
      }
    } else {
      if (player2 == RPSType.ROCK) {
        result = one;
      } else {
        result = two;
      }
    }
    return result;

  }

  private static void totalResult(int[] totalResult,int game) {

    int winner1 = totalResult[0];
    int winner2 = totalResult[1];
    int aiko = totalResult[2];
    int total = winner1 + winner2 + aiko;

    if (game == 0) {
      System.out.println("");
      System.out.println("【結果】 " + total + "戦中 " + winner1 + "勝 " + winner2 + "敗 " + aiko + "分け");
      if (winner1 > winner2) {
        System.out.println("あなたのほうが多く勝ちました！！おめでとう！！");
        System.out.println("");
      } else if (winner2 > winner1) {
        System.out.println("コンピュータのほうが多く勝ちました！！");
        System.out.println("");
      } else {
        System.out.println("引き分けです！");
        System.out.println("");
      }
    } else {
      System.out.println("【結果】 " + total + "戦中 " + winner1 + "勝 " + winner2 + "敗 " + aiko + "分けと");
      System.out.println("         " + total + "戦中 "
          + winner2 + "勝 " + winner1 + "敗 " + aiko + "分けで");
      if (winner1 > winner2) {
        System.out.println("コンピュータ１のほうが多く勝ちました！");
        System.out.println("");
      } else if (winner2 > winner1) {
        System.out.println("コンピュータ２のほうが多く勝ちました！");
        System.out.println("");
      } else {
        System.out.println("引き分けです！");
        System.out.println("");
      }
    }

  }

}
