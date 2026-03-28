import random


def is_valid_number(text: str):
    return text.isdigit() and 1 <= int(text) <= 100


def get_input(prompt: str):
    return input(prompt)


def check_guess(guess: int, secret_number: int):
    if guess < secret_number:
        return "Too low. Guess again "
    elif guess > secret_number:
        return "Too High. Guess again "
    else:
        return None


def main():
    secret_number = random.randint(1, 100)
    guessed_correctly = False
    guess_text = get_input("Guess a number between 1 and 100: ")
    attempts = 0

    while not guessed_correctly:

        if not is_valid_number(guess_text):
            guess_text = get_input(
                "I wont count this one Please enter a number between 1 to 100 "
            )
            continue

        attempts += 1
        guess = int(guess_text)

        result = check_guess(guess, secret_number)
        if result:
            guess_text = get_input(result)
        else:
            print("You guessed it in", attempts, "guesses!")
            guessed_correctly = True


main()
