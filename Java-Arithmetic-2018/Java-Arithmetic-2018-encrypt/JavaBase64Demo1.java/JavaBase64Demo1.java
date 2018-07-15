// JavaBase64Demo1.java
import javax.crypto.*;
import java.security.*;
import java.util.Base64;

class JavaBase64Demo1{
	public final String _keyStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=";
	public final Base64.Encoder b64encoder = Base64.getEncoder();	// Java 1.8 之后支持

	public static void main(String[] args){
		JavaBase64Demo1 jbd = new JavaBase64Demo1();

		String strSource = "go-base64-demo-1.go\n程序中书写着所见所闻所感，编译着心中的万水千山。";;
		byte[] data = jbd.getBytes(strSource);

		String strUserEncrypt = jbd.Base64Encode(strSource);
		System.out.println("JavaBase64Demo1.java");
		log( jbd.b64encoder.encodeToString( data ) );
		log( strUserEncrypt );
	}

	public byte[] getBytes(String s){
		try{
			return s.getBytes("UTF-8");
		}catch(Exception ex){}
		return null;
	}

	public String Base64Encode(String s){
		byte[] data = getBytes(s);
		int nCount = data.length;
		byte chr1 = 0, chr2 = 0, chr3 = 0;
		int enc1 = 0, enc2 = 0, enc3 = 0, enc4 = 0;
		int i = 0;
		StringBuilder sb = new StringBuilder();
		while(i < nCount){
			chr1 = data[i++];
			enc1 = chr1 >> 2 & 63;
			if(i < nCount){
				chr2 = data[i++];
				enc2 = ((chr1 & 3) << 4) | ((chr2 & 0xf0) >> 4 & 15);
				if(i < nCount){
					chr3 = data[i++];
					enc3 = ((chr2 & 15) << 2) | ((chr3 & 0xc0) >> 6 & 3);
					enc4 = chr3 & 63;
				}else{
					enc3 = (chr2 & 15) << 2;
					enc4 = 64;
				}
			}else{
				enc2 = (chr1 & 3) << 4;
				enc3 = enc4 = 64;
			}
			log(chr1);
			log(enc1);
			sb.append(_keyStr.charAt(enc1));
			sb.append(_keyStr.charAt(enc2));
			sb.append(_keyStr.charAt(enc3));
			sb.append(_keyStr.charAt(enc4));
		}
		return sb.toString();
	}

	public static void log(Object o){
		System.out.println(o);
	}
}

/*

Z28tYmFzZTY0LWRlbW8tMS5nbwrnqIvluo/kuK3kuablhpnnnYDmiYDop4HmiYDpl7vmiYDmhJ/vvIznvJbor5HnnYDlv4PkuK3nmoTkuIfmsLTljYPlsbHjgII=
Z28tYmFzZTY0LWRlbW8tMS5nbwrnqIvluo/kuK3kuablhpnnnYDmiYDop4HmiYDpl7vmiYDmhJ/vvIznvJbor5HnnYDlv4PkuK3nmoTkuIfmsLTljYPlsbHjgII=


*/