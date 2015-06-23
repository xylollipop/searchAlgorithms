import java.security.MessageDigest;

/**
 * Created by xylollipop on 2015/6/22.
 * Copyleft
 */

public class ShortURL {
    public static String[] chars = new String[]{          //62个生成URL的字符
            "a","b","c","d","e","f","g","h",
            "i","j","k","l","m","n","o","p",
            "q","r","s","t","u","v","w","x",
            "y","z", "0","1","2","3","4","5",
            "6","7","8","9","A","B","C","D",
            "E","F","G","H","I","J","K","L",
            "M","N","O","P","Q","R","S","T",
            "U","V", "W","X","Y","Z"
    };

    private final static String[] hexNum = {  //16进制数字字母
            "0","1","2","3","4","5","6","7",
            "8","9","a","b","c","d","e","f"
    };

    private String key = "xyMiracle"; //加入算法混用的key

    public static String[] MD5Code(String t){
        String s = new String(t);
        byte[] media = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            s.trim();
            media = md.digest(s.getBytes("UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        String hexGet = arrayToHex(media);
        int len = hexGet.length();
        int count = len/8;
        String[] shortStr = new String[4];
        for(int i=0;i<count;i++){
            String out = "";
            int j=i+1;
            String subHex = hexGet.substring(i*8,j*8);
            long id = Long.valueOf("3FFFFFFF",16)& Long.valueOf(subHex,16);
            for(int k =0;k<6;k++){        //chars数组里共有62个  16进制即3D 0到61  需要6位即可
                int indexNum = (int)(Long.valueOf("0000003D",16)&id);
                out = out+chars[indexNum];
                id = id>>5;      //每次右移5位，共30位，与3FFFFF相与就是为了只考虑30位有效位
            }

            shortStr[i] = out;
        }

        return shortStr;

    }


    public static String arrayToHex(byte[] bytes){
        StringBuffer bf = new StringBuffer();
        String x = null;
        int n;
        for(int i=0;i<bytes.length;i++){
            n = bytes[i];
            if(n<0)
                n = n+256;           //一个字节8位  2^8=256
            x = hexNum[n/16]+hexNum[n%16];
            bf.append(x);
        }
        return bf.toString();

    }



    public static void main(String[] args){
        String url = "http://img5q.duitang.com/uploads/item/201408/05/20140805203226_4tJwj.jpeg";
        String[] result = MD5Code(url);
        for(String s:result)
            System.out.print(s+" ");

    }


}