//package me.carrio.hangman;

import javax.swing.JLabel;

public class TimedLabel extends JLabel{
	
	private TimeThread timeThread;

	public TimedLabel(){
		super();		
	}

	public void shortText(String text){
		super.setText(text);
		timeThread = new TimeThread(this);
		timeThread.start();
	}

	private class TimeThread extends Thread{
		private JLabel j;
		private int t;
		public TimeThread(JLabel j){
			super();
			this.j=j;
		}

		public void run(){
			try{
				
				Thread.sleep(2500);
			} catch(Exception e){
				;
			} finally {
				j.setText("");
				return;
			}
		}
	}
}