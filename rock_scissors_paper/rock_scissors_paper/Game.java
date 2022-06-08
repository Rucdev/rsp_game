package rock_scissors_paper;
import java.util.Scanner;

public class Game {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		Scanner rspScanner = new Scanner(System.in);
		System.out.println("じゃんけんゲームスタート");
		System.out.println("何人対戦にしますか？[デフォルトは二人]");
		int enemyAmount = 2;
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
		rspScanner.close();
	}

}