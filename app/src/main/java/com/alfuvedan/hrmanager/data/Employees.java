package com.alfuvedan.hrmanager.data;

import androidx.annotation.NonNull;

import java.util.HashMap;

public class Employees {
    private static final HashMap<Long, Employee> es = new HashMap<>();

    public Employees(){
        String[][] info = {
                {"Angela", "Hodges", "angela.hodges@alfuvedan.com", "Sales", "Sales Person", "100000"},
                {"Abigail", "Terry", "abigail.terry@alfuvedan.com", "Sales", "Sales Person", "120000"},
                {"Jacob", "Terry", "jacob.terry@alfuvedan.com", "Sales", "Supervisor", "150000"},
                {"Molly", "Murray", "molly.murray@alfuvedan.com", "Sales", "Manager", "200000"},
                {"Oliver", "Hart", "oliver.hart@alfuvedan.com", "Marketing", "Social Media Promoter", "100000"},
                {"Julia", "Baker", "julia.baker@alfuvedan.com", "Marketing", "Supervisor", "140620"},
                {"Diana", "Lambert", "diana.lambert@alfuvedan.com", "Marketing", "Manager", "160012"},
                {"Felicity", "Langdon", "felicity.langdon@alfuvedan.com", "Corporate", "Vice President", "250000"},
                {"Eric", "Bell", "eric.bell@alfuvedan.com", "Corporate", "Assistant Vice President", "200000"},
                {"Jasmine", "Martin", "jasmine.martin@alfuvedan.com", "Corporate", "CEO", "300000"},
        };

        for (String[] strings : info) {
            Employee e = new Employee(new PersonalInfo(strings[0], strings[1], strings[2]),
                    new JobInfo(strings[3], strings[4], Double.parseDouble(strings[5])));
            es.put(e.getID(), e);
        }
    }

    public void addEmployee(@NonNull Employee e){
        es.put(e.getID() , e);
    }

    public void removeEmployee(long id){
        if(es.containsKey(id))
            throw new IllegalArgumentException("There is no employee with id " + id);

        es.remove(id);
    }

}
