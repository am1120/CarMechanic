
package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Problem's Category
 * @author Alexander
 */
public class Category {
    
    /* * * Attributes * * */
    private int cat_id;
    private String name;
    private List<Problem> problems;
    private String latinName;
    
    public Category(){}

    public Category(int cat_id, String name, List<Problem> problems) {
        this.cat_id = cat_id;
        this.name = name;
        this.problems = problems;
    }
    
    public Category(int cat_id, String name) {
        this.cat_id = cat_id;
        this.name = name;
        //this.latinName = latin;
        this.problems = new ArrayList<>();
    }

    public int getCat_id() {
        return cat_id;
    }

    public String getName() {
        return name;
    }

    public List<Problem> getProblems() {
        return problems;
    }

    public void setCat_id(int cat_id) {
        this.cat_id = cat_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProblems(List<Problem> problems) {
        this.problems = problems;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + this.cat_id;
        hash = 29 * hash + Objects.hashCode(this.name);
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
        final Category other = (Category) obj;
        if (this.cat_id != other.cat_id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Category{" + "cat_id=" + cat_id + ", name=" + name + ", problems=" + problems + '}';
    }
    
    
}
