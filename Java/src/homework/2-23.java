package homework;

class Answer0//2-23
{
    public static int SUM = 0;

    public static void main(String[] args)
    {
        int i, j;
        int sum = 0;
        for (i = 4; i <= 400; i++)
        {
            if (!su(i))
            {
                calSum(i);
                if (cal(i) == SUM)
                {
                    System.out.println(i);
                }
                SUM = 0;
            }
        }
    }

    public static void calSum(int num)//����ÿ���������Ӳ�����λ�ۼ�
    {
        int i = 2;
        while (i <= num)
        {
            if (num % i == 0)
            {
                SUM += cal(i);
                num = num / i;
                i = 2;
            }
            else
                i++;
        }
    }

    public static int cal(int num)//�������ָ�λ֮��
    {
        int sum = 0;
        int i = (int) Math.log10(num);
        while (num != 0)
        {
            sum += num / Math.pow(10, i);
            num = num % (int) Math.pow(10, i);
            i = (int) Math.log10(num);
        }
        return sum;
    }

    public static boolean su(int num)//�ж��Ƿ�Ϊ����
    {
        int i = 2;
        for (; i <= Math.sqrt(num); i++)
        {
            if (num % i == 0)
            {
                return false;
            }
        }
        return true;
    }
}
