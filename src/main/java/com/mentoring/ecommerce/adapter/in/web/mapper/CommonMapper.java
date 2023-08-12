package com.mentoring.ecommerce.adapter.in.web.mapper;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

public interface CommonMapper<E, D> {

    E toEntity(final D dto);

    D toDto(final E entity);

    default List<E> toEntity(final Collection<D> dtos) {
        return mapFromCollection(dtos, this::toEntity);
    }

    default List<D> toDto(final List<E> entities) {
        return mapFromCollection(entities, this::toDto);
    }

    default Page<D> toDto(Page<E> page) {
        if (page == null) {
            return Page.empty();
        }

        List<D> dtoList = mapFromCollection(page.getContent(), this::toDto);
        return new PageImpl<>(dtoList, page.getPageable(), page.getTotalElements());
    }

    static <E, R> List<R> mapFromCollection(final Collection<E> elements, final Function<E, R> mapper) {
        return elements != null
                ? elements.stream().map(mapper).toList()
                : Collections.emptyList();
    }
}
