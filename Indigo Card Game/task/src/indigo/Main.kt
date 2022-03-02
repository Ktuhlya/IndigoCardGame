package indigo

import kotlin.system.exitProcess

val fullDeck = mutableListOf<String>("A♦", "2♦", "3♦", "4♦", "5♦", "6♦", "7♦",
    "8♦", "9♦", "10♦", "J♦", "Q♦", "K♦",
    "A♥", "2♥", "3♥", "4♥", "5♥", "6♥", "7♥",
    "8♥", "9♥", "10♥", "J♥", "Q♥", "K♥", "A♠",
    "2♠", "3♠", "4♠", "5♠", "6♠", "7♠", "8♠",
    "9♠", "10♠", "J♠", "Q♠", "K♠", "A♣",
    "2♣", "3♣", "4♣", "5♣", "6♣", "7♣",
    "8♣", "9♣", "10♣", "J♣", "Q♣", "K♣")
var bitoDeck = MutableList<String>(52){"empty"}

fun main() {
    var deck = fullDeck
     letsPlay(deck)

}

fun letsPlay(deck: MutableList<String>) {
    print("Choose an action (reset, shuffle, get, exit):\n>")
    when(readln()) {
        "reset" -> resetC()
        "shuffle" -> shuffleC(deck)
        "get" -> getCards(deck)
        "exit" -> exitP()
        else -> wrong(deck)
    }
}

fun exitP() {
    println("Bye")
    exitProcess(0)
}

fun wrong(deck: MutableList<String>) {
    println("Wrong action.")
    letsPlay(deck)
}

fun getCards(deck: MutableList<String>) {
    println("Number of cards:")
    val number = readln()
    if (number.matches(Regex("[1-9]|[1-4][0-9]|5[1-2]"))){
      if (number.toInt() > deck.size) {
          println("The remaining cards are insufficient to meet the request.")
          letsPlay(deck)
      }else{
          for (i in 0 until number.toInt()) {
              print("${deck[i]} ")
          }
       //   deck.map { it.removeRange(0 until number.toInt())}
            for (i in number.toInt()-1 downTo 0) {
                deck.removeAt(i)
            }
          println()
          letsPlay(deck)
          }

    }else{
        println("Invalid number of cards.")
        letsPlay(deck)
    }

}

fun shuffleC(deck: MutableList<String>) {
    deck.shuffle()
    println("Card deck is shuffled.")
    letsPlay(deck)
}

fun resetC() {
    println("Card deck is reset.")
    letsPlay(fullDeck)
}

fun chekRegex(str: String): Boolean {
   // val regex = Regex(""
    return true

}
