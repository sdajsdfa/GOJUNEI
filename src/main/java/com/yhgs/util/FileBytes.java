package com.yhgs.util;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class FileBytes {
    //private Xml.Encoding Enc = Xml.Encoding.valueOf("GB2312");
    private int mIndex = 0;
    private final String EMPTY = "null";

    private Charset charset = StandardCharsets.UTF_8;

    //存
    private HashMap<Integer, byte[]> mFileBytes = null;

    //取
    private byte[] Data = null;

    public FileBytes() {
        this.mFileBytes = new HashMap<>();
        this.mIndex = 0;
    }

    public FileBytes(byte[] data, Charset charset) {
        this.Data = data;
        this.mIndex = 0;
        this.charset = charset;
    }


    //改改改改改改改改改改改改改改改改改改改改改改改改改改改改改改改改改改改改改改改改改改改改改改改改改改改改改改改改改改改改改改改改改改改
    public static void ReWriteIntValue(byte[] data, int newValue, int index) {
        if (data == null) {
            return;
        }

        if (index > (data.length - 4)) {
            return;
        }

        byte[] values = BitConverters.GetBytes(newValue);
        System.arraycopy(values, 0, data, index, 4);
    }

    public static void ReWriteFloatValue(byte[] data, float newValue, int index) {
        if (data == null) {
            return;
        }

        if (index > (data.length - 4)) {
            return;
        }

        byte[] values = BitConverters.GetBytes(index);
        System.arraycopy(values, 0, data, index, 4);
    }

    public static void ReWriteByteValue(byte[] data, byte newValue, int index) {
        if (data == null) {
            return;
        }

        if (index > (data.length - 4)) {
            return;
        }

        data[index] = newValue;
    }

    //存存存存存存存存存存存存存存存存存存存存存存存存存存存存存存存存存存存存存存存存存存存存存存存存存存存存存存存存存存存存存存存存存存存
    /// <summary>
    /// 定长字符串
    /// </summary>
    /// <param name="value"></param>
    /// <param name="length"></param>
    /// <returns></returns>
    public void Save(String value, int length) {
        byte[] data = GetBytes(value, length);
        mFileBytes.put(mIndex, data);
        mIndex++;
    }

    /// <summary>
    /// 非定长字符串
    /// </summary>
    /// <param name="value"></param>
    /// <returns></returns>
    public void Save(String value) {
        byte[] data = GetBytes(value);
        Save(data.length);

        mFileBytes.put(mIndex, data);
        mIndex++;
    }

    public void Save(long value) {
        mFileBytes.put(mIndex, BitConverters.GetBytes(value));
        mIndex++;
    }


    public void Save(int value) {
        mFileBytes.put(mIndex, BitConverters.GetBytes(value));
        mIndex++;
    }

    public void Save(double value) {
        mFileBytes.put(mIndex, BitConverters.GetBytes(value));
        mIndex++;
    }

    public void Save(float value) {
        mFileBytes.put(mIndex, BitConverters.GetBytes(value));
        mIndex++;
    }

    public void Save(short value) {
        mFileBytes.put(mIndex, BitConverters.GetBytes(value));
        mIndex++;
    }

    public void Save(byte value) {
        mFileBytes.put(mIndex, new byte[]{value});
        mIndex++;
    }

    public boolean Save(byte[] data) {
        if (data == null) {
            return false;
        }
        mFileBytes.put(mIndex, data);
        mIndex++;
        return true;
    }

    public boolean Save(byte[] data, int length) {
        if (data == null || length < 0) {
            return false;
        }
        byte[] d = new byte[length];
        System.arraycopy(data, 0, d, 0, length);
        mFileBytes.put(mIndex, d);
        mIndex++;
        return true;
    }

    public void Save(short[] data) {
        byte[] temp = new byte[data.length * 2];
        byte[] tempEle;
        for (int i = 0; i < data.length; i++) {
            tempEle = BitConverters.GetBytes(data[i]);
            System.arraycopy(tempEle, 0, temp, i * 2, 2);
        }

        Save(temp);
    }

    public void Save(float[] data) {
        byte[] temp = new byte[data.length * 4];
        byte[] tempEle;
        for (int i = 0; i < data.length; i++) {
            tempEle = BitConverters.GetBytes(data[i]);
            System.arraycopy(tempEle, 0, temp, i * 4, 4);
        }

        Save(temp);
    }
    //存存存存存存存存存存存存存存存存存存存存存存存存存存存存存存存存存存存存存存存存存存存存存存存存存存存存存存存存存存存存存存存存存存存

    //取取取取取取取取取取取取取取取取取取取取取取取取取取取取取取取取取取取取取取取取取取取取取取取取取取取取取取取取取取取取取取取取取取取
    public void IndexAdd(int add) {
        mIndex += add;
    }

    public void IndexEqual(int value) {
        mIndex = value;
    }

    public int GetIndex() {
        return mIndex;
    }

    public String GetString() {
        if (Data == null) {
            return EMPTY;
        }

        int length = BitConverters.ToInt32(Data, mIndex);
        mIndex += 4;

        String value = BitConverters.BytesToString(Data, mIndex, length);
        mIndex += length;
        return value;
    }

    public String GetString(int length) {
        if (Data == null) {
            return EMPTY;
        }

        String value = BitConverters.BytesToString(Data, mIndex, length).replace("\0", "");
        mIndex += length;
        return value;
    }

    public String GetImageString(int length) {
        if (Data == null)
            return EMPTY;

        String value = new String(Data, mIndex, length);
        mIndex += length;
        return value;
    }

    public int GetInt() {
        if (Data == null) {
            return Integer.MAX_VALUE;
        }
        int value = BitConverters.ToInt32(Data, mIndex);
        mIndex += 4;
        return value;
    }

    public long GetLong() {
        if (Data == null) {
            return Long.MAX_VALUE;
        }
        long value = BitConverters.ToUInt64(Data, mIndex);
        mIndex += 8;
        return value;
    }

    public short GetShort() {
        if (Data == null) {
            return Short.MAX_VALUE;
        }
        short value = BitConverters.ToInt16(Data, mIndex);
        mIndex += 2;
        return value;
    }

    public byte GetByte() {
        if (Data == null) {
            return Byte.MAX_VALUE;
        }
        byte value = Data[mIndex];
        mIndex += 1;
        return value;
    }

    public double GetDouble() {
        if (Data == null) {
            return Double.MAX_VALUE;
        }
        double value = BitConverters.ToDouble(Data, mIndex);
        mIndex += 8;
        return value;
    }

    public float GetFloat() {
        if (Data == null) {
            return Float.MAX_VALUE;
        }
        float value = BitConverters.ToSingle(Data, mIndex);
        mIndex += 4;
        return value;
    }
/*
    public String GetSYSTEMTIME() {
        String time = GetShort() + "-" + (GetShort() + 1) + "-";
        GetShort();//略去星期
        time += GetShort() + " ";
        time += GetShort() + ":";
        time += GetShort() + ":";
        time += GetShort();
        GetShort();
        return time;
    }*/

    public String GetSYSTEMTIMEFormat() {
        //年
        String time = GetShort() + "-";
        //月
        int month = GetShort();
        if (month < 10) {
            time += "0";
        }
        time += month;
        time += "-";

        //略去星期
        GetShort();

        //日
        int day = GetShort();
        if (day < 10) {
            time += "0";
        }
        time += day;
        time += " ";

        //时
        int hour = GetShort();
        if (hour < 0) {
            hour = 0;
        }
        if (hour < 10) {
            time += "0";
        }
        time += hour;
        time += ":";

        //分
        int minute = GetShort();
        if (minute < 0) {
            minute = 0;
        }
        if (minute < 10) {
            time += "0";
        }
        time += minute;
        time += ":";

        //秒
        int second = GetShort();
        if (second < 0) {
            second = 0;
        }
        if (second < 10) {
            time += "0";
        }
        time += second;

        GetShort();

        return time;
    }

    public byte[] GetByteArray(int length) {
        byte[] data = new byte[length];
        System.arraycopy(Data, mIndex, data, 0, length);

        mIndex += length;
        return data;
    }

    public short[] GetShortArray(int length) {
        short[] temp = new short[length];
        for (int i = 0; i < temp.length; i++) {
            temp[i] = GetShort();
        }

        return temp;
    }

    public float[] GetFloatArray(int length) {
        float[] temp = new float[length];
        for (int i = 0; i < temp.length; i++) {
            temp[i] = GetFloat();
        }

        return temp;
    }

    public int[] GetIntArray(int length) {
        int[] temp = new int[length];
        for (int i = 0; i < temp.length; i++) {
            temp[i] = GetInt();
        }

        return temp;
    }
    //取取取取取取取取取取取取取取取取取取取取取取取取取取取取取取取取取取取取取取取取取取取取取取取取取取取取取取取取取取取取取取取取取取取


    //其他操作
    /// <summary>
    /// 清空
    /// </summary>
    public void Clear() {
        mIndex = 0;
        mFileBytes.clear();
    }

    private int GetLength() {
        int count = 0;
        for (int i = 0; i < mFileBytes.size(); i++) {
            if (mFileBytes.get(i) != null) {
                count += mFileBytes.get(i).length;
            }
        }
        return count;
    }

    public byte[] ToBytes() {
        byte[] data = new byte[GetLength()];
        int index = 0;
        for (int i = 0; i < mFileBytes.size(); i++) {
            if (mFileBytes.get(i) != null) {
                System.arraycopy(mFileBytes.get(i), 0, data, index, mFileBytes.get(i).length);
                index += mFileBytes.get(i).length;
            }
        }
        return data;
    }


    /// <summary>
    /// 生成定长数组
    /// </summary>
    /// <param name="str"></param>
    /// <param name="length">固定值</param>
    /// <returns></returns>
    private byte[] GetBytes(String str, int length) {
        if (length < 0) {
            length = 0;
        }
        byte[] data = new byte[length];
        if (str == null) {
            return data;
        }

        byte[] t = str.getBytes(charset);

        System.arraycopy(t, 0, data, 0, Math.min(t.length, length));
        /*for (int i = t.length; i < length - 1; i++) {
            data[i] = ' ';
        }*/
        return data;
    }

    /// <summary>
    /// 生成不定长数组
    /// </summary>
    /// <param name="str"></param>
    /// <returns></returns>
    private byte[] GetBytes(String str) {
        if (str == null) {
            return new byte[0];
        }

        return str.getBytes(charset);
    }
}

