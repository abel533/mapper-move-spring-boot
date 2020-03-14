package io.mybatis.demo.model;

import io.mybatis.demo.custom.seq.Seq;
import tk.mybatis.mapper.annotation.Order;

import javax.persistence.Id;
import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID = 2948492684594057424L;
    @Id
    private Long id;
    @Seq
    @Order
    private Integer sequence;
    private String name;
    private String py;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPy() {
        return py;
    }

    public void setPy(String py) {
        this.py = py;
    }
}
