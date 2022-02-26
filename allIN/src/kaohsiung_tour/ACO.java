package kaohsiung_tour;

public class ACO {
	// �i���
	
	// �Ѹջ~�k���աA�q�` beta > alpha �H��o�̨ε��G
    private final double alpha = 1; // �P���X���n��
    private final double beta = 2; // �Z�����n��
	private final double c = 10; // ��l�P���X�@��
	private final double evaporation = 0.8; // �P���X�I�h�ѼơA�i�]��0.8 ~ 0.9����
	private final double Q = 100; // �P���X�j��
	private final double antFactor = 1; // ���ƪ��ƶq�A��ĳ ���Ƽƶq = �`�I�`�ƶq
	
	private final int maxIterator = 100; // ���榸��
	
	// ���i���
	
	// �x�s��
	private int numberOfCity; // �`�I�ƶq
	private int numberOfAnt; // ���Ƽƶq
	
	private double[][] visibility; // �ਣ��
	private double[][] pheromone; // �P���X
	
//	private City[] cities; // ����
	private Ant[] ants; // ����
	
	// �p���
	private int currentIndex; // �������Ʒ�e�y�X��������
	private double[] probability; // �p��y�X�������v
	
	// �̨θ��|
	public int[] bestTourOrder = null; // �̨ήȦ涶��
	private double bestTourLength = 0; // �̨ήȦ涶��
	
	// ��l��
	public ACO(City[] cities){
		int citySize = cities.length;
		this.numberOfCity = citySize;
		this.numberOfAnt = (int) (this.antFactor * citySize);
//		this.cities = cities;
		this.ants = new Ant[this.numberOfAnt];
		this.probability = new double[citySize];
		this.visibility = new double[citySize][citySize];
		this.pheromone = new double[citySize][citySize];
		
		// �N���D�ର���|�Φ�
		for(int i = 0; i < this.numberOfCity; i++) {
			for(int j = 0; j < this.numberOfCity; j++) {
				this.visibility[i][j] = this.getDistance(cities[i], cities[j]);
			}
		}
		
		// ��l�ƶO���X�@��
		for(int i = 0; i < this.numberOfCity; i++) {
    		for(int j = 0; j < this.numberOfCity; j++) {
    			this.pheromone[i][j] = c;
        	}
    	}
		
		// ��l�ƿ���
		for(int i = 0; i < this.numberOfAnt; i++) {
			this.ants[i] = new Ant(this.numberOfCity);
		}
	}
	
	// �p���ӫ��������Z��
	private double getDistance(City fromCity, City toCity) {
    	double a = Math.pow(fromCity.x - toCity.x, 2);
    	double b = Math.pow(fromCity.y - toCity.y, 2);
		return Math.sqrt(a + b);
	}
	
	// �B�����
	public void execute() {
		
		// �]�w�������
		for(int i = 1; i <= this.maxIterator; i++) {
			// ���Ʒj�M���|
			this.setupAnts();
			this.moveAnts();
			// ��s�O���X
			this.updatePheromones();
			// ��s�̨θ��|
			this.updateBest();
			// ��X�i��
			int process = i * 100 / this.maxIterator;
			System.out.println(process);
		}
		
		// ��X��T
	}
	
	// �N�����H�����b�U�ӫ����W
	private void setupAnts() {
		this.currentIndex = 0;
		for(int i = 0; i < this.numberOfAnt; i++) {
			Ant ant = this.ants[i];
			ant.clear();
			ant.visit(this.currentIndex, (int) (Math.random() * this.numberOfCity));
		}
	}
	
	// �y�X�Ҧ������íp���`�Z��
	private void moveAnts() {
		// �y�X�Ҧ�����
		for(int nextIndex = 1; nextIndex < this.numberOfCity; nextIndex++) {
			for(int i = 0; i < this.numberOfAnt; i++) {
				Ant ant = this.ants[i];
				int nextCity = this.selectNextCity(ant);
				ant.visit(nextIndex, nextCity);
			}
			this.currentIndex = nextIndex;
		}
		// �p��Z��
		for(int i = 0; i < this.numberOfAnt; i++) {
			Ant ant = this.ants[i];
			double total = 0;
			for(int j = 0; j < this.numberOfCity; j++) {
				int fromCity = ant.tour[j];
				int toCity = ant.tour[(j + 1) % this.numberOfCity];
				total += this.visibility[fromCity][toCity];
			}
			ant.length = total;
		}
	}
	
	// ���ƿ�ܤU�@�ӳy�X������
	private int selectNextCity(Ant ant) {
		return this.updateProbarbility(ant);
	}
	
	// ��s�ഫ���v�C i => ��e���� , j => ���y�X����
	private int updateProbarbility(Ant ant) {
		
		int i = ant.tour[this.currentIndex];
		
		double denominator = 0; // ����
		for(int j = 0; j < this.numberOfCity; j++) {
			if(!ant.isVisited(j)) {
				// �p�GAB�u�q�Z����0(�P�@�a�I)�A�g�b�o�̥i�H�ֶ]�@���j��
				if(this.visibility[i][j] == 0) return j;
				// ���y�X�L���� * �u�q���׭˼�
				double reciprocal = 1 / this.visibility[i][j];
				if(Double.isFinite(reciprocal)) {
					denominator += Math.pow(this.pheromone[i][j], this.alpha) * Math.pow(reciprocal, this.beta);
				}
			}
		}
		
		for(int j = 0; j < this.numberOfCity; j++) {
			double numerator = 0; // ���l
			
			// �Y i �w�y�X�L j || ������0
 			if(ant.isVisited(j) || denominator == 0) {
				this.probability[j] = 0;
			}else {
				// ���y�X���� * �u�q���׭˼�
				numerator = 0;
				double reciprocal = 1 / this.visibility[i][j];
				if(Double.isFinite(reciprocal)) {
					numerator = Math.pow(this.pheromone[i][j], this.alpha) * Math.pow(reciprocal, this.beta);					
				}
				
				this.probability[j] = numerator / denominator;
			}
		}
		
		return this.rouletteWheelSelection();
	}
	
	// ���L�k�H���y�X����
	private int rouletteWheelSelection() {
		
		// �H����ܫ���
		double rnd = Math.random();
		double total = 0;
		for(int i = 0; i < this.numberOfCity; i++) {
			total += this.probability[i];
			if(total > rnd) return i;
		}
		
		throw new RuntimeException("�S����y�X������");
	}
	
	// ��s�O���X�@�סA�����s�k
	private void updatePheromones() {
		
		// �¶P���X�I�h
		for(int i = 0; i < this.numberOfCity; i++) {
			for(int j = 0; j < this.numberOfCity; j++) {
				this.pheromone[i][j] *= this.evaporation;
			}
		}
		
		// �s�O���X�|�[
		for(int i = 0; i < this.numberOfAnt; i++) {
			Ant ant = this.ants[i];
			double pheromoneDropsAmount = this.Q / ant.length;
			for(int j = 0; j < this.numberOfCity; j++) {
				int fromCity = ant.tour[j];
				int toCity = ant.tour[(j + 1) % this.numberOfCity];
				this.pheromone[fromCity][toCity] += pheromoneDropsAmount;
			}
		}
	}
	
	// ��s�̨θ��|
	private void updateBest() {
		
		for(int i = 0; i < this.numberOfAnt; i++) {
			Ant ant = this.ants[i];
			if(this.bestTourOrder == null || ant.length < this.bestTourLength) {
				this.bestTourOrder = ant.tour;
				this.bestTourLength = ant.length;
			}
		}
		
	}
}