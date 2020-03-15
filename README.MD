# covid19-unified-start-time-line-chart
Compare the Evolution of daily new cases detected of coronavirus Covid-19 between countries using a linear chart taking into account the population number and the  date of first case detected in each country

We can see the evolution of the total coronavirus cases or the coronavirus new cases per country and compare them.
Because we cant simply compare the numbers between a country having 1 400 000 000 person (china) and a country having 340 000 person (Iceland) we taking into account the population number of the country : the X Axis show ((the number of cases of coronavirus reporter) / country population) * 10 000 000
and because we cant compare a country which had its first (corona case/population)  on 20 Janury and an other which had its first (corona case/population) on 1 Mars. the Y Axis doesn't show the dates but it is showing a number of days whish depends on when the country have detected it's firts (corona case/population).

We have 2 kind of charts : Charts showing the total coronavirus cases and charts shoing the new coronavirus cases for each day.

### "China", "Italy", "Iran", "SouthKorea", "Spain", "Germany", "France", "US", "Tunisia"

-![alt text](https://github.com/influence160/covid19-unified-start-time-line-chart/tree/master/img/lesGeants-totalCases.JPG)

-![alt text](https://github.com/influence160/covid19-unified-start-time-line-chart/tree/master/img/lesGeants-newCases.JPG)



### "China", "Italy", "Switzerland", "Sweden", "Denmark", "Iceland", "Ireland"

-![alt text](https://github.com/influence160/covid19-unified-start-time-line-chart/tree/master/img/estEurp-totalCases.JPG)

-![alt text](https://github.com/influence160/covid19-unified-start-time-line-chart/tree/master/img/estEurp-newCases.JPG)



### "China", "UnitedArabEmirates", "Taiwan", "Finland", "Japan", "France" , "Thailand"

-![alt text](https://github.com/influence160/covid19-unified-start-time-line-chart/tree/master/img/lesVieux-totalCases.JPG)

-![alt text](https://github.com/influence160/covid19-unified-start-time-line-chart/tree/master/img/lesVieux-newCases2.JPG)



### "China", "Italy", "UnitedArabEmirates", "Taiwan", "Finland", "Japan", "France" , "Thailand"

-![alt text](https://github.com/influence160/covid19-unified-start-time-line-chart/tree/master/img/lesVieux-totalCases2.JPG)

-![alt text](https://github.com/influence160/covid19-unified-start-time-line-chart/tree/master/img/lesVieux-newCases2.JPG)




## to run
Download the runnable jar from /target/covid19-unified-start-time-line-chart-0.0.1-SNAPSHOT-jar-with-dependencies.jar
or with maven : exec:java -Dexec.mainClass="com.othmen.test.covid19.Main"  



I've downloaded the CSV data files from this project
https://github.com/CSSEGISandData/COVID-19