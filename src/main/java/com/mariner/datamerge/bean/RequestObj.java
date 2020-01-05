package com.mariner.datamerge.bean;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import com.google.gson.annotations.SerializedName;

/**
 * Each object represents one client request record
 * 
 * @author leo
 *
 */
@Root
@Element(name="report")
public class RequestObj implements Comparable<RequestObj> {
	@SerializedName("client-address")
	@Element(name = "client-address")
	private String reqAddress;

	@SerializedName("client-guid")
	@Element(name = "client-guid")
	private String reqGuid;

	@Element(name = "request-time")
	private Date reqTime;

	@SerializedName("request-time")
	private long requestTime;

	@SerializedName("service-guid")
	@Element(name = "service-guid")
	private String serviceGuid;

	@SerializedName("retries-request")
	@Element(name = "retries-request")
	private int retriesRequest;

	@SerializedName("packets-requested")
	@Element(name = "packets-requested")
	private int packetsRquested;

	@SerializedName("packets-serviced")
	@Element(name = "packets-serviced")
	private int packetsServiced;

	@SerializedName("max-hole-size")
	@Element(name = "max-hole-size")
	private int maxHoleSize;

	public String getReqAddress() {
		return reqAddress;
	}

	public void setReqAddress(String reqAddress) {
		this.reqAddress = reqAddress;
	}

	public String getReqGuid() {
		return reqGuid;
	}

	public void setReqGuid(String reqGuid) {
		this.reqGuid = reqGuid;
	}

	public Date getReqTime() {
		Calendar cal = Calendar.getInstance();
		cal.setTimeZone(TimeZone.getTimeZone("ADT"));
		cal.setTimeInMillis(this.requestTime);

		return reqTime == null ? cal.getTime() : reqTime;
	}

	public void setReqTime(Date reqTime) {
		this.reqTime = reqTime;
	}

	public String getServiceGuid() {
		return serviceGuid;
	}

	public void setServiceGuid(String serviceGuid) {
		this.serviceGuid = serviceGuid;
	}

	public int getRetriesRequest() {
		return retriesRequest;
	}

	public void setRetriesRequest(int retriesRequest) {
		this.retriesRequest = retriesRequest;
	}

	public int getPacketsRquested() {
		return packetsRquested;
	}

	public void setPacketsRquested(int packetsRquested) {
		this.packetsRquested = packetsRquested;
	}

	public int getPacketsServiced() {
		return packetsServiced;
	}

	public void setPacketsServiced(int packetsServiced) {
		this.packetsServiced = packetsServiced;
	}

	public int getMaxHoleSize() {
		return maxHoleSize;
	}

	public void setMaxHoleSize(int maxHoleSize) {
		this.maxHoleSize = maxHoleSize;
	}

	public long getRequestTime() {
		return requestTime;
	}

	public void setRequestTime(long requestTime) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeZone(TimeZone.getTimeZone("ADT"));
		cal.setTimeInMillis(requestTime);

		this.requestTime = requestTime;
		this.reqTime = cal.getTime();
	}

	@Override
	public int compareTo(RequestObj o) {
		return this.reqTime.compareTo(o.getReqTime());
	}

}
