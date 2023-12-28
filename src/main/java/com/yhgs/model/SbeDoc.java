package com.yhgs.model;

import lombok.Data;
import com.yhgs.util.FileBytes;

import java.io.*;


/**
 * <p>
 * 
 * </p>
 *
 * @author 易生雄
 * @since 2023-06-25
 */
@Data
public class SbeDoc implements Serializable {

    public String Name; //设备标识符”RS-ST01E”

    public byte Edition; //最新版本号=6,原来的版本号=3

    public String BaseInfoId;//桩基本信息GUID

    public String SerialNo;//测试流水号

    public String ShangGangZheng;//上岗证号

    public String MachineId;//仪器编号

    public String PileNo;//桩号

    public String TestTime;//检测日期

    public byte GpsIsValid; //GPS信息是否有效

    public String AffuseDate;//灌注日期

    public String ConcreteStrength;//混凝土强度

    public double GpsLongitude; //经度

    public double GpsLatitude; //纬度

    public int PileDiameterHeight;//桩径

    public int PileDiameterWidth;//桩径

    public float PileLength;//桩长,=起点深度,4个字节

    public float StartDepth;//起点深度, mm，4个字节

    public float MinDepth;//最小深度(设备的终止深度)

    public byte TubeCount;//声测管道数量

    public int Angle;//1管北偏角

    public byte PileType;//0-圆形桩截面;1-正方形;2-长方形

    public float Step;//每前进多少个脉冲记录一个测

    public float SysZeroTime;//几个剖面的平均系统零声时

    public int SectionCount;//保存的剖面数

    public byte SensorType;//计数传感器的类型-目前使用的是20脉冲/周

    public float Dwheel;//轮径, mm

    public float Dcable;//线径, mm

    public SectionData[] SectionDatas;//剖面数据指针链表


    public SbeDoc(SbeDoc sbeDoc) {
        Name = sbeDoc.getName();
        Edition = sbeDoc.getEdition();
        BaseInfoId = sbeDoc.getBaseInfoId();
        SerialNo = sbeDoc.getSerialNo();
        ShangGangZheng = sbeDoc.getShangGangZheng();
        MachineId = sbeDoc.getMachineId();
        PileNo = sbeDoc.getPileNo();
        TestTime = sbeDoc.getTestTime();
        GpsIsValid = sbeDoc.getGpsIsValid();
        AffuseDate = sbeDoc.getAffuseDate();
        ConcreteStrength = sbeDoc.getConcreteStrength();
        GpsLongitude = sbeDoc.getGpsLongitude();
        GpsLatitude = sbeDoc.getGpsLatitude();
        PileDiameterHeight = sbeDoc.getPileDiameterHeight();
        PileDiameterWidth = sbeDoc.getPileDiameterWidth();
        PileLength = sbeDoc.getPileLength();
        StartDepth = sbeDoc.getStartDepth();
        MinDepth = sbeDoc.getMinDepth();
        TubeCount = sbeDoc.getTubeCount();
        Angle = sbeDoc.getAngle();
        PileType = sbeDoc.getPileType();
        Step = sbeDoc.getStep();
        SysZeroTime = sbeDoc.getSysZeroTime();
        SectionCount = sbeDoc.getSectionCount();
        SensorType = sbeDoc.getSensorType();
        this.Dwheel = sbeDoc.getDwheel();
        this.Dcable = sbeDoc.getDcable();
        SectionDatas = sbeDoc.getSectionDatas();
        for (int i=0;i<sbeDoc.getSectionDatas().length;i++){
           new SectionData(sbeDoc.getSectionDatas()[i]);
        }
    }

    @Data
    public class SectionData {

        public String BaseInfoId;

        public String SectionName ;//剖面名称

        public byte TestMode;//测试模式,0水平同步,1斜同步,2扇型测量

        public int XcHeight;//斜测高差, mm

        public long StartDepth;//起点深度, mm

        public short Trl;//移动方向,0:向下; 1:向上

        public float SampleInterval;//采样间隔,0.1-1638.4us

        public short SampleLength;//采样长度,0:0.5k , 1:1k , 2:2k

        public float Step;//每前进多少个脉冲记录一个测点

        public byte MoveDirect;//移动方向,0:向下; 1:向上

        public int NodesCount;//有效测点数

        public NodeData[] SectionData; //测点指针链表,序号从小到

        public SectionData(SectionData sectionData) {
            BaseInfoId = sectionData.getBaseInfoId();
            SectionName = sectionData.getSectionName();
            TestMode = sectionData.getTestMode();
            XcHeight = sectionData.getXcHeight();
            StartDepth = sectionData.getStartDepth();
            Trl = sectionData.getTrl();
            SampleInterval = sectionData.getSampleInterval();
            SampleLength = sectionData.getSampleLength();
            Step = sectionData.getStep();
            MoveDirect = sectionData.getMoveDirect();
            NodesCount = sectionData.getNodesCount();
            SectionData = sectionData.getSectionData();
            for (int i=0;i<sectionData.getSectionData().length;i++){
                new NodeData(sectionData.getSectionData()[i]);
            }
        }

        public byte getSampleLength() {
            short[] SampleLengthArray = {512, 1024, 2048, 4096, 8192};
            for (int i = 0; i < SampleLengthArray.length; i++) {
                if (SampleLength == SampleLengthArray[i]) {
                    return (byte) i;
                }
            }
            return 0;
        }

    }

    @Data
    public class NodeData {
        public String NodeId;//测点id

        public String SampleTime;//采样时间

        public float RecevHeight;//采样高度

        public float Psd;//psd

        public String Name;//测点名

        public float SampleRate;//采样间隔

        public short SampleLength;//采样长度

        public float Delay;//延迟时间（us）

        public byte TrigMode;

        public byte TriLevel;

        public short EmitVol;

        public byte EmitWidth;//0:10us  1:20us 2:25us 3:30us  4:40us

        public float Gain;

        public float Amp;//采样增益,实际大小是byte

        public int LowPass;//低通

        public int HighPass;//高通

        public byte Acc;

        public byte Offset;

        public float Time;//首波时间（us）

        public float A;//首波波幅(db)

        public float Frequency;//接收频率

        public int Trl;//测距（mm）

        public int Depth;//测点深度(mm)

        public short[] Wave;//测点数据,14位精度

        public float FirstPeakPos;//首波位置

        public NodeData(NodeData nodeData) {
            NodeId = nodeData.getNodeId();
            SampleTime = nodeData.getSampleTime();
            RecevHeight = nodeData.getRecevHeight();
            Psd = nodeData.getPsd();
            Name = nodeData.getName();
            SampleRate = nodeData.getSampleRate();
            SampleLength = nodeData.getSampleLength();
            Delay = nodeData.getDelay();
            TrigMode = nodeData.getTrigMode();
            TriLevel = nodeData.getTriLevel();
            EmitVol = nodeData.getEmitVol();
            EmitWidth = nodeData.getEmitWidth();
            Gain = nodeData.getGain();
            Amp = nodeData.getAmp();
            LowPass = nodeData.getLowPass();
            HighPass = nodeData.getHighPass();
            Acc = nodeData.getAcc();
            Offset = nodeData.getOffset();
            Time = nodeData.getTime();
            A = nodeData.getA();
            Frequency = nodeData.getFrequency();
            Trl = nodeData.getTrl();
            Depth = nodeData.getDepth();
            Wave = nodeData.getWave();
            FirstPeakPos = nodeData.getFirstPeakPos();
        }

        public byte getSampleLength() {
            short[] SampleLengthArray = {512, 1024, 2048, 4096, 8192};
            for (int i = 0; i < SampleLengthArray.length; i++) {
                if (this.SampleLength == SampleLengthArray[i]) {
                    return (byte) i;
                }
            }
            return 0;
        }

        // 增益dB转成放大倍数amp和衰减pre
        public void AmpdBToAmpPre() {
            float zoom = (float)Math.pow(10, Gain / 20.0);
            if (zoom <= 0.05) {
                Gain = 1;
                Amp = 0;
            } else if (zoom < 1) {
                Gain = 0;
                Amp = 0;
            } else if (zoom < 10) {
                Gain = 0;
                Amp = (short) (zoom / 2);
            } else if (zoom < 100) {
                Gain = 0;
                Amp = (short) (zoom / 20);
            } else if (zoom < 1000) {
                Gain = 0;
                Amp = (short) (zoom / 200);
            } else if (zoom < 10000) {
                Gain = 0;
                Amp = (short) (zoom / 2000);
            }

            if (Amp < 0)
                Amp = 0;
            else if (Amp > 15)
                Amp = 15;
        }

    }

    private final String NAME_SIGN_ST01E = "RS-ST01E";

    public static final byte EDITION_SIX = 6;


    public byte[] ToEditionSixBytes() {
        FileBytes f = new FileBytes();
        f.Save(NAME_SIGN_ST01E, 10);
        f.Save(EDITION_SIX);
        f.Save(BaseInfoId, 33);
        f.Save(SerialNo);
        f.Save(ShangGangZheng);
        f.Save(MachineId);
        f.Save(PileNo);
        f.Save(TestTime);
        f.Save(AffuseDate);
        f.Save(ConcreteStrength);
        f.Save(GpsIsValid);
        f.Save(GpsLongitude);
        f.Save(GpsLatitude);
        f.Save(PileDiameterHeight);
        f.Save((int) (PileLength * 1000));
        f.Save((int) StartDepth);
        f.Save((int) MinDepth);
        f.Save(TubeCount);
        f.Save(Angle);
        f.Save(PileType);
        f.Save((byte) (Step/10/1.25));
        f.Save(SysZeroTime);
        f.Save(SectionCount);
        f.Save(SensorType);
        f.Save(Dwheel);
        f.Save(Dcable);
        SectionData s;
        NodeData n;
        for (int i = 0; i < SectionCount; i++) {
            s = SectionDatas[i];
            f.Save(s.BaseInfoId, 33);
            f.Save(s.SectionName);
            f.Save(s.TestMode);
            f.Save(s.XcHeight);
            f.Save((int)s.StartDepth);
            f.Save(s.Trl);
            f.Save(s.SampleInterval);
            f.Save(s.getSampleLength());
            f.Save((byte)(Step/10/1.25));
            f.Save(s.MoveDirect);
            f.Save(s.NodesCount);
            for (int j = 0; j < s.NodesCount; j++) {
                n = s.SectionData[j];
                n.AmpdBToAmpPre();
                f.Save(n.NodeId, 33);
                f.Save(n.SampleTime);
                f.Save(n.RecevHeight);
                f.Save(n.Psd);
                f.Save(n.Name, 8);
                f.Save(n.SampleRate);
                f.Save(n.getSampleLength());
                f.Save(n.Delay);
                f.Save(n.TrigMode);
                f.Save(n.TriLevel);
                f.Save((byte) (n.EmitVol == 500 ? 0 : 1));
                f.Save(n.EmitWidth);
                f.Save((byte) (n.Gain == 1 ? 0 : 1));
                f.Save((byte) n.Amp);
                f.Save((byte) (n.LowPass == 60000 ? 0 : 1));
                f.Save((byte) (n.HighPass == 10 ? 0 : 1));
                f.Save(n.Acc);
                f.Save(n.Offset);
                f.Save(n.Time);
                f.Save(n.A);
                f.Save(n.Frequency);
                f.Save(n.Trl);
                f.Save(n.Depth);
                f.Save(getByteArrayByShortArray(n.Wave));
            }
        }
        return f.ToBytes();
    }


    private byte[] getByteArrayByShortArray(short[] wave) {
        boolean needDivide = false;
        short maxValue = 0;
        for (short value : wave) {
            if (value >= Byte.MAX_VALUE || value <= Byte.MIN_VALUE) {
                needDivide = true;

                if (maxValue < (short) Math.abs(value)) maxValue = (short) Math.abs(value);
            }

        }

        float scale = maxValue * 1.0F / Byte.MAX_VALUE + 0.01f;

        byte[] temp = new byte[wave.length];
        int intValue = 0;
        for (int i = 0; i < wave.length; i++) {
            if (needDivide) {
                temp[i] = (byte) (wave[i] / scale);
            } else {
                temp[i] = (byte) wave[i];
            }

        }

        return temp;
    }


}
