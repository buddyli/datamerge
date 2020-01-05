package com.mariner.datamerge.loader;

import java.util.List;

import com.mariner.datamerge.bean.RequestObj;

/**
 * File parsers parent interface
 * 
 * @author leo
 *
 */
public interface RecordsLoader {
	public List<RequestObj> loadRecords(String file);
}
