import pyautogui as pg 
import time

total = int(input('Enter Total Loop: '))

for count in range(total):

	for i in range(10):
		xp = 100+(i*50)
		yp = 50
		pg.moveTo(xp,yp) #pg.moveTo(x,y)
		print(xp,",",yp)


	for i in range(10):
		xp2 = xp
		yp2 = 50+(i*50)
		pg.moveTo(xp2,yp2) #pg.moveTo(x,y)
		print(xp2,",",yp2)

	for i in range(10):
		xp3 = xp-(i*50)
		yp3 = xp2
		pg.moveTo(xp3,yp3) #pg.moveTo(x,y)
		print(xp3,",",yp3)

	for i in range(10):
		xp4 = xp3
		yp4 = yp3-(i*50)
		pg.moveTo(xp4,yp4) #pg.moveTo(x,y)
		print(xp4,",",yp4)
