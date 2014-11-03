import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.xml.parsers.ParserConfigurationException;

import com.fasterxml.jackson.databind.ObjectMapper;



public class Main {

	public static void main(String[] args) throws IOException, ParserConfigurationException {
		
		
		Cat marrameu = new Cat(100);
		System.out.println("XML cargado, y array lleno de urls...");

		ArrayList<String> miau = marrameu.getCat();
		ObjectMapper mapper = new ObjectMapper(); 

		File fur = new File("llistagats.json");	
		mapper.writeValue(fur, "Gato");
		mapper.writeValue(fur, miau);
		
		
		int purr = 0;		
		try {
            String path = miau.get(purr);
            miau.remove(purr);
            System.out.println("descargando primer gato desde... " + path);
            URL url = new URL(path);
            BufferedImage image = ImageIO.read(url);           
            
            JLabel label = new JLabel(new ImageIcon(image));
            label.addMouseListener(new MouseAdapter()   {   

                public void mouseClicked(MouseEvent e)   
                {                   	
                	if (!miau.isEmpty()) {
                		String path = miau.get(purr);
                    	miau.remove(purr);                	 
    					try {	                    
    						URL url = new URL(path);
    						System.out.println("Cargando siguiente imagen de gato desde..." + path);
    						BufferedImage image = ImageIO.read(url);
    	                    label.setIcon(new ImageIcon(image));
    					} catch (MalformedURLException e1) {
    						// TODO Auto-generated catch block
    						//e1.printStackTrace();
    					} catch (IOException e1) {
    						// TODO Auto-generated catch block
    						//e1.printStackTrace();
    					}
					}else{						
						System.out.println("Ya no hay mas gatos.");
					}               	
                              
                }   
            });            
            JFrame f = new JFrame();            
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f.getContentPane().add(label);
            f.pack();
            f.setLocation(200, 200);
            f.setVisible(true);
            
        } catch (Exception exp) {
            exp.printStackTrace();
        }
		
		
		
		
	}
	
	
}
