package vue.palette;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuBar  {
	JMenu menu;
	JMenuItem e1, e2, e3, e4, e5, e6;

	public MenuBar() {
		JMenuBar menubar = new JMenuBar();
		JMenu menu = new JMenu("Menu");
		JMenuItem e1 = new JMenuItem("Element 1");
		JMenuItem e2 = new JMenuItem("Element 2");
		JMenuItem e3 = new JMenuItem("Element 3");

		menu.add(e1);
		menu.add(e2);
		menu.add(e3);
		menubar.add(menu);
	}
}
