package BlockChainSim;

import java.util.ArrayList;
import java.util.List;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MerkleTree extends SumTree<String>{

	public MerkleTree(String[] leafs, String init) {
		super(String[].class, leafs, init);
		// TODO Auto-generated constructor stub
	}

	private String getSHA256(String str){
		String SHA = "";
		try{
		MessageDigest sh = MessageDigest.getInstance("SHA-256");
		sh.update(str.getBytes());
		byte byteData[] = sh.digest();
		StringBuffer sb = new StringBuffer();
		for(int i = 0 ; i < byteData.length ; i++){
		sb.append(Integer.toString((byteData[i]&0xff) + 0x100, 16).substring(1));
		}
		SHA = sb.toString();
		}catch(NoSuchAlgorithmException e){
		e.printStackTrace();
		SHA = null;
		}
		return SHA;
		}

	@Override
	public String add(String a, String b) {
		// TODO Auto-generated method stub
		return Utils.getSHA256(a+b);
	}
}
