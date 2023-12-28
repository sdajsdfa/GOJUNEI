/**
 *
 */
package com.yhgs.util;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @version V_1.1.0
 * @author：xjw
 * @email nokiaxjw@126.com
 * @date Jul 22, 2016
 * @description
 */

public class BitConverters {
    public static short ToInt16(byte[] bytes, int offset) {
        short result = ByteBuffer.wrap(bytes, (int) offset, 2).order(ByteOrder.LITTLE_ENDIAN).getShort();
        return result;
        /*short result = (short) ((int)bytes[offset]&0xFF);
        result |= ((int)bytes[offset+1]&0xFF) << 8;

		return (short) (result & 0xFFFF);*/
    }

    public static int ToUInt16(byte[] bytes, int offset) {
        int result = (int) bytes[offset + 1] & 0xFF;
        result |= ((int) bytes[offset] & 0xFF) << 8;

        return result & 0xFFFF;
    }

    // 小端数据转Int32
    public static int ToInt32(byte[] bytes, int offset) {
        int result = ByteBuffer.wrap(bytes, (int) offset, 4).order(ByteOrder.LITTLE_ENDIAN).getInt();
        return result;
        /*int result = (int)bytes[offset]&0xFF;
        result |= ((int)bytes[offset+1]&0xFF) << 8;
		result |= ((int)bytes[offset+2]&0xFF) << 16;
		result |= ((int)bytes[offset+3]&0xFF) << 24;

		return result;*/
    }

    public static long ToUInt32(byte[] bytes, int offset) {
        long result = (int) bytes[offset] & 0xFF;
        result |= ((int) bytes[offset + 1] & 0xFF) << 8;
        result |= ((int) bytes[offset + 2] & 0xFF) << 16;
        result |= ((int) bytes[offset + 3] & 0xFF) << 24;

        return result & 0xFFFFFFFFL;
    }

    public static long ToUInt64(byte[] bytes, int offset) {
        long result = 0;
        for (int i = 0; i <= 56; i += 8) {
            result |= ((int) bytes[offset++] & 0xFF) << i;
        }
        return result;
    }


    public static float ToSingle(byte[] bytes, int offset) {
        float result = ByteBuffer.wrap(bytes, (int) offset, 4).order(ByteOrder.LITTLE_ENDIAN).getFloat();
        return result;

		/*long l;
        l = b[(int)offset+0];
	    l &= 0xFF;
	    l |= ((long) b[(int)offset + 1] << 8);
	    l &= 0xFFFF;
	    l |= ((long) b[(int)offset + 2] << 16);
	    l &= 0xFFFFFF;
	    l |= ((long) b[(int)offset + 3] << 24);
	    return Float.intBitsToFloat((int)l); */
    }


    public static double ToDouble(byte[] bytes, long offset) {
        double result = ByteBuffer.wrap(bytes, (int) offset, 8).order(ByteOrder.LITTLE_ENDIAN).getDouble();
        return result;
        /*ByteBuffer buf = ByteBuffer.wrap(b, (int) offset, 8);
        double outp = buf.getDouble();
		return outp;*/
        /*int start=(int)offset;//???
        int i = 0;
		    int len = 8;
		    int cnt = 0;
		    byte[] tmp = new byte[len];
		    for (i = start; i < (start + len); i++) {
		        tmp[cnt] = b[i];
		        //System.out.println(java.lang.Byte.toString(arr[i]) + " " + i);
		        cnt++;
		    }
		    long accum = 0;
		    i = 0;
		    for ( int shiftBy = 0; shiftBy < 64; shiftBy += 8 ) {
		        accum |= ( (long)( tmp[i] & 0xFF ) ) << shiftBy;
		        i++;
		    }

		   return Double.longBitsToDouble(accum);*/
    }

    public static byte[] GetBytes(int value) {
        ByteBuffer buffer = ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN);
        buffer.putInt(value);

        return buffer.array();
        /*byte[] bytes = new byte[4];
        bytes[3] = (byte) ((value & 0xFF000000) >> 24);
		bytes[2] = (byte) ((value & 0x00FF0000) >> 16);
		bytes[1] = (byte) ((value & 0x0000FF00) >> 8);
		bytes[0] = (byte) ((value & 0x000000FF));GetX0
		return bytes;*/
        /*byte[] bytes = new byte[4];
        bytes[0] = (byte) (value >> 24);
		bytes[1] = (byte) (value >> 16);
		bytes[2] = (byte) (value >> 8);
		bytes[3] = (byte) (value);
		//System.out.println(Arrays.toString(bytes));
		return bytes;*/
    }

    public static byte[] GetBytes(long value) {
        ByteBuffer buffer = ByteBuffer.allocate(8).order(ByteOrder.LITTLE_ENDIAN);
        buffer.putLong(value);
        return buffer.array();
        /*byte[] bytes = new byte[4];
        bytes[0] = (byte) (value >> 24);
		bytes[1] = (byte) (value >> 16);
		bytes[2] = (byte) (value >> 8);
		bytes[3] = (byte) (value);
		//System.out.println(Arrays.toString(bytes));
		return bytes;*/
    }

    //小端模式转换浮点数据
    public static byte[] GetBytes(float value) {
        /*int valueint = (int)value;
        byte[] bytes = new byte[4];
		bytes[0] = (byte) (valueint >> 24);
		bytes[1] = (byte) (valueint >> 16);
		bytes[2] = (byte) (valueint >> 8);
		bytes[3] = (byte) (valueint);
		//System.out.println(Arrays.toString(bytes));
		return bytes;*/
        ByteBuffer buffer = ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN);
        buffer.putFloat(value);
        return buffer.array();
    }

    //小端模式转换双浮点数据
    public static byte[] GetBytes(double value) {
        /*int valueint = (int)value;
        byte[] bytes = new byte[4];
		bytes[0] = (byte) (valueint >> 24);
		bytes[1] = (byte) (valueint >> 16);
		bytes[2] = (byte) (valueint >> 8);
		bytes[3] = (byte) (valueint);
		//System.out.println(Arrays.toString(bytes));
		return bytes;*/
        ByteBuffer buffer = ByteBuffer.allocate(8).order(ByteOrder.LITTLE_ENDIAN);
        buffer.putDouble(value);
        return buffer.array();
    }

    /**
     * 小端模式转换short类型数据
     */
    public static byte[] GetBytes(short value) {
        ByteBuffer buffer = ByteBuffer.allocate(2).order(ByteOrder.LITTLE_ENDIAN);
        buffer.putShort(value);
        return buffer.array();
    }

    public static String SYSTEMTIMEToString(byte[] bytes, int offset) {
        //测试时间		16 年
        short wYear = BitConverters.ToInt16(bytes, offset);

        //测试时间		16 月
        short wMonth = BitConverters.ToInt16(bytes, offset + 2);

        //测试时间		16   代表的星期几，1代表周日、2代表周一
        short wDayOfWeek = BitConverters.ToInt16(bytes, offset + 4);

        //测试时间		16  //日
        short wDay = BitConverters.ToInt16(bytes, offset + 6);

        //测试时间		16  //时
        short wHour = BitConverters.ToInt16(bytes, offset + 8);

        //测试时间		16  //分
        short wMinute = BitConverters.ToInt16(bytes, offset + 10);

        //测试时间		16  //秒
        short wSecond = BitConverters.ToInt16(bytes, offset + 12);

        //测试时间		16  //毫秒
        int wMilliseconds = BitConverters.ToInt16(bytes, offset + 14);

        String str = String.format("%04d-%02d-%02d %02d:%02d:%02d", wYear, wMonth, wDay, wHour, wMinute, wSecond);
        return str;
    }

    public static Date StringToDate(String str) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//小写的mm表示的是分钟
        Date date = null;
        try {
            date = dateFormat.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }

    public static Date StringNoHourToDate(String str) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = dateFormat.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }

    public static String DateToString(Date date) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String str = format.format(date);
        return str;
    }


    public static String BytesToString(byte[] buf) {
        if (((buf == null) || (buf.length == 0)) || (buf[0] == 0)) {
            return "";
        }
        try {
            for (int i = 1; i < buf.length; i++) {
                if (buf[i] == 0) {
                    byte[] destinationArray = new byte[i];
                    System.arraycopy(buf, 0, destinationArray, 0, i);
                    return new String(destinationArray, "gb2312");
                }
            }
            return new String(buf, "gb2312");
        } catch (java.lang.Exception e) {
            return "";
        }
    }

    public static String BytesToGBKString(byte[] buf) {
        if (((buf == null) || (buf.length == 0)) || (buf[0] == 0)) {
            return "";
        }
        try {
            for (int i = 1; i < buf.length; i++) {
                if (buf[i] == 0) {
                    byte[] destinationArray = new byte[i];
                    System.arraycopy(buf, 0, destinationArray, 0, i);
                    return new String(destinationArray, "GBK");
                }
            }
            return new String(buf, "GBK");
        } catch (java.lang.Exception e) {
            return "";
        }
    }


    public static String BytesToString(byte[] buf, int offset, int length) {
        if (((buf == null) || (buf.length == 0)) || (offset >= buf.length)) {
            return "";
        }
        if ((offset + length) > buf.length) {
            length = buf.length - offset;
        }
        if (length <= 0) {
            return "";
        }
        byte[] destinationArray = new byte[length];
        System.arraycopy(buf, offset, destinationArray, 0, length);
        return BytesToString(destinationArray);
    }

    public static String BytesToGBKString(byte[] buf, int offset, int length) {
        if (((buf == null) || (buf.length == 0)) || (offset >= buf.length)) {
            return "";
        }
        if ((offset + length) > buf.length) {
            length = buf.length - offset;
        }
        if (length <= 0) {
            return "";
        }
        byte[] destinationArray = new byte[length];
        System.arraycopy(buf, offset, destinationArray, 0, length);
        return BytesToString(destinationArray);
    }

    //如果字节序为big-endian，返回true;
    //反之为   little-endian，返回false
    boolean IsBig_Endian() {
        if (ByteOrder.nativeOrder() == ByteOrder.BIG_ENDIAN)
            return true;

        return false;
    }
}
/*
public class BitConverter {

	public class BytesString {
		public byte[] bytes;
		public int start;
		public int length;
	}

    public static byte[] GetBytesFloat(float value)
    {
    	ByteBuffer buffer = ByteBuffer.allocate(4).order(ByteOrder.nativeOrder());
    	buffer.putFloat(value);
    	return buffer.array();
    }

    public static byte[] GetBytesInt(int value)
    {
    	ByteBuffer buffer = ByteBuffer.allocate(4).order(ByteOrder.nativeOrder());
    	buffer.putInt(value);
    	return buffer.array();
    }

    public static int ToInt32(byte[] b,int index)
    {
    	ByteBuffer buffer = ByteBuffer.allocate(4).order(ByteOrder.nativeOrder());
    	buffer.put(b, index, 4);
    	buffer.flip();
    	return buffer.getInt();
    }

    public static String BytesToString(byte[] b,int start, int length) {
        StringBuffer result = new StringBuffer("");
        for (int i=0; i<length; i++) {
          result.append((char)(b[start+i]));
        }
        return result.toString();
      }

    public static String GetString(BytesString str) {
        int count = BitConverter.ToInt32(str.bytes, str.start);
        int stringStart = str.start;
        str.start += 4;

        return (count > 0) ? BytesToString(str.bytes, stringStart, count) : "";
    }
}*/
