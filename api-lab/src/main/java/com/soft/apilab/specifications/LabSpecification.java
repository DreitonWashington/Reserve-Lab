package com.soft.apilab.specifications;

import com.soft.apilab.models.Lab;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.EqualIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;

@And({
        @Spec(path = "capacity", spec = Equal.class),
        @Spec(path = "name", spec = EqualIgnoreCase.class),
        @Spec(path = "labId", spec = EqualIgnoreCase.class)
})
public interface LabSpecification extends Specification<Lab> {
}
