package com.erdemir.yigit.kupaalmaz;

import java.util.ArrayList;
import java.util.List;

import com.erdemir.yigit.kupaalmaz.core.Board;
import com.erdemir.yigit.kupaalmaz.core.Card;
import com.erdemir.yigit.kupaalmaz.core.Card.Rank;
import com.erdemir.yigit.kupaalmaz.core.Card.Suit;
import com.erdemir.yigit.kupaalmaz.core.Player;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {

	Player p1 = new Player();
	Player p2 = new Player();
	Player p3 = new Player();
	Player p4 = new Player();
	Player playerToMove = null;

	p1.setUsername("ali");
	p2.setUsername("veli");
	p3.setUsername("osman");
	p4.setUsername("yunus");

	List<Player> playerList = new ArrayList<Player>();
	playerList.add(p1);
	playerList.add(p2);
	playerList.add(p3);
	playerList.add(p4);
	Board board = new Board(playerList);
	for (int i = 0; i < 4; i++) {
	    for (int j = 0; j < 13; j++) {
		if (playerList.get(i).getCards().get(j).getRank() == Rank.TWO
			&& playerList.get(i).getCards().get(j).getSuit() == Suit.CLUBS) {
		    playerToMove = playerList.get(i);
		    System.out.println("Player to Move of Main is " + playerToMove.getUsername());
		}
	    }
	}

	System.out.println("Player to Move of the Board is " + board.getPlayerToMove().getUsername());
	try {
	    board.makeMove(null, playerToMove.getCards().get(0));
	    System.out.println(
		    playerToMove.getCards().get(0).getSuit() + " of " + playerToMove.getCards().get(0).getRank());
	} catch (Exception e) {
	    e.printStackTrace();
	}

	for (Card card : board.getDeck()) {
	    System.out.println(card.getRank().toString() + " of " + card.getSuit().toString());
	}

    }
}