# AggieThings Documentation

## Architecture
The following figure shows how this IoT simulator works.

![alt text](https://docs.google.com/drawings/d/1rbJk8jpd1BLOrh4lfPErCu1P0rGmb6Z7BI78QjneTek/pub?w=960&h=720 "Title")

All the arrows in the figure represents Socket Connections in the network. Multiple sensors send data to one aggregator. The aggregator uploads the data to the cloud. Currently there is only one aggregator and one cloud server.

## Module
### Sensor
Sensors can be configured to send certain amount of data to the aggregator with fixed intervals.
### Aggregator
The aggregator collect the data from all the sensors and attach time-stamps to each data item received from them. It saves these data items to a upload buffer. The upload buffer uploads the data to the cloud server. Currently, only one upload policy is implemented, which is upload as soon as possible.
### Cloud
The cloud server receives data from the aggregator and insert into the database. Currently, it is not connected to the database.

## Configuration
The only way to configure this project is to use a config file which is a .json file. There are four items in the json file, which are shown in the following table.


1. byteNum: The number of bytes in each data item.
2. intervalLength: The time interval between sending two data items.
3. itemNum: The total number of items send by a sensor.
4. sensorNum: The number of sensors connected to the aggregator.

If you want to change the IP address or the port, you need to dive into common.PortInfo to change it.
## Next Step
1. Add two more uploading policies to the aggregator.
2. Connect the project with influxDB.
3. Add a class which encloses all the steps needed to start each part of the project.
4. Let the cloud can connect to multiple aggregators.
5. Enable aggregators to upload data to an aggregator.

