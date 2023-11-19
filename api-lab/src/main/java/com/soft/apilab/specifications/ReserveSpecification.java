package com.soft.apilab.specifications;

import com.soft.apilab.models.Reserve;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;

@And({
        @Spec(path = "startTime", spec = Equal.class),
        @Spec(path = "endTime", spec = Equal.class),
        @Spec(path = "lab", spec = Equal.class),
        @Spec(path = "instructor", spec = Equal.class),
        @Spec(path = "reserveStatus", spec = Equal.class)
})
public interface ReserveSpecification extends Specification<Reserve> {
}
