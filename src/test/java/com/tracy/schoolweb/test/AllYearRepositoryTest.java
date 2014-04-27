/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tracy.schoolweb.test;

import com.tracy.schoolweb.app.conf.ConnectionConfig;
import com.tracy.schoolweb.domain.AllYear;
import com.tracy.schoolweb.repository.AllYearRepository;
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
public class AllYearRepositoryTest {
    
    public static ApplicationContext ctx;
    private long id;
    private AllYearRepository repo;
    
    public AllYearRepositoryTest() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void createAllYear() {
        
        repo = ctx.getBean(AllYearRepository.class);

        AllYear a = new AllYear.Builder("Netball")
                .coach("Mrs Wendy")
                .build();
                
        repo.save(a);
        id = a.getId();
        Assert.assertNotNull(a);
    }
    
    @Test(dependsOnMethods = "createAllYear")
    public void readAllYear(){
        
        repo = ctx.getBean(AllYearRepository.class);
        AllYear s = repo.findOne(id);
        Assert.assertEquals(s.getCoach(), "Mrs Wendy");   
    }
    
    @Test(dependsOnMethods = "readAllYear")
    private void updateAllYear(){
        
         repo = ctx.getBean(AllYearRepository.class);
         AllYear s = repo.findOne(id);
         AllYear updatedS = new AllYear.Builder("Netball")
                .allYear(s)
                .coach("Mrs Ball")
                 .build();
        
         repo.save(updatedS);
         
         AllYear newAllYear = repo.findOne(id);
         Assert.assertEquals(newAllYear.getCoach(), "Mrs Ball");
         
    }
    
    @Test(dependsOnMethods = "updateAllYear")
    private void deleteAllYear(){
         repo = ctx.getBean(AllYearRepository.class);
         AllYear s = repo.findOne(id);
         repo.delete(s);
         
         AllYear sDeleted = repo.findOne(id);
         
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
