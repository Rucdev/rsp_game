package rock_scissors_paper;

import java.util.Random;

public class AutoPlayer {
	private Hand hand;
	private int choiceFactor;

	public AutoPlayer() {
		// TODO 自動生成されたコンストラクター・スタブ
	}
	
	public void setRSP() {
		// 手のセット
		this.choiceFactor = new SelectEngine().value;
		this.hand = new Hand(this.choiceFactor);
	}
	public Hands getRSP() {
		return this.hand.getHand();
	}
}

// 選択エンジン
class SelectEngine {
	Random rand = new Random();
	int value;
	public SelectEngine() {
		this.value = rand.nextInt(100);
	}
}