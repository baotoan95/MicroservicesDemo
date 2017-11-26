package com.btit95.sample.filter;

import com.btit95.sample.common.ZuulFilterType;
import com.netflix.zuul.ZuulFilter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PreFilter extends ZuulFilter {

	@Override
	public Object run() {
		log.info("Previous filter");
		return null;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public int filterOrder() {
		return 0;
	}

	@Override
	public String filterType() {
		return ZuulFilterType.PREVIOUS.toString();
	}

}
