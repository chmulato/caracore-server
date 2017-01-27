package com.example.demo.controllers.datatables;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Request implements Serializable {

	public static class Column extends HashMap<String, Object> {

		private static final long serialVersionUID = 1L;

		public Column() {
			super();
			setSearch(new Search());
		}

		public String getData() {
			return (String) get("data");
		}

		public String getName() {
			return (String) get("name");
		}

		public Boolean getOrderable() {
			return Boolean.valueOf((String) get("orderable"));
		}

		public Search getSearch() {
			return (Search) get("search");
		}

		public Boolean getSearchable() {
			return Boolean.valueOf((String) get("searchable"));
		}

		public void setData(String data) {
			put("data", data);
		}

		public void setName(String name) {
			put("name", name);
		}

		public void setOrderable(String orderable) {
			put("orderable", orderable);
		}

		public void setSearch(Search search) {
			put("search", search);
		}

		public void setSearchable(String searchable) {
			put("searchable", searchable);
		}
	}

	public static class Order extends HashMap<String, Object> {

		private static final long serialVersionUID = 1L;

		public Integer getColumn() {
			return Integer.valueOf((String) get("column"));
		}

		public String getDir() {
			return (String) get("dir");
		}

		public void setColumn(String column) {
			put("column", column);
		}

		public void setDir(String dir) {
			put("dir", dir);
		}
	}

	public static class Search extends HashMap<String, Object> {

		private static final long serialVersionUID = 1L;

		public Boolean getRegex() {
			return Boolean.valueOf((String) get("regex"));
		}

		public String getValue() {
			return (String) get("value");
		}

		public void setRegex(String regex) {
			put("regex", regex);
		}

		public void setValue(String value) {
			put("value", value);
		}
	}

	private static final long serialVersionUID = 1L;

	private Integer draw;
	private Integer start;
	private Integer length;
	private Search search;
	private List<Order> order;
	private List<Column> columns;

	public List<Column> getColumns() {
		return columns;
	}

	public Integer getDraw() {
		return draw;
	}

	public String[] getFilterColumns() {
		List<String> fc = new LinkedList<String>();
		for (Column column: columns) {
			if (column.getSearchable()) {
				fc.add(column.getData());
			}
		}
		
		String[] filterColumns = new String[fc.size()];
		return fc.toArray(filterColumns);
	}

	public Integer getLength() {
		if (length == null || length <= 0) {
			return Integer.MAX_VALUE;
		}
		return length;
	}

	public List<Order> getOrder() {
		return order;
	}

	public String[] getOrderColumns() {
		List<String> oc = new LinkedList<String>();
		if (order != null) {
			for (Order or: order) {
				Column col = columns.get(or.getColumn());
				oc.add(col.getData() + " " + or.getDir());
			}
		}
		
		String[] orderColumns = new String[oc.size()];
		return oc.toArray(orderColumns);
	}

	public Search getSearch() {
		return search;
	}

	public Integer getStart() {
		if (start == null || start <= 0) {
			return 0;
		}
		return start;
	}

	public void setColumns(List<Column> columns) {
		this.columns = columns;
	}

	public void setDraw(Integer draw) {
		this.draw = draw;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public void setOrder(List<Order> order) {
		this.order = order;
	}
	
	public void setSearch(Search search) {
		this.search = search;
	}
	
	public void setStart(Integer start) {
		this.start = start;
	}
}
