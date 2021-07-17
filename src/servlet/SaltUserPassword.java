package servlet;

public class SaltUserPassword {

	// ソルト：　適当な文字列
	 String SALT = "9b7a8d5fb348bd4949536cdc5b8d30c426b80d2fe30c5c308e2cdec422ae3244";
	    public String getDigest(String before1,String before2) {

	        String after = null;
	        ToSHA2 toSha2 = new ToSHA2();

	        String con = SALT + toSha2.getDigest(before1) + toSha2.getDigest(before2);

	        //SHA256でハッシュ
	        after = toSha2.getDigest(con);


	        return after;
	    }

	}
