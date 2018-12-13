package homework;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.security.Key;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;
import java.util.prefs.Preferences;

class ComplexCal

{

    private Toolkit toolkit = Toolkit.getDefaultToolkit();

    private JLabel labelForTitle;

    private JDialog dialogOfWrongInput;

    private JLabel wrongInfo;

    private JFrame f;

    private int count = 2;

    private Button createItem, deleteItem, equals;

    private JComplexList jComplexList;

    private JLabel result;


    public ComplexCal()

    {

        init();

    }


    private void init()

    {

        FrameInit();//�����ڳ�ʼ��

        labelInit();//��һ����ʾ��Ϣ��ʼ��

        JComplexInit();//����������ʼ��

        ResultInit();//���������ʼ��

        f.validate();

        /* ��������Ҫ��validate()����ˢ�����ݣ�

         �����ֻ���ֶ���һ�´��ڲ���������ʾ����*/

    }


    private void JComplexInit()

    {

        jComplexList = new JComplexList();


    }


    public void FrameInit()

    {

        f = new JFrame("�������ʽ����");

        f.setLayout(new FlowLayout());

        f.setBounds((toolkit.getScreenSize().width - 400) / 2, (toolkit.getScreenSize().height - 400) / 2, 462, 389);

        //��Ļ���ȼ�ȥFrame�ĳ����/2������Ļ����

        f.setVisible(true);

        f.addWindowListener(new WindowAdapter()

        {

            @Override

            public void windowClosing(WindowEvent e)

            {

                System.exit(0);

            }

        });

        //�������Ͻǹرհ�ť�ĵ���¼�

        f.setResizable(false);

        //���ɸı��С


    }


    public void ResultInit()

    {


        equals.addMouseListener(new MouseAdapter()

        {

            @Override

            public void mouseClicked(MouseEvent e)

            {

                String operator = "+";

                double sumOfComplex = 0;

                double sumOfReal = 0;

                Boolean flag = true;

                for (Object aJComplexList : jComplexList)//����������������ArrayList��Ԫ��

                {

                    JComplex jComplex = (JComplex) (aJComplexList);


                    try

                    {

                        double m = Integer.parseInt(jComplex.getTextOfComplex());

                        double n = Integer.parseInt(jComplex.getTextOfReal());

                        if ("+".equals(operator))

                        {

                            sumOfComplex += m;

                            sumOfReal += n;

                        }

                        else if ("-".equals(operator))

                        {

                            sumOfComplex -= m;

                            sumOfReal -= n;

                        }


                    } catch (NumberFormatException e1)

                    {

                        wrongDialog();

                        flag = false;

                        break;

                    }

                    operator = jComplex.getOperator();

                }

                if (flag)//���쳣���־Ͳ��������㣬Ҳ���������Dialog

                {

                    result = new JLabel();

                    result.setText(sumOfComplex + "" + "+" + sumOfReal + "" + "i");

                    result.setFont(new Font("΢���ź�", 1, 20));

                    resultDialog();

                }


            }

        });

    }


    public void labelInit()

    {

        JPanel jPanel = new JPanel();

        jPanel.setLayout(new FlowLayout());

        labelForTitle = new JLabel("�����������Ϣ���õ���Ӧ�����", SwingConstants.CENTER);

        labelForTitle.setFont(new Font("΢���ź�", 1, 20));

        f.add(labelForTitle);


        createItem = new Button("�½���");

        jPanel.add(createItem);

        deleteItem = new Button("ɾ����");

        jPanel.add(deleteItem);

        f.add(jPanel);

        createItem.addMouseListener(new MouseAdapter()

        {

            @Override

            public void mouseClicked(MouseEvent e)

            {

                jComplexList.addItem();

            }

        });

        deleteItem.addMouseListener(new MouseAdapter()

        {

            @Override

            public void mouseClicked(MouseEvent e)

            {

                jComplexList.deleteItem();

            }

        });

        equals = new Button("�鿴���");

        jPanel.add(equals);

        f.add(jPanel);

    }


    public void wrongDialog()//Dialog��ʾ����

    {

        dialogOfWrongInput = new JDialog(f, "��������", true);

        dialogOfWrongInput.setBounds((toolkit.getScreenSize().width - 200) / 2, (toolkit.getScreenSize().height - 200) / 2, 300, 100);

        //dialog��ʾ��Ϣ

        JPanel jPanel = new JPanel();

        jPanel.setLayout(new BorderLayout());//����jPanel�������洢����������������ΪBorderLayout����

        JLabel jLabel = new JLabel("�������Ϣ�������飡");

        jLabel.setFont(new Font("΢���ź�", 1, 20));

        jLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JButton jButton = new JButton("ȷ��");

        jPanel.add(jLabel, BorderLayout.CENTER);

        jPanel.add(jButton, BorderLayout.SOUTH);

        dialogOfWrongInput.add(jPanel);

        dialogOfWrongInput.validate();//ͬ�����ݣ��������ԭ��һ��

        jButton.addActionListener(new ActionListener()

        {

            @Override

            public void actionPerformed(ActionEvent e)

            {

                dialogOfWrongInput.setVisible(false);//���ȷ������Ϊ���ɼ�

            }

        });

        dialogOfWrongInput.setResizable(false);//���ɵ�����С

        dialogOfWrongInput.setVisible(true);


    }


    public void resultDialog()

    {

        JDialog jDialog = new JDialog(f, "���", true);

        jDialog.setBounds((toolkit.getScreenSize().width - 200) / 2, (toolkit.getScreenSize().height - 200) / 2, 300, 100);


        JPanel jPanel = new JPanel();

        JButton jButton = new JButton("ȷ��");

        jPanel.setLayout(new BorderLayout());

        jPanel.add(result);

        result.setHorizontalAlignment(SwingConstants.CENTER);

        jPanel.add(jButton, BorderLayout.SOUTH);

        jDialog.add(jPanel);

        jDialog.setResizable(false);

        jButton.addActionListener(new ActionListener()

        {

            @Override

            public void actionPerformed(ActionEvent e)

            {

                jDialog.setVisible(false);

            }

        });

        jDialog.validate();

        jDialog.setVisible(true);


    }


    public void deleteDialog()

    {

        dialogOfWrongInput = new JDialog(f, "ɾ������", true);

        dialogOfWrongInput.setBounds((toolkit.getScreenSize().width - 200) / 2, (toolkit.getScreenSize().height - 200) / 2, 300, 100);

        //dialog��ʾ��Ϣ

        JPanel jPanel = new JPanel();

        jPanel.setLayout(new BorderLayout());//����jPanel�������洢����������������ΪBorderLayout����

        JLabel jLabel = new JLabel("����Ҫ��������������");

        jLabel.setFont(new Font("΢���ź�", 1, 20));

        jLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JButton jButton = new JButton("ȷ��");

        jPanel.add(jLabel, BorderLayout.CENTER);

        jPanel.add(jButton, BorderLayout.SOUTH);

        dialogOfWrongInput.add(jPanel);

        dialogOfWrongInput.validate();//ͬ�����ݣ��������ԭ��һ��

        jButton.addActionListener(new ActionListener()

        {

            @Override

            public void actionPerformed(ActionEvent e)

            {

                dialogOfWrongInput.setVisible(false);//���ȷ������Ϊ���ɼ�

            }

        });

        dialogOfWrongInput.setResizable(false);//���ɵ�����С

        dialogOfWrongInput.setVisible(true);

    }


    class JComplexList extends ArrayList//List��̬���飬�ڲ������ʽ���Է��㹲������

    {

        public int sum = 2;


        public JComplexList()//��ʼ��һ��������������ʽ

        {

            JComplex J1 = new JComplex(f);

            J1.setJComboBoxVisible(true);

            JComplex J2 = new JComplex(f);

            J2.setJComboBoxVisible(false);

            super.add(J1);

            super.add(J2);

        }


        public void addItem()

        {

            sum = super.size();

            JComplex temp = (JComplex) (super.get(sum - 1));

            temp.setJComboBoxVisible(true);

            JComplex jComplex = new JComplex(f);

            super.add(jComplex);

            jComplex.setJComboBoxVisible(false);//���һ��ʽ�Ӳ���ʾ�����

        }


        public void deleteItem()

        {

            if (jComplexList.size() >= 3)

            {

                sum = super.size();

                JComplex temp = (JComplex) (super.get(sum - 1));

                temp.setVisible(false);//�ֽ����һ��Ԫ������

                jComplexList.remove(temp);//ɾ��

                sum = super.size();//���»�ȡ��С

                temp = (JComplex) (super.get(sum - 1));

                temp.setJComboBoxVisible(false);//�������һ��Ԫ�ص���������ɼ�

            }

            else

            {

                deleteDialog();//���������

            }

        }

    }


    public static void main(String[] args)

    {

        new ComplexCal();

    }

}

class JComplex extends JComponent //����������̳�JComponent

{

    private TextField textFieldOfReal;//ʵ�������

    private TextField textFieldOfComplex;//�鲿�������

    private JComboBox<String> jComboBox;

    private String textOfReal;//ʵ������

    private String textOfComplex;//�鲿����

    private String operate = "+";//Ĭ��Ϊ"+"

    private JPanel jPanel;

    private JLabel jLabelPlus, jLabeli;//�Ӻź�"i"�ַ���JLabel

    private String[] operator = new String[]{"+", "-"};


    public TextField getTextFieldOfReal()

    {

        return textFieldOfReal;

    }


    public TextField getTextFieldOfComplex()

    {

        return textFieldOfComplex;

    }


    public JComplex(Frame f)

    {

        createModule();

        addModule(f);

    }


    public void createModule()//�������

    {


        jComboBox = new JComboBox<String>(operator);

        jComboBox.setSelectedIndex(0);

        jComboBox.addItemListener(e ->
        {

            if (e.getStateChange() == ItemEvent.SELECTED)

            {

                JComplex.this.operate = jComboBox.getSelectedItem().toString();

            }

        });


        textFieldOfComplex = new TextField(5);

        textFieldOfComplex.setFont(new Font("΢���ź�", 1, 20));

        inputLimit(textFieldOfComplex);


        textFieldOfReal = new TextField(5);

        textFieldOfReal.setFont(new Font("΢���ź�", 1, 20));

        inputLimit(textFieldOfReal);


        jLabelPlus = new JLabel("+");

        jLabelPlus.setFont(new Font("΢���ź�", 1, 20));


        jLabeli = new JLabel("i   ");

        jLabeli.setFont(new Font("΢���ź�", 1, 20));


        //

    }


    private void inputLimit(TextField textFieldOfReal)

    {

        textFieldOfReal.addKeyListener(new KeyAdapter()//���̼���

        {

            @Override

            public void keyPressed(KeyEvent e)

            {

                int code = e.getKeyCode();

                String s = textFieldOfReal.getText();

                if (s.length() >= 6)//��������

                {

                    e.consume();

                }

                if (!(code >= KeyEvent.VK_0 && code <= KeyEvent.VK_9) && !(code == KeyEvent.VK_PERIOD) && !(code == KeyEvent.VK_DELETE) && !(code == KeyEvent.VK_BACK_SPACE))

                    e.consume();//ֻ���������ֻ���С����

            }

        });

    }


    public void addModule(Frame f)//������

    {

        jPanel = new JPanel(new GridLayout(1, 0, 5, 5));


        jPanel.add(textFieldOfComplex);

        jPanel.add(jLabelPlus);

        jPanel.add(textFieldOfReal);

        jPanel.add(jLabeli);

        jPanel.add(jComboBox);

        f.add(jPanel);

    }


    public void setJComboBoxVisible(boolean isLast)//����������ɼ���

    {

        jComboBox.setVisible(isLast);

    }


    public String getTextOfReal()

    {

        return textFieldOfReal.getText();

    }//��ȡʵ���ַ���


    public String getTextOfComplex()

    {

        return textFieldOfComplex.getText();

    }//��ȡ�鲿�ַ���


    public String getOperator()

    {

        return operate;

    }//��ȡJCombobox�������


    @Override

    public void setVisible(boolean aFlag)

    {

        jPanel.setVisible(aFlag);

    }

}
