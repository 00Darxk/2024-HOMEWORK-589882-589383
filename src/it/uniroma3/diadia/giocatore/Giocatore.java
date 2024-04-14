package it.uniroma3.diadia.giocatore;

public class Giocatore {
		private int CFU;
		private Borsa bag;
		
		public Giocatore() {
			this.bag = new Borsa();
		}
		
		public Giocatore(int CFU) {
			this.bag= new Borsa();
			this.CFU= CFU;
		} 
		
		
		public int getCFU() {
			return CFU;
		}
		public void setCFU(int cFU) {
			CFU = cFU;
		}
		public Borsa getBag() {
			return bag;
		}
		public void setBag(Borsa bag) {
			this.bag = bag;
		}
		
		
}
