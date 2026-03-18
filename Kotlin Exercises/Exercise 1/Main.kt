fun main() {
    val studentName: String = "Zephyr"
    val middleName: String? = null

    println("Welcome, $studentName ${middleName ?: "No Middle Name"}!")
}
