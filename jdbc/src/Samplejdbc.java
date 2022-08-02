import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;


public class Samplejdbc extends JFrame implements ActionListener {

    int[] a =new int[5];

    JLabel Jname,LblMobile,LblGender,Lbldob,LblAd,Lblheading,Lblid;
    JPanel jPanel;
    JFrame jFrame;
    JTextField Txtname,Txtmobile,Txtid;
    JTextArea TxtAddress,Display;
    JRadioButton Male,Female;
    JComboBox<Integer> Date,Year;
    JComboBox<String> Month;
    JCheckBox c;
    JButton b,r;
    ButtonGroup group;

    HashMap<String,Integer> hash=new HashMap<>();
    Samplejdbc(){
        jPanel=new JPanel();
        jPanel.setLayout(null);
        jPanel.setBackground(new Color(123, 111, 222));
        jFrame=new JFrame();
        jFrame.setTitle("Registration form");
        jFrame.setSize(700,600);

        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Lblheading=new JLabel("Employee Registration");
        Lblheading.setBounds(220,10,300,50);
        Lblheading.setFont(new Font("Serif", Font.PLAIN, 25));
        jPanel.add(Lblheading);

        Lblid=new JLabel("Employee id:");
        Lblid.setBounds(50,70,100,50);
        jPanel.add(Lblid);

        Txtid=new JTextField();
        Txtid.setBounds(160,80,100,30);
        jPanel.add(Txtid);

        Jname=new JLabel("Name:");
        Jname.setBounds(50,120,50,50);
        jPanel.add(Jname);

        Txtname= new JTextField();
        Txtname.setBounds(100,130,100,30);
        jPanel.add(Txtname);

        LblMobile=new JLabel("Mobile:");
        LblMobile.setBounds(50,180,100,30);
        jPanel.add(LblMobile);
        Txtmobile=new JTextField();
        Txtmobile.setBounds(100,180,150,30);
        jPanel.add(Txtmobile);

        LblGender=new JLabel("Gender:");
        LblGender.setBounds(50,230,100,30);

        jPanel.add(LblGender);

        group=new ButtonGroup();

        Male=new JRadioButton("Male");
        Male.setActionCommand("Male");
        //Point to be noted
        Male.setBackground(new Color(123, 111, 222));
        Female=new JRadioButton("Female");
        Female.setActionCommand("Female");
        Female.setBackground(new Color(123, 111, 222));

        Male.setBounds(100,230,100,30);
        Female.setBounds(200,230,100,30);
        group.add(Male);
        group.add(Female);

        jPanel.add(Male);
        jPanel.add(Female);

        Lbldob=new JLabel("DOB:");
        Lbldob.setBounds(50,280,100,30);
        jPanel.add(Lbldob);

        Integer[] date=new Integer[31];
        for (int i = 1; i <32 ; i++) {
            date[i-1]=i;
        }
        Date =new JComboBox<Integer>(date);  //Shows error in notepad if we use new jcombo(date)
        Date.setBounds(100,280,50,30);
        jPanel.add(Date);

        String[] month={"JAN","FEB","MAR","APR","MAY","JUN","JUL","AUG","SEP","OCT","NOV","DEC"};
        int i=0;
        for (String a :
                month) {
            hash.put(a,i++);
        }
        Month=new JComboBox<String>(month);
        Month.setBounds(150,280,65,30);
        jPanel.add(Month);

        Integer[] year=new Integer[72];
        int count=0;
        for ( i = 1950; i<2022; ++i){
            year[count]=i;
            ++count;
        }
        Year=new JComboBox<Integer>(year);
        Year.setBounds(220,280,70,30);
        jPanel.add(Year);

        LblAd=new JLabel("Address:");
        LblAd.setBounds(50,320,100,30);
        jPanel.add(LblAd);

        TxtAddress=new JTextArea();
        TxtAddress.setBounds(100,320,200,100);
        jPanel.add(TxtAddress);

        Display=new JTextArea();
        Display.setBounds(350,100,300,380);
        jPanel.add(Display);

        c=new JCheckBox("Agree to terms and conditions");
        c.setBounds(100,430,200,50);
        c.setBackground(new Color(123, 111, 222));
        jPanel.add(c);

        b=new JButton("Submit");
        b.setBounds(100,490,90,20);
        b.addActionListener(this);
        jPanel.add(b);
        r=new JButton("Show all");
        r.setBounds(220,490,90,20);
        r.addActionListener(this);
        jPanel.add(r);
        jFrame.getContentPane().add(jPanel);

        jFrame.setVisible(true);
        jFrame.setLayout(null);
        jFrame.getContentPane().setBackground(new Color(123, 111, 222));
    }

    public void Datemap(String d){

    }

    public void actionPerformed(ActionEvent e){


        if(e.getSource()==b){
           try {
               String sid=Txtid.getText();
               String sname=Txtname.getText();
               String smobile=Txtmobile.getText();
               String sadd=TxtAddress.getText();
               String sgen= group.getSelection().getActionCommand();

               String date=Date.getSelectedItem().toString();
               String month=Month.getSelectedItem().toString();
               String year=Year.getSelectedItem().toString();

               Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/company","root","system2003");


               String dob=year+"-"+hash.get(month)+"-"+date;

               PreparedStatement stmt=connection.prepareStatement("insert into employee"+" (idemployee,emp_name,emp_gender,emp_dob,emp_add,emp_pno)"+"values(?,?,?,?,?,?)");
               stmt.setString(1,sid);
               stmt.setString(2,sname);
               stmt.setString(3,sgen);
               stmt.setString(4,dob);
               stmt.setString(5,sadd);
               stmt.setString(6,smobile);

                int i=stmt.executeUpdate();

                Display.setText(i+" record added to db");


           }
           catch (Exception ex){
               ex.printStackTrace();

           }
        }
        if(e.getSource()==r){
            try{


                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/company","root","system2003");

                Statement statement=connection.createStatement();

                ResultSet set = statement.executeQuery("select * from employee");
                Display.setText("");
                while (set.next()){

                    Display.append("\nId:"+set.getString(1)+"\n"+"Name:"+set.getString(2)+"\n"+"Gender:"+set.getString(3)+
                            "\n"+"Dob:"+set.getString(4)+"\n"+"Address:"+set.getString(5)+"\n"+"PhoneNo"+set.getString(6));
                }


            }catch(Exception el){
                el.printStackTrace();
            }

        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Samplejdbc();
            }
        });
    }
}


