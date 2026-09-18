
import os
os.getcwd()
os.chdir("C:/Users/hari2/OneDrive/Desktop/SmartFarmingHelper-00-02-09 (2) (1)/SmartFarmingHelper-00-02-09/src/CropRecommender/tjs.py")
file=open("n.txt","w")
file.write("Hello, World!")
file.close()

file=open("n.txt","r")
print(file.readline())
# print(file)
file.close()


file = open("n.txt","a")
file.write("\n Welcome to Python programming!")
file.close()