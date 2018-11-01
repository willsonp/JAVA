//LA IMAGEN BIENE EN EL ARCHIVO ASI QUE SI QUIEREN PUEDEN //BAJARSELO NO? :D
 
 
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package imagenes;
 
/**
 *
 * @author Alumno
 */
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
 
public class ImagePanel extends JPanel {
 
    private BufferedImage image;
 
    public ImagePanel() {
        try {
            image = ImageIO.read(new File("lily.jpg"));
        } catch (IOException ex) {
// handle exception...
        }
    }
 
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null); // see javadoc for more info on the parameters
    }
 
    public static void main(String args[]) {
        ImagePanel obj = new ImagePanel();
        JFrame mn = new JFrame();
        mn.setSize(800, 800);
        mn.add(obj);
        mn.setVisible(true);
    }
}
 
//----------------------------
 
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package imagenes;
 
/**
 *
 * @author Alumno
 */
import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
 
public class Translucida extends JPanel {
 
    @Override
    public void paint(Graphics g) {
// En este caso tendremos que trabajar con una BufferedImage
        URL imgURL = Expositor.class.getResource("lily.jpg");
        BufferedImage img = null;
        try {
            img = ImageIO.read(imgURL);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int w = img.getWidth(null);
        int h = img.getHeight(null);
// Necesitaremos una imagen auxiliar a la que aplicar un filtro
        BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics gI = bi.getGraphics();
// Sobre esta imagen dibujaremos la imagen original
        gI.drawImage(img, 0, 0, null);
        Graphics2D g2d = (Graphics2D) g;
// Establecemos la componente alpha de nuestros graficos 50%
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
        /* Draw the image, applying the filter */
        g2d.drawImage(bi, null, 0, 0);
// Pintamos el resto de componentes
        super.paint(g);
    }
}
 
//-----------------------------
 
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package imagenes;
 
/**
 *
 * @author Alumno
 */
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
 
public class Original extends JPanel {
// Cargamos la imagen original
    private ImageIcon photo = Expositor.createImage("lily.jpg");
 
    @Override
    public void paint(Graphics g) {
        g.drawImage(photo.getImage(), 0, 0, null);
// Pintamos el resto de componentes
        super.paint(g);
    }
}
 
//------------------------
 
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package imagenes;
 
/**
 *
 * @author Alumno
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
 
public class Expositor extends JPanel {
 
    /**
     * Constructor.
     */
    public Expositor() {
        /*
         * Creamos un layout de 4 paneles, en cada uno mostraremos una imagen
         * "aparentemente" distinta
         */
        super(new GridLayout(2, 2));
        setPreferredSize(new Dimension(800, 534));
// Los paneles con las imagenes
        Original original = new Original();
        EscalaDeGrises escalaDeGrises = new EscalaDeGrises();
        Espejo espejo = new Espejo();
        Translucida translucida = new Translucida();
        Dimension photoDimension = new Dimension(400, 267);
        original.setPreferredSize(photoDimension);
        escalaDeGrises.setPreferredSize(photoDimension);
        espejo.setPreferredSize(photoDimension);
        translucida.setPreferredSize(photoDimension);
        original.setOpaque(false);
        escalaDeGrises.setOpaque(false);
        espejo.setOpaque(false);
        translucida.setOpaque(false);
// Etiquetas
        JLabel originalLabel = new JLabel("ORIGINAL");
        JLabel escalaDeGrisesLabel = new JLabel("ESCALA DE GRISES");
        JLabel espejoLabel = new JLabel("ESPEJO");
        JLabel translucidaLabel = new JLabel("TRANSLUCIDA");
        originalLabel.setForeground(Color.BLACK);
        escalaDeGrisesLabel.setForeground(Color.BLACK);
        espejoLabel.setForeground(Color.BLACK);
        translucidaLabel.setForeground(Color.BLACK);
        original.add(originalLabel);
        escalaDeGrises.add(escalaDeGrisesLabel);
        espejo.add(espejoLabel);
        translucida.add(translucidaLabel);
        this.add(original);
        this.add(escalaDeGrises);
        this.add(espejo);
        this.add(translucida);
    }
 
    /**
     * Para recuperar una imagen de un archivo...
     *     
* @param path Ruta de la imagen relativa al proyecto
     * @return una imagen
     */
    public static ImageIcon createImage(String path) {
        URL imgURL = Expositor.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
}
 
------------------------------
 
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package imagenes;
 
/**
 *
 * @author Alumno
 */
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
 
public class Espejo extends JPanel {
// Cargamos la imagen original
    private ImageIcon photo = Expositor.createImage("lily.jpg");
 
    @Override
    public void paint(Graphics g) {
// Imagen
        Image img = photo.getImage();
        int width = photo.getIconWidth();
        int height = photo.getIconHeight();
// Rotamos la imagen
        g.drawImage(img, 0, 0, width, height, 0, height, width, 0, null);
// Pintamos el resto de componentes
        super.paint(g);
    }
}
 
--------------------------------
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package imagenes;
 
/**
 *
 * @author Alumno
 */
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import javax.swing.GrayFilter;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
 
public class EscalaDeGrises extends JPanel {
// Cargamos la imagen original
    private ImageIcon photo = Expositor.createImage("lily.jpg");
 
    @Override
    public void paint(Graphics g) {
// Imagen
        Image img = photo.getImage();
// Filtro de escala de grises
        ImageFilter filter = new GrayFilter(true, 50);
        ImageProducer producer = new FilteredImageSource(img.getSource(), filter);
        ImageIcon newIcon = new ImageIcon(this.createImage(producer));
        newIcon.paintIcon(this, g, 0, 0);
// Pintamos el resto de componentes
        super.paint(g);
    }
}