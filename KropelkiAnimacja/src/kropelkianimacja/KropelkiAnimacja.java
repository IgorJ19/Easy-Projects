package kropelkianimacja;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.SecureRandom;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
public class KropelkiAnimacja extends JFrame{
    
    public KropelkiAnimacja(){
        this.setTitle("Animacja Kropelki");
        this.setBounds(250, 300, 300, 250);
        panelButtonow.add(start);
        panelButtonow.add(usun);
        panelButtonow.add(przerwa);
        panelAnimacji.setBackground(Color.GRAY);
        start.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
            startAnimation();    
            }
        
    });
        usun.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
            stopAnimation();    
            }
    
    });
         przerwa.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
             ileKlkniecPrzerwy++;  
             if(ileKlkniecPrzerwy >1)
                 ileKlkniecPrzerwy = 0;
                System.out.println(ileKlkniecPrzerwy);
                 
            }
            
            
    });
         
       
        
        przerwa.setToolTipText("1 click = stop;  click = przywróć ");
        this.getContentPane().add(panelAnimacji);
        this.getContentPane().add(panelButtonow, BorderLayout.SOUTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
 
    private JPanel panelButtonow = new JPanel();
    private JButton start = new JButton("start");
    private JButton usun = new JButton("usun");
    private JButton przerwa = new JButton("przerwa");
    private PanelAnimacji panelAnimacji = new PanelAnimacji();
    static int ileKlkniecPrzerwy = 0;
    
    
    Thread watek;
    ThreadGroup grupaWatkow = new ThreadGroup("Grupa kropelek");
    public void startAnimation(){
        panelAnimacji.addKropelka();
        
        
    }
    public void stopAnimation(){
       panelAnimacji.stop();
    }
    
    
    
    public static void main(String[] args) 
    {
        new KropelkiAnimacja().setVisible(true);
    }
    class PanelAnimacji extends JPanel{
        
        public void addKropelka(){
            
            watek = new Thread(grupaWatkow, new KropelkaRunnable());
            watek.start();
           
            
            grupaWatkow.list();
            
           
        }
        
        public void stop(){
           grupaWatkow.interrupt();
        }
        @Override
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            
            for(int i = 0; i<listaKropelek.size(); i++){
                g.drawImage(Kropelka.getImg(),((Kropelka)listaKropelek.get(i)).x, ((Kropelka)listaKropelek.get(i)).y, null);
                
            }
        }
        ArrayList listaKropelek = new ArrayList();
        
        public class KropelkaRunnable implements Runnable{
            
            
            
            
            
            public void run(){
               try{
                
                while(!Thread.interrupted()){
                
                listaKropelek.add(new Kropelka());
                for (int i = 0; i<600; i++){
                    for(int j=0; j<listaKropelek.size(); j++){
                        if(ileKlkniecPrzerwy == 0){
                    ((Kropelka)listaKropelek.get(j)).ruszKropelka(panelAnimacji);
                        } 
                        if(ileKlkniecPrzerwy == 1){
                    ((Kropelka)listaKropelek.get(j)).zatrzymajKropelke(panelAnimacji);
                        } 
                    repaint();
                     Thread.sleep(1);
                    }
               }
             }
                
                   
                                       
                }catch (InterruptedException ex) {
                    System.out.println(ex.getMessage());
                    listaKropelek.clear();
                    repaint();
                }
            }
        }
     }
  }
class Kropelka extends losowanieStartu{
    
    public static Image getImg(){
        return Kropelka.kropelka;
    }
    
    public void ruszKropelka(JPanel pojemnik){
       Rectangle granicePanelu = pojemnik.getBounds();
        
        x += dx;
        y += dy;
        int czyOdbilo = 0;
        if(y + yKropelki >= granicePanelu.getMaxY()){
            y = (int)(granicePanelu.getMaxY()-yKropelki);
            dy = -dy;
           
        }
        if(x + xKropelki >= granicePanelu.getMaxX()){
            x = (int)(granicePanelu.getMaxX() - xKropelki);
            dx = -dx;
        }
        if(x <= granicePanelu.getMinX()){
            x = (int)(granicePanelu.getMinX() + xKropelki);
            dx = -dx;
        }
        if(y <= granicePanelu.getMinY()){
            y = (int)(granicePanelu.getMinY() + yKropelki);
           dy = -dy;
            
        }
    }
       public void zatrzymajKropelke(JPanel pojemnik){
       Rectangle granicePanelu = pojemnik.getBounds();
        
        x += 0;
        y += 0;
        
        if(y + yKropelki >= granicePanelu.getMaxY()){
            y = (int)(granicePanelu.getMaxY()-yKropelki);
            dy = -dy;
        }
        
        if(x + xKropelki >= granicePanelu.getMaxX()){
            x = (int)(granicePanelu.getMaxX() - xKropelki);
            dx = -dx;
        }
        if(x <= granicePanelu.getMinX()){
            x = (int)(granicePanelu.getMinX() + xKropelki);
            dx = -dx;
        }
        if(y <= granicePanelu.getMinY()){
            y = (int)(granicePanelu.getMinY() + yKropelki);
            dy = -dy;
        }
    }
    public static Image kropelka = new ImageIcon("C:\\Users\\jagus\\Documents\\NetBeansProjects\\animacjaKropelki\\src\\main\\java\\com\\mycompany\\animacjakropelki\\kropelka.gif").getImage();
    

int dx = 1;
int dy = 1;
int xKropelki = kropelka.getWidth(null);
int yKropelki = kropelka.getHeight(null);
int wysEkranu = Toolkit.getDefaultToolkit().getScreenSize().height;



    }
class losowanieStartu{
    
       Random rand = new Random();
       int szer = Toolkit.getDefaultToolkit().getScreenSize().width;
       int los = rand.nextInt((szer-0)+1)+0;
       int x = los;
       int y = 0;
   
}


