package rock_scissors_paper;
import java.util.Scanner;

public class Game {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		Scanner rspScanner = new Scanner(System.in);
		System.out.println("じゃんけんゲームスタート");
		System.out.println("何人対戦にしますか？[デフォルトは二人]");
		int enemyAmount = 1;
		while(true) {
			String tmpInput = rspScanner.nextLine();
			try {
				if (tmpInput=="") {
					break;
				}
				int parseInput = Integer.parseInt(tmpInput);
				enemyAmount = parseInput;
				break;
			}catch(NumberFormatException e){
				System.out.println("数字で入力してください");
			}
		}
		Stadium rspStadium = new Stadium(enemyAmount);
		String rspSelectMessage = """
				0. Rock
				1. Scissors
				2. Paper
				""";
		int result= 0;
		while(result == 0) {
			System.out.println(rspSelectMessage);
			System.out.println("あなたの手を入力してください。");
			rspScanner.useDelimiter(",|¥n");
			String tmpRSP = rspScanner.nextLine();
			try {
				Hand myHand = new Hand(Integer.parseInt(tmpRSP));
				result = rspStadium.battle(myHand);
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