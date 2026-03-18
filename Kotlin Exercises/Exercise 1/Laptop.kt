data class Laptop(val brand: String, val ramGB: Int)

fun Int.toLehmanGigabytes(): String = "$this GB (Lehman Standard)"

fun main() {
    val laptop1 = Laptop("Razer", 32)
    val laptop2 = Laptop("Lenovo", 64)

    println(laptop1.ramGB.toLehmanGigabytes())
    println(laptop2.ramGB.toLehmanGigabytes())
}
