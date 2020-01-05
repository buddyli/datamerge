package com.mariner.datamerge.loader;

import java.io.Reader;
import java.util.List;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import com.mariner.datamerge.bean.RequestObj;
import com.mariner.datamerge.bean.XMLRecords;

/**
 * Parse XML file by simple-xml library.
 * 
 * @author leo
 *
 */
public class XMLRecordsLoader implements RecordsLoader {

	@Override
	public List<RequestObj> loadRecords(Reader file) {
		Serializer serializer = new Persister();

		try {
			XMLRecords records = serializer.read(XMLRecords.class, file);
			return records.getRecords();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

}
