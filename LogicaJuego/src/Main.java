public class Main {
    public static void main(String[] args) {
        BlackJack game = new BlackJack();

        Player player1 = new Player(game, "Juan");
        Player player2 = new Player(game, "Carlos");
        Player player3 = new Player(game, "Luis");

        game.addPlayer(player1);
        game.addPlayer(player2);
        game.addPlayer(player3);
        for (int i = 0; i < 2; i++) {
            for (Player player : game.getPlayers()) {
                player.requestCard();
            }
        }

        game.endGame();
    }
}
