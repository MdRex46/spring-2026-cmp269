# CMP 269: Programming Methods III
# Python Introduction Exercises - Mister White

def exercise_1_basics():
    """
    Goal: Practice basic syntax and string formatting.
    Task: Create variables for a course name and a number of students.
    Print a sentence using an f-string.
    """
    course = "Programming Methods III"
    students = 24
    print(f"The course {course} has {students} students.")


def exercise_2_collections():
    """
    Goal: Manipulate lists and dictionaries.
    Task:
    1. Create a list of 5 colors.
    2. Add a 6th color to the end.
    3. Create a dictionary with keys 'name' and 'gpa'.
    """
    # 1. Five colors of the NYC subway line map
    colors = ["scarlet", "cobalt", "amber", "slate", "crimson"]

    # 2. Add a 6th color
    colors.append("ivory")
    print("Colors:", colors)

    # 3. Student dictionary
    student = {
        "name": "Jordan Rivera",
        "gpa": 3.6
    }
    print("Student:", student)


def exercise_3_logic():
    """
    Goal: Use loops and conditionals.
    Task: Iterate through a list of numbers.
    If a number is even, add it to a new list called 'evens'.
    """
    numbers = [11, 4, 7, 22, 3, 16, 9, 8, 5, 14]
    evens = []

    for num in numbers:
        if num % 2 == 0:
            evens.append(num)

    print("Even numbers:", evens)


if __name__ == "__main__":
    print("--- Exercise 1 ---")
    exercise_1_basics()
    print("\n--- Exercise 2 ---")
    exercise_2_collections()
    print("\n--- Exercise 3 ---")
    exercise_3_logic()
