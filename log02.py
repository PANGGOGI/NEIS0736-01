Python 3.6.5 (v3.6.5:f59c0932b4, Mar 28 2018, 16:07:46) [MSC v.1900 32 bit (Intel)] on win32
Type "copyright", "credits" or "license()" for more information.
>>> import tultle
Traceback (most recent call last):
  File "<pyshell#0>", line 1, in <module>
    import tultle
ModuleNotFoundError: No module named 'tultle'
>>> import turtle
>>> tao.turtle.Pen()
Traceback (most recent call last):
  File "<pyshell#2>", line 1, in <module>
    tao.turtle.Pen()
NameError: name 'tao' is not defined
>>> tao.turtle1.Pen()
Traceback (most recent call last):
  File "<pyshell#3>", line 1, in <module>
    tao.turtle1.Pen()
NameError: name 'tao' is not defined
>>> import turtle
>>> tao = turtle.Pen()
>>> turtle.shape()
'classic'
>>> turtle.shape("turtlr")
Traceback (most recent call last):
  File "<pyshell#7>", line 1, in <module>
    turtle.shape("turtlr")
  File "<string>", line 8, in shape
  File "C:\Users\TK_GoGii\AppData\Local\Programs\Python\Python36-32\lib\turtle.py", line 2776, in shape
    raise TurtleGraphicsError("There is no shape named %s" % name)
turtle.TurtleGraphicsError: There is no shape named turtlr
>>> turtle.shape()
'classic'
>>> for i in range(4):
	tao.forward(100)
	tao.left(90)
	print("I value", i)

	
I value 0
I value 1
I value 2
I value 3
>>> friend = ['s','p','n','g']
>>> print(friend[2])
n
>>> xy = (300,400)
>>> grade = {'s': 3.2,'p':3.5,'n':4.0,'g':3.7}
>>> print(grade)
{'s': 3.2, 'p': 3.5, 'n': 4.0, 'g': 3.7}
>>> print (grade['p'])
3.5
>>> print(friend)
['s', 'p', 'n', 'g']
>>> friend.append('sompong')
>>> prient(friend)
Traceback (most recent call last):
  File "<pyshell#22>", line 1, in <module>
    prient(friend)
NameError: name 'prient' is not defined
>>> print(friend)
['s', 'p', 'n', 'g', 'sompong']
>>> firend.insert(2,sommai)
Traceback (most recent call last):
  File "<pyshell#24>", line 1, in <module>
    firend.insert(2,sommai)
NameError: name 'firend' is not defined
>>> friend.insert(2,'somying')
>>> print(friend)
['s', 'p', 'somying', 'n', 'g', 'sompong']
>>> friend.remove('somying')
>>> print(friend)
['s', 'p', 'n', 'g', 'sompong']
>>> del friend[0]
>>> print(friend)
['p', 'n', 'g', 'sompong']

>>> for number in friend:
	print(runner)
	
KeyboardInterrupt
>>> for runner in friend:
	print(runner)

	
p
n
g
sompong
>>> range(10)
range(0, 10)
>>> list(range(10))
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
>>> number = [20,99,34,30]
>>> for i in number:
	print(i)

	
20
99
34
30
>>> 
