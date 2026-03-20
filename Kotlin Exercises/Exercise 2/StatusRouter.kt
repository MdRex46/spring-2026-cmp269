fun describeStatus(code: Int): String {
    return when (code) {
        in 200..299 -> "Success: The request was fulfilled."
        in 400..499 -> "Client Error: Check your URL or parameters."
        in 500..599 -> "Server Error: The Lehman Server is having trouble."
        else -> "Unknown status code."
    }
}

fun routeRequest(path: String, user: String?): String {
    return when (path) {
        "/home" -> "Welcome to the Lehman Homepage, ${user ?: "Guest"}!"
        "/grades" ->
            if (user == null) {
                "Error: Unauthorized access to grades."
            } else {
                "Loading grades for $user..."
            }
        else -> "404: Path $path not found."
    }
}
