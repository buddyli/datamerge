package com.mariner.datamerge.loader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import com.mariner.datamerge.bean.RequestObj;

/**
 * Parse CSV by Apache commons-csv tool.
 * 
 * @author leo
 *
 */
public class CSVRecordsLoader implements RecordsLoader {

	@Override
	public List<RequestObj> loadRecords(Reader file) {
		List<RequestObj> list = null;

		try {
			list = new LinkedList<RequestObj>();
			Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(file);

			for (CSVRecord record : records) {
				RequestObj rObj = new RequestObj();

				rObj.setMaxHoleSize((Integer.parseInt(record.get("max-hole-size"))));
				rObj.setPacketsRquested(Integer.parseInt(record.get("packets-requested")));
				rObj.setPacketsServiced(Integer.parseInt(record.get("packets-serviced")));
				rObj.setReqAddress(record.get("client-address"));
				rObj.setReqGuid(record.get("client-guid"));

				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
				try {
					rObj.setReqTime(sdf.parse(record.get("request-time")));
				} catch (ParseException e) {
					e.printStackTrace();
				}

				rObj.setRetriesRequest(Integer.parseInt(record.get("retries-request")));
				rObj.setServiceGuid(record.get("service-guid"));

				list.add(rObj);
			}

			return list;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

}
