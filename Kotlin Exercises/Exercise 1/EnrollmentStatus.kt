sealed class EnrollmentStatus {
    data class Success(val courseCode: String) : EnrollmentStatus()
    data class Error(val message: String) : EnrollmentStatus()
    object Loading : EnrollmentStatus()
}

fun printStatus(status: EnrollmentStatus) {
    when (status) {
        is EnrollmentStatus.Success -> println("You're in! Course ${status.courseCode} has been added to your schedule.")
        is EnrollmentStatus.Error   -> println("Oops! Could not enroll: ${status.message}")
        is EnrollmentStatus.Loading -> println("Hang tight, checking enrollment...")
    }
}

fun main() {
    printStatus(EnrollmentStatus.Success("MTH301"))
    printStatus(EnrollmentStatus.Error("Prerequisites not met"))
}
