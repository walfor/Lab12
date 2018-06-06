package it.polito.tdp.rivers.model;

import java.util.PriorityQueue;

public class Simulazione {
	
	
	
	//parametri simulazione
	private float Q ;
	private float C ;
	private float f_in;
	private float f_out;
	private double f_out_min;
	private float f_med;
	
	
	
	
	//modello del mondo
	PriorityQueue<Event> queue;
	
	//coda degli eventi
	
	
	//parametri output

	
	
	
	public Simulazione(float f_med, int k) {
		
		Q=k*f_med*30;
		C=Q/2;
		this.f_med=f_med;	
		f_out_min = (0.8*f_med);
		queue= new PriorityQueue<>();
		
		
	}
	
	
	public void run() {
		while(!queue.isEmpty()) {
			
			Event e =queue.poll();
			this.processEvent(e);
		}
		
	}


	private void processEvent(Event e) {
		
		
	}
	
}
