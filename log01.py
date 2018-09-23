Python 3.6.5 (v3.6.5:f59c0932b4, Mar 28 2018, 16:07:46) [MSC v.1900 32 bit (Intel)] on win32
Type "help", "copyright", "credits" or "license" for more information.
>>> x = 100
>>> y = 200
>>> print("Position X: %d , y: %y "%(x,y))
Traceback (most recent call last):
  File "<stdin>", line 1, in <module>
ValueError: unsupported format character 'y' (0x79) at index 21
>>> print("Position X: %d , y: %d "%(x,y))
Position X: 100 , y: 200 
>>> print('Position x: {} , y: {} '.format(x,y))
Position x: 100 , y: 200 
>>> money = 1000000
>>> print('I have money: {}'.format(money))
I have money: 1000000
>>> print('I have money: {:,d}'.format(money))
I have money: 1,000,000
>>> account = 45678903454306.95795687
>>> print('My bank account: %f bath'%account)
My bank account: 45678903454306.960938 bath
>>> print('My bank account: %.2f bath'%account)
My bank account: 45678903454306.96 bath
>>> print('My bank account: {:,.2f} bath'.format(account))
My bank account: 45,678,903,454,306.96 bath
>>> print('My bank account: {ac:,.2f} bath cash money{ch:,d}'.format(ac=account,ch=money))
My bank account: 45,678,903,454,306.96 bath cash money1,000,000
>>> print('My bank account: {ac:,.2f} bath cash money {ch:,d}'.format(ac=account,ch=money))
My bank account: 45,678,903,454,306.96 bath cash money 1,000,000
>>> 