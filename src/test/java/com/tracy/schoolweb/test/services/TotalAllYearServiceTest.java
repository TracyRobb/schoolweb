/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tracy.schoolweb.test.services;

import com.tracy.schoolweb.app.conf.ConnectionConfig;
import com.tracy.schoolweb.domain.AllYear;
import com.tracy.schoolweb.repository.AllYearRepository;
import com.tracy.schoolweb.services.TotalAllYearService;
import java.util.List;
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
public class TotalAllYearServiceTest {
    public static ApplicationContext ctx;
    

    private TotalAllYearService service;
    private AllYearRepository allRepository;
    
    public TotalAllYearServiceTest() {
        
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void totalAllYear() {
        service = ctx.getBean(TotalAllYearService.class);
         List<AllYear> all = service.getTotalAllYear();
         
         Assert.assertEquals( all.size(),1, " Expect 1 All year sports");
    
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
        
        allRepository = ctx.getBean(AllYearRepository.class);
        //assRepository.deleteAll();
        
        
    }
}
