/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tracy.schoolweb.test.repository;

import com.tracy.schoolweb.app.conf.ConnectionConfig;
import com.tracy.schoolweb.domain.Winter;
import com.tracy.schoolweb.repository.WinterRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.testng.Assert;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Tracy
 */
public class WinterRepositoryTest {
    
    public static ApplicationContext ctx;
    private long id;
    private WinterRepository repo;
    
    public WinterRepositoryTest() {
        
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void createWinter() {
        
        repo = ctx.getBean(WinterRepository.class);

        Winter a = new Winter.Builder("Hockey")
                .coach("Mrs Man")
                .build();
                
        repo.save(a);
        id = a.getId();
        Assert.assertNotNull(a);
    }
    
    @Test(dependsOnMethods = "createWinter")
    public void readWinter(){
        
        repo = ctx.getBean(WinterRepository.class);
        Winter s = repo.findOne(id);
        Assert.assertEquals(s.getCoach(), "Mrs Man");   
    }
    
    @Test(dependsOnMethods = "readWinter")
    private void updateWinter(){
        
         repo = ctx.getBean(WinterRepository.class);
         Winter s = repo.findOne(id);
         Winter updatedS = new Winter.Builder("Hockey")
                .winter(s)
                .coach("Mrs Balls Chutney")
                 .build();
        
         repo.save(updatedS);
         
         Winter newAllYear = repo.findOne(id);
         Assert.assertEquals(newAllYear.getCoach(), "Mrs Balls Chutney");
         
    }
    
    @Test(dependsOnMethods = "updateWinter")
    private void deleteWinter(){
         repo = ctx.getBean(WinterRepository.class);
         Winter s = repo.findOne(id);
         repo.delete(s);
         
         Winter sDeleted = repo.findOne(id);
         
         Assert.assertNull(sDeleted);
           
     }

    @BeforeClass
    public static void setUpClass() throws Exception {
        ctx = new AnnotationConfigApplicationContext(ConnectionConfig.class);
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }
}
