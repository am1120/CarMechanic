
package model;

import java.util.Objects;

/**
 *
 * @author Alexander
 */
public class Car {
    
    /* * * Attributes * * */
    private String maker;
    private String model_id;
    private String model;
    private String year;
    private String engine;
    
    
    public Car(String maker,String model_id,String model, String year, String engine){
        this.maker = maker;
        this.model_id = model_id;
        this.model = model;
        this.year = year;
        this.engine = engine;
    }

    /* * * Getters * * */
    public String getMaker() {
        return maker;
    }

    public void setModel_id(String maker_id) {
        this.model_id = maker_id;
    }

    public String getModel_id() {
        return model_id;
    }

    public String getModel() {
        return model;
    }

    public String getYear() {
        return year;
    }

    public String getEngine() {
        return engine;
    }

    /* * * * Setters * * * */
    public void setMaker(String maker) {
        this.maker = maker;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 61 * hash + Objects.hashCode(this.maker);
        hash = 61 * hash + Objects.hashCode(this.model);
        hash = 61 * hash + Objects.hashCode(this.year);
        hash = 61 * hash + Objects.hashCode(this.engine);
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
        final Car other = (Car) obj;
        if (!Objects.equals(this.maker, other.maker)) {
            return false;
        }
        if (!Objects.equals(this.model, other.model)) {
            return false;
        }
        if (!Objects.equals(this.year, other.year)) {
            return false;
        }
        if (!Objects.equals(this.engine, other.engine)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Car{" + "maker=" + maker + ", model=" + model + ", year=" + year + ", engine=" + engine + "}";
    }
    
    
   
    
}
