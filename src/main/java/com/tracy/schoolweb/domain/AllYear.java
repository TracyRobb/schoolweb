/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tracy.schoolweb.domain;

import com.tracy.schoolweb.domain.Student;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

/**
 *
 * @author student
 */
@Entity
public class AllYear implements Serializable{
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String coach;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "allyearSport_id")
    private List<Student> students;
    
    private AllYear()
    {
        
    }
    
    private AllYear(Builder builder) 
    {
        id = builder.id;
        name = builder.name;
        coach = builder.coach;
        students = builder.students;

    }
    
    public static class Builder{
        
        private long id;
        private String name;
        private String coach;
        private List<Student> students;

        public Builder(String Value) {
            this.name = Value;
        }

        public Builder coach(String value){
            coach = value;
            return this;
        }
        
        public Builder students(List<Student> value){
            students = value;
            return this;
        }
        
        public Builder allYear(AllYear a)
        {
            id = a.getId();
            name = a.getName();
            coach = a.getCoach();
            students = a.getStudents();
            return this;
        }

        public AllYear build(){
            return new AllYear(this);
        }
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name; 
    }

    public String getCoach() {
        return coach;
    }
    
    public List<Student> getStudents() {
        return students;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 11 * hash + (int) (this.id ^ (this.id >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AllYear other = (AllYear) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    
    
    
    
}
