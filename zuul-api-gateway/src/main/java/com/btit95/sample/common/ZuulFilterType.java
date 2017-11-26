package com.btit95.sample.common;

public enum ZuulFilterType {
	ERROR("error"), PREVIOUS("pre"), POST("post"), ROUTE("route");

	private final String value;

	private ZuulFilterType(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return this.value;
	}

	public boolean equalsName(String otherValue) {
		return value.equals(otherValue);
	}
}
