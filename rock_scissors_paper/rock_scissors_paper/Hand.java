package rock_scissors_paper;

public class Hand {
	private Hands hand;
	
	public Hand(int hand_choice) {
		
		if(hand_choice % 3 ==0) {
			this.hand = Hands.Rock;
		}
		else if(hand_choice % 3 == 1) {
			this.hand = Hands.Scissors;
		}
		else if(hand_choice % 3 == 2) {
			this.hand = Hands.Paper;
		}
	}
	
	public Hands getHand() {
		return this.hand;
	}
}