//package me.carrio.hangman;

import javax.swing.JLabel;

public class TimedLabel extends JLabel{
	
	private String defaultText;
	private TimeThread timeThread,tmpThread;

	/**
	 * Generates a regular JLabel object with a Toast-like feature
	 */
	public TimedLabel(String defaultText){
		super(defaultText);
		this.defaultText=defaultText;		
	}

	/**
	 * Sets a temporary text in in the JLabel object
	 *
	 * The text in the JLabel will be set as the given String temporarily,
	 * which then reverts back to the defaultText String given to create the 
	 * object.
	 */
	public void shortText(String text){
		super.setText(text);
		if(timeThread!=null)
			timeThread.suspend();
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
				j.setText(defaultText);
				return;
			}
		}
	}
}