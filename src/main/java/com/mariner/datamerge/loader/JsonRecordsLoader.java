package com.mariner.datamerge.loader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.mariner.datamerge.bean.RequestObj;

/**
 * Parse json file by leveraging Google gson utility.
 * 
 * @author leo
 *
 */
public class JsonRecordsLoader implements RecordsLoader {

	@Override
	public List<RequestObj> loadRecords(String file) {

		try {
			Gson gson = new Gson();
			List<RequestObj> list = gson.fromJson(new FileReader(file), new TypeToken<List<RequestObj>>() {
			}.getType());

			return list;
		} catch (JsonIOException | JsonSyntaxException | FileNotFoundException e) {
			e.printStackTrace();
		}

		return null;
	}

}
