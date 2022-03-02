package indigo

fun main() {
   val rank: List<String> ="A 2 3 4 5 6 7 8 9 10 J Q K".split(" ").toList()
    val suit =("♦ ♥ ♠ ♣").split(" ").toList()
     val card = mutableListOf<String>()
    suit.forEach { s: String -> rank.forEach{rank -> card.add(rank+s)}}
    println(card.joinToString(" "))

}
