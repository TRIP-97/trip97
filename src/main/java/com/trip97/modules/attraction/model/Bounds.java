package com.trip97.modules.attraction.model;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Bounds {

	int content;
	int sidoCode;
	int gugunCode;
	String title;

	BigDecimal ha; // 남
	BigDecimal qa; // 서
	BigDecimal oa; // 북
	BigDecimal pa; // 동

	public Bounds(BigDecimal ha, BigDecimal qa,BigDecimal oa, BigDecimal pa) {
		this.ha = ha;
		this.qa = qa;
		this.oa = oa;
		this.pa = pa;
	}

	public Bounds(int content, int sidoCode, int gugunCode, String title, BigDecimal ha, BigDecimal qa, BigDecimal oa, BigDecimal pa) {
		this.content = content;
		this.sidoCode = sidoCode;
		this.gugunCode = gugunCode;
		this.title = title;
		this.ha = ha;
		this.qa = qa;
		this.oa = oa;
		this.pa = pa;
	}

}
