import requests
import os

"""
CMP 269: Programming Methods III
In-Class Assignment: File I/O and API Integration
"""

def task_1_append_logger():
    """
    TASK 1: The Persistent Logger
    Goal: Use append mode to store notes in a file and display the history.
    """
    print("--- Task 1: Append Logger ---")
    note = input("Enter a short note for the log: ")

    with open("session_log.txt", "a") as log_file:
        log_file.write(note + "\n")

    print("\nSaved Notes:")
    with open("session_log.txt", "r") as log_file:
        print(log_file.read())


def task_2_word_count_utility():
    """
    TASK 2: The File Analyzer
    Goal: Create a text file, then read it and count the words.
    """
    print("\n--- Task 2: Word Count Utility ---")

    motto = "Knowledge is Power. Go Lightning! Python makes data easy."

    with open("lehman_motto.txt", "w") as motto_file:
        motto_file.write(motto)

    with open("lehman_motto.txt", "r") as motto_file:
        text = motto_file.read()
        word_count = len(text.split())
        print(f"Word count: {word_count}")


def task_3_api_status_checker():
    """
    TASK 3: API Resilience
    Goal: Handle status codes and timeout errors from a web request.
    """
    print("\n--- Task 3: API Status Checker ---")

    url = "https://jsonplaceholder.typicode.com/posts/101"

    try:
        response = requests.get(url, timeout=5)

        if response.status_code == 200:
            print(response.json())
        elif response.status_code == 404:
            print("Error: Post not found.")
        else:
            print(f"Unexpected status code: {response.status_code}")

    except requests.exceptions.Timeout:
        print("Error: Request timed out.")
    except requests.exceptions.RequestException as error:
        print(f"Network error: {error}")


def task_4_data_filtering():
    """
    TASK 4: JSON Data Processing
    Goal: Print the names of users whose address suite contains "Suite".
    """
    print("\n--- Task 4: Data Filtering ---")

    url = "https://jsonplaceholder.typicode.com/users"

    try:
        response = requests.get(url, timeout=5)
        users = response.json()

        for user in users:
            suite = user.get("address", {}).get("suite", "")
            if "Suite" in suite:
                print(user.get("name"))

    except requests.exceptions.RequestException as error:
        print(f"Network error: {error}")


def task_5_integration_report():
    """
    TASK 5: The Integration Challenge
    Goal: Fetch a post and save its title and body to a local report file.
    """
    print("\n--- Task 5: Integration Report ---")

    url = "https://jsonplaceholder.typicode.com/posts/1"

    try:
        response = requests.get(url, timeout=5)
        post = response.json()

        title = post.get("title", "No title available")
        body = post.get("body", "No body available")

        with open("api_report.txt", "w") as report_file:
            report_file.write("API Report\n")
            report_file.write("===========\n\n")
            report_file.write(f"Title: {title}\n\n")
            report_file.write(f"Body:\n{body}\n")

        print("Report Generated")

    except requests.exceptions.RequestException as error:
        print(f"Network error: {error}")


if __name__ == "__main__":
    # Uncomment any task below to run it individually.
    # task_1_append_logger()
    # task_2_word_count_utility()
    # task_3_api_status_checker()
    # task_4_data_filtering()
    # task_5_integration_report()
    pass
