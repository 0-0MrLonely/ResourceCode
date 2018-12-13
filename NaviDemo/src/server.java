import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Iterator;

public class server
{
    private Socket socket;
    private ServerSocket serverSocket;
    private String ipInfo;
    private BufferedReader bufferedReader;
    private BufferedOutputStream bufferedOutputStream;
    private FileOutputStream fileOutputStream;
    private String adminKey;
    private HashSet<User> hashSet;
    private String account, passwd, inviteCode;
    private File infofile = new File("D://info.key");
    private boolean isAdmin = false;

    public server()
    {
        try
        {
            serverSocket = new ServerSocket(10001);
            socket = serverSocket.accept();
            ipInfo = socket.getInetAddress().getHostAddress().toString();
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println(ipInfo);
            if (bufferedReader.readLine().equals("0"))
            {
                login();
            }else
                regist();
        } catch (IOException e)
        {

        }

    }

    public void login()
    {
        String result;
        String status;
        PrintWriter printWriter = null;
        if (isAdmin)//ȷ���ҵ����û������
        {
            status = "����Ա";
        }
        else

            status = "һ���û�";
        try
        {
            readFile(infofile);//�ȶ��ļ�
            System.out.println(bufferedReader);
            account = bufferedReader.readLine();//�ͻ��˴��������ʺ�����
            System.out.println(account);

            passwd = bufferedReader.readLine();
            System.out.println(passwd);
            User user = new User(account, passwd);//��װ����
            if (isExists(user, false))//�ҵ���
            {
                result = "��¼�ɹ�����ݣ�" + status;//���������Ϣ
            }
            else
            {
                result = "��¼ʧ�ܣ�������ʺ����룡";
            }
            printWriter = new PrintWriter(socket.getOutputStream(), true);
            printWriter.println(result);//���ؿͻ���
        } catch (IOException e)
        {
            e.printStackTrace();
        } finally
        {
            printWriter.close();
        }
    }

    public void regist()
    {
        PrintWriter printWriter = null;
        String status = null;
        try
        {
            printWriter = new PrintWriter(socket.getOutputStream());
            account = bufferedReader.readLine();//�ͻ��˴��������ʺ�����
            passwd = bufferedReader.readLine();
            inviteCode = bufferedReader.readLine();

            User user = new User(account, passwd);
            readFile(infofile);
            if (!isExists(user, true))
            {
                user.setAdmin(inviteCode);
                if (user.isAdmin())
                {
                    status = "����Ա";
                }
                else
                    status = "һ���û�";

                hashSet.add(user);//û�ҵ�����ӽ�Set
                writeFile(infofile);
                printWriter.println("ע��ɹ�����ݣ�" + status);
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        } finally
        {
            printWriter.close();
        }

    }

    public void readFile(File file)
    {
        ObjectInputStream objectInputStream = null;
        PrintWriter printWriter = null;
        try
        {
            printWriter = new PrintWriter(socket.getOutputStream());
            objectInputStream = new ObjectInputStream(new FileInputStream(file));//��ȡ�����ļ�
            hashSet = (HashSet) objectInputStream.readObject();//��Ϣ����hashSet����ʽ������ļ���
        } catch (IOException e)
        {
            if (hashSet == null)
            {
                hashSet = new HashSet<>();//�����һ������ʱ��ӽ���hashMap��null����Ҫ��ʵ����һ��
                writeFile(infofile);//Ȼ����д��ȥ
            }
        } catch (ClassNotFoundException e)
        {
            printWriter.println("�����ļ��쳣�������ļ���");
        } finally
        {
            try
            {
                objectInputStream.close();
            } catch (IOException e)
            {
                printWriter.println("�ر���ʧ�ܣ��������ˣ�");
            }
        }
    }

    public void writeFile(File file)
    {
        PrintWriter printWriter = null;
        ObjectOutputStream objectOutputStream = null;
        try
        {
            objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));//����д����
            objectOutputStream.writeObject(hashSet);//��hashSetд���ļ�
            printWriter = new PrintWriter(socket.getOutputStream());
        } catch (IOException e)
        {
            printWriter.println("�����ļ��쳣�������ļ���");
        } finally
        {
            printWriter.close();
            try
            {
                objectOutputStream.close();
            } catch (IOException e)
            {
                printWriter.println("�ر���ʧ�ܣ��������ˣ�");
            }
        }
    }

    public boolean isExists(User user, boolean isRegister)
    {
        String account = user.getAccount();
        String passwd = user.getPasswd();
        Iterator iterator = hashSet.iterator();
        while (iterator.hasNext())
        {
            User stu = (User) iterator.next();
            isAdmin = stu.isAdmin();
            if (stu.getAccount().equals(account))//����ҵ�����ͬ�û���
            {
                if (isRegister)//ע��Ļ�
                {
                    return true;//�Ѿ��ҵ���
                }
                return stu.getPasswd().equals(passwd);//��½�Ļ���Ҫ�Ƚ������Ƿ���ͬ
            }
        }
        return false;//û�ҵ����Ǽ�
    }


    public void setAdminKey(String string)
    {
        adminKey = string;
    }

    public String getAdminKey()
    {
        return adminKey;
    }

    public static void main(String[] args)
    {
        new server();
    }
}

class User implements Serializable
{
    private String account;
    private String passwd;
    private boolean isAdmin = false;

    public User(String account, String passwd)
    {
        this.account = account;
        this.passwd = passwd;
    }

    public String getAccount()
    {
        return account;
    }

    public void setAccount(String account)
    {
        this.account = account;
    }

    public String getPasswd()
    {
        return passwd;
    }

    public void setPasswd(String passwd)
    {
        this.passwd = passwd;
    }

    public boolean isAdmin()
    {
        return isAdmin;
    }

    public void setAdmin(String string)
    {
        if (string.equals(new server().getAdminKey()))
        {
            isAdmin = true;
        }
    }

    @Override
    public int hashCode()
    {
        return account.hashCode() + passwd.hashCode() * 3;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (!(obj instanceof User))
        {
            return false;
        }
        User user = (User) obj;
        return account.equals(user.account);
    }
}
