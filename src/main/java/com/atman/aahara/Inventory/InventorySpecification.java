package com.atman.aahara.Inventory;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class InventorySpecification {

    public  Specification<Inventory> hasName(String name) {
        return (root, query, cb) ->
                (name == null || name.isEmpty()) ? null :
                        cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%");
    }

    public  Specification<Inventory> descriptionContains(String description) {
        return (root, query, cb) ->
                (description == null || description.isEmpty()) ? null :
                        cb.like(cb.lower(root.get("description")), "%" + description.toLowerCase() + "%");
    }

    public  Specification<Inventory> priceBetween(Double min, Double max) {
        return (root, query, cb) -> {
            if (min == null && max == null) return null;
            if (min == null) return cb.le(root.get("rawPrice"), max);
            if (max == null) return cb.ge(root.get("rawPrice"), min);
            return cb.between(root.get("rawPrice"), min, max);
        };
    }
}
