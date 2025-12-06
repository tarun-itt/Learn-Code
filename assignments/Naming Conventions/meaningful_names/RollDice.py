import random

def rollDice(sides):
    result = random.randint(1, sides)
    return result


def main():
    diceSides = 6
    keepRolling = True

    while keepRolling:
        userInput = input("Ready to roll? Enter Q to Quit")

        if userInput.lower() != "q":
            rolledNumber = rollDice(diceSides)
            print("You have rolled a", rolledNumber)
        else:
            keepRolling = False

main()