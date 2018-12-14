import javax.naming.Name;
import javax.print.DocFlavor;
import javax.sound.midi.spi.MidiFileReader;
import javax.swing.*;
import javax.swing.text.DefaultEditorKit;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;


public class Home
{
    private JFrame jFrame;
    private JPanel jPanel;
    private JLabel jLabelOfDestion, jLabelOfOrigin, title, map;
    private JComboBox jComboBoxOfDestin, jComboBoxOfOrigin;
    private Font titleFont = new Font("΢���ź�", 1, 28);
    private Font charFont = new Font("΢���ź�", 1, 20);
    private Toolkit toolkit = Toolkit.getDefaultToolkit();

    private File pointFile = new File("D://point.txt");
    private File lengthFile = new File("D://length.txt");
    private File mapFile = new File("D://map.png");
    private File infoFile = new File("D://info.txt");

    private HashSet<String> Name;
    private String[] point;
    private int[][] a;

    private boolean isAdmin = false;

    public Home(boolean isAdmin)
    {
        this.isAdmin = isAdmin;//ȷ���û����
        init();
    }

    public void init()
    {
        jFrame = new JFrame();
        jFrame.setLayout(new BorderLayout());

        titleInit();//��ʼ��������
        mapInit();//��ʼ����ͼ
        comboboxInit();//��ʼ���ײ�ѡ����

        point = new String[Name.size()];//����ȡ��������ת��Ϊ���飬
        point = Name.toArray(point);//�����СΪArrayList�ĳ���

        jFrame.setBounds((toolkit.getScreenSize().width - 829) / 2, (toolkit.getScreenSize().height - 660) / 2, 700, 450);
        jFrame.setVisible(true);
    }

    public void titleInit()
    {
        title = new JLabel("У԰����ϵͳ", SwingConstants.CENTER);
        title.setFont(titleFont);
        jFrame.add(title, BorderLayout.NORTH);//��������
    }

    public void mapInit()
    {
        ImageIcon imageIcon = new ImageIcon(mapFile.getPath());
        imageIcon.setImage(imageIcon.getImage().getScaledInstance(imageIcon.getIconWidth(),
                imageIcon.getIconHeight(), Image.SCALE_DEFAULT));
        map = new JLabel();
        map.setBounds(0, 0, 690, 400);
        map.setHorizontalAlignment(0);
        map.setIcon(imageIcon);
        jPanel = new JPanel();
        jPanel.setSize(690, 400);
        jPanel.add(map);
        jFrame.add(jPanel, BorderLayout.CENTER);//��ͼ��ʾ
    }


    public void comboboxInit()
    {
        jComboBoxOfDestin = new JComboBox();
        jComboBoxOfOrigin = new JComboBox();
        for (String string : Name)
        {
            jComboBoxOfDestin.addItem(string);
            jComboBoxOfOrigin.addItem(string);
        }
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new FlowLayout());

        jLabelOfOrigin = new JLabel("ʼ���أ�");
        jLabelOfOrigin.setFont(charFont);
        jPanel.add(jLabelOfOrigin);
        jPanel.add(jComboBoxOfOrigin);

        jLabelOfDestion = new JLabel("Ŀ�ĵأ�");
        jLabelOfDestion.setFont(charFont);
        jPanel.add(jLabelOfDestion);
        jPanel.add(jComboBoxOfDestin);

        JButton jbutton = new JButton("ȷ��");
        jbutton.setFont(charFont);
        jbutton.setSize(20, 30);
        jPanel.add(jbutton);
        if (isAdmin)
        {
            JButton admin = new JButton("����Ա�˵�");
            admin.setFont(charFont);
            jPanel.add(admin);
            admin.addMouseListener(new MouseAdapter()
            {
                @Override
                public void mouseClicked(MouseEvent e)
                {

                }
            });
        }

        jFrame.add(jPanel, BorderLayout.SOUTH);

    }


    public void adminTips()
    {
        String errorTitle = "���ݴ���";
        try
        {
            checkFile(mapFile, "��ͼ");
        } catch (IOException e)
        {
            e.printStackTrace();
            new mDialog(errorTitle, "�����Ա��¼���ͼ���ݣ�", jFrame);
            //writeMap
        }
        try
        {
            checkFile(pointFile, "����");

        } catch (IOException e)
        {
            e.printStackTrace();
            new mDialog(errorTitle, "�����Ա��¼�뾰�����ݣ�", jFrame);
            //writePoint
        }
        try
        {
            checkFile(lengthFile, "����");

        } catch (IOException e)
        {
            e.printStackTrace();
            new mDialog(errorTitle, "�����Ա��¼��������ݣ�", jFrame);
            //writeLength
        }
    }

    public void checkFile(File file, String string) throws IOException
    {
        if (!file.exists() || file.length() == 0)
        {
            throw new IOException(string + "�ļ���ʧ�ܣ�");
        }
    }


    public static void main(String[] args)
    {
        Home home = new Home(true);
    }
}

class adminMenu
{
    private JFrame jFrame;
    private JButton createPoint, editPoint, deletePoint, createLength, editLength;
    private JButton okayButton, cancelButton;
    private Toolkit toolkit = Toolkit.getDefaultToolkit();
    private Font font = new Font("΢���ź�", 1, 20);
    private File pointFile = new File("D://point.txt");
    private File lengthFile = new File("D://length.txt");
    private JFrame childFrame;
    private JPanel childPanel;

    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;


    public adminMenu()
    {
        jFrame = new JFrame();
        jFrame.setBounds((toolkit.getScreenSize().width - 829) / 2, (toolkit.getScreenSize().height - 660) / 2, 350, 450);
        jFrame.setLayout(new FlowLayout());

        childPanel = new JPanel();
        childPanel.setLayout(new FlowLayout());
        cancelButton = new JButton("ȡ��");
        okayButton = new JButton("ȷ��");
        childPanel.add(cancelButton);
        childPanel.add(okayButton);

        createPoint = new JButton("1.����������Ϣ");
        createPoint.setFont(font);
        createPoint.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                new CreatePoint();
            }
        });

        editPoint = new JButton("2.�޸ľ�����Ϣ");
        editPoint.setFont(font);
        editPoint.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                new EditPoint();
            }
        });

        deletePoint = new JButton("3.ɾ��������Ϣ");
        deletePoint.setFont(font);
        deletePoint.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                new DeletePoint();
            }
        });

        createLength = new JButton("4.������·��Ϣ");
        createLength.setFont(font);
        createLength.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                super.mouseClicked(e);
            }
        });

        editLength = new JButton("5.�޸ĵ�·��Ϣ");
        editLength.setFont(font);
        editLength.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                super.mouseClicked(e);
            }
        });


    }


}
