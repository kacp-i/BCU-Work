weight = float(input("What is your weight in KG\n"))
height = float(input("What is your height in Meters\n"))

bmi = weight / (height * height)

if bmi < 18.5:
    print("You are underweight")
elif bmi >= 18.5 and bmi < 25:
    print("You are healthy")
elif bmi >= 25 and bmi < 30:
    print("You are overweight")
else:
    print("You are obese-")