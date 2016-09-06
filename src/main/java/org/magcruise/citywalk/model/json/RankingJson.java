package org.magcruise.citywalk.model.json;

import java.util.ArrayList;
import java.util.List;

public class RankingJson {

	private RankJson rank;
	private RankJson groupRank;
	private List<RankJson> ranking = new ArrayList<>();
	private List<RankJson> groupRanking = new ArrayList<>();

	public RankJson getGroupRank() {
		return groupRank;
	}

	public List<RankJson> getGroupRanking() {
		return groupRanking;
	}

	public RankJson getRank() {
		return rank;
	}

	public List<RankJson> getRanking() {
		return ranking;
	}

	public void setGroupRank(RankJson rankJson) {
		this.groupRank = rankJson;
	}

	public void setGroupRanking(List<RankJson> groupRanking) {
		this.groupRanking.addAll(groupRanking);

	}

	public void setRank(RankJson rankJson) {
		this.rank = rankJson;
	}

	public void setRanking(List<RankJson> ranking) {
		this.ranking.addAll(ranking);
	}

}
