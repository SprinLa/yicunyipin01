package com.yicunyipin.util;


public class HByte
{

    /**
     *
     */
    public HByte()
    {
        ibyte=new DES3Byte();
    }

    DES3Byte ibyte;

    private char abyte;

    public byte getAbyte()
    {
//        System.out.println("get byte");
        int[] array=
            {
            (int)ibyte.bit0,
            (int)ibyte.bit1,
            (int)ibyte.bit2,
            (int)ibyte.bit3,
            (int)ibyte.bit4,
            (int)ibyte.bit5,
            (int)ibyte.bit6,
            (int)ibyte.bit7};
        String bString="";
        for(int i=0;i<array.length;i++)
        {
            bString=array[i]+bString;
        }

        return getByteFromChar((char)Integer.valueOf(bString,2).intValue());


    }

    public void setAbyte(byte abyte)
    {
//        System.out.println("abyte is " + abyte);
        String bString=null;
        this.abyte=getChar(abyte); //转换
        if(this.abyte>=128)
        {
            bString=Integer.toBinaryString(this.abyte);
        }
        else
        {
            //更新ibyte
            bString=Integer.toString(abyte,2); //Integer.toBinaryString(abyte);
            int len=bString.length();
            for(int i=len;i<8;i++)
            {
                bString="0"+bString;
            }
        }
        ibyte.bit7=(char)Integer.parseInt(bString.charAt(0)+"");
        ibyte.bit6=(char)Integer.parseInt(bString.charAt(1)+"");
        ibyte.bit5=(char)Integer.parseInt(bString.charAt(2)+"");
        ibyte.bit4=(char)Integer.parseInt(bString.charAt(3)+"");
        ibyte.bit3=(char)Integer.parseInt(bString.charAt(4)+"");
        ibyte.bit2=(char)Integer.parseInt(bString.charAt(5)+"");
        ibyte.bit1=(char)Integer.parseInt(bString.charAt(6)+"");
        ibyte.bit0=(char)Integer.parseInt(bString.charAt(7)+"");

    }

    public static char getChar(byte b)
    {

        if(b>=0)
        {
            return(char)b;
        }
        else
        {
            char temp=(char)(b*(-1));

            if(b==-128)
            {
                return temp;
            }
            else
            {
                temp=(char)(~temp+1);
                String bString=Integer.toBinaryString(temp);
                return(char)getBinaryValue(bString.substring(bString.length()-8));
            }
        }
    }

    /**
     * 获得二进制字符串�?�?对应的char�?�?
     * @param bString
     * @return
     */
    public static char getBinaryValue(String bString)
    {
        int result=0;
        String temp=bString;
        for(int i=0;i<32-bString.length();i++)
        {
            temp="0"+temp;
        }
        result=result+Integer.valueOf(temp,2).intValue();

        return(char)result;
    }

    /**
     * 兼容C语言中unsigned char和char之间的转�?�?
     * @param c 无符号的char
     * @return 带符号的char（对应java中的byte�?�?
     */
    public static byte getByteFromChar(char c)
    {
        if(c>=128)
        {
            int t=~c+1;
//            System.out.println("t is " + t);
            String bString=Integer.toBinaryString(t);
//            System.out.println("bstring is " + bString);
            bString=bString.substring(bString.length()-8);

            int temp=Integer.valueOf(bString,2).intValue()*(-1);
//            System.out.println("temp is " + (int)temp);

            return(byte)temp;
        }
        else
        {
            return(byte)c;
        }
    }
}
