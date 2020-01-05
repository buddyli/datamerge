# Data sorting and filtering

## Usage
Usage: `java -jar [datamerge-0.0.1-SNAPSHOT.jar] ["full path of the destination CSV file]`.

This project will generate a CSV output file with the full file path you specified, also it will print record numbers associated with each service-guid in console.

* Please change the ** jar name ** if you built the package with a different name.
* The destination file path should be the full path, e.g. "**/tmp/target.csv**".

## Introduction
The main entrance class is `com.mariner.datamerge.Datamerge`. It's responsibility is to load data from JSON, XML and CSV files, filter out records with packets-serviced equal to zero, sort by request-time in ascending order by lambda expression in one line, and write results to target CSV finally. Also this class calculates record numbers associated with each service-guid and print in console with format "service-guid: [service-guid], number: [number]".


## Environment requirements:
1. Java 8+
2. *If you want to build package by yourself, please make sure you have Maven installed and configured in your local.*

## Here are libraries used in this project to parse CSV, JSON and XML:
1. Google gson: By leveraging this library to parse JSON files. Can easily read from JSON and convert to Objects directly, with supporting of java annotation.
2. Simple-XML: By leveraging this library to parse XML files. It's a high performance XML serialization and configuration frame for Java, with full object serialization and deserialization, and great annotations support. 
3. Apache commons-csv: By leveraging this library to parse and write CSV files. This framework provides a common and simple interface for reading and writing CSV files.
