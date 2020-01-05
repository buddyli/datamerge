package com.mariner.datamerge.loader;

import java.io.Reader;
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
	public List<RequestObj> loadRecords(Reader file) {

		try {
			Gson gson = new Gson();
			List<RequestObj> list = gson.fromJson(file, new TypeToken<List<RequestObj>>() {
			}.getType());

			return list;
		} catch (JsonIOException | JsonSyntaxException e) {
			e.printStackTrace();
		}

		return null;
	}

}
