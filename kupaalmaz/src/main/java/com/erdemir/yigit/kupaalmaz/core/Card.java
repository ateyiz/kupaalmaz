package com.erdemir.yigit.kupaalmaz.core;

public class Card {
    public Card(Suit suit, Rank rank) {
	this.suit = suit;
	this.rank = rank;
    }

    public static enum Suit {
	HEARTS, SPADES, DIAMONDS, CLUBS

    }

    public static enum Rank {

	A(13), K(12), Q(11), J(10), TEN(9), NINE(8), EIGHT(7), SEVEN(6), SIX(5), FIVE(4), FOUR(3), THREE(2), TWO(1);
	private int point;

	private Rank(int p) {
	    this.point = p;
	}

	public boolean isBigger(Rank rank) {

	    return this.point > rank.point;
	}
    }

    private Suit suit;
    private Rank rank;
    private boolean isPlayed;

    public Rank getRank() {
	return rank;
    }

    public Suit getSuit() {
	return suit;
    }

    public boolean isPlayed() {
	return isPlayed;
    }

    public void setPlayed(boolean isPlayed) {
	this.isPlayed = isPlayed;
    }

}
