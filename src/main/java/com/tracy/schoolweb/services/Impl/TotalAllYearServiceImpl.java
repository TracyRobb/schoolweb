/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tracy.schoolweb.services.Impl;

import com.tracy.schoolweb.domain.AllYear;
import com.tracy.schoolweb.repository.AllYearRepository;
import com.tracy.schoolweb.services.TotalAllYearService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Tracy
 */
@Service
public class TotalAllYearServiceImpl implements TotalAllYearService{
    
    @Autowired
    private AllYearRepository allyearRepository;

    @Override
    public List<AllYear> getTotalAllYear() {
        return allyearRepository.findAll();
    }
    
}
