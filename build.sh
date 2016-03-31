dir=("library" "sensor" "aggregator" "cloud" "config")
for i in ${dir[@]}
do
	cd $i 
	echo "Building $i"
	mvn clean install | grep "BUILD\|ERROR"
	cd ..
done
