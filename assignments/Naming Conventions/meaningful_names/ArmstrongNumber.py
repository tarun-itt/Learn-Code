import math
def armstrongSum(number):
    # Initializing Sum and Number of Digits
    sumOfExponents = 0
    digitsCount = 0

    # Calculating Number of individual digits
    tempValue = number
    while tempValue > 0:
        digitsCount += 1
        tempValue = tempValue // 10

    # Finding Armstrong Number
    tempValue = number
    for index in range(1, tempValue + 1):
        currentDigit = tempValue % 10
        sumOfExponents += (currentDigit ** digitsCount)
        tempValue //= 10
    return sumOfExponents


# End of Function

# User Input
userInput = int(input("\nPlease Enter the Number to Check for Armstrong: "))

if (userInput == armstrongSum(userInput)):
    print("\n %d is Armstrong Number.\n" % userInput)
else:
    print("\n %d is Not a Armstrong Number.\n" % userInput)