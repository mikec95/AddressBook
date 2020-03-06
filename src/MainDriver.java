import javax.swing.JFrame;

/**
 * @author Michael Carbonari Main driver containing main class
 */
public class MainDriver {
	public static void main(String[] args) {
		JFrame jf = new JFrame("Contacts");// set title of JFrame
		AddressBook ab = new AddressBook();// create AddressBook object
		jf.setResizable(false);// user able to resize window? nope.
		jf.setSize(500, 500);// 500 px X 500px all they get
		jf.setVisible(true);// why not invisible
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// close when you press red 'x' at top right
		jf.add(ab);// add address book to jframe
	}
}
