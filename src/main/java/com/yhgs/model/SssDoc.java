package com.yhgs.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;
import com.yhgs.util.BitConverter;

import java.io.*;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author 易生雄
 * @since 2023-06-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SssDoc implements Serializable {

    public String MachineId;

    public String SerialNo;

    public String PileNo;

    public String CreateName;

    public Date TestTime;

    public float PileLength;

    public String PileDiameter;

    public float PileVelocity;

    public String ConcreteStrength;

    public int GpsIsValid;

    public double GpsLongitude;

    public double GpsLatitude;

    public String ShangGangZheng;

    public int IsHighStrainTest;

    public float LengthUnderSensor;

    public float HammerWeight;

    public float HammerDropHeight;

    public float SectionArea;

    public float BottomArea;

    public float Density;

    public float SectionCircum;

    public float Jc;

    public float DepthIn;

    public float Vs;

    public String BaseInfoId;

    public ChannelData[] Channels;

    @Data
    public static class ChannelData {

        public String BaseInfoId;

        public byte SignalType ;

        public float SampleInterval;

        public short SampleGain;

        public short SampleLength;

        public float SensorSensitive;

        public Date SampleTime;

        public short FilterFrequency;

        public short[] ChannelData;

        public short DataVersion;
    }

    public static byte[] makeSssFile(SssDoc sssDoc) {
        if (sssDoc == null) throw new IllegalArgumentException("传入的SssDoc对象为空");
        int num = 720;
        for (int i = 0; i < sssDoc.getChannels().length; i++)
        {
            num += sssDoc.getChannels()[i].getSampleLength() * 2 + 64;
        }
        byte[] array = new byte[num];
        byte[] array2 = new byte[0];
        array2 = StringToBytes("RS-V2.00(W1616)");
        System.arraycopy(array2, 0, array, 0, array2.length);
        array[16] = (byte)(sssDoc.getIsHighStrainTest()>0 ? 2 : 0);
        System.arraycopy(array2, 0, array, 0, array2.length);
        array2 = StringToBytes(sssDoc.getSerialNo());
        System.arraycopy(array2, 0, array, 52, array2.length);
        array2 = StringToBytes(sssDoc.getPileNo());
        System.arraycopy(array2, 0, array, 148, array2.length);
        array2 = StringToBytes(sssDoc.getShangGangZheng());
        System.arraycopy(array2, 0, array, 241, array2.length);
        array2 = StringToBytes(sssDoc.getMachineId());
        System.arraycopy(array2, 0, array, 209, array2.length);
        System.arraycopy(BitConverter.getBytes(sssDoc.getPileLength()), 0, array, 337, 4);
        System.arraycopy(BitConverter.getBytes(sssDoc.getPileVelocity()), 0, array, 341, 4);
        int result = 0;
        try {
            Integer.valueOf(sssDoc.getConcreteStrength());
            System.arraycopy(BitConverter.getBytes(result), 0, array, 164, 2);
        } catch (NumberFormatException e) {
            result = 0;
        }
        array[345] = (byte)(sssDoc.getChannels().length & 0xFF);
        if(sssDoc.getIsHighStrainTest()==1){
            System.arraycopy(BitConverter.getBytes(sssDoc.getHammerWeight()),0, array, 347, 4);
            System.arraycopy(BitConverter.getBytes(sssDoc.getSectionArea()), 0, array, 351, 4);
            System.arraycopy(BitConverter.getBytes(sssDoc.getDensity()), 0, array, 355, 4);
        }
        for (int j = 0; j < sssDoc.getChannels().length; j++)
        {
            if (sssDoc.getChannels()[j].getSignalType() == 2)
            {
                array[359] = 1;
                break;
            }
        }
        String[] array3 = sssDoc.getPileDiameter().split("Φ", '×');
        int result2 = 0;
        int result3 = 0;
        try {
            Integer.valueOf(array3[0]);
        } catch (NumberFormatException e) {
            result2 = 0;
        }
        if (array3.length >= 2)
        {
            try {
                Integer.valueOf(array3[1]);
            } catch (NumberFormatException e) {
                result3 = 0;
            }
        }
        if (result2 > 0 && result3 > 0)
        {
            array[166] = 1;
            System.arraycopy(BitConverter.getBytes(result2), 0, array, 168, 4);
            System.arraycopy(BitConverter.getBytes(result3), 0, array, 172, 4);
        }
        else
        {
            result2 = ((result2 > result3) ? result2 : result3);
            if (result2 > 0)
            {
                System.arraycopy(BitConverter.getBytes(result2), 0, array, 168, 4);
            }
        }
        array[176] = (byte)(sssDoc.getTestTime().getYear() & 0xFF);
        array[177] = (byte)(sssDoc.getTestTime().getYear() >> 8);
        array[178] = (byte)(sssDoc.getTestTime().getMonth() & 0xFF);
        array[180] = (byte)(sssDoc.getTestTime().getDay() & 255);
        array[182] = (byte)(sssDoc.getTestTime().getDay() & 0xFF);
        array[184] = (byte)(sssDoc.getTestTime().getHours() & 0xFF);
        array[186] = (byte)(sssDoc.getTestTime().getMinutes() & 0xFF);
        array[188] = (byte)(sssDoc.getTestTime().getSeconds() & 0xFF);
        if (sssDoc.getGpsIsValid()==1)
        {
            array[192] = 1;
            System.arraycopy(BitConverter.getBytes(sssDoc.getGpsLongitude()), 0, array, 193, 8);
            System.arraycopy(BitConverter.getBytes(sssDoc.getGpsLatitude()), 0, array, 201, 8);
        }
        if(sssDoc.getIsHighStrainTest()==1){
            System.arraycopy(BitConverter.getBytes(sssDoc.getPileLength()), 0, array, 401, 4);
            System.arraycopy(BitConverter.getBytes(sssDoc.getPileVelocity()), 0, array, 405, 4);
            System.arraycopy(BitConverter.getBytes(sssDoc.getLengthUnderSensor()), 0, array, 409, 4);
            System.arraycopy(BitConverter.getBytes(sssDoc.getHammerWeight() / 1000f), 0, array, 413, 4);
            System.arraycopy(BitConverter.getBytes(sssDoc.getHammerDropHeight()), 0, array, 417, 4);
            System.arraycopy(BitConverter.getBytes(sssDoc.getSectionArea()), 0, array, 421, 4);
            System.arraycopy(BitConverter.getBytes(sssDoc.getBottomArea()), 0, array, 425, 4);
            System.arraycopy(BitConverter.getBytes(sssDoc.getDensity()), 0, array, 429, 4);
            System.arraycopy(BitConverter.getBytes(sssDoc.getSectionCircum()), 0, array, 433, 4);
            System.arraycopy(BitConverter.getBytes(sssDoc.getJc()), 0, array, 437, 4);
            System.arraycopy(BitConverter.getBytes(sssDoc.getDepthIn()), 0, array, 441, 4);
            System.arraycopy(BitConverter.getBytes(sssDoc.getVs()), 0, array, 445, 4);
        }
        int num2 = 720;
        for (int k = 0; k < sssDoc.getChannels().length; k++)
        {
            System.arraycopy(BitConverter.getBytes(sssDoc.getChannels()[k].getSensorSensitive()), 0, array, num2, 4);
            System.arraycopy(BitConverter.getBytes(sssDoc.getChannels()[k].getSampleInterval()), 0, array, num2 + 4, 4);
            array[num2 + 8] = (byte)(sssDoc.getChannels()[k].getSampleLength() & 0xFF);
            array[num2 + 9] = (byte)(sssDoc.getChannels()[k].getSampleLength() >> 8);
            System.arraycopy(BitConverter.getBytes((float)sssDoc.getChannels()[k].getSampleGain()), 0, array, num2 + 10, 4);
            array[num2 + 16] = sssDoc.getChannels()[k].getSignalType();
            array[num2 + 18] = (byte)(sssDoc.getChannels()[k].getFilterFrequency() & 0xFF);
            array[num2 + 19] = (byte)(sssDoc.getChannels()[k].getFilterFrequency() >> 8);
            array[num2 + 20] = (byte)(sssDoc.getChannels()[k].getSampleTime().getYear() & 0xFF);
            array[num2 + 21] = (byte)(sssDoc.getChannels()[k].getSampleTime().getYear() >> 8);
            array[num2 + 22] = (byte)(sssDoc.getChannels()[k].getSampleTime().getMonth() & 0xFF);
            array[num2 + 24] = (byte)(sssDoc.getChannels()[k].getSampleTime().getDay() & 255);
            array[num2 + 26] = (byte)(sssDoc.getChannels()[k].getSampleTime().getDay() & 0xFF);
            array[num2 + 28] = (byte)(sssDoc.getChannels()[k].getSampleTime().getHours() & 0xFF);
            array[num2 + 30] = (byte)(sssDoc.getChannels()[k].getSampleTime().getMinutes() & 0xFF);
            array[num2 + 32] = (byte)(sssDoc.getChannels()[k].getSampleTime().getSeconds() & 0xFF);
            num2 += 64;
            short[] channelData =sssDoc.getChannels()[k].ChannelData;
            for (short num3:channelData) {
                array[num2] = (byte)(num3 & 0xFF);
                num2++;
                array[num2] = (byte)(num3 >> 8);
                num2++;
            }
        }
        return array;
    }

    public static byte[] StringToBytes(String str){
        if (str == null || str.length() == 0)
        {
            return new byte[0];
        }
        try
        {
            return str.getBytes("gb2312");
        }
        catch(Exception e)
        {
            return new byte[0];
        }
    }


}
