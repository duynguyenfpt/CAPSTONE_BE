package com.hoc.dto;

import java.util.ArrayList;
import java.util.List;

import com.hoc.entity.NewEntity;

public class CategoryDTO extends AbstractDTO<CategoryDTO>{
	private String code;
	
	private String name;
	
	private List<NewEntity> news = new ArrayList<>(); 

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<NewEntity> getNews() {
		return news;
	}

	public void setNews(List<NewEntity> news) {
		this.news = news;
	}
}
