import random;

while 1:
	if random.getrandbits(1):
		print("Hello");
	else :
		x = {}
		for i in range(1000000):
			x = {1: x}
		repr(x)

##input("press close to exit");