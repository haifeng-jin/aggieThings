dir=("library" "sensor" "aggregator" "cloud" "config")
for i in ${dir[@]}
do
	cd $i 
	echo "Generating javadoc of $i"
	mvn javadoc:javadoc | grep "BUILD\|ERROR"
	cd ..
done
