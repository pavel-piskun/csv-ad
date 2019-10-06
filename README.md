# csv-ad
Test task

## Description
Load data from ten csv files in 3 thread by default

## Usage
1. mvn clean package
2. java -jar target/csv-ad-1.0-SNAPSHOT.jar

## Api
http://localhost:8080/api/GetAds - returns random 100 ad's without duplicates

http://localhost:8080/api/GetAds?count=200&strategy=WEIGHT - returns 200 ad's collected by configured compaign weigh.

Default compaign weigth configuration: campaigns={A:40, B:20, C:30}

## Config properties(could be easily changed by java arg)
```
csv.dir=src/main/resources/data
csv.loader.thread.count=3
default.ad.count=100
default.chooser.type=RANDOM
campaigns={A:40, B:20, C:30}
```
