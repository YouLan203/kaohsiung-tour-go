package kaohsiung_tour;

public class Ant {
	private int tourSize; // ���|����
	private boolean[] visited; // �O�_���L
	
	public int[] tour; // �w���X����
	public double length; // �w���X���|����
	
	// ��l��
	public Ant(int tourSize) {
		this.tourSize = tourSize;
		this.tour = new int[tourSize];
		this.visited = new boolean[tourSize];
	}
	
	// ���Ƴy�X���ǡA�y�X����
	public void visit(int cityIndex, int city) {
		this.tour[cityIndex] = city;
		this.visited[city] = true;
	}
	
	// �ˬd�O�_���X�L�ӫ���
	public boolean isVisited(int city) {
		return this.visited[city];
	}
	
	// �M�����X����
	public void clear() {
		for(int i = 0; i < this.tourSize; i++) {
			this.visited[i] = false;
		}
	}
	
}