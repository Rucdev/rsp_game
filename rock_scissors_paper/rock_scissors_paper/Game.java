package rock_scissors_paper;
import java.util.Scanner;

public class Game {

	public static void main(String[] args) {
		Scanner rspScanner = new Scanner(System.in);
		System.out.println("じゃんけんゲームスタート");

		// 対戦人数選択
		System.out.println("何人対戦にしますか？[デフォルトは1人]");
		int enemyAmount = 1;
		while(true) {
			String tmpInput = rspScanner.nextLine();
			if (tmpInput.isEmpty()) { // Enterキーのみでデフォルト値
				break;
			}
			try {
				int parseInput = Integer.parseInt(tmpInput);
				if (parseInput > 0) {
					enemyAmount = parseInput;
					break;
				} else {
					System.out.println("1以上の数字を入力してください。");
				}
			}catch(NumberFormatException e){
				System.out.println("数字で入力してください");
			}
		}

		// 難易度選択
		Difficulty selectedDifficulty = Difficulty.NORMAL; // デフォルトはNORMAL
		System.out.println("難易度を選択してください:");
		System.out.println("0. Easy");
		System.out.println("1. Normal (デフォルト)");
		System.out.println("2. Hard");
		while(true) {
			String diffInput = rspScanner.nextLine();
			if (diffInput.isEmpty()) { // Enterキーのみでデフォルト値
				break;
			}
			try {
				int diffChoice = Integer.parseInt(diffInput);
				if (diffChoice == 0) {
					selectedDifficulty = Difficulty.EASY;
					break;
				} else if (diffChoice == 1) {
					selectedDifficulty = Difficulty.NORMAL;
					break;
				} else if (diffChoice == 2) {
					selectedDifficulty = Difficulty.HARD;
					break;
				} else {
					System.out.println("0, 1, 2のいずれかを入力してください。");
				}
			} catch (NumberFormatException e) {
				System.out.println("数字で入力してください。");
			}
		}


		Stadium rspStadium = new Stadium(enemyAmount, selectedDifficulty);
		String rspSelectMessage = """
				0. Rock
				1. Scissors
				2. Paper
				""";
		int result= 0;
		Hand myPreviousHand = null; // プレイヤーの前の手を記録

		while(result == 0) {
			System.out.println(rspSelectMessage);
			System.out.println("あなたの手を入力してください。");
			// rspScanner.useDelimiter(",|¥n"); // この行はnextLine()を使う場合、不要または意図しない挙動の原因になる可能性
			String tmpRSP = rspScanner.nextLine();
			try {
				int handInput = Integer.parseInt(tmpRSP);
				if (handInput < 0 || handInput > 2) {
					System.out.println("0, 1, 2のいずれかを入力してください。");
					continue;
				}
				Hand myHand = new Hand(handInput);
				result = rspStadium.battle(myHand, myPreviousHand); // プレイヤーの前の手も渡す
				myPreviousHand = myHand; // 現在の手を次のために記録
				if(result!=0) {
					break;
				}
			}catch(NumberFormatException e){
				System.out.println("数字で入力してください");
			
			}
			System.out.println("--Draw--");
			System.out.println("----------------");
		
		}
		if (result==1) {
			System.out.println("You win!!!");
		}else {
			System.out.println("You lose...");
		}
		rspScanner.close();

	}
}