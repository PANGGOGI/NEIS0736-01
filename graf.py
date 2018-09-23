import matplotlib.pyplot as plt
import webstock


xbar = [0,1,2,3]
data = [150,300,400,310]
day = ['1 June','2 June','3 June','4 June']

data2 = []
sname = []

stocklist = ['PTT','CPN','CPALL','AOT']
for s in stocklist:
	stn,stp = webstock.checkprice(s)

	data2.append(float(stp))
	sname.append(stn)

print(data2)

diff = float(max(data2))
print(diff)
diff2 = diff * 0.01
count = len(data2)

xbar2 = range(count)

#plt.plot(xbar,data)
plt.bar(xbar2,data2)

for i in range(count):
	textshow = "{}".format(data2[i])
	plt.text(xbar2[i],data2[i]+diff,str(data2[i]), ha='center')

#title
plt.xticks(xbar2,sname)

plt.show()
