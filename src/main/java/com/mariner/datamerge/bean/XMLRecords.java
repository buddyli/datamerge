package com.mariner.datamerge.bean;

import java.util.List;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root
public class XMLRecords {
	@ElementList(inline = true, entry="report")
	private List<RequestObj> records;

	public List<RequestObj> getRecords() {
		return records;
	}

	public void setRecords(List<RequestObj> records) {
		this.records = records;
	}

}
