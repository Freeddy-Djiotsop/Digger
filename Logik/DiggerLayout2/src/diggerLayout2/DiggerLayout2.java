package diggerLayout2;

import java.awt.EventQueue;
import javax.swing.JFrame;

/**
 *
 * @author Aldemashki
 */
public class DiggerLayout2 extends JFrame {
    
    public DiggerLayout2(){
        initUI();
    }
    

    private void initUI() {
        add(new Map());
        setTitle("Digger");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600,592);
        setResizable(false);
       
        setLocationRelativeTo(null);
    
    }



    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
     EventQueue.invokeLater(() -> {
			
			DiggerLayout2 ex = new DiggerLayout2();
			ex.setVisible(true);
			
			String filepath = "Msic.wav";
			Map musicObject = new Map();
			musicObject.playMusic(filepath);
		

	});
        // TODO code application logic here
    }
}
    

