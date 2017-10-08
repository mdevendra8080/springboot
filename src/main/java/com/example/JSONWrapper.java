package com.example;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAnyElement;

public class JSONWrapper<T> {
	private List<T> items;
	 
    public JSONWrapper() {
        items = new ArrayList<T>();
    }
 
    public JSONWrapper(List<T> items) {
        this.items = items;
    }
 
    @XmlAnyElement(lax=true)
    public List<T> getItems() {
        return items;
    }
}
