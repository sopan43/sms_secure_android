# sms_secure_android
download apk  https://drive.google.com/open?id=0B0BshktCLzxoaE9WYzU0MjROMzg

This project is an attempt to use Android 5.1 to make Sms Encryption and Decryption.
The following example uses symmetric key for encryption and decryption. “Data Encryption Standard (DES)”.
DESede is a triple DES and a stronger variant of DES.
1.	Add the Security Provider. We are using the SunJCE Provider that is available with the JDK.
2.	Generate Secret Key. Use KeyGenerator and an algorithm to generate a secret key. We are using DESede. There are other algorithms like blowfish.
3.	Encode Text. For consistency across platform encode the plain text as byte using UTF-8 encoding.
4.	Encrypt Text. Instantiate Cipher with ENCRYPT_MODE, use the secret key and encrypt the bytes.
5.	Decrypt Text. Instantiate Cipher with DECRYPT_MODE, use the same secret key and decrypt the bytes.
