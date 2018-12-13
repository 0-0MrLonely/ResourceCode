import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.net.Socket;

public class cilent
{
    private JFrame jFrame;
    private JLabel accountLabel, passwdLabel, inviteLabel;
    private JTextField accountText, passwdText, inviteText;
    private Toolkit toolkit = Toolkit.getDefaultToolkit();
    private JPanel accountJPanel, passwdJPanel, buttonjPanel, invitejPanel;
    private JButton loginButton, registButton;
    private Font font = new Font("΢���ź�", 1, 18);

    private Socket socket;
    private BufferedWriter bufferedWriter;
    private BufferedReader bufferedReader;

    private String account, passwd;
    private String tips;

    public cilent()
    {
        clientInit();
        init();
    }

    public void init()
    {
        jFrame = new JFrame("�û���¼");
        jFrame.setLayout(new FlowLayout());
        jFrame.setBounds((toolkit.getScreenSize().width - 400) / 2, (toolkit.getScreenSize().height - 400) / 2, 270, 200);

        componentInit(accountJPanel = new JPanel(), accountLabel = new JLabel(), accountText = new JTextField(), "   �ʺ�");
        componentInit(passwdJPanel = new JPanel(), passwdLabel = new JLabel(), passwdText = new JTextField(), "   ����");
        componentInit(invitejPanel = new JPanel(), inviteLabel = new JLabel(), inviteText = new JTextField(), "������");

        loginButtonInit();
        registButtonInit();

        jFrame.setVisible(true);
        jFrame.setResizable(false);

    }

    public void componentInit(JPanel jPanel, JLabel jLabel, JTextField jTextField, String str)
    {
        jPanel.setLayout(new FlowLayout());

        jLabel.setText(str);
        jLabel.setFont(font);

        jTextField.setText("");
        jTextField.setColumns(14);

        jPanel.add(jLabel);
        jPanel.add(jTextField);

        jFrame.add(jPanel);
    }

    public void loginButtonInit()
    {
        loginButton = new JButton("��¼");

        loginButton.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                sendInfo(0);
            }
        });
        jFrame.add(loginButton);
    }

    public void sendInfo(int code)
    {
        account = accountText.getText();
        passwd = passwdText.getText();
        String string;
        if (code == 0)
        {
            string = "��¼";
        }
        else
            string = "ע��";
        try
        {
            bufferedWriter.write(code + "\r\n");
            bufferedWriter.flush();//�����ʾ�����߷�����ǵ�¼����ע�ᣬ��¼Ϊ0��ע��Ϊ1

            bufferedWriter.write(account + "\r\n");//����Ҫ�н�����ʾ���������˲���ֹͣ��ȡ
            bufferedWriter.flush();                    //ˢ����
            bufferedWriter.write(passwd + "\r\n");
            bufferedWriter.flush();
            if (code == 1)          //ע��Ļ���һ�������룬��Ҫ�ഫ��һ��
            {
                bufferedWriter.write(inviteText.getText() + "\r\n");
                bufferedWriter.flush();
            }
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            tips = bufferedReader.readLine();
            System.out.println(tips);
        } catch (IOException e1)
        {
            mDialog(string + "���", "��������ʧ�ܣ�");

        } catch (NullPointerException e1)
        {
            mDialog(string + "���", "����˹رգ����ȴ򿪷���ˣ�");
        } finally
        {
            try
            {
                bufferedReader.close();
                bufferedWriter.close();
            } catch (IOException e1)
            {
                tips = "���ر�ʧ�ܣ�";
                mDialog(string + "���", tips);
            }
            mDialog(string + "���", tips);
        }
    }

    public void registButtonInit()
    {
        registButton = new JButton("ע��");
        registButton.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                sendInfo(1);
            }
        });
        jFrame.add(registButton);
    }

    public void clientInit() throws NullPointerException
    {
        try
        {
            socket = new Socket("localhost", 10001);
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            if (bufferedWriter == null)
            {
                throw new NullPointerException();
            }
        } catch (IOException e)
        {

        }


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
        new cilent();
    }

}
