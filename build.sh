cd library
mvn clean install | grep "BUILD\|ERROR"
cd ..
cd sensor
mvn clean install | grep "BUILD\|ERROR"
cd ..
cd aggregator
mvn clean install | grep "BUILD\|ERROR"
cd ..
cd cloud
mvn clean install | grep "BUILD\|ERROR"
cd ..
cd config
mvn clean install | grep "BUILD\|ERROR"
cd ..
