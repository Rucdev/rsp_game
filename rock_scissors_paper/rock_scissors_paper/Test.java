package rock_scissors_paper;

public class Test {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		Stadium testStadium = new Stadium(7);
		Hand myHand = new Hand(0);
		int result = testStadium.battle(myHand);
		if (result==1) {
			System.out.println("You win!");
		}else if (result==2){
			System.out.println("You lose...");
		}else{
			System.out.println("--Draw--");
		};
	}

}
