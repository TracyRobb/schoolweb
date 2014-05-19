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
public class Summer implements Serializable{
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String coach;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "summerSport_id")
    private List<Student> students;
    
    private Summer()
    {
        
    }
    
    private Summer(Builder builder) 
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

        public Builder(String value) {
            this.name = value;
        }


        public Builder coach(String value){
            coach = value;
            return this;
        }
        
        public Builder students(List<Student> value){
            students = value;
            return this;
        }
        
        public Builder summer(Summer a)
        {
            id = a.getId();
            name = a.getName();
            coach = a.getCoach();
            students = a.getStudents();
            return this;
        }

        public Summer build(){
            return new Summer(this);
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
        int hash = 5;
        hash = 97 * hash + (int) (this.id ^ (this.id >>> 32));
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
        final Summer other = (Summer) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    
    
    
    
}
