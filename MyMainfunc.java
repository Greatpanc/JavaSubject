/*扑克牌最少出牌次数
 * 众所周知，一副扑克牌按大小分为13种牌，每一种牌各四个花色，总共52张牌
 * 规定13种牌按从大到小的顺序分别为A23456789TJQK，现在从一副牌中抽取20张，每轮选择下列规则中一项出牌：
 * 1、单牌：任出一张牌
 * 2、对子：两张大小相同的牌
 * 3、三带：三张大小相同的牌，附带至多一张牌
 * 4、四带：四张大小相同的牌，附带至多两张牌
 * 5、顺子：至少五张大小连续的牌
 * 求至少需要多少轮将输入的20张牌出完？
 * 例子：	输入：“8K67A65K27T59K346AK2”（字符串）
 * 		输出：4
 * Author： Greatpan
 * */

import java.util.*;

public class MyMainfunc {
    public static void main(String[] args){
       @SuppressWarnings("resource")
	Scanner input=new Scanner(System.in);
       System.out.print("请输入：");
       String str=input.next();	//8K67A65K27T59K346AK2
       char[] c=str.toCharArray();
       int[]  pai=new int[13];	//作为记录各类牌有多少张的数组
       for(int i=0;i<13;i++)	//初始时，各牌0张
    	   pai[i]=0;
       for(int i=0;i<20;i++)	//用于计算各牌有多少张
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
    	   			System.out.println("输入有错误，程序结束");
    	   			System.exit(0); break;	//字符串有错误字符时，结束
    	   }

//       for(int i=0;i<13;i++)
//    	   for(int j=0;j<pai[i];j++)
//    		   System.out.print((i+1)+" ");
//       System.out.println();
//       for(int i=0;i<13;i++)
//    	   System.out.print(pai[i]+" ");
//       System.out.println();
       System.out.println("一共多少次："+paicount(pai));
    }
    
    //该函数计算最少的出牌顺序的思想是先出最小的牌，通过群举法的方式，将所有出牌的可能性举出来，最后返回最少的情况的轮数
    public static int paicount(int[] pai)
    {
    	int count=0,n=0,cishu=20;
    	
    	while(pai[n]==0)
    		if(++n==13)	return 0;
    	
    	int[] paitmp=pai;
    	//出单牌
    	count=danpai(pai,n);
    	if(cishu>count)
			cishu=count;

    	//出双牌
    	if(pai[n]>=2)
    	{
    		count=duizi(pai,n);
    		if(cishu>count)
    			cishu=count;
    	}
    	
    	//出三带
    	if(pai[n]>=3)
    	{
    		count=sandai(pai,n);
    		if(cishu>count)
    			cishu=count;
    	}
    	
    	//顺子情况
    	if(n<9)
    	{
    		if(paitmp[n]!=0&&paitmp[n+1]!=0&&paitmp[n+2]!=0&&paitmp[n+3]!=0&&paitmp[n+4]!=0)
    		{
    			count=shunzi(pai,n);
        		if(cishu>count)
        			cishu=count;
    		}
    	}
    	//被带情况
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
		//不带牌
		count=paicount(pai)+1;
		cishu=count;
		//带一张比自己大的牌
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
		//不带牌
		count=paicount(pai)+1;
		cishu=count;
		
		for(int i=n;i<13;i++)
		{
			if(pai[i]!=0)
			{
				pai[i]--;
				//带一张牌
				count=paicount(pai)+1;
    			if(cishu>count)
	    			cishu=count;
    			
				for(int j=i;j<13;j++)
					if(pai[j]!=0)
					{
						pai[j]--;
						//带两张牌
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