package edu.smu.musicstorecatalog.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "label")
public class Label {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "label_id")
    private int labelId;
    @NotNull(message = "name must not be null")
    @Size(max = 50)
    private String name;
    @Nullable
    @Size(max = 255)
    private String website;

    public Label(int labelId, String name, String website) {
        this.labelId = labelId;
        this.name = name;
        this.website = website;
    }

    public Label(String name, String website) {
        this.name = name;
        this.website = website;
    }

    public Label() {
    }

    public int getLabelId() {
        return labelId;
    }

    public void setLabelId(int labelId) {
        this.labelId = labelId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Label label = (Label) o;
        return labelId == label.labelId && Objects.equals(name, label.name) && Objects.equals(website, label.website);
    }

    @Override
    public int hashCode() {
        return Objects.hash(labelId, name, website);
    }

    @Override
    public String toString() {
        return "Label{" +
                "labelId=" + labelId +
                ", name='" + name + '\'' +
                ", website='" + website + '\'' +
                '}';
    }
}
