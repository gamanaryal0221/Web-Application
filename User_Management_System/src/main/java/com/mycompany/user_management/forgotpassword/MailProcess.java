/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.user_management.forgotpassword;

import com.mycompany.user_management.registration.RegistrationDatabaseModel;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Asus
 */
public class MailProcess {

    String MailReceiverID;
    String hostname;
    String port;
    String str = "";
    String Mail_Subject;
    

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public void setMailReceiverID(String MailReceiverID) {
        this.MailReceiverID = MailReceiverID;
    }
    
    public String getMailReceiverID() {
        return MailReceiverID;
    }

    public boolean emailDoesExist() {
        Boolean checked = false;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/coursework?serverTimezone=UTC", "root", "");
            String Sql_Query = "select * from users where email = ?";

            PreparedStatement Pre_Stat = conn.prepareStatement(Sql_Query);
            Pre_Stat.setString(1, MailReceiverID);
            ResultSet r1 = Pre_Stat.executeQuery();

            if (r1.next()) {
                checked = true;
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(RegistrationDatabaseModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return checked;
    }

    public String generateCode() throws ClassNotFoundException, SQLException {
        Random r = new Random();
        int min = 100000000;
        int max = 1000000000;
        int res = r.nextInt(max - min) + min;

        for (int i = 0; i <= 4; i++) {
            //checks that the last two digits fall under the ASCII number
            int remainder = res % 100;
            if ((remainder >= 63) && (remainder <= 90) || (remainder >= 35) && (remainder <= 38) || (remainder >= 97) && (remainder <= 122)) {

                str = Character.toString((char) remainder) + str;
            } else {
                str = Integer.toString(remainder) + str;
            }
            res = (res - (remainder % 10)) / 100;
        }

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/coursework?serverTimezone=UTC", "root", "");

        String Sql_Query = "update resetpasswordrecord set code=? , email=?  where serial=1";
        PreparedStatement Pre_Stat = conn.prepareStatement(Sql_Query);
        Pre_Stat.setString(1, str);
        Pre_Stat.setString(2, MailReceiverID);
        Pre_Stat.executeUpdate();
        conn.close();

        Mail_Subject = "Reset Your Password   //   Your Code is :- " + str;

        return str;
    }

    String Mail_Content = "<!DOCTYPE html>\n"
            + "<html>\n"
            + "<head>\n"
            + "\n"
            + "  <meta charset=\"utf-8\">\n"
            + "  <meta http-equiv=\"x-ua-compatible\" content=\"ie=edge\">\n"
            + "  <title>Password Reset</title>\n"
            + "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n"
            + "  <style type=\"text/css\">\n"
            + "  @media screen {\n"
            + "    @font-face {\n"
            + "      font-family: 'Source Sans Pro';\n"
            + "      font-style: normal;\n"
            + "      font-weight: 400;\n"
            + "      src: local('Source Sans Pro Regular'), local('SourceSansPro-Regular'), url(https://fonts.gstatic.com/s/sourcesanspro/v10/ODelI1aHBYDBqgeIAH2zlBM0YzuT7MdOe03otPbuUS0.woff) format('woff');\n"
            + "    }\n"
            + "\n"
            + "    @font-face {\n"
            + "      font-family: 'Source Sans Pro';\n"
            + "      font-style: normal;\n"
            + "      font-weight: 700;\n"
            + "      src: local('Source Sans Pro Bold'), local('SourceSansPro-Bold'), url(https://fonts.gstatic.com/s/sourcesanspro/v10/toadOcfmlt9b38dHJxOBGFkQc6VGVFSmCnC_l7QZG60.woff) format('woff');\n"
            + "    }\n"
            + "  }\n"
            + "\n"
            + "  /**\n"
            + "   * Avoid browser level font resizing.\n"
            + "   * 1. Windows Mobile\n"
            + "   * 2. iOS / OSX\n"
            + "   */\n"
            + "  body,\n"
            + "  table,\n"
            + "  td,\n"
            + "  a {\n"
            + "    -ms-text-size-adjust: 100%; /* 1 */\n"
            + "    -webkit-text-size-adjust: 100%; /* 2 */\n"
            + "  }\n"
            + "  table,\n"
            + "  td {\n"
            + "    mso-table-rspace: 0pt;\n"
            + "    mso-table-lspace: 0pt;\n"
            + "  }\n"
            + "\n"
            + "  /**\n"
            + "   * Better fluid images in Internet Explorer.\n"
            + "   */\n"
            + "  img {\n"
            + "    -ms-interpolation-mode: bicubic;\n"
            + "  }\n"
            + "  a[x-apple-data-detectors] {\n"
            + "    font-family: inherit !important;\n"
            + "    font-size: inherit !important;\n"
            + "    font-weight: inherit !important;\n"
            + "    line-height: inherit !important;\n"
            + "    color: inherit !important;\n"
            + "    text-decoration: none !important;\n"
            + "  }\n"
            + "\n"
            + "  div[style*=\"margin: 16px 0;\"] {\n"
            + "    margin: 0 !important;\n"
            + "  }\n"
            + "\n"
            + "  body {\n"
            + "    width: 100% !important;\n"
            + "    height: 100% !important;\n"
            + "    padding: 0 !important;\n"
            + "    margin: 0 !important;\n"
            + "  }\n"
            + "  table {\n"
            + "    border-collapse: collapse !important;\n"
            + "  }\n"
            + "\n"
            + "  a {\n"
            + "    color: #1a82e2;\n"
            + "  }\n"
            + "\n"
            + "  img {\n"
            + "    height: auto;\n"
            + "    line-height: 100%;\n"
            + "    text-decoration: none;\n"
            + "    border: 0;\n"
            + "    outline: none;\n"
            + "  }\n"
            + "  </style>\n"
            + "\n"
            + "</head>\n"
            + "<body style=\"background-color: #e9ecef;\">\n"
            + "\n"
            + "  <!-- start preheader -->\n"
            + "  <div class=\"preheader\" style=\"display: none; max-width: 0; max-height: 0; overflow: hidden; font-size: 1px; line-height: 1px; color: #fff; opacity: 0;\">\n"
            + "    You are given this code so that you you would be able to change your password and again get chance to access and use the features of User Management System.\n"
            + "  </div>\n"
            + "  <!-- end preheader -->\n"
            + "\n"
            + "  <!-- start body -->\n"
            + "  <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n"
            + "\n"
            + "    <!-- start logo -->\n"
            + "    <tr>\n"
            + "      <td align=\"center\" bgcolor=\"#e9ecef\">\n"
            + "        <!--[if (gte mso 9)|(IE)]>\n"
            + "        <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"600\">\n"
            + "        <tr>\n"
            + "        <td align=\"center\" valign=\"top\" width=\"600\">\n"
            + "        <![endif]-->\n"
            + "        <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width: 600px;\">\n"
            + "          <tr>\n"
            + "            <td align=\"center\" valign=\"top\" style=\"padding: 36px 24px;\">\n"
            + "              <a href=\"http://localhost:8080/User_Management_System/homepage.jsp\" target=\"_blank\" style=\"display: inline-block;\">\n"
            + "                <img src=\"logo.jpg\" alt=\"Logo\" border=\"0\" width=\"48\" style=\"display: block; width: 48px; max-width: 48px; min-width: 48px;\">\n"
            + "              </a>\n"
            + "            </td>\n"
            + "          </tr>\n"
            + "        </table>\n"
            + "        <!--[if (gte mso 9)|(IE)]>\n"
            + "        </td>\n"
            + "        </tr>\n"
            + "        </table>\n"
            + "        <![endif]-->\n"
            + "      </td>\n"
            + "    </tr>\n"
            + "    <!-- end logo -->\n"
            + "\n"
            + "    <!-- start hero -->\n"
            + "    <tr>\n"
            + "      <td align=\"center\" bgcolor=\"#e9ecef\">\n"
            + "        <!--[if (gte mso 9)|(IE)]>\n"
            + "        <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"600\">\n"
            + "        <tr>\n"
            + "        <td align=\"center\" valign=\"top\" width=\"600\">\n"
            + "        <![endif]-->\n"
            + "        <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width: 600px;\">\n"
            + "          <tr>\n"
            + "            <td align=\"left\" bgcolor=\"#ffffff\" style=\"padding: 36px 24px 0; font-family: 'Source Sans Pro', Helvetica, Arial, sans-serif; border-top: 3px solid #d4dadf;\">\n"
            + "              <h1 style=\"margin: 0; font-size: 32px; font-weight: 700; letter-spacing: -1px; line-height: 48px;\">Reset Your Password</h1>\n"
            + "            </td>\n"
            + "          </tr>\n"
            + "        </table>\n"
            + "        <!--[if (gte mso 9)|(IE)]>\n"
            + "        </td>\n"
            + "        </tr>\n"
            + "        </table>\n"
            + "        <![endif]-->\n"
            + "      </td>\n"
            + "    </tr>\n"
            + "    <!-- end hero -->\n"
            + "\n"
            + "    <!-- start copy block -->\n"
            + "    <tr>\n"
            + "      <td align=\"center\" bgcolor=\"#e9ecef\">\n"
            + "        <!--[if (gte mso 9)|(IE)]>\n"
            + "        <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"600\">\n"
            + "        <tr>\n"
            + "        <td align=\"center\" valign=\"top\" width=\"600\">\n"
            + "        <![endif]-->\n"
            + "        <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width: 600px;\">\n"
            + "\n"
            + "          <!-- start copy -->\n"
            + "          <tr>\n"
            + "            <td align=\"left\" bgcolor=\"#ffffff\" style=\"padding: 24px; font-family: 'Source Sans Pro', Helvetica, Arial, sans-serif; font-size: 16px; line-height: 24px;\">\n"
            + "              <p style=\"margin: 0;\">Tap the button below to reset your customer account password. If you didn't request a new password, you can safely delete this email.</p>\n"
            + "            </td>\n"
            + "          </tr>\n"
            + "          <!-- end copy -->\n"
            + "\n"
            + "          <!-- start button -->\n"
            + "          <tr>\n"
            + "            <td align=\"left\" bgcolor=\"#ffffff\">\n"
            + "              <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n"
            + "                <tr>\n"
            + "                  <td align=\"center\" bgcolor=\"#ffffff\" style=\"padding: 12px;\">\n"
            + "                    <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\n"
            + "                      <tr>\n"
            + "                        <td align=\"center\" bgcolor=\"#1a82e2\" style=\"border-radius: 6px;\">\n"
            + "                          <a href=\"http://localhost:8080/User_Management_System/entercode.jsp\" target=\"_blank\" style=\"display: inline-block; padding: 16px 36px; font-family: 'Source Sans Pro', Helvetica, Arial, sans-serif; font-size: 16px; color: #ffffff; text-decoration: none; border-radius: 6px;\">Reset Password</a>\n"
            + "                        </td>\n"
            + "                      </tr>\n"
            + "                    </table>\n"
            + "                  </td>\n"
            + "                </tr>\n"
            + "              </table>\n"
            + "            </td>\n"
            + "          </tr>\n"
            + "          <!-- end button -->\n"
            + "\n"
            + "          <!-- start copy -->\n"
            + "          <tr>\n"
            + "            <td align=\"left\" bgcolor=\"#ffffff\" style=\"padding: 24px; font-family: 'Source Sans Pro', Helvetica, Arial, sans-serif; font-size: 16px; line-height: 24px;\">\n"
            + "		\n"
            + "              <p style=\"margin: 0;\">If that doesn't work, Copy paste the following link in your browser or simply click to move to the User Management System:</p>\n"
            + "              <p style=\"margin: 0;\"><a href=\"http://localhost:8080/User_Management_System/entercode.jsp\" target=\"_blank\">http://localhost:8080/User_Management_System/entercode.jsp</a></p>\n"
            + "            </td>\n"
            + "          </tr>\n"
            + "          <!-- end copy -->\n"
            + "\n"
            + "          <!-- start copy -->\n"
            + "          <tr>\n"
            + "            <td align=\"left\" bgcolor=\"#ffffff\" style=\"padding: 24px; font-family: 'Source Sans Pro', Helvetica, Arial, sans-serif; font-size: 16px; line-height: 24px; border-bottom: 3px solid #d4dadf\">\n"
            + "              <p style=\"margin: 0;\">User Management System,<br> Group Name:- Dipjyoti</p>\n"
            + "            </td>\n"
            + "          </tr>\n"
            + "          <!-- end copy -->\n"
            + "\n"
            + "        </table>\n"
            + "        <!--[if (gte mso 9)|(IE)]>\n"
            + "        </td>\n"
            + "        </tr>\n"
            + "        </table>\n"
            + "        <![endif]-->\n"
            + "      </td>\n"
            + "    </tr>\n"
            + "    <!-- end copy block -->\n"
            + "\n"
            + "    <!-- start footer -->\n"
            + "    <tr>\n"
            + "      <td align=\"center\" bgcolor=\"#e9ecef\" style=\"padding: 24px;\">\n"
            + "        <!--[if (gte mso 9)|(IE)]>\n"
            + "        <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"600\">\n"
            + "        <tr>\n"
            + "        <td align=\"center\" valign=\"top\" width=\"600\">\n"
            + "        <![endif]-->\n"
            + "        <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width: 600px;\">\n"
            + "\n"
            + "          <!-- start permission -->\n"
            + "          <tr>\n"
            + "            <td align=\"center\" bgcolor=\"#e9ecef\" style=\"padding: 12px 24px; font-family: 'Source Sans Pro', Helvetica, Arial, sans-serif; font-size: 14px; line-height: 20px; color: #666;\">\n"
            + "              <p style=\"margin: 0;\">You received this email because we received a request for [password reset] for your account. If you didn't request for [password reset] you can safely delete this email.</p>\n"
            + "            </td>\n"
            + "          </tr>\n"
            + "          <!-- end permission -->\n"
            + "\n"
            + "          <!-- start unsubscribe -->\n"
            + "          <tr>\n"
            + "            <td align=\"center\" bgcolor=\"#e9ecef\" style=\"padding: 12px 24px; font-family: 'Source Sans Pro', Helvetica, Arial, sans-serif; font-size: 14px; line-height: 20px; color: #666;\">\n"
            + "              <p style=\"margin: 0;\">To visit our system, you can go through this link at any time.<a href=\"http://localhost:8080/User_Management/homepage.jsp\" target=\"_blank\"></p>\n"
            + "              <p style=\"margin: 0;\">User Management System, Developed by Gaman Aryal and Group</p>\n"
            + "            </td>\n"
            + "          </tr>\n"
            + "          <!-- end unsubscribe -->\n"
            + "\n"
            + "        </table>\n"
            + "        <!--[if (gte mso 9)|(IE)]>\n"
            + "        </td>\n"
            + "        </tr>\n"
            + "        </table>\n"
            + "        <![endif]-->\n"
            + "      </td>\n"
            + "    </tr>\n"
            + "    <!-- end footer -->\n"
            + "\n"
            + "  </table>\n"
            + "  <!-- end body -->\n"
            + "\n"
            + "</body>\n"
            + "</html>";
//    String Mail_Content = "<h2>Reset Password</h2><br><br><p> You are given this code so that you you would be able to change your password and again get chance to access and uuse the features of User Management System</P<br><br> <strong> Your Code is :- <b> " + generateCode()+" </b> </strong>";

    public void sendMail(final String SenderName, final String SenderEmail, final String SenderPassword) throws UnsupportedEncodingException, MessagingException {
        Properties properties = new Properties();

        properties.put("mail.smtp.host", hostname);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(properties,
                new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(SenderEmail, SenderPassword);
            }
        });

        // creates a new e-mail message
        Message msg = new MimeMessage(session);

        msg.setFrom(new InternetAddress(SenderEmail, SenderName));
        InternetAddress[] toAddresses = {new InternetAddress(MailReceiverID)};
        msg.setRecipients(Message.RecipientType.TO, toAddresses);
        msg.setSubject(Mail_Subject);
        msg.setContent(Mail_Content, "text/html");

        // sends the e-mail
        Transport.send(msg);

    }
}
