package mini.hakwon;
import java.awt.EventQueue;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import mini.hakwon.view.View;

public class Hakwon {
	/* git test */
	/**
	 * ���α׷� ����
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
		 * ������ ����
		 */
		View frame = new View();
		frame.setVisible(true);
		
	}

}
