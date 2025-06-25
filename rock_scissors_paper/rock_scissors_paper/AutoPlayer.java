package rock_scissors_paper;

import java.util.Random;

public class AutoPlayer {
	private Hand hand;
	private Difficulty difficulty;
	private Hand playerPreviousHand; // プレイヤーの前の手を記録

	public AutoPlayer(Difficulty difficulty) {
		this.difficulty = difficulty;
	}

	// NORMALレベル以上でプレイヤーの前の手をセットするために使用
	public void setPlayerPreviousHand(Hand hand) {
		this.playerPreviousHand = hand;
	}
	
	public void setRSP() {
		// 手のセット
		SelectEngine selectEngine = new SelectEngine(this.difficulty, this.playerPreviousHand);
		this.hand = new Hand(selectEngine.getChoice());
	}
	public Hands getRSP() {
		return this.hand.getHand();
	}
}

// 選択エンジン
class SelectEngine {
	private Random rand = new Random();
	private int choice;
	private Difficulty difficulty;
	private Hand playerPreviousHand;

	public SelectEngine(Difficulty difficulty, Hand playerPreviousHand) {
		this.difficulty = difficulty;
		this.playerPreviousHand = playerPreviousHand;
		this.selectHand();
	}

	private void selectHand() {
		switch (this.difficulty) {
			case EASY:
				this.choice = rand.nextInt(3); // 0: Rock, 1: Scissors, 2: Paper (Handクラスの仕様に合わせる)
				break;
			case NORMAL:
				if (this.playerPreviousHand != null) {
					// プレイヤーの前の手に勝つ手を出しやすくする
					// 例: プレイヤーがグーなら、パーを出す確率を上げる
					// 簡単な実装として、70%の確率で勝つ手を、残りはランダムとする
					if (rand.nextInt(10) < 7) { // 70%の確率
						switch (this.playerPreviousHand.getHand()) {
							case Rock:
								this.choice = 2; // Paper
								break;
							case Scissors:
								this.choice = 0; // Rock
								break;
							case Paper:
								this.choice = 1; // Scissors
								break;
							default:
								this.choice = rand.nextInt(3); // 念のためランダム
								break;
						}
					} else {
						this.choice = rand.nextInt(3);
					}
				} else {
					// 初回など、プレイヤーの前の手がない場合はランダム
					this.choice = rand.nextInt(3);
				}
				break;
			case HARD:
				// HARDレベルのロジック（今回はNORMALと同じにするか、より高度なものに）
				// ここでは例として、NORMALと同じロジックを使用
				if (this.playerPreviousHand != null) {
					if (rand.nextInt(10) < 8) { // 80%の確率で勝つ手
						switch (this.playerPreviousHand.getHand()) {
							case Rock:
								this.choice = 2; // Paper
								break;
							case Scissors:
								this.choice = 0; // Rock
								break;
							case Paper:
								this.choice = 1; // Scissors
								break;
							default:
								this.choice = rand.nextInt(3);
								break;
						}
					} else {
						this.choice = rand.nextInt(3);
					}
				} else {
					this.choice = rand.nextInt(3);
				}
				break;
			default:
				this.choice = rand.nextInt(3); // デフォルトはランダム
				break;
		}
	}

	public int getChoice() {
		// Handクラスは0, 1, 2ではなく、手の強さの数値で初期化される想定かもしれない。
		// Handクラスのコンストラクタに合わせて調整が必要。
		// ここでは仮に0:Rock, 1:Scissors, 2:Paperとする。
		// Handクラスの実装を確認し、必要であればここの値を調整する。
		// Handクラスの`new Hand(int hand)`が `hand % 3` をしているため、0,1,2で問題ない。
		return this.choice;
	}
}