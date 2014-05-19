/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tracy.schoolweb.test.repository;

import com.tracy.schoolweb.app.conf.ConnectionConfig;
import com.tracy.schoolweb.domain.Summer;
import com.tracy.schoolweb.repository.SummerRepository;
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
public class SummerRepositoryTest {
    
    public static ApplicationContext ctx;
    private long id;
    private SummerRepository repo;
    
    public SummerRepositoryTest() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void createSummer() {
        
        repo = ctx.getBean(SummerRepository.class);

        Summer a = new Summer.Builder("Baseball")
                .coach("Mr Chuck")
                .build();
                
        repo.save(a);
        id = a.getId();
        Assert.assertNotNull(a);
    }
    
    @Test(dependsOnMethods = "createSummer")
    public void readSummer(){
        
        repo = ctx.getBean(SummerRepository.class);
        Summer s = repo.findOne(id);
        Assert.assertEquals(s.getCoach(), "Mr Chuck");   
    }
    
    @Test(dependsOnMethods = "readSummer")
    private void updateSummer(){
        
         repo = ctx.getBean(SummerRepository.class);
         Summer s = repo.findOne(id);
         Summer updatedS = new Summer.Builder("Baseball")
                .summer(s)
                .coach("Mr Ball")
                 .build();
        
         repo.save(updatedS);
         
         Summer newAllYear = repo.findOne(id);
         Assert.assertEquals(newAllYear.getCoach(), "Mr Ball");
         
    }
    
    @Test(dependsOnMethods = "updateSummer")
    private void deleteSummer(){
         repo = ctx.getBean(SummerRepository.class);
         Summer s = repo.findOne(id);
         repo.delete(s);
         
         Summer sDeleted = repo.findOne(id);
         
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
