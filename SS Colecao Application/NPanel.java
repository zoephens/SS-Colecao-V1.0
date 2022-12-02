
import javax.swing.*;  
import java.awt.*;  
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner; 


public class NPanel {
    private static JDialog dialog;

    private JPanel centrePanel;
    private JPanel topPanel;
    private JPanel btmPanel;
    private JButton nxt;
    private JButton showAll;
    private JLabel nMsg;
    private ArrayList<Notification> nlist;
    private JLabel nText;

    private  int counter;

    public NPanel(){//change to a panel
        nlist=getNotiText();
        counter=0;

        JFrame f= new JFrame("Notifications");
        f.setLayout(new BorderLayout());

        centrePanel = new JPanel();
        btmPanel= new JPanel();
        topPanel= new JPanel();


        nxt=new JButton("Next");
        nxt.addActionListener(new NextButtonListener());
        
        showAll=new JButton("Show all ("+notiLength(nlist)+") notification/s");
        showAll.addActionListener(new ShowButtonListener());

        nText= new JLabel ();
        nText.setText(nxtNotification());

        nText.addMouseListener(new nTextMouseListener());
        
        topPanel.add(nText); //Notification Text 
        btmPanel.add(nxt);
        btmPanel.add(showAll);   
        

        f.add(topPanel,BorderLayout.NORTH);
        f.add(btmPanel,BorderLayout.SOUTH);

        f.setSize(200,200);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.pack();
        f.setVisible(true);


        

        

    }
    
    private ArrayList<Notification> getNotiText(){
        
        Scanner nscan= null;
        
        ArrayList<Notification> notiList = new ArrayList<Notification>();
        
        try{
            
            nscan  = new Scanner(new File("database/notifications.txt"));//path needs to be changed to a variable
            
            while(nscan.hasNext())
            {
                
                String [] nextLine = nscan.nextLine().split("%");
                
                String title = nextLine[0];
                
                String text= nextLine[1];
                

                Notification noti= new Notification(title, text);
                notiList.add(noti);
                
            }
            nscan.close();
        }
        catch(IOException e){}
        for (Notification string : notiList) {
            System.out.println("|p| "+ string);
        }
        return notiList;
        //returns an array list of all the notifications

    }
    private String nxtNotification(){
        int notiLine=1;
        String textString="";
        
        if (nlist.size()>0)
        {
            for(Notification n: nlist)
            {
                
                if (notiLine==counter)
                {
                    textString=n.toString();
                    break;
                }
                notiLine++;
                
            }
            
           
        }else{
            textString= "There are no new notifications";
            return textString;
        }
        return textString;
    }
    
        
    
    
    private int notiLength(ArrayList list){
        return list.size();
    }


    private void deleteNoti(String selectedNoti){
        //notification deleted when clicked

    }

    private String gettitleNoti(){
        String notificationTxt= nText.getText();
        String[] nScreen=notificationTxt.split(":");
        String titleNoti=nScreen[0];
        return titleNoti;
    }


    private class nTextMouseListener implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {//when notification label is clicked it directs to corresponding screen
            // TODO Auto-generated method stub
            switch(gettitleNoti()){
                case "Warehouse":
                System.out.println("Entered Warehouse");
                break;
                case "Orders":
                System.out.println("Entered Order List");
                break;
                case "Delivery":
                System.out.println("Entered Delivery List");
                break;

            }
            
        }

        @Override
        public void mousePressed(MouseEvent e) {
            // TODO Auto-generated method stub
            
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            // TODO Auto-generated method stub
            
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            // TODO Auto-generated method stub
            
        }

        @Override
        public void mouseExited(MouseEvent e) {
            // TODO Auto-generated method stub
            
        }
        
    }

    private class NextButtonListener implements ActionListener
    {
        public void actionPerformed( ActionEvent e )  
            {  
                //opens next notification
                counter++;
                
                if(counter>notiLength(nlist)){
                    counter=1;
                }
                System.out.println("Next");
                nText.setText(nxtNotification());
            }  
        };  
    

    private class ShowButtonListener implements ActionListener
    {
        public void actionPerformed( ActionEvent e )  
            {  
                //open Notification List
                //NPanel.d.setVisible(false);
                System.out.println("Notification List opened");  
            }  
        }

}