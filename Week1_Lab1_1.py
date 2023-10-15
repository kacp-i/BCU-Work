# Lab1  Exercise #9 (Part 1)

import math #import math library


A = float(input("Enter the coefficient A: ")) #3 sets of number inputs (float type)
B = float(input("Enter the coefficient B: "))
C = float(input("Enter the coefficient C: "))

print("The coefficients of the equation are:") #prints all assigned variables
print("Coefficient A =", A)
print("Coefficient B =", B)
print("Coefficient C =", C)


# ****Modify that program to compute the two roots of a quadratic equation, as described in the program comments. ****
D = math.sqrt((B * B) - (4 * A * C))

root1 = ((-B + D) / (2 * A))
root2 = ((-B - D) / (2 * A))


print("\nThe roots of the equation:")
print("Root #1 = ", round(root1,2))  # round the result to two decimal places before printing
print("Root #2 = ", round(root2,2))