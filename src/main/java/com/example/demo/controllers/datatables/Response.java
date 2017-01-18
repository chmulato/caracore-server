package com.example.demo.controllers.datatables;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;

public class Response<T extends Serializable> implements Serializable {

	private static final long serialVersionUID = 1L;

	private int draw;
	private long recordsTotal;
	private long recordsFiltered;
	private List<T> data;
	private String error;

	public List<T> getData() {
		return data;
	}

	public int getDraw() {
		return draw;
	}

	public String getError() {
		return error;
	}

	public long getRecordsFiltered() {
		return recordsFiltered;
	}

	public long getRecordsTotal() {
		return recordsTotal;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public void setDraw(int draw) {
		this.draw = draw;
	}

	public void setError(String error) {
		this.error = error;
	}

	public void setRecordsFiltered(long recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}

	public void setRecordsTotal(long recordsTotal) {
		this.recordsTotal = recordsTotal;
	}
	
	public static <T extends Serializable> Response<T> fromPage(int draw, Page<T> page) {
		Response<T> response = new Response<T>();
		response.setDraw(draw);
		response.setData(page.getContent());
		response.setRecordsFiltered(page.getTotalElements());
		response.setRecordsTotal(page.getTotalElements());
		return response;
	}
}
