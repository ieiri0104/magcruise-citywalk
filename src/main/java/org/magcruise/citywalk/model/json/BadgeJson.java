package org.magcruise.citywalk.model.json;

public class BadgeJson {

	private String name;
	private String imgSrc;

	public BadgeJson(String name, String imgSrc) {
		this.name = name;
		this.imgSrc = imgSrc;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImgSrc() {
		return imgSrc;
	}

	public void setImgSrc(String imgSrc) {
		this.imgSrc = imgSrc;
	}

}
