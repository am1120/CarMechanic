/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;

/**
 *
 * @author Alexander
 */
public class Problem {
    
    /* * * Attributes * * */
    private int p_id;
    private int model_id;
    private String description;
    private String solution;
    private String photo;
    private Date createdAt;
    private String status;
    private int userId;
    private int categoryId;
    
    public int getP_id() {
        return p_id;
    }

    public int getModel_id() {
        return model_id;
    }

    public String getDescription() {
        return description;
    }

    public String getSolution() {
        return solution;
    }

    public String getPhoto() {
        return photo;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public String getStatus() {
        return status;
    }

    public void setP_id(int p_id) {
        this.p_id = p_id;
    }

    public void setModel_id(int model_id) {
        this.model_id = model_id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Problem{" + "p_id=" + p_id + ", model_id=" + model_id + ", description=" + description + ", solution=" + solution + ", photo=" + photo + ", createdAt=" + createdAt + ", status=" + status + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + this.p_id;
        hash = 97 * hash + this.model_id;
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
        final Problem other = (Problem) obj;
        if (this.p_id != other.p_id) {
            return false;
        }
        if (this.model_id != other.model_id) {
            return false;
        }
        return true;
    }

    // TODO: Add photos!
    public Problem(int p_id, int model_id, String description, String solution, Date createdAt, String status) {
        this.p_id = p_id;
        this.model_id = model_id;
        this.description = description;
        this.solution = solution;
        this.createdAt = createdAt;
        this.status = status;
    }

    public Problem(int p_id, int model_id, String description, String solution, Date createdAt, String status, int userId, int categoryId) {
        this.p_id = p_id;
        this.model_id = model_id;
        this.description = description;
        this.solution = solution;
        this.createdAt = createdAt;
        this.status = status;
        this.userId = userId;
        this.categoryId = categoryId;
    }
    
    
}
