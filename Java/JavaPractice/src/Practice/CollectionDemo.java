package Practice;

import java.util.ArrayList;
import java.util.Iterator;

public class CollectionDemo
{
    public static void main(String[] args)
    {
        ArrayList arrayList = new ArrayList();
        arrayList.add("123");
        arrayList.add("456");
        arrayList.add("789 ");//���������Ԫ��
        Iterator iterator = arrayList.iterator();//��ȡ������
        while (iterator.hasNext())//�����Ե����Ļ�
        {
            System.out.println(iterator.next());//��ӡ������Ԫ��
        }
    }
    /*
    123
    456
    789
     */
}
