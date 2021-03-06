/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package qa.qcri.aidr.predictui.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

//import org.codehaus.jackson.annotate.JsonBackReference;
//import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
//import org.codehaus.jackson.annotate.JsonManagedReference;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 *
 * @author Imran
 */

@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown=true)
public class Model implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @XmlElement private Integer modelID;
    
 
    @XmlElement private double avgPrecision;
    
  
    @XmlElement private double avgRecall;
    
  
    @XmlElement private double avgAuc;
    
 
    @XmlElement private int trainingCount;
    

    @XmlElement private Date trainingTime;
    
 
    @JsonBackReference
    private ModelFamily modelFamily;
   
    @JsonManagedReference
    private Collection<ModelNominalLabel> modelNominalLabelCollection;

    @XmlElement private Boolean isCurrentModel;

    public Model() {
    }

    public Model(Integer modelID) {
        this.modelID = modelID;
    }

    public Model(Integer modelID, double avgPrecision, double avgRecall, double avgAuc, int trainingCount, Date trainingTime) {
        this.modelID = modelID;
        this.avgPrecision = avgPrecision;
        this.avgRecall = avgRecall;
        this.avgAuc = avgAuc;
        this.trainingCount = trainingCount;
        this.trainingTime = trainingTime;
    }

    public Integer getModelID() {
        return modelID;
    }

    public void setModelID(Integer modelID) {
        this.modelID = modelID;
    }

    public double getAvgPrecision() {
        return avgPrecision;
    }

    public void setAvgPrecision(double avgPrecision) {
        this.avgPrecision = avgPrecision;
    }

    public double getAvgRecall() {
        return avgRecall;
    }

    public void setAvgRecall(double avgRecall) {
        this.avgRecall = avgRecall;
    }

    public double getAvgAuc() {
        return avgAuc;
    }

    public void setAvgAuc(double avgAuc) {
        this.avgAuc = avgAuc;
    }

    public int getTrainingCount() {
        return trainingCount;
    }

    public void setTrainingCount(int trainingCount) {
        this.trainingCount = trainingCount;
    }

    public Date getTrainingTime() {
        return trainingTime;
    }

    public void setTrainingTime(Date trainingTime) {
        this.trainingTime = trainingTime;
    }

    public ModelFamily getModelFamily() {
        return modelFamily;
    }

    public void setModelFamily(ModelFamily modelFamily) {
        this.modelFamily = modelFamily;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<ModelNominalLabel> getModelNominalLabelCollection() {
        return modelNominalLabelCollection;
    }

    public void setModelNominalLabelCollection(Collection<ModelNominalLabel> modelNominalLabelCollection) {
        this.modelNominalLabelCollection = modelNominalLabelCollection;
    }

//    @XmlTransient
//    @JsonIgnore
//    public Collection<ModelFamily> getModelFamilyCollection() {
//        return modelFamilyCollection;
//    }
//
//    public void setModelFamilyCollection(Collection<ModelFamily> modelFamilyCollection) {
//        this.modelFamilyCollection = modelFamilyCollection;
//    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (modelID != null ? modelID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Model)) {
            return false;
        }
        Model other = (Model) object;
        if ((this.modelID == null && other.modelID != null) || (this.modelID != null && !this.modelID.equals(other.modelID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "qa.qcri.aidr.predictui.entities.Model[ modelID=" + modelID + " ]";
    }

    public Boolean getIsCurrentModel() {
        return isCurrentModel;
    }

    public void setIsCurrentModel(Boolean isCurrentModel) {
        this.isCurrentModel = isCurrentModel;
    }
    
}
