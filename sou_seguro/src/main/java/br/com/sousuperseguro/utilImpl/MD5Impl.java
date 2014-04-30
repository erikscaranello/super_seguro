package br.com.sousuperseguro.utilImpl;

import org.springframework.stereotype.Component;

import br.com.sousuperseguro.util.MD5;


@Component
public class MD5Impl implements MD5{

	@Override
	public String gerarMd5(String senha) {
	   try {
	        java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
	        byte[] array = md.digest(senha.getBytes());
	        StringBuffer sb = new StringBuffer();
	        for (int i = 0; i < array.length; ++i) {
	          sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
	       }
	        return sb.toString();
	    } catch (java.security.NoSuchAlgorithmException e) {
	    	return null;
	    }
	    
	}

}
