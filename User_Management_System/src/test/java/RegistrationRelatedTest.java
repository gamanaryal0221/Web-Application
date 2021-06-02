
import com.mycompany.user_management.registration.RegistrationDatabaseModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Asus
 */
public class RegistrationRelatedTest {

    RegistrationDatabaseModel rdbms = new RegistrationDatabaseModel();

    @Before
    public void setUp() {
        System.out.println("* RegistrationTest: Before method setUp()");
    }

    @After
    public void tearDown() {
        System.out.println("* RegistrationTest: After method tearDown()");
    }

    /**
     * unit test for formMessage method.
     */
    @Test
    public void testUsernameDoesExists() {
        String username = "gamanaryal";
        System.out.println("Yes " + username + " does exists");
        rdbms.setUsername(username);
        
        boolean ResultFromTest = rdbms.usernameDoesExist();
        boolean ExpectedResult = true;
        assertEquals(ExpectedResult,ResultFromTest);
    }
    
    @Test
    public void testEmailDoesExists(){
        String email = "gamanaryal@gmail.com";
        System.out.println("Yes " + email + " does exists");
        rdbms.setEmail(email);
        
        boolean ResultFromTest = rdbms.emailDoesExist();
        boolean ExpectedResult = true;
        assertEquals(ExpectedResult,ResultFromTest);
    }
    
               
    @Test
    public void testPasswordIsSameAsUsername(){
        String Username = "GamanAryal";
        String Password = "Password@#$%7";
        System.out.println("No "+ Username +" and " + Password + " are not equal");
        
        rdbms.setEmail(Username);
        rdbms.setPassword(Password);
        
        boolean ResultFromTest = rdbms.passwordIsSameAsUsername();
        boolean ExpectedResult = false;
        assertEquals(ExpectedResult,ResultFromTest);
    }

    @Test
    public void testPasswordIsValid(){
        String Password = "Password@#$%7";
        System.out.println("Yes " + Password + " is valid password");
        rdbms.setPassword(Password);
        
        boolean ResultFromTest = rdbms.passwordIsValid();
        boolean ExpectedResult = true;
        
        assertEquals(ExpectedResult,ResultFromTest);
    }
    
    @Test
    public void testPasswordAreSame(){
        
        String Password = "Password@#$%7";
        String ConfirmPassword = "Password@#$%7";
        System.out.println("Yes " + Password + " and "+ ConfirmPassword +" are same.");
        
        rdbms.setPassword(Password);
        rdbms.setConfirmpassword(ConfirmPassword);
        
        boolean ResultFromTest = rdbms.passwordAreSame();
        boolean ExpectedResult = true;
        
        assertEquals(ExpectedResult,ResultFromTest);
    }

    
    @Test
    public void testAddUser(){
        String email = "gamanaryal@gmail.com";
        System.out.println("Yes " + email + " does exists");
        rdbms.setEmail(email);
        
        boolean ResultFromTest = rdbms.emailDoesExist();
        boolean ExpectedResult = true;
        assertEquals(ExpectedResult,ResultFromTest);
        
    }
 
}
