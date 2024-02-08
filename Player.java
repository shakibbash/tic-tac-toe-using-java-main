package tic_tac_toe;

class Player {
	private String name;
	private String mark;

	public Player(String name, String mark) {
		this.name = name;
		this.mark = mark;
	}

	public String getName() {
		return name;
	}

	public String getMark() {
		return mark;
	}
}