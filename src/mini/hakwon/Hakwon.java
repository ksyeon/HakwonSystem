package mini.hakwon;
import java.awt.EventQueue;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import mini.hakwon.view.View;

public class Hakwon {
	/* git test */
	/**
	 * 프로그램 실행
	 */
	public static void main(String[] args) {

		Model con = new Model();

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		} catch (UnsupportedLookAndFeelException e1) {
			e1.printStackTrace();
		}
		/*
		 * 프레임 띄우기
		 */
		View frame = new View();
		frame.setVisible(true);
		
	}

}
