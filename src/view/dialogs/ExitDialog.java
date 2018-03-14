package view.dialogs;

import javax.swing.JOptionPane;

public class ExitDialog {

	public static void confirmExit() {
		String ObjButtons[] = {"Yes","No"};
		int result = JOptionPane.showOptionDialog(null,"Are you sure you want to exit?",
				"Confirm Exitting",JOptionPane.DEFAULT_OPTION,
				JOptionPane.WARNING_MESSAGE,null,ObjButtons,ObjButtons[1]);
		
		if(result ==JOptionPane.YES_OPTION)
        {
            System.exit(0);
        }
	}
}
