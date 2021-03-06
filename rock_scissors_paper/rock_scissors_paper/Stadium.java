package rock_scissors_paper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Stadium {
	private List<AutoPlayer> enemys;
//	private Hand hand1P;
	
	public Stadium(int numberOfEnemys) {
		// TODO 自動生成されたコンストラクター・スタブ
		enemys = new ArrayList<AutoPlayer>();
		for (int i = 0; i < numberOfEnemys; i++) {
			enemys.add(new AutoPlayer());
		}
	}
	
	public int battle(Hand selectedHand) {
		List<String> allHands = new ArrayList<String>();
		allHands.add(selectedHand.getHand().name().toString());
		System.out.print("You are ");
		System.out.println(selectedHand.getHand().name());
		int index = 0;
		for(int i = 0; i < enemys.size(); i++) {
			enemys.get(i).setRSP();
			allHands.add(enemys.get(i).getRSP().name().toString());
			index = index + 1;
			System.out.print("Enemy" + (i + 1) + " is ");
			System.out.println(enemys.get(i).getRSP().name());
		};
		List<Integer> battleResult = Judge.result(allHands);

		// 0があいこ, 1が勝ち, 2が負け
		if (battleResult.size()==0) {
			return 0;
		}
		else if (battleResult.contains(1)) {
			return 1;
		}else{
			return 2;
		}
	}

}

class Judge {
	public static List<Integer> result(List<String> hands) {
		// 勝敗判定用クラスメソッド
		List<Integer> results = new ArrayList<Integer>();
		
		// handsの集合をとる
		Set<String> handPattern = new HashSet<String>(hands);
		
		// 勝敗がつくパターン
		String ROCK ="Rock";
		String SCISSORS = "Scissors";
		String PAPER = "Paper";
		Set<String> RockScissors = new HashSet<String>();
		RockScissors.add(ROCK);
		RockScissors.add(SCISSORS);
		
		Set<String> ScissorsPaper = new HashSet<String>();
		ScissorsPaper.add(SCISSORS);
		ScissorsPaper.add(PAPER);
		
		Set<String> PaperRock = new HashSet<String>();
		PaperRock.add(PAPER);
		PaperRock.add(ROCK);
		
		
		
		// じゃんけん判定
		if ((handPattern.size() == 3) || (handPattern.size() == 1)) {
			// あいこ
			return new ArrayList<Integer>();
		}
		else if (handPattern.equals(RockScissors)) {
			// グーの勝ち
			return check(hands, ROCK);
		}else if (handPattern.equals(ScissorsPaper)) {
			// チョキの勝ち
			return check(hands, SCISSORS);
		}else if (handPattern.equals(PaperRock)) {
			// パーの勝ち
			return check(hands, PAPER);
		}
		
		return results;
	}
	
	private static List<Integer> check(List<String> hands, String winner) {
		// 勝者の配列番号を出す。あいこは[]
		List<Integer> winnerList = new ArrayList<Integer>();
		int i = 0;
		for (String h: hands) {
			i++;
			if (h == winner) {
				winnerList.add(i);
			}
		}
		return winnerList;
	}
}