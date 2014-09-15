package com.my.others;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.my.f.R;

public class SignatureTest extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mytext);
		TextView tx = (TextView) findViewById(R.id.TextView01);
		tx.setText(getSignature(this));
	}

	// public String getSignature(Context context) {
	// PackageManager pm = context.getPackageManager();
	// int flags = PackageManager.GET_SIGNATURES;
	// try {
	// PackageInfo pi = pm.getPackageInfo("com.yearwifi.yeahwifitool",
	// flags);
	// Signature[] signatures = pi.signatures;
	// byte[] cert = signatures[0].toByteArray();
	// InputStream input = new ByteArrayInputStream(cert);
	//
	// CertificateFactory cf = null;
	// cf = CertificateFactory.getInstance("X509");
	// X509Certificate c = null;
	// c = (X509Certificate) cf.generateCertificate(input);
	// MessageDigest md = MessageDigest.getInstance("SHA1");
	// byte[] publicKey = md.digest(c.getPublicKey().getEncoded());
	// StringBuffer hexString = new StringBuffer();
	// for (int i = 0; i < publicKey.length; i++) {
	// String appendString = Integer.toHexString(0xFF & publicKey[i]);
	// if (appendString.length() == 1)
	// hexString.append("0");
	// hexString.append(appendString);
	// }
	// Log.d("Example", "Cer: " + hexString.toString());
	// return hexString.toString();
	//
	// } catch (NameNotFoundException e) {
	// e.printStackTrace();
	// } catch (CertificateException e) {
	// e.printStackTrace();
	// } catch (NoSuchAlgorithmException e) {
	// e.printStackTrace();
	// }
	// return null;
	//
	// }

	public String getSignature(Context context) {
		PackageManager pm = context.getPackageManager();
		int flags = PackageManager.GET_SIGNATURES;
		try {
			PackageInfo pi = pm.getPackageInfo("com.yearwifi.yeahwifitool",
					flags);
			Signature[] signatures = pi.signatures;
			Log.v("signatures.length", signatures.length + "");

			byte[] cert = signatures[0].toByteArray();
			InputStream input = new ByteArrayInputStream(cert);

			CertificateFactory cf = null;
			cf = CertificateFactory.getInstance("X509");
			X509Certificate c = null;
			c = (X509Certificate) cf.generateCertificate(input);
			MessageDigest md = MessageDigest.getInstance("SHA1");
			byte[] publicKey = md.digest(c.getPublicKey().getEncoded());
			StringBuffer hexString = new StringBuffer();
			for (int j = 0; j < publicKey.length; j++) {
				String appendString = Integer.toHexString(0xff & publicKey[j]);
				if (appendString.length() == 1)
					hexString.append("0");
				hexString.append(appendString);
			}
			Log.d("Example", "Cer: " + hexString.toString());
			return hexString.toString();

		} catch (NameNotFoundException e) {
			e.printStackTrace();
		} catch (CertificateException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;

	}
}
