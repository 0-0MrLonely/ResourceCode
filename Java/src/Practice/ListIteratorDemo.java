package Practice;

import java.util.ArrayList;
import java.util.ListIterator;

public class ListIteratorDemo
{
    public static void main(String[] args)
    {
        ArrayList arrayList = new ArrayList();
        arrayList.add("1");
        arrayList.add("2");
        arrayList.add("3");
        arrayList.add("4");//���Ԫ��

        ListIterator listIterator = arrayList.listIterator();
        while (listIterator.hasNext())
        {
            listIterator.next();//�������������ָ���ƶ����б�β
        }
        System.out.println(arrayList);

        while (listIterator.hasPrevious())//���������
        {
            Object object = listIterator.previous();
            if (object.equals("1"))//��Ԫ�ص��ڡ�1��
            {
                listIterator.set("9");//�滻Ϊ��9��
            }
        }
        System.out.println(arrayList);
    }
}
