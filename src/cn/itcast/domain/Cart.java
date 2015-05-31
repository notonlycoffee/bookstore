package cn.itcast.domain;

import java.util.LinkedHashMap;
import java.util.Map;

public class Cart {
	
	private Map<String,CartItem> map = new LinkedHashMap<String,CartItem>();
	private double price;
	
	public void addBook(Book book) {
		CartItem item = map.get(book.getId());
		if( item == null) {
			item = new CartItem();
			item.setBook(book);
			item.setQuantity(1);
			map.put(book.getId(), item);
		} else {
			item.setQuantity(item.getQuantity() + 1);
		}
	}
	
	public Map<String, CartItem> getMap() {
		return map;
	}
	public void setMap(Map<String, CartItem> map) {
		this.map = map;
	}
	public double getPrice() {
		
		double totalPrice = 0;
		for(Map.Entry<String, CartItem> entry : this.map.entrySet()) {
			CartItem item = entry.getValue();
			totalPrice += item.getPrice();
		}
		this.price = totalPrice;
		return price;
	}
	
	
}
