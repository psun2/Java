package jdbc_p;

public class RankDTO { // DB�� ��ȯ���ִ� ����. ����� getter�� setter�� �̷����

	String id;
	Integer score, rank;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	@Override
	public String toString() { // �����Ͱ� ���� ���������� ������ ����
		return "���� : " + rank + ", ID  : " + id + ", ���� : " + score;
	}

}
