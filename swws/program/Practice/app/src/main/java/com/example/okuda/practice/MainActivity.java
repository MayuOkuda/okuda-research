package com.example.okuda.practice;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


        Button noButton = (Button) findViewById(R.id.no);
        noButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              finish();
            }
        });


    }

    public void sendMail(View view) {
        //final String email = "often0.ch@gmail.com";
        final String email = "okuda@pl.info.kochi-tech.ac.jp";
        final String password = "cy9mrskats";
        final String user = "okuda";
        String body = "これがメールの本文になります";
        String subject = "これがメールの件名になります";
        //String server = "smtp.gmail.com";
        String server = "pl.info.kochi-tech.ac.jp";
        //String server = "222.229.69.56";
        String port = "465";

        try {
            //email と password更新
            SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
            sp.edit().putString("email", email).commit();
            sp.edit().putString("password", password).commit();

            //以下メール送信
            final Properties property = new Properties();
            property.put("mail.smtp.host", server);
            property.put("mail.host", server);
            property.put("mail.smtp.port", port);
            property.put("mail.smtp.socketFactory.port", port);
            property.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            property.put("mail.smtp.socketFactory.fallback", "false");
            property.put("mail.debug", "true");
            property.put("mail.smtp.auth", "true");

            // セッション
            final Session session = Session.getInstance(property, new javax.mail.Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(user, password);
                }
            });

            MimeMessage mimeMsg = new MimeMessage(session);

            mimeMsg.setSubject(subject, "utf-8");
            mimeMsg.setFrom(new InternetAddress(email));
            mimeMsg.setRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(email));


            final MimeBodyPart txtPart = new MimeBodyPart();
            txtPart.setText(body, "utf-8");

             /* 添付ファイルをする場合はこれを使う
            final MimeBodyPart filePart = new MimeBodyPart();
            File file = new File("[添付ファイルパス]");
            FileDataSource fds = new FileDataSource(file);
            DataHandler data = new DataHandler(fds);
            filePart.setDataHandler(data);
            filePart.setFileName(MimeUtility.encodeWord("[メール本文の添付ファイル名]")); */


            final Multipart mp = new MimeMultipart();
            //mp.addBodyPart(txtPart);
            //mp.addBodyPart(filePart); //添付ファイルをする場合はこれ
            mimeMsg.setContent(mp);

            // メール送信する。
            final Transport transport = session.getTransport("smtps");
            transport.connect(user, password);
            transport.sendMessage(mimeMsg, mimeMsg.getAllRecipients());
            transport.close();

        } catch (MessagingException e) {
            System.out.println("exception = " + e);

        } /*catch (UnsupportedEncodingException e) {
            必要あるのか不明
        }*/ finally {
            System.out.println("finish sending email");
        }
    }
}