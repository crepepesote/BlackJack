import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

public class BlackJack {

    private HashMap<String, Integer> cards;
    private ArrayList<Player> players;

    public BlackJack() {
        cards = new HashMap<>();
        players = new ArrayList<>();
        initCards();
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void giveCard(Player player) {
        String[] cardKeys = cards.keySet().toArray(new String[0]);
        Random random = new Random();
        int randomIndex = random.nextInt(cardKeys.length);
        String randomCard = cardKeys[randomIndex];
        int cardValue = cards.get(randomCard);
        player.addCard(cardValue);
        verifyAlivePlayers();
    }

    private void verifyAlivePlayers() {
        int playersPassed = 0;
        for (Player p : players) {
            if (p.getTotal() > 21 && p.getAlive()) {
                p.setAlive();
                playersPassed++;
            }
            if (p.getTotal() == 21) {
                p.setPosition(1);
                playersPassed++;
            }
        }
        if (playersPassed == players.size()) {
            endGame();
        }
    }

    private void initCards() {
        cards.put("1", 1);
        cards.put("2", 2);
        cards.put("3", 3);
        cards.put("4", 4);
        cards.put("5", 5);
        cards.put("6", 6);
        cards.put("7", 7);
        cards.put("8", 8);
        cards.put("9", 9);
        cards.put("10", 10);
        cards.put("J", 10);
        cards.put("Q", 10);
        cards.put("K", 10);
        cards.put("A", 11);
    }

    public void endGame() {
        displayResults();
    }

    private void displayResults() {
        Collections.sort(players, (p1, p2) -> {
            int diff1 = Math.abs(p1.getTotal() - 21);
            int diff2 = Math.abs(p2.getTotal() - 21);
            return Integer.compare(diff1, diff2);
        });

        int position = 1;
        for (Player player : players) {
            player.setPosition(position++);
            System.out.println(
                    player.getName() + " - Total: " + player.getTotal() + " - Position: " + player.getPosition());
        }
    }
}