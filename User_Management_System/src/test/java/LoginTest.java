
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import com.mycompany.user_management.login.LoginNeeds;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Asus
 */
public class LoginTest {

    LoginNeeds lgn = new LoginNeeds();  
    
    @Before
    public void setUp() {
        System.out.println("* PasswordEncryptionAndDecryption: Before method setUp()");
    }

    @After
    public void tearDown() {
        System.out.println("* PasswordEncryptionAndDecryption: After method tearDown()");
    }

    /**
     * unit test for formMessage method.
     */ 
            
    @Test
    public void testLoginWithWrongUsername() {
        System.out.println("Login Test with wrong Username and right Password.");
        String Username = "gamanaryal1";
        String Password = "Password@123";
        
        lgn.setLoginemail(Username);
        lgn.setLoginPassword(Password);
        boolean abletologin_result = lgn.doLogin();
        boolean ExpectedResult = false;

        assertEquals(ExpectedResult, abletologin_result);
    }

    @Test
    public void testLoginWithWrongPassword() {
        System.out.println("Login Test with right Username and Wrong Password.");
        String Username = "gamanaryal";
        String Password = "Password@12";
        
        lgn.setLoginemail(Username);
        lgn.setLoginPassword(Password);
        boolean abletologin_result = lgn.doLogin();
        boolean ExpectedResult = false;

        assertEquals(ExpectedResult, abletologin_result);
    }
    
    @Test
    public void testLoginWithWrongUsernameAndPassword() {
        System.out.println("Login Test with wrong Username and Password.");
        String Username = "gamanaryal1";
        String Password = "Password@12";
        
        lgn.setLoginemail(Username);
        lgn.setLoginPassword(Password);
        boolean abletologin_result = lgn.doLogin();
        boolean ExpectedResult = false;

        assertEquals(ExpectedResult, abletologin_result);
    }
    
    @Test
    public void testLoginWithRightUsernameAndPassword() {
        System.out.println("Login Test with right Username and Password.");
        String Username = "gamanaryal";
        String Password = "Password@123";
        
        lgn.setLoginemail(Username);
        lgn.setLoginPassword(Password);
        boolean abletologin_result = lgn.doLogin();
        boolean ExpectedResult = true;

        assertEquals(ExpectedResult, abletologin_result);
    }
}
