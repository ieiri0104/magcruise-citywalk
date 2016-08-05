package org.magcruise.citywalk.model.json;

import java.util.ArrayList;
import java.util.List;

public class RewardJson {

	private List<String> badges = new ArrayList<>();

	private int rank = 0;

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

}
