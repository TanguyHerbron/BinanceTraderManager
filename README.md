# BinanceTraderManager

Manager for [yasinkuyu's trading bot](https://github.com/yasinkuyu/binance-trader).
The manager will reboot automatically the python script and keep track of its logs in a logfile.

# Requirements

Java 9.0 or upper

# Installation

Create a directory "log" where you have placed the jar file.

The manager will start the script by the name of "trader.sh" that has to contain your configuration for the python bot.

An example of command to put in your trader.sh file :
'''trader.sh
python trader.py --symbol XVGBTC --quantity 300 --profit 1.3
'''

Then, I recommand to use another scipt to start the Manager with a custom JVM argument, it'll make your logs readable.
I use a script called "starter.sh" which looks like this :
'''starter.sh
java -Djava.util.logging.SimpleFormatter.format='%1$tY-%1$tm-%1$td %1$tH:%1$tM:%1$tS %4$s %2$s %5$s%6$s%n' -jar BinanceHandlerTrader.jar
'''

Finally, your directory should look like this :
![Example](https://github.com/TanguyHerbron/BinanceTraderManager/blob/master/img/example.png)
