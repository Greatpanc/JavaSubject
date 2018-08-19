/*�˿������ٳ��ƴ���
 * ������֪��һ���˿��ư���С��Ϊ13���ƣ�ÿһ���Ƹ��ĸ���ɫ���ܹ�52����
 * �涨13���ư��Ӵ�С��˳��ֱ�ΪA23456789TJQK�����ڴ�һ�����г�ȡ20�ţ�ÿ��ѡ�����й�����һ����ƣ�
 * 1�����ƣ��γ�һ����
 * 2�����ӣ����Ŵ�С��ͬ����
 * 3�����������Ŵ�С��ͬ���ƣ���������һ����
 * 4���Ĵ������Ŵ�С��ͬ���ƣ���������������
 * 5��˳�ӣ��������Ŵ�С��������
 * ��������Ҫ�����ֽ������20���Ƴ��ꣿ
 * ���ӣ�	���룺��8K67A65K27T59K346AK2�����ַ�����
 * 		�����4
 * Author�� Greatpan
 * */

import java.util.*;

public class MyMainfunc {
    public static void main(String[] args){
       @SuppressWarnings("resource")
	Scanner input=new Scanner(System.in);
       System.out.print("�����룺");
       String str=input.next();	//8K67A65K27T59K346AK2
       char[] c=str.toCharArray();
       int[]  pai=new int[13];	//��Ϊ��¼�������ж����ŵ�����
       for(int i=0;i<13;i++)	//��ʼʱ������0��
    	   pai[i]=0;
       for(int i=0;i<20;i++)	//���ڼ�������ж�����
    	   switch(c[i])
    	   {
    	   		case 'A':	pai[0]++;	break;
    	   		case '2':	pai[1]++;	break;
    	   		case '3':	pai[2]++;	break;
    	   		case '4':	pai[3]++;	break;
    	   		case '5':	pai[4]++;	break;
    	   		case '6':	pai[5]++;	break;
    	   		case '7':	pai[6]++;	break;
    	   		case '8':	pai[7]++;	break;
    	   		case '9':	pai[8]++;	break;
    	   		case 'T':	pai[9]++;	break;
    	   		case 'J':	pai[10]++;	break;
    	   		case 'Q':	pai[11]++;	break;
    	   		case 'K':	pai[12]++;	break;
    	   		default :	
    	   			System.out.println("�����д��󣬳������");
    	   			System.exit(0); break;	//�ַ����д����ַ�ʱ������
    	   }

//       for(int i=0;i<13;i++)
//    	   for(int j=0;j<pai[i];j++)
//    		   System.out.print((i+1)+" ");
//       System.out.println();
//       for(int i=0;i<13;i++)
//    	   System.out.print(pai[i]+" ");
//       System.out.println();
       System.out.println("һ�����ٴΣ�"+paicount(pai));
    }
    
    //�ú����������ٵĳ���˳���˼�����ȳ���С���ƣ�ͨ��Ⱥ�ٷ��ķ�ʽ�������г��ƵĿ����Ծٳ�������󷵻����ٵ����������
    public static int paicount(int[] pai)
    {
    	int count=0,n=0,cishu=20;
    	
    	while(pai[n]==0)
    		if(++n==13)	return 0;
    	
    	int[] paitmp=pai;
    	//������
    	count=danpai(pai,n);
    	if(cishu>count)
			cishu=count;

    	//��˫��
    	if(pai[n]>=2)
    	{
    		count=duizi(pai,n);
    		if(cishu>count)
    			cishu=count;
    	}
    	
    	//������
    	if(pai[n]>=3)
    	{
    		count=sandai(pai,n);
    		if(cishu>count)
    			cishu=count;
    	}
    	
    	//˳�����
    	if(n<9)
    	{
    		if(paitmp[n]!=0&&paitmp[n+1]!=0&&paitmp[n+2]!=0&&paitmp[n+3]!=0&&paitmp[n+4]!=0)
    		{
    			count=shunzi(pai,n);
        		if(cishu>count)
        			cishu=count;
    		}
    	}
    	//�������
    	count=beidai(pai,n);
		if(cishu>count)
			cishu=count;
    	
    	return cishu;
    }
    
    public static int danpai(int[] pai,int n)
    {
    	int count=0;
    	pai[n]--;
    	count=paicount(pai)+1;
    	pai[n]++;
    	return count;
    }
    
    public static int duizi(int[] pai,int n)
    {
    	int count=0;
    	pai[n]-=2;
    	count=paicount(pai)+1;
    	pai[n]+=2;
    	
    	return count;
    }
    
    public static int sandai(int[] pai,int n)
    {
    	int cishu=0,count=0;
    	pai[n]=pai[n]-3;
		//������
		count=paicount(pai)+1;
		cishu=count;
		//��һ�ű��Լ������
		for(int i=n;i<13;i++)
			if(pai[i]!=0)
			{
				pai[i]--;
				count=paicount(pai)+1;
				pai[i]++;
				if(cishu>count)
	    			cishu=count;
			}
		pai[n]=pai[n]+3;
		return cishu;
    }
    
    public static int sidai(int[] pai,int n)
    {
    	int cishu=0,count=0;
    	pai[n]=pai[n]-4;
		//������
		count=paicount(pai)+1;
		cishu=count;
		
		for(int i=n;i<13;i++)
		{
			if(pai[i]!=0)
			{
				pai[i]--;
				//��һ����
				count=paicount(pai)+1;
    			if(cishu>count)
	    			cishu=count;
    			
				for(int j=i;j<13;j++)
					if(pai[j]!=0)
					{
						pai[j]--;
						//��������
						count=paicount(pai)+1;
    					pai[j]++;
    					if(cishu>count)
    		    			cishu=count;
					}
				pai[i]++;
			}
		}
		pai[n]=pai[n]+4;
		return cishu;
    }
    
    public static int shunzi(int[] pai,int n)
    {
    	int cishu=0,count=0;
    	for(int i=0;i<5;i++)
			pai[n+i]--;
		count=paicount(pai)+1;
		cishu=count;
		for(int i=0;i<5;i++)
			pai[n+i]++;
		
		for(int i=0;i<9-n;i++)
		{
			if(pai[n+4+i]!=0)
			{
				for(int j=0;j<5+i;j++)
    				pai[n+j]--;
    			count=paicount(pai)+1;
    			for(int j=0;j<5+i;j++)
    				pai[n+j]++;
    			if(cishu>count)
        			cishu=count;
			}
			else
				break;
		}
		return cishu;
    }
    
    public static int beidai(int[] pai,int n)
    {
    	int cishu=20,count=0;
    	for(int i=n+1;i<13;i++)
    	{
    		pai[n]--;
    		if(pai[i]>=3)
    		{
    			pai[i]=pai[i]-3;
    			count=paicount(pai)+1;
    			pai[i]=pai[i]+3;
    			if(cishu>count)
        			cishu=count;
    		}
    		if(pai[i]>=4)
    		{
    			pai[i]=pai[i]-4;
    			count=paicount(pai)+1;
    			if(cishu>count)
        			cishu=count;
    			for(int j=n;j<13;j++)
    			{
    				if(pai[j]!=0)
    				{
    					pai[j]=pai[j]-1;
    					count=paicount(pai)+1;
    					pai[j]=pai[j]+1;
    					if(cishu>count)
    	        			cishu=count;
    				}
    			}
    			pai[i]=pai[i]+4;
    		}
    		pai[n]++;
    	}
		return cishu;
    }
}