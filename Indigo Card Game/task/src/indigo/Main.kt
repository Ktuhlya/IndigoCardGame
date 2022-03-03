package indigo

open class Player(var cardOnHand: MutableList<String>) {

    open fun getCard(): String? {
        var numCard: Any = 0
        if (cardOnHand.size == 0) return null
        cardOnHand.mapIndexed { index, s -> "${index + 1})$s" }
            .joinToString(" ", prefix = "Cards in hand: ").apply { println(this) }
        while (numCard !in 1..cardOnHand.size) {
            println("Choose a card to play (1-${cardOnHand.size}):")
            numCard = readln()
            if (numCard == "exit") return null
            else numCard = numCard.toIntOrNull() ?: 0
        }
        val card = cardOnHand[numCard.toString().toInt() - 1]
        cardOnHand.remove(card)
        if (cardOnHand.isEmpty()) cardOnHand = CardDeck.getCard()
        return card
    }

}

class PlayerComputer(cardOnHand: MutableList<String>) : Player(cardOnHand) {
    override fun getCard(): String? {
        if (cardOnHand.size == 0) return null
        val card = cardOnHand.random()
        cardOnHand.remove(card)
        if (cardOnHand.isEmpty()) cardOnHand = CardDeck.getCard()
        println("Computer plays $card")
        return card
    }
}

class Table {
    companion object {
        var cardOnTable = CardDeck.getCard(4)
        override fun toString(): String =
            "${cardOnTable.size} cards on the table, and the top card is ${cardOnTable.last()}"

        fun putCard(card: String) {
            cardOnTable.add(card)
        }
    }
}

class CardDeck {
    companion object {
        private var cardDeck = mutableListOf<String>()
        private val suits = listOf("♦", "♥", "♠", "♣")
        private val ranks = listOf("A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K")

        init {
            for (suit in suits)
                for (rank in ranks) {
                    cardDeck.add(rank + suit)
                }
            cardDeck.shuffle()
        }

        fun getCard(num: Int = 6): MutableList<String> {
            if (cardDeck.size < num) return mutableListOf()
            val getCards = cardDeck.slice(0 until num).toMutableList()
            cardDeck = cardDeck.drop(num).toMutableList()
            return getCards
        }
    }
}

class IndigoCardGame {
    fun star() {
        println("Indigo Card Game")
        var answer: String
        while (true) {
            println("Play first?")
            answer = readln().lowercase()
            when (answer) {
                "yes" -> {
                    game(player1 = Player(CardDeck.getCard()), player2 = PlayerComputer(CardDeck.getCard()))
                    break
                }
                "no" -> {
                    game(player1 = PlayerComputer(CardDeck.getCard()), player2 = Player(CardDeck.getCard()))
                    break
                }
                "exit" -> break
            }
        }
        println("Game Over")
    }

    private fun game(player1: Player, player2: Player) {
        var cardsFromPlayer: String
        println("Initial cards on the table: ${Table.cardOnTable.joinToString(" ")}")
        while (true) {
            println()
            println(Table)
            cardsFromPlayer = player1.getCard() ?: return
            Table.putCard(cardsFromPlayer)
            println()
            println(Table)
            cardsFromPlayer = player2.getCard() ?: return
            Table.putCard(cardsFromPlayer)
        }
    }
}

fun main() {
    IndigoCardGame().star()
}

/*
////////

            Solution by  Vladimir Nosov

////////
 */