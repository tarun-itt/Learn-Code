import random

def isInputInRange(playerInput):
    if playerInput.isdigit() and 1<= int(playerInput) <=100:
        return True
    else:
        return False

def main():
    numberToGuess = random.randint(1,100)
    guessedCorrect= False
    playerInput = input("Guess a number between 1 and 100:")
    guessCount = 0

    while not guessedCorrect :
        if not isInputInRange(playerInput):
            playerInput = input("I wont count this one Please enter a number between 1 to 100")
            continue
        else:
            guessCount += 1
            guessedNumber = int(playerInput)

        if guessedNumber < numberToGuess:
            playerInput = input("Too low. Guess again")
        elif guessedNumber > numberToGuess:
            playerInput = input("Too High. Guess again")
        else:
            print("You guessed it in",guessCount,"guesses!")
            guessedCorrect=True

main()