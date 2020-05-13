package com.xinmintx.agent.utilAgent;

import cfca.org.bouncycastle.jce.provider.BouncyCastleProvider;
import cfca.util.Base64;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.security.KeyStore;
import java.security.Security;
import java.security.Signature;
import java.security.SignatureException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPrivateKey;
import java.util.Enumeration;

public class CAUtil
{
  private static RSAPrivateKey privKey;
  private static X509Certificate certificate;

  static
  {
    Security.addProvider(new BouncyCastleProvider());
  }

  public static RSAPrivateKey loadPrivPFX(String pfx, String password)
    throws Exception
  {
    KeyStore store = KeyStore.getInstance("PKCS12");
    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
    URL url = classLoader.getResource(pfx);
    InputStream is = null;
    if (url != null)
      try {
        is = new FileInputStream(url.getFile());
      } catch (FileNotFoundException e) {
        is = classLoader.getResourceAsStream(pfx);
      }
    else {
      is = new FileInputStream(pfx);
    }
    store.load(is, password.toCharArray());
    is.close();
    String keyAlias = null;
    Enumeration e = store.aliases();
    if (e.hasMoreElements())
      keyAlias = (String)e.nextElement();
    else
      return null;
    privKey = (RSAPrivateKey)store.getKey(keyAlias, password.toCharArray());
    System.out.println("privKey:" + privKey);
    return privKey;
  }

  public static X509Certificate loadCertificate(String pfx, String password) throws Exception
  {
    KeyStore store = KeyStore.getInstance("PKCS12");
    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
    URL url = classLoader.getResource(pfx);
    InputStream is = null;
    if (url != null)
      try {
        is = new FileInputStream(url.getFile());
      } catch (FileNotFoundException e) {
        is = classLoader.getResourceAsStream(pfx);
      }
    else {
      is = new FileInputStream(pfx);
    }
    store.load(is, password.toCharArray());
    is.close();
    String keyAlias = null;
    Enumeration e = store.aliases();
    if (e.hasMoreElements())
      keyAlias = (String)e.nextElement();
    else
      return null;
    certificate = (X509Certificate)store.getCertificate(keyAlias);
    return certificate;
  }

  public static X509Certificate loadCertificate(String pem) throws Exception
  {
    CertificateFactory factory = CertificateFactory.getInstance("X.509");
    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
    URL url = classLoader.getResource(pem);
    InputStream is = null;
    if (url != null)
      try {
        is = new FileInputStream(url.getFile());
      } catch (FileNotFoundException e) {
        is = classLoader.getResourceAsStream(pem);
      }
    else {
      is = new FileInputStream(pem);
    }
    certificate = (X509Certificate)factory.generateCertificate(is);
    is.close();
    return certificate;
  }

  public static byte[] sign(byte[] data) throws Exception {
    Signature sig = Signature.getInstance("SHA1WithRSA");

    System.out.println("privKey:" + privKey);
    sig.initSign(privKey);
    System.out.println("-->data:" + new String(data));
    sig.update(data);
    byte[] b = sig.sign();
    System.out.println("--->sig:" + new String(b));
    System.out.println("===>sig:" + new String(Base64.encode(b)));
    return Base64.encode(b);
  }

  public static boolean verify(byte[] data, byte[] signData) throws Exception
  {
    Signature sig = Signature.getInstance("SHA1WithRSA");
    sig.initVerify(certificate);
    sig.update(data);
    byte[] b = Base64.decode(signData);
    try {
      return sig.verify(b);
    } catch (SignatureException e) {
      e.printStackTrace();
    }return false;
  }
}