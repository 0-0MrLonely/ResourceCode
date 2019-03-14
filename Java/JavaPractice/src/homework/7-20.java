package homework;

import javax.swing.*;
import javax.swing.Timer;
import javax.swing.text.MutableAttributeSet;
import javax.tools.Tool;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.Array;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.*;

import static java.lang.Math.*;

class Answer
{
    private JFrame jFrame;
    private Timer timer;
    private Date date1,date2;
    private SimpleDateFormat simpleDateFormat;
    private JLabel jLabel;
    private Toolkit toolkit = Toolkit.getDefaultToolkit();

    public Answer()
    {
        init();
    }

    public void init()
    {
        jFrame = new JFrame("����ʱ��");
        jFrame.setLayout(new FlowLayout());
        jFrame.setBounds((toolkit.getScreenSize().width - 400) / 2, (toolkit.getScreenSize().height - 400) / 2, 462, 80);
        date2 = new Date();
        jLabel = new JLabel(new SimpleDateFormat("yyyy" + "��" + "MM" + "��" + "dd" +
                "��" + " " + "h" + "ʱ" + "mm" + "��" + "s" + "��" + " " + "a" + " " + "E").format(date2));
        jLabel.setFont(new Font("΢���ź�", 1, 20));
        jFrame.add(jLabel);
        int delay = 1000;
        ActionListener actionListener = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                date1 = new Date();
                simpleDateFormat = new SimpleDateFormat("yyyy" + "��" + "MM" + "��" + "dd" +
                        "��" + " " + "h" + "ʱ" + "mm" + "��" + "s" + "��" + " " + "a" + " " + "E");
                String time = simpleDateFormat.format(date1);
                jLabel.setText(time);
                jLabel.setFont(new Font("΢���ź�", 1, 20));
            }
        };
        timer = new Timer(delay, actionListener);
        timer.start();
        jFrame.setVisible(true);
        jFrame.setResizable(false);
    }

    public static void main(String[] args)
    {
        new Answer();
    }

}