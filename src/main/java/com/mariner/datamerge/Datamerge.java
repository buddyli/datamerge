package com.mariner.datamerge;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.lang3.StringUtils;

import com.mariner.datamerge.bean.RequestObj;
import com.mariner.datamerge.loader.CSVRecordsLoader;
import com.mariner.datamerge.loader.JsonRecordsLoader;
import com.mariner.datamerge.loader.RecordsLoader;
import com.mariner.datamerge.loader.XMLRecordsLoader;

/**
 * Main entrance of datamerge
 * 
 * @author leo
 *
 */
public class Datamerge {
	public static void main(String[] args) {
		// Accept output file path via args parameter list
		if (args.length == 0 || StringUtils.isBlank(args[0])) {
			System.out.println("[Usage] java com.mariner.datamerge \"full path of destination output\"");
			System.exit(-1);
		}

		String destCsv = args[0];
		List<RequestObj> list = new LinkedList<>();

		// Load all records from csv, xml and json files
		RecordsLoader loader = new CSVRecordsLoader();
		list.addAll(loader.loadRecords(new InputStreamReader(Datamerge.class.getResourceAsStream("/reports.csv"))));

		loader = new JsonRecordsLoader();
		list.addAll(loader.loadRecords(new InputStreamReader(Datamerge.class.getResourceAsStream("/reports.json"))));

		loader = new XMLRecordsLoader();
		list.addAll(loader.loadRecords(new InputStreamReader(Datamerge.class.getResourceAsStream("/reports.xml"))));

		// Remove records with packts-serviced ==0, and sort by request time in
		// ascending order
		list = list.stream().filter(x -> x.getPacketsServiced() != 0)
				.sorted((r1, r2) -> r1.getReqTime().compareTo(r2.getReqTime())).collect(Collectors.toList());

		// Write sorted result to target CSV with the same format as "reports.csv"
		try (CSVPrinter printer = new CSVPrinter(new FileWriter(destCsv), CSVFormat.EXCEL)) {
			printer.printRecord("client-address", "client-guid", "request-time", "service-guid", "retries-request",
					"packets-requested", "packets-serviced", "max-hole-size");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");

			list.forEach(r -> {
				try {
					printer.printRecord(r.getReqAddress(), r.getReqGuid(), sdf.format(r.getReqTime()),
							r.getServiceGuid(), r.getRetriesRequest(), r.getPacketsRquested(), r.getPacketsServiced(),
							r.getMaxHoleSize());
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		// Calculate records number by associated service-guid and print
		Map<String, Long> counting = list.stream()
				.collect(Collectors.groupingBy(record -> record.getServiceGuid(), Collectors.counting()));
		counting.forEach((k, v) -> System.out.println("service-guid: " + k + ", number: " + v));
	}
}