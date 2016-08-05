package org.magcruise.citywalk.model.json;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class RewardJson {

	private List<String> badges = new ArrayList<>();

	private int rank = 0;

	public RewardJson() {
		// TODO 自動生成されたコンストラクター・スタブ
		rank = 3;
		badges = Arrays.asList("早稲田マスター", "AEDマスター");
	}

	public List<String> getBadges() {
		return badges;
	}

	public void setBadges(List<String> badges) {
		this.badges = badges;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
