package homework;


import javax.xml.stream.events.EntityReference;
import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Resources
{
    private double balance;
    Lock lock = new ReentrantLock();
    Condition conditionOfCon = lock.newCondition();//�����ߵ�condition
    Condition conditionOfPro = lock.newCondition();//�����ߵ�condition
    private boolean isEmpty = true;

    public Resources(double balance)
    {
        this.balance = balance;
    }

    public void In()//��������
    {
        lock.lock();
        try
        {
            balance += 100.0;
            System.out.println(Thread.currentThread().getName() + " " + "In:��ǰ�˻���" + balance);
            conditionOfCon.signal();
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            lock.unlock();
        }
    }

    public void Out()//���ѷ���
    {
        lock.lock();
        try
        {
            while (balance - 50 < 0)//��ԴΪ��
                conditionOfCon.await();//�����ߵȴ�
            balance -= 50.0;
            System.out.println(Thread.currentThread().getName() + " " + "Out:��ǰ�˻���" + balance);

            conditionOfPro.signal();
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            lock.unlock();
        }
    }
}

class Consumer implements Runnable
{
    private Resources resources;

    public Consumer(Resources resources)
    {
        this.resources = resources;
    }

    public void run()
    {
        resources.Out();
    }
}

class Producer implements Runnable
{
    private Resources resources;

    public Producer(Resources resources)
    {
        this.resources = resources;
    }

    public void run()
    {
        for (int i = 0; i < 3; i++)
            resources.In();
    }
}

class ProducerConsumer
{
    public static void main(String[] args)
    {
        System.out.println("�˻���ʼ�����٣�");
        Scanner scanner = new Scanner(System.in);
        double balance = scanner.nextDouble();
        Resources resources = new Resources(balance);
        Thread thread1 = new Thread(new Consumer(resources));
        Thread thread2 = new Thread(new Consumer(resources));
        Thread thread3 = new Thread(new Consumer(resources));
        Thread thread4 = new Thread(new Producer(resources));
        Thread thread5 = new Thread(new Producer(resources));
        Thread thread6 = new Thread(new Producer(resources));
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
        thread6.start();
    }
}
