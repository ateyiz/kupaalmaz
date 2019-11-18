package com.erdemir.yigit.kupaalmaz.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.erdemir.yigit.kupaalmaz.core.Card.Rank;
import com.erdemir.yigit.kupaalmaz.core.Card.Suit;

public class Board {
    private List<Player> players;
    private List<Card> deck = new ArrayList<Card>();
    private Player playerToMove = null;
    private Suit currentSuit = null;
    private boolean heartsOut = false;
    private List<Card> handCards = new ArrayList<Card>();

    public Board(List<Player> players) {
	this.players = players;

	for (Rank rank : Card.Rank.values()) {
	    for (Suit suit : Card.Suit.values()) {
		Card card = new Card(suit, rank);
		deck.add(card);
	    }
	}
	Collections.shuffle(deck);

	for (int i = 0; i < 4; i++) {
	    Player player = this.players.get(i);
	    player.setCards(new ArrayList<Card>());
	    for (int j = 0; j < 13; j++) {
		Card card = deck.get(i * 13 + j);
		player.getCards().add(card);
		if (card.getRank() == Card.Rank.TWO && card.getSuit() == Card.Suit.CLUBS) {
		    this.playerToMove = player;

		}

	    }
	}

    }

    public void makeMove(Player player, Card card) throws Exception {
	if (player != playerToMove) {
	    throw new Exception("Not your turn!");
	}
	if (!player.getCards().contains(card)) {
	    throw new Exception("Not your card!");
	}

	if (currentSuit == null) {
	    if (!heartsOut && card.getSuit() == Suit.HEARTS) {
		throw new Exception("No hearts out!");
	    }
	} else {
	    if (currentSuit != card.getSuit()) {
		boolean hasCurrentSuit = false;
		for (Card pcard : player.getCards()) {
		    if (pcard.getSuit() == currentSuit) {
			hasCurrentSuit = true;
			break;
		    }
		}
		if (hasCurrentSuit) {
		    throw new Exception("Has current suit");
		}
	    }
	}
	player.getCards().remove(card);
	handCards.add(card);

	if (handCards.size() < this.players.size()) {
	    int indexOfCurrentPlayer = this.players.indexOf(player);
	    this.playerToMove = this.players.get((indexOfCurrentPlayer + 1) % this.players.size());
	} else {

	    // TO DO
	    /*
	     * 1) Eli kim aldı? 2) Cezalı kağıtlar bu oyuncuya eklenecek 3) handcardsı
	     * temizle 4) kupa çıktı mı set et. 5) currentsuit null olacak
	     */
	    int indexOfHandOwner = 0;
	    Card biggestCard = handCards.get(0);
	    for (int i = 0; i < handCards.size(); i++) {
		Card handCard = handCards.get(i);
		heartsOut = heartsOut || handCard.getSuit() == Suit.HEARTS;
		if (currentSuit == handCard.getSuit() && handCard.getRank().isBigger(biggestCard.getRank())) {
		    biggestCard = handCard;
		    indexOfHandOwner = i;
		}
	    }
	    int indexOfLastMover = this.players.indexOf(player);
	    int playerShift = handCards.size() - 1 - indexOfLastMover;
	    indexOfHandOwner = (indexOfHandOwner - playerShift) % handCards.size();
	    Player handOwner = this.players.get(indexOfHandOwner);

	    for (Card hcard : handCards) {
		if (hcard.getSuit() == Suit.HEARTS || (hcard.getSuit() == Suit.SPADES && hcard.getRank() == Rank.Q)) {
		    handOwner.getPenaltyCards().add(hcard);
		}
	    }
	    currentSuit = null;
	    handCards.clear();
	    playerToMove = handOwner;
	}

    }

    public List<Player> getPlayers() {
	return players;
    }

    public List<Card> getDeck() {
	return deck;
    }

    public Player getPlayerToMove() {
	return playerToMove;
    }

}
