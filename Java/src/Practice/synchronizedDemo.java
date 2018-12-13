package Practice;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static java.lang.Thread.MAX_PRIORITY;

class Res       //������Դ
{
    int age = 0;
    String name;
    boolean isEmpty = true;//��Դ�Ƿ�Ϊ��
    Lock lock = new ReentrantLock();
    private Condition conditionOfConusmer=lock.newCondition();
    private Condition conditionOfProducer=lock.newCondition();
    public void In(String name, int age)//��������
    {
        lock.lock();
        try
        {
            while (!isEmpty)//�����Դ�ǿ�
            {
                conditionOfProducer.await();//�ȴ�
            }
            this.name = name;
            this.age = age;
            isEmpty = false;//������ϣ���Դ�ǿ�
            conditionOfConusmer.signal();
        } catch (Exception e)
        {
            e.printStackTrace();
        }finally
        {
            lock.unlock();
        }
    }

    public synchronized void Out()//���ѷ���
    {
        lock.lock();
        try
        {
            while (isEmpty)//��ԴΪ��
            {
                conditionOfConusmer.await();//�ȴ�
            }
            System.out.println("������" + name + "  ���䣺" + age);
            isEmpty = true;
            conditionOfProducer.signal();
        } catch (Exception e)
        {
            e.printStackTrace();
        }finally
        {
            lock.unlock();
        }
    }
}


class Producer implements Runnable
{
    private Res res;
    private int i = 0;

    public Producer(Res res)
    {
        this.res = res;
    }

    @Override
    public void run()
    {
        while (true)
        {
            if (i % 2 == 0)
                res.In("С��", 10);
            else
                res.In("����", 70);
            i++;
        }

    }
}

class Consumer implements Runnable
{
    private Res res;

    public Consumer(Res res)
    {
        this.res = res;

    }

    @Override
    public void run()
    {
        while (true)
        {
            res.Out();
        }


    }
}

class synchronizedDemo
{
    public static void main(String[] args)
    {
        Res res = new Res();//�ֱ𴴽����������������������ߣ�����ͻ������
        Thread thread1 = new Thread(new Consumer(res));
        thread1.setPriority(MAX_PRIORITY);
        Thread thread2 = new Thread(new Producer(res));
        Thread thread3 = new Thread(new Consumer(res));
        Thread thread4 = new Thread(new Producer(res));
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();



    }
}


