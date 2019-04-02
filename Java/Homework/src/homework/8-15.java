package homework;


import javax.swing.*;
import javax.tools.Tool;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.*;

class Info
{
    private JFrame jFrame;
    private JTextField name, passwd;
    private JButton login, register;
    private JPanel jPanel1, jpaneL2;
    private HashSet<Student> hashSet;
    private File file;
    private Toolkit toolkit = Toolkit.getDefaultToolkit();
    private Font font = new Font("΢���ź�", 1, 20);
    private JLabel jLabel1, jLabel2;

    public Info()
    {
        init();
    }

    public void init()
    {
        file = new File("D://info.obj");
        jFrame = new JFrame("��¼");
        jFrame.setLayout(new FlowLayout());
        name = new JTextField(14);
        name.setVisible(true);
        name.setFont(font);
        passwd = new JTextField(14);
        passwd.setFont(font);
        register = new JButton("ע��");
        login = new JButton("��¼");

        jLabel1 = new JLabel("�ʺ�");
        jLabel1.setFont(font);
        jLabel2 = new JLabel("����");
        jLabel2.setFont(font);

        jpaneL2 = new JPanel();
        jpaneL2.setLayout(new FlowLayout());
        jpaneL2.add(login);
        jpaneL2.add(register);

        jFrame.add(jLabel1);
        jFrame.add(name);
        jFrame.add(jLabel2);
        jFrame.add(passwd);
        jFrame.add(jpaneL2);
        jFrame.setBounds((toolkit.getScreenSize().width - 400) / 2, (toolkit.getScreenSize().height - 400) / 2, 350, 170);

        jFrame.setVisible(true);
        login.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                String Name = name.getText();
                String PassWd = passwd.getText();
                Student student = new Student(Name, PassWd);
                readFile();
                if (isExists(student, false))
                {
                    mDialog("��¼�ɹ�","��ӭ��");
                }
                else
                {
                    mDialog("��¼ʧ��","��˶��ʺ������Ƿ�ƥ�䣡");
                }
            }
        });

        register.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                String Name = name.getText();
                String PassWd = passwd.getText();
                Student student = new Student(Name, PassWd);
                readFile();
                if (!isExists(student, true))
                {
                    hashSet.add(student);//û�ҵ�����ӽ�Set
                    System.out.println(hashSet);
                    writeFile();
                    mDialog("ע��ɹ�",Name+" ע��ɹ���");
                }
                else
                {
                    mDialog("ע��ʧ��",Name+" �Ѵ��ڣ�");
                }
            }
        });
    }

    public void readFile()
    {
        try
        {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file));
            hashSet = (HashSet) objectInputStream.readObject();
        } catch (IOException e)
        {

            if (hashSet == null)
            {
                hashSet = new HashSet<>();//�����һ������ʱ��ӽ���hashMap��null����Ҫ��ʵ����һ��
                writeFile();//Ȼ����д��ȥ
            }
        } catch (ClassNotFoundException e)
        {
            mDialog("����","�����ļ����ݶ�ȡʧ��!");
        }
    }

    public void writeFile()
    {
        try
        {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
            objectOutputStream.writeObject(hashSet);
        } catch (IOException e)
        {
            e.printStackTrace();
            mDialog("����","�����ļ�д��ʧ�ܣ�");
        }
    }


    public boolean isExists(Student student, boolean isRegister)
    {
        String userName = student.getUserName();
        String passWd = student.getPasswd();
        Iterator iterator = hashSet.iterator();
        while (iterator.hasNext())
        {
            Student stu = (Student) iterator.next();
            if (stu.getUserName().equals(userName))//����ҵ�����ͬ�û���
            {
                if (isRegister)//ע��Ļ�
                {
                    return true;//�Ѿ��ҵ���
                }
                return stu.getPasswd().equals(passWd);//��½�Ļ���Ҫ�Ƚ������Ƿ���ͬ
            }
        }
        return false;//û�ҵ����Ǽ�
    }

    public void mDialog(String title, String tips)
    {
        JDialog jDialog = new JDialog(jFrame, title, true);
        jDialog.setBounds((toolkit.getScreenSize().width - 200) / 2, (toolkit.getScreenSize().height - 200) / 2, 300, 100);
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BorderLayout());
        JLabel jLabel = new JLabel(tips);
        jLabel.setFont(font);

        jLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JButton jButton = new JButton("ȷ��");

        jPanel.add(jLabel, BorderLayout.CENTER);

        jPanel.add(jButton, BorderLayout.SOUTH);

        jDialog.add(jPanel);

        jDialog.validate();//ͬ�����ݣ��������ԭ��һ��

        jButton.addActionListener(new ActionListener()

        {

            @Override

            public void actionPerformed(ActionEvent e)

            {

                jDialog.setVisible(false);//���ȷ������Ϊ���ɼ�

            }

        });

        jDialog.setResizable(false);//���ɵ�����С

        jDialog.setVisible(true);


    }

    public static void main(String[] args)
    {
        new Info();
    }
}

class Student implements Serializable
{
    private String userName;
    private String passwd;

    public Student(String userName, String passwd)
    {
        this.userName = userName;
        this.passwd = passwd;
    }

    public String getUserName()
    {
        return userName;
    }

    public String getPasswd()
    {
        return passwd;
    }

    @Override
    public int hashCode()
    {
        return userName.hashCode() + passwd.hashCode() * 3;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (!(obj instanceof Student))
        {
            return false;
        }
        Student student = (Student) obj;
        return userName.equals(student.userName);
    }
}
