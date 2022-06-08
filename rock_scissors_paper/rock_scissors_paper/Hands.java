package rock_scissors_paper;

public enum Hands {
	Rock(0),
	Scissors(2),
	Paper(5);
	
	private int score;
	
	private Hands(int value) {
		this.score = value;
	}

	public int getScore() {
		return this.score;
	}
}