fun main() {
    val successResponse = WebResponse(
        statusCode = 200,
        statusMessage = "OK",
        body = """{ "message": "Request succeeded." }"""
    )

    val notFoundResponse = WebResponse(
        statusCode = 404,
        statusMessage = "Not Found",
        body = "<h1>404 - Page Missing</h1>"
    )

    println("=== Exercise 1: WebResponse instances ===")
    println(successResponse)
    println(notFoundResponse)
    println()

    println("=== Exercise 2: Status descriptions ===")
    val codesToTest = listOf(201, 404, 503, 123)
    for (code in codesToTest) {
        println("Code $code -> ${describeStatus(code)}")
    }
    println()

    println("=== Exercise 3: Routing results ===")
    println(routeRequest("/home", "Alice"))
    println(routeRequest("/home", null))
    println(routeRequest("/grades", null))
    println(routeRequest("/grades", "Bob"))
    println(routeRequest("/profile", "Charlie"))
}
