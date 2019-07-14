package com.company;

/**
 * @author tortoiselala
 */
public class ClassModifier {
    /**
     * Class文件中常量池的起始偏移
     */
    private static final int CONSTANT_POOL_COUNT_INDEX = 8;
    /**
     * CONSTANT_Utf8_info常量的tag标志
     */
    private static final int CONSTANT_UTF8_INFO = 1;

    /**
     * 常量池中11种常量所占的长度，CONSTANT_Utf8_info常量除外，因为他是不定长的
     */
    private final static int[] CONSTANT_ITEM_LENGTH = {-1, -1, -1, 5, 5, 9, 9, 3, 3, 5, 5, 5, 5};

    public  final static int U1 = 1;
    public final static int U2 = 2;

    private byte[] classByte;

    public ClassModifier(byte[] classByte) {
        this.classByte = classByte;
    }

    public int getConstantPoolCount() {
        return ByteUtils.bytes2Int(classByte, CONSTANT_POOL_COUNT_INDEX, U2);
    }

    public byte[] modifyUtf8Constant(String oldStr, String newStr){
        int cpc = getConstantPoolCount();
        int offset = CONSTANT_POOL_COUNT_INDEX + U2;
        for(int i = 0; i < cpc; ++i) {
            int tag = ByteUtils.bytes2Int(classByte, offset, U1);
            if (tag == CONSTANT_UTF8_INFO) {
                int len = ByteUtils.bytes2Int(classByte, offset + U1, U2);
                offset += (U1 + U2);
                String str = ByteUtils.bytes2String(classByte, offset, len);
                if (str.equalsIgnoreCase(oldStr)) {
                    byte[] strBytes = ByteUtils.string2Bytes(newStr);
                    byte[] strLen = ByteUtils.int2Bytes(newStr.length(), U2);

                    classByte = ByteUtils.byteReplace(classByte, offset - U2, U2, strLen);
                    classByte = ByteUtils.byteReplace(classByte, offset, len, strBytes);
                    return classByte;
                } else {
                    offset += len;
                }
            }else{
                offset += CONSTANT_ITEM_LENGTH[tag];
            }
        }
        return classByte;
    }
}
