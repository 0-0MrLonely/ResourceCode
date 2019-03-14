package homework;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class IO
{
    private JFrame jFrame;
    private JPanel jPanel;
    private JLabel jLabel;
    private JButton chooseFile;
    private JFileChooser jFileChooser;
    private File file;
    private Toolkit toolkit;
    private StringBuilder codeOfText;
    private String codeOfUser;
    private JTextField jTextField;
    private JButton okButton;

    public IO()
    {
        toolkit = Toolkit.getDefaultToolkit();

        init();
    }


    public void init()
    {
        jFrame = new JFrame("���������룺");
        jFrame.setLayout(new BorderLayout());
        jFrame.setBounds((toolkit.getScreenSize().width - 400) / 2, (toolkit.getScreenSize().height - 400) / 2, 300, 200);
        jFrame.addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                System.exit(0);
            }
        });
        jFrame.setResizable(false);

        jLabel = new JLabel("����������");
        jLabel.setFont(new Font("΢���ź�", 1, 20));

        chooseFile = new JButton("�������ļ� ");
        chooseFile.setSize(50, 25);
        chooseFile.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {

                chooseFile();
            }
        });

        jTextField = new JTextField(10);
        jTextField.setFont(new Font("΢���ź�", 1, 20));


        okButton = new JButton("ȷ��");


        okButton.addMouseListener(new MouseAdapter()
        {

            @Override
            public void mouseClicked(MouseEvent e)
            {
                try
                {
                    codeOfUser = jTextField.getText();
                    if (codeOfText.toString().equals(codeOfUser))
                    {
                        mDialog(1);
                    }
                    else
                        mDialog(0);
                } catch (NullPointerException e1)
                {
                    mDialog(-1);
                }

            }
        });

        jPanel = new JPanel();
        jPanel.add(jLabel);
        jPanel.add(chooseFile);
        jPanel.add(jTextField);
        jPanel.add(okButton);
        jFrame.add(jPanel);
        jFrame.setVisible(true);
    }

    public void chooseFile()
    {
        jFileChooser = new JFileChooser(".");
        jFileChooser.showOpenDialog(jFrame);
        jFileChooser.setVisible(true);
        jFileChooser.setBounds((toolkit.getScreenSize().width - 400) / 2, (toolkit.getScreenSize().height - 400) / 2, 300, 200);
        jFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        file = jFileChooser.getSelectedFile();
        BufferedInputStream bufferedInputStream = null;
        try
        {
            bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
            Byte str[] = new Byte[bufferedInputStream.available()];
            int b;
            codeOfText = new StringBuilder();
            while ((b = bufferedInputStream.read()) != -1)
            {
                codeOfText.append((char) b);
            }
        } catch (IOException e)
        {
            System.out.println("���ļ�ʧ�ܣ�");
        } finally
        {
            try
            {
                if (bufferedInputStream != null)
                {
                    bufferedInputStream.close();
                }
            } catch (IOException e)
            {
                System.out.println("�ر���ʧ�ܣ�");
            }
        }
    }

    public void mDialog(int isRight)//Dialog��ʾ����

    {

        JDialog jDialog = new JDialog(jFrame, "��������", true);

        jDialog.setBounds((toolkit.getScreenSize().width - 200) / 2, (toolkit.getScreenSize().height - 200) / 2, 300, 100);

        //dialog��ʾ��Ϣ

        JPanel jPanel = new JPanel();

        jPanel.setLayout(new BorderLayout());//����jPanel�������洢����������������ΪBorderLayout����

        JLabel jLabel = new JLabel();
        if (isRight == 1)
            jLabel.setText("������ȷ��");
        else if (isRight == 0)
            jLabel.setText("�������");
        else
            jLabel.setText("���ȴ��ļ���");

        jLabel.setFont(new Font("΢���ź�", 1, 20));

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
        new IO();
    }

}
