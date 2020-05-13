package com.xinmintx.factory.api.util;

import cfca.util.CertUtil;
import cfca.util.KeyUtil;
import cfca.util.cipher.lib.JCrypto;
import cfca.util.cipher.lib.Session;
import cfca.x509.certificate.X509Cert;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.nio.charset.Charset;
import java.security.PrivateKey;
import java.util.Properties;

public class ConfigInfo {
	private static String url;
	private static String merchantId;
	private static String versionId;
	private static String charset;
	private static String sendMsg;
	private static String signature;
	private static String signType;
	private static String md5Key;
    private static String password;
    private static String keyFile;
	private static Properties properties = new Properties();
	private static String defenc = Charset.defaultCharset().displayName();
	private static HttpURLConnection conn;
	public  static Session session;
	public  static PrivateKey privateKey;
	public  static X509Cert X509Cert;
	private static String secretKeyFile;

	public static String merchantmerchantWechatPay;
	public static String merchantmerchantTransQuery;
	public static String merchantmerchantRefund;
	public static String merchantWebOnlinePay;
	public static String merchantScanCodePay;
	public static String merchantSwtSmPay;
	public static String merchantNoCardConsume;
	public static String merchantSinglePayQuery;
	public static String merchantAppSmsCode;
	public static String singleDebit;

	public static String getMerchantAppSmsCode() {
		return merchantAppSmsCode;
	}
	public static void setMerchantAppSmsCode(String merchantAppSmsCode) {
		ConfigInfo.merchantAppSmsCode = merchantAppSmsCode;
	}
	public static String getMerchantNoCardConsume() {
		return merchantNoCardConsume;
	}
	public static String getMerchantSinglePayQuery() {
		return merchantSinglePayQuery;
	}

	public static void setMerchantSinglePayQuery(String merchantSinglePayQuery) {
		ConfigInfo.merchantSinglePayQuery = merchantSinglePayQuery;
	}
	public static void setMerchantNoCardConsume(String merchantNoCardConsume) {
		ConfigInfo.merchantNoCardConsume = merchantNoCardConsume;
	}

	public static String getMerchantSwtSmPay() {
		return merchantSwtSmPay;
	}

	public static void setMerchantSwtSmPay(String merchantSwtSmPay) {
		ConfigInfo.merchantSwtSmPay = merchantSwtSmPay;
	}

	static {
		ClassLoader cl = Thread.currentThread().getContextClassLoader();
		try {
			InputStream config = cl.getResourceAsStream("payConfig.properties");
			properties.load(config);
			config.close();

			url = properties.getProperty("connSvr");
			merchantId = properties.getProperty("merchantId");
			versionId = properties.getProperty("versionId");
			charset = properties.getProperty("charset");
			signType = properties.getProperty("signType");
			md5Key = properties.getProperty("md5Key");
			merchantmerchantWechatPay = properties.getProperty("merchantmerchantWechatPay");
			merchantmerchantTransQuery = properties.getProperty("merchantmerchantTransQuery");
			merchantmerchantRefund = properties.getProperty("merchantmerchantRefund");
			merchantWebOnlinePay = properties.getProperty("MerchantWebOnlinePay");
			merchantScanCodePay = properties.getProperty("MerchantScanCodePay");
			merchantSwtSmPay = properties.getProperty("MerchantSwtSmPay");
			merchantNoCardConsume = properties.getProperty("MerchantNoCardConsume");
			merchantSinglePayQuery = properties.getProperty("MerchantSinglePayQuery");
			merchantAppSmsCode = properties.getProperty("merchantAppSmsCode");
			singleDebit = properties.getProperty("singleDebit");
			secretKeyFile = properties.getProperty("secretKeyFile");
			if (properties.getProperty("keyFile") != ""){
				keyFile = properties.getProperty("keyFile");
		        password = properties.getProperty("password");
		        if("ZJCA".equals(signType)){
		        	CAUtil.loadPrivPFX(properties.getProperty("keyFile"),
							properties.getProperty("password"));

					CAUtil.loadCertificate(properties.getProperty("keyFile"),
					          properties.getProperty("password"));
		        }else if("CFCA".equals(signType)){
		        	JCrypto.getInstance().initialize("JSOFT_LIB", null);
					session = JCrypto.getInstance().openSession("JSOFT_LIB");
			    	privateKey = KeyUtil.getPrivateKeyFromPFX(keyFile, password);
			    	X509Cert = CertUtil.getCertFromPfx(keyFile, password);
		        }
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getSecretKeyFile() {
		return secretKeyFile;
	}

	public static void setSecretKeyFile(String secretKeyFile) {
		ConfigInfo.secretKeyFile = secretKeyFile;
	}

	public static String getMerchantScanCodePay() {
		return merchantScanCodePay;
	}

	public static void setMerchantScanCodePay(String merchantScanCodePay) {
		ConfigInfo.merchantScanCodePay = merchantScanCodePay;
	}

	public static String getMerchantWebOnlinePay() {
		return merchantWebOnlinePay;
	}

	public static void setMerchantWebOnlinePay(String merchantWebOnlinePay) {
		ConfigInfo.merchantWebOnlinePay = merchantWebOnlinePay;
	}

	public static String getMerchantmerchantWechatPay() {
		return merchantmerchantWechatPay;
	}

	public static void setMerchantmerchantWechatPay(String merchantmerchantWechatPay) {
		ConfigInfo.merchantmerchantWechatPay = merchantmerchantWechatPay;
	}

	public static String getMerchantmerchantTransQuery() {
		return merchantmerchantTransQuery;
	}

	public static void setMerchantmerchantTransQuery(
			String merchantmerchantTransQuery) {
		ConfigInfo.merchantmerchantTransQuery = merchantmerchantTransQuery;
	}

	public static String getMerchantmerchantRefund() {
		return merchantmerchantRefund;
	}

	public static void setMerchantmerchantRefund(String merchantmerchantRefund) {
		ConfigInfo.merchantmerchantRefund = merchantmerchantRefund;
	}

	public static String getUrl() {
		return url;
	}

	public static void setUrl(String url) {
		ConfigInfo.url = url;
	}

	public static String getMerchantId() {
		return merchantId;
	}

	public static void setMerchantId(String merchantId) {
		ConfigInfo.merchantId = merchantId;
	}

	public static String getVersionId() {
		return versionId;
	}

	public static void setVersionId(String versionId) {
		ConfigInfo.versionId = versionId;
	}

	public static String getCharset() {
		return charset;
	}

	public static void setCharset(String charset) {
		ConfigInfo.charset = charset;
	}

	public static String getSendMsg() {
		return sendMsg;
	}

	public static void setSendMsg(String sendMsg) {
		ConfigInfo.sendMsg = sendMsg;
	}

	public static String getSignature() {
		return signature;
	}

	public static void setSignature(String signature) {
		ConfigInfo.signature = signature;
	}

	public static String getSignType() {
		return signType;
	}

	public static void setSignType(String signType) {
		ConfigInfo.signType = signType;
	}

	public static String getMd5Key() {
		return md5Key;
	}

	public static void setMd5Key(String md5Key) {
		ConfigInfo.md5Key = md5Key;
	}

	public static Properties getProperties() {
		return properties;
	}

	public static void setProperties(Properties properties) {
		ConfigInfo.properties = properties;
	}

	public static String getDefenc() {
		return defenc;
	}

	public static void setDefenc(String defenc) {
		ConfigInfo.defenc = defenc;
	}

	public static HttpURLConnection getConn() {
		return conn;
	}

	public static void setConn(HttpURLConnection conn) {
		ConfigInfo.conn = conn;
	}

	public static String getPassword() {
		return password;
	}

	public static void setPassword(String password) {
		ConfigInfo.password = password;
	}

	public static String getKeyFile() {
		return keyFile;
	}

	public static void setKeyFile(String keyFile) {
		ConfigInfo.keyFile = keyFile;
	}

	public static Session getSession() {
		return session;
	}

	public static void setSession(Session session) {
		ConfigInfo.session = session;
	}
	
	public static PrivateKey getPrivateKey() {
		return privateKey;
	}

	public static void setPrivateKey(PrivateKey privateKey) {
		ConfigInfo.privateKey = privateKey;
	}

	public static X509Cert getX509Cert() {
		return X509Cert;
	}

	public static void setX509Cert(X509Cert x509Cert) {
		X509Cert = x509Cert;
	}
	public static String getSingleDebit() {
		return singleDebit;
	}
	public static void setSingleDebit(String singleDebit) {
		ConfigInfo.singleDebit = singleDebit;
	}
}