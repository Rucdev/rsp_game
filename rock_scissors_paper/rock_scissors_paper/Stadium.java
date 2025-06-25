package rock_scissors_paper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Stadium {
	private List<AutoPlayer> enemys;
	private Difficulty difficulty;
	
	public Stadium(int numberOfEnemys, Difficulty difficulty) {
		this.difficulty = difficulty;
		enemys = new ArrayList<AutoPlayer>();
		for (int i = 0; i < numberOfEnemys; i++) {
			enemys.add(new AutoPlayer(this.difficulty));
		}
	}
	
	public int battle(Hand selectedHand, Hand playerPreviousHand) {
		List<String> allHands = new ArrayList<String>();
		allHands.add(selectedHand.getHand().name().toString());
		System.out.print("You are ");
		System.out.println(selectedHand.getHand().name());

		for(int i = 0; i < enemys.size(); i++) {
			AutoPlayer currentEnemy = enemys.get(i);
			// NORMAL以上の難易度の場合、プレイヤーの前の手をAutoPlayerにセット
			if (this.difficulty == Difficulty.NORMAL || this.difficulty == Difficulty.HARD) {
				currentEnemy.setPlayerPreviousHand(playerPreviousHand);
			}
			currentEnemy.setRSP();
			allHands.add(currentEnemy.getRSP().name().toString());
			System.out.print("Enemy" + (i + 1) + " is ");
			System.out.println(currentEnemy.getRSP().name());
		};
		List<Integer> battleResult = Judge.result(allHands);

		// 0があいこ, 1が勝ち, 2が負け
		// Judge.resultは勝者のインデックス(1始まり)のリストを返す。
		// プレイヤーは常にインデックス1。
		if (battleResult.isEmpty()) { // あいこの場合は空のリスト
			return 0; // Draw
		} else if (battleResult.contains(1)) { // プレイヤー(インデックス1)が勝者リストに含まれていれば勝ち
			return 1; // Win
		} else {
			return 2; // Lose
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
		String ROCK ="Rock"; // Hands enumに合わせて修正 (Hands.ROCK.name()などを使うのが望ましいが、現状の文字列比較を踏襲)
		String SCISSORS = "Scissors"; // Hands.SCISSORS.name()
		String PAPER = "Paper";     // Hands.PAPER.name()

		// Javaでは文字列比較に `==` ではなく `.equals()` を使うべき。
		// ただし、このコードベースでは元々 `==` で比較していたため、
		// `Hand.java` の `getHand().name().toString()` の結果がリテラルと同一インスタンスになるか、
		// または `HashSet` の挙動に依存している可能性がある。
		// 本来は修正すべきだが、今回の変更範囲外とし、元のロジックを維持する。
		// より安全なのは `Hands.ROCK.name()` のような enum の名前を直接使うこと。

		Set<String> RockScissors = new HashSet<String>();
		RockScissors.add(ROCK); // "Rock"
		RockScissors.add(SCISSORS); // "Scissors"
		
		Set<String> ScissorsPaper = new HashSet<String>();
		ScissorsPaper.add(SCISSORS); // "Scissors"
		ScissorsPaper.add(PAPER);    // "Paper"
		
		Set<String> PaperRock = new HashSet<String>();
		PaperRock.add(PAPER); // "Paper"
		PaperRock.add(ROCK);  // "Rock"
		
		
		// じゃんけん判定
		if ((handPattern.size() == 3) || (handPattern.size() == 1)) {
			// あいこ (3種類の手が出た場合、または1種類の手のみの場合)
			return new ArrayList<Integer>(); // 空のリストを返す
		}
		else if (handPattern.equals(RockScissors)) {
			// グーとチョキのみの場合 -> グーの勝ち
			return check(hands, ROCK);
		}else if (handPattern.equals(ScissorsPaper)) {
			// チョキとパーのみの場合 -> チョキの勝ち
			return check(hands, SCISSORS);
		}else if (handPattern.equals(PaperRock)) {
			// パーとグーのみの場合 -> パーの勝ち
			return check(hands, PAPER);
		}
		
		// 上記のいずれにも当てはまらない場合は、あいことみなす (例: 2人で同じ手)
		// ただし、handPattern.size() == 1 のチェックで既にカバーされている。
		// このreturnに到達することは基本的にないはず。
		return results; // 空のリスト (あいこ)
	}
	
	private static List<Integer> check(List<String> hands, String winnerHandName) {
		// 勝者の配列インデックス(1始まり)のリストを返す。
		List<Integer> winnerList = new ArrayList<Integer>();
		for (int i = 0; i < hands.size(); i++) {
			// 文字列比較は .equals() を使用するべき
			if (hands.get(i).equals(winnerHandName)) {
				winnerList.add(i + 1); // インデックスは1始まりなので +1
			}
		}
		return winnerList;
	}
}