from bs4 import BeautifulSoup as soup

from urllib.request import urlopen as req

def checkprice(name):

	url = 'http://www.settrade.com/C04_02_stock_historical_p1.jsp?txtSymbol='+name+'&ssoPageId=10&selectPage=2'

	webopen = req(url)
	page_html = webopen.read()  #open Web 
	webopen.close()  #Close Web

	stock_page = soup(page_html,"html.parser")

	#print(stock_page.title)

	data = stock_page.findAll('div',{'class':'col-xs-6'})
	#print(len(data))

	stock_name = data[0].text
	stock_price = data[2].text

	#print(stock_name)
	#print(data[1].text)
	#print(data[2].text)
	print("STOCK: {} PRICE: {} BATH".format(stock_name,stock_price))
	return(stock_name,stock_price)

"""
checkprice('PTT')  #แสดงราคา
checkprice('TMB')
checkprice('SUPER')
"""
