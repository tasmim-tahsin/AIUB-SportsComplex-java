import javax.swing.*;
import java.awt.*;
 
public class SplashScreen {
    JFrame frame;
    JLabel image=new JLabel(new ImageIcon("aiublogo.png"));
    JLabel text=new JLabel("AIUB Sports Complex");
    JProgressBar progressBar=new JProgressBar();
    JLabel message=new JLabel();
    SplashScreen()
    {
        createGUI();
        addImage();
        addText();
        addProgressBar();
        addMessage();
        runningPBar();
        
    }
    public void createGUI(){
        frame=new JFrame();
        frame.getContentPane().setLayout(null);
        frame.setUndecorated(true);
        frame.setSize(600,400);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(Color.WHITE);
        frame.setVisible(true);
 
    }
    public void addImage(){
        image.setBounds(200,10, 200,200);
        frame.add(image);
    }
    public void addText()
    {
        text.setFont(new Font("arial",Font.BOLD,30));
        text.setBounds(140,220,600,40);
        text.setForeground(new Color(144,238,144));
        frame.add(text);
    }
    public void addMessage()
    {
        message.setBounds(250,320,200,40);
        message.setForeground(new Color(144,238,144));
        message.setFont(new Font("arial",Font.BOLD,15));
        frame.add(message);
    }
    public void addProgressBar(){
        progressBar.setBounds(100,280,400,30);
        progressBar.setBorderPainted(true);
        progressBar.setStringPainted(true);
        progressBar.setBackground(Color.WHITE);
        progressBar.setForeground(new Color(144,238,144));
        progressBar.setValue(0);
        frame.add(progressBar);
    }
    public void runningPBar(){

        int i=0;
        
        while( i<=100)
        {
            try{
                Thread.sleep(30);
                progressBar.setValue(i);
                message.setText("LOADING "+Integer.toString(i)+"%");
                i++;
                if(i==100)
                    frame.dispose();
            }catch(Exception e){
                e.printStackTrace();
            }
 
 
 
        }
    }
}
