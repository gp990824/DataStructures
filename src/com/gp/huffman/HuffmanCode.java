package com.gp.huffman;

import java.util.*;

/**
 * @author gp
 * @create 2020/1/6 17:57
 */
//赫夫曼编码(哈夫曼编码) 利用赫夫曼数将数据进行编码,前缀编码
public class HuffmanCode {
    //将byte类型转成二进制形式的字符串
    public static String byteToString(boolean flag, byte b) {

        int temp = b;
        //如果是正数,还需要补高位,如果是最后一个字节无需补高位
        if (flag) {
            temp |= 256;
        }
        String str = Integer.toBinaryString(temp);//返回的是temp对应的二进制补码
        if (flag || temp < 0) {
            return str.substring(str.length() - 8);//
        } else {
            return str;
        }

    }

    /**
     * 哈夫曼解码
     *
     * @param map 存储每个字符对应的哈夫曼编码的hashmap
     * @param bs  经过哈夫曼编码后压缩成的字节数组
     * @return
     */
    public static byte[] decode(Map<Byte, String> map, byte[] bs) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < bs.length; i++) {
            boolean flag = (i == bs.length - 1);//如果是最后一个字节,则为true
            stringBuilder.append(byteToString(!flag, bs[i]));//不是最后 一个字节就取反,不用补高位
        }
//        System.out.println(stringBuilder.toString());
        //反转传进来的hashmap
        List<Byte> bytes = new ArrayList<>();
        Map<String, Byte> inverseMap = new HashMap<>();
        for (Map.Entry<Byte, String> entry : map.entrySet()) {
            inverseMap.put(entry.getValue(), entry.getKey());
        }
//        System.out.println(inverseMap);
        for (int i = 0; i < stringBuilder.length();) {
            int count = 1;
            Byte b = null;
            while (true) {
                String str = stringBuilder.substring(i, i + count);
                b = inverseMap.get(str);
                if (b == null) {//如果从反转后的hashmap中没有匹配到改二进制字符串对应的value,则使count++,继续截取
                    count++;
                } else {
                    break;
                }
            }
            i += count;
            bytes.add(b);
        }

        byte[] bs2 = new byte[bytes.size()];
        for (int i = 0; i < bs2.length; i++) {
            bs2[i] = bytes.get(i);
        }
        return bs2;
    }

    public static void main(String[] args) {
        String str = "i like like like java do you like a java";
//        System.out.println(putList(str));
//        huffmanTree(putList(str)).preOrder();
//        getHuffmanCode(huffmanTree(putList(str)),"",stringBuilder);
//        System.out.println(getHuffmanCode(str));
//        System.out.println(Arrays.toString(huffmanCode(str)));
        Map<Byte, String> map = getMap(str);//获取每一字符对应的哈夫曼编码的hashmap
        byte[] bytes = huffmanCode(str);//哈夫曼编码压缩成的字节数组

        System.out.println(new String(decode(map, bytes)));
    }

    //封装成一个方法
    public static byte[] huffmanCode(String str) {
        return parseByte(codeString(getMap(str), str.getBytes()));
    }

    //将数据放入一个list集合中
    public static List<NodeCode> putList(String str) {
        byte[] bytes = str.getBytes();
        List<NodeCode> list = new ArrayList<>();
        Map<Byte, Integer> map = new HashMap<>();
        for (byte b : bytes) {
            Integer i = map.get(b);//查看数据为b的value是否为空
            if (i == null) {
                map.put(b, 1);
            } else {
                map.put(b, i + 1);
            }
        }

        for (Map.Entry<Byte, Integer> entry : map.entrySet()) {//遍历map集合
            list.add(new NodeCode(entry.getKey(), entry.getValue()));
        }

        return list;
    }

    //生成赫夫曼数,返回该赫夫曼数的根节点
    public static NodeCode huffmanTree(List<NodeCode> list) {

        while (list.size() > 1) {

            //排序集合中的元素
            Collections.sort(list);
            NodeCode leftNode = list.get(0);
            NodeCode rightNode = list.get(1);
            NodeCode root = new NodeCode(null, leftNode.times + rightNode.times);
            root.left = leftNode;
            root.right = rightNode;
            list.remove(leftNode);
            list.remove(rightNode);
            list.add(root);
            Collections.sort(list);

        }
        return list.get(0);

    }

    public static Map<Byte, String> getMap(String str) {
        //将每个字符转成单一的哈夫曼编码并存放到hashmap中
        getHuffmanCode(huffmanTree(putList(str)), "", stringBuilder);
        return codeMap;
    }

    static Map<Byte, String> codeMap = new HashMap<>();
    static StringBuilder stringBuilder = new StringBuilder();

    /**
     * @param node          哈夫曼树的根节点
     * @param code          向左编码为0,向右编码为1
     * @param stringBuilder 用于拼接某个数据的编码
     */
    public static void getHuffmanCode(NodeCode node, String code, StringBuilder stringBuilder) {

        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
        stringBuilder2.append(code);
        if (node != null) {
            //如果是非叶子节点,递归处理
            if (node.data == null) {
                getHuffmanCode(node.left, "0", stringBuilder2);
                getHuffmanCode(node.right, "1", stringBuilder2);
            } else {
                //已找到叶子节点
                codeMap.put(node.data, stringBuilder2.toString());//放入hashmap中
            }
        }
    }


    /**
     * @param map 根据存放编码的hashmap,将数据进行编码
     * @param bs  字符串数据转换成的byet数组
     * @return
     */
    public static String codeString(Map<Byte, String> map, byte[] bs) {
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : bs) {
            stringBuilder.append(map.get(b));
        }
        return stringBuilder.toString();
    }

    //将二进制字符串转成byte数组
    public static byte[] parseByte(String str) {
        int index = 0;
        int len = (str.length() + 7) / 8;
        byte[] bytes = new byte[len];
        for (int i = 0; i < str.length(); i += 8) {
            if (i + 8 > str.length()) {//最后的字符串不够八位
                bytes[index] = (byte) Integer.parseInt(str.substring(i), 2);
            } else {
                bytes[index] = (byte) Integer.parseInt(str.substring(i, i + 8), 2);
            }
            index++;
        }
        return bytes;
    }
}

class NodeCode implements Comparable<NodeCode> {
    Byte data;//存放该数据
    int times;//表示该数组出现的次数
    NodeCode left;
    NodeCode right;

    public NodeCode(Byte data, int times) {
        this.data = data;
        this.times = times;
    }

    @Override
    public String toString() {
        return "NodeCode{" +
                "data=" + data +
                ", times=" + times +
                '}';
    }

    //前序遍历
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }


    @Override
    public int compareTo(NodeCode o) {
        return this.times - o.times;
    }
}
